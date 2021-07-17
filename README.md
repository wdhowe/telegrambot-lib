# telegrambot-lib

[![Build Status][gh-actions-badge]][gh-actions] [![Clojars Project][clojars-badge]][clojars] [![Clojure Docs][cljdoc-badge]][cljdoc-link] [![Clojure version][clojure-v]](project.clj)

A Clojure library for interacting with the Telegram Bot API.

The goal of this library is to be a complete implementation of the Telegram Bot API in Clojure,
without adding extra bot logic.

Each API call is a Clojure function, with docstrings outlining required and optional parameters.

> **For more information on updates to the Telegram Bot API version, see the [CHANGELOG](CHANGELOG.md).**

## Usage

Get started using the `telegrambot-lib`.

### Adding Dependencies

See the `telegrambot-lib` [Clojars page][clojars] for details on adding this library to projects for Leiningen, Boot, 
and CLI/deps.edn.

You will also need to provide your favorite JSON mapper library as an explicit project dependency or just make sure
you already have one in the classpath of your project (which is usually the case when you are building a web app).

The following JSON mappers are currently supported:
- `["cheshire"]`
- `["metosin/jsonista"]`
- `["org.clojure/data.json"]`

So, with Leiningen you'll end up with these two entries in the project dependencies:

```clojure
[telegrambot-lib "1.0.0"]
[cheshire "5.10.1"]
```

### Requiring the Library

In the REPL:

```clojure
(require '[telegrambot-lib.core :as tbot])
```

In your application codebase:

```clojure
(ns my-app.core
  (:require [telegrambot-lib.core :as tbot]))
```

### Using the Library

Pre-Reqs:

- You have [created a Telegram bot](https://core.telegram.org/bots#3-how-do-i-create-a-bot) and received an auth token.
- The library is added as a project dependency.
- The library is required as indicated above, with a `tbot` alias for the `core` namespace.

The Bot API auth token can be passed to the bot instance by either:

- **Environment variable** — 
  the lib will look for `bot-token` or `BOT_TOKEN` vars in the environment.
  ```clojure
  (def mybot (tbot/create))
  ```

or

- **Function argument** — 
  for the `create` function which has a dedicated optional second parameter.
  ```clojure
  (def mybot (tbot/create my-token))
  ```

Verify your bot instance is working by firing the [`getMe`](https://core.telegram.org/bots/api/#getme)
Bot API method call, which is now as simple as calling the `get-me` function.

```clojure
(tbot/get-me mybot)
=>
{:ok true,
 :result {:id 123,
          :is_bot true,
          :first_name "mybot",
          :username "my_roboto",
          :can_join_groups true,
          :can_read_all_group_messages false,
          :supports_inline_queries false}}
```

## Available Functions

All of the Telegram Bot API functions in this library are available from the `telegrambot-lib.core` namespace.

Find the list of the available functions with their parameters in the 
[namespace documentation](https://cljdoc.org/d/telegrambot-lib/telegrambot-lib/CURRENT/api/telegrambot-lib.core).

### Functions Contract

All library functions expect the bot instance as their first argument.

Some functions, such as `get-me`, only take this single argument.

Most functions are multi-arity with the following options:

- Send all parameters in a `content` map.
  ```clojure
  (let [content {:chat_id -560000000
                 :text "Greetings!"
                 :parse_mode "MarkdownV2"}]
    (tbot/send-message mybot content))
  ```

- Send only the required parameters as simple values.
  ```clojure
  (let [chat-id -560000000
        text "Greetings!"]
    (tbot/send-message mybot chat-id text))
  ```

- Send the required parameters as simple values and then `optional` parameters map.
  ```clojure
  (let [chat-id -560000000
        text "Greetings!"
        optional {:parse_mode "MarkdownV2"}]
    (tbot/send-message mybot chat-id text optional))
  ```

Required function parameters are named to match the Telegram Bot API method params.
Refer to the function docstrings or the [Telegram Bot API documentation](https://core.telegram.org/bots/api)
for the exact names and a complete list of valid `optional` parameters.

### Generic Function Call

Additionally, there is a generic `call` function that may be used to send a request to any Bot API endpoint.
This will come in handy in the rare case when the Telegram Bot API already provides some method that this library 
does not yet cover with a function.

```clojure
;; generic call example
(tbot/call mybot "getMe")

;; generic call example with content
(tbot/call mybot "sendMessage" {:chat_id 280000000 :text "Hello Bot World!"})
```

## How-To's

In the Telegram client:
- Add your bot to a group chat/channel or message it directly, in a private chat.
- When testing plain messages in a group chat, make sure to disable the 
  [privacy mode](https://core.telegram.org/bots#privacy-mode) or make the bot an "admin" first.

On the side of your bot:
- Setup it to [receive updates](https://core.telegram.org/bots/api/#getting-updates)
  (either by long-polling or via a webhook).

[//]: # (TODO: Add an exemplary code snippet on how to do updates long-polling?)

### Determining the Chat and User

Recent updates (messages, inline queries, etc.) are returned from the server along with the `chat` information.

For instance, this is what the `get-updates` function can return when called without optional params.

```clojure
(tbot/get-updates mybot)
=>
{:ok true
 :result [{:update_id 760000018
           :message {:message_id 9
                     :from {:id 280000000, :is_bot false, :first_name "Bill", :last_name "Howe", :username "myusername", :language_code "en"}
                     :chat {:id 280000000, :first_name "Bill", :last_name "Howe", :username "myusername", :type "private"}
                     :date 1602815917
                     :text "Oh hi bot!"}}
          .....
          {:update_id 760000010
           :message {:message_id 1
                     :from {:id 123, :is_bot true, :first_name "mybot", :username "my_roboto"}
                     :chat {:id 280000000, :first_name "Bill", :last_name "Howe", :username "myusername", :type "private"}
                     :date 1602813645
                     :text "Hello Bot World!"}}]}
```

In this case, since we are in a private chat with the bot, the sender (the `message` > `from` > `id`) is the same
as the chat (the `message` > `chat` > `id`).

### Sending a Message into a Chat

Send your message to the target `chat_id` in any of the ways [described above](#functions-contract).

```clojure
;; Option 1: send all `content` as a map
(tbot/send-message mybot {:chat_id 280000000 :text "Oh hi yourself."})

;; Option 2: send only required parameters as simple values
(tbot/send-message mybot 280000000 "Oh hi yourself.")

;; Option 3: send required params as simple values + `optional` params as a map
(tbot/send-message mybot 280000000 "Oh hi yourself." {:disable_notification true})
```

In all three cases you'll get a similar response parsed into a Clojure map:
```clojure
=>
{:ok true
 :result {:message_id 10
          :from {:id 123, :is_bot true, :first_name "mybot", :username "my_roboto"}
          :chat {:id 280000000, :first_name "Bill", :last_name "Howe", :username "myusername", :type "private"}
          :date 1602816282
          :text "Oh hi yourself."}}
```

Notice the incrementing `message_id` number. This is useful for keeping track of the message order and which messages
have already been processed. Save it and use it later as the `get-updates` optional `offset` parameter.

## Advanced Use

### Error Handling

The library signals unsuccessful Bot API requests and other arbitrary errors during the Bot API calls.

```clojure
(tbot/set-webhook mybot nil)
=>
{:ok false,
 :error_code 400,
 :description "Bad Request: bad webhook: Failed to resolve host: No address associated with hostname",
 :error #error{...}}
```

Upon any exception, it is passed either:
- along with the Bot API response (in a form of Clojure map) in case the request was unsuccessful
[in terms of the Bot API](https://core.telegram.org/bots/api/#making-requests), or
- just as `{:error ex}` map in all other situations when we're unable to retrieve a response body.

It's up to you to decide on how to react to an error in each particular case.

The usual choices are:
- to just log (w/ `:error_code` and `:description`), or
- to retry (if the `:parameters` as present), or
- to rethrow the `:error` object.

> **NOTE:** In asynchronous code, where callbacks can be/are processed on a different thread
> there is no sense in rethrowing exceptions. See the next section for details on how to make
> an async Telegram Bot API request with the `telegrambot-lib`.

### Making Asynchronous Calls

Basically, you have to pass an `:async true` parameter along the bot instance to make any function end up
with firing an asynchronous request. The only difference is that the function returns immediately, with a
regular `core.async` channel to take the response (or [error result](#error-handling)) from later on.

Here's how such a call may look like:

```clojure
;; Option 1: temporarily modify the bot instance for a particular async request
(tbot/send-message (assoc mybot :async true)
                   {:chat_id 280000000 :text "Drop-and-run!"})

;; Option 2: use a global/shared bot instance for all asynchronous requests
(def my-async-bot (-> (tbot/create)
                      (assoc :async true)))
(tbot/send-message my-async-bot 280000000 "Drop-and-run!")
```

In either case you'll get a similar response — a `core.async` channel to take from later on:
```clojure
=>
#object[clojure.core.async.impl.channels.ManyToManyChannel 0x3d059861 "clojure.core.async.impl.channels.ManyToManyChannel@3d059861"]
```

## Miscellaneous

### Logging

This library uses the `org.clojure/tools.logging`, a standard facade library, for logging purposes.
Thus, any logger implementation you pick for your project is supported, and all the logs from the `telegrambot-lib`
will be redirected to it.

### Version Conflicts

The `telegrambot-lib` depends on a particular version of the `clj-http` library (which itself implicitly depends on a
particular JSON mapper version) that is known to cause version conflicts in end user projects from time to time.

There is a known fix for this type of issue: you either remove your own dependency on a particular `clj-http` version
or exclude the one that comes with the `telegrambot-lib` (with e.g. `:exclusions [clj-http]` for Leiningen projects).

## License

Copyright © 2020-2021 Bill Howe and contributors

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
<http://www.eclipse.org/legal/epl-2.0>.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at <https://www.gnu.org/software/classpath/license.html>.

----

<!-- Named page links below: /-->

[gh-actions-badge]: https://github.com/wdhowe/telegrambot-lib/workflows/ci%2Fcd/badge.svg
[gh-actions]: https://github.com/wdhowe/telegrambot-lib/actions
[cljdoc-badge]: https://cljdoc.org/badge/telegrambot-lib/telegrambot-lib
[cljdoc-link]: https://cljdoc.org/d/telegrambot-lib/telegrambot-lib/CURRENT
[clojure-v]: https://img.shields.io/badge/clojure-1.10.0-blue.svg
[clojars]: https://clojars.org/telegrambot-lib
[clojars-badge]: https://img.shields.io/clojars/v/telegrambot-lib.svg
