# telegrambot-lib

[![Build Status][gh-actions-badge]][gh-actions] [![Clojars Project][clojars-badge]][clojars] [![Clojure Docs][cljdoc-badge]][cljdoc-link] [![Clojure version][clojure-v]](project.clj)

A Clojure library for interacting with the Telegram Bot API.

The goal of this library is to be a complete implementation of the Telegram Bot
API in Clojure, without adding extra bot logic.

Each Bot API call is implemented in a Clojure function, with docstrings
outlining required and optional parameters.

This allows for minimal reference to the Telegram documentation and staying in the editor.

Focus on writing your bot and leave the API call handling to this library.

Implemented Telegram functions accurate as of [Bot API 5.0](https://core.telegram.org/bots/api#november-4-2020)

## Usage

Get started using the telegrambot-lib.

### Installation

Leiningen/Boot Project file

```clojure
[telegrambot-lib "0.3.1"]
```

### Include the Library

In the REPL

```clojure
(require '[telegrambot-lib.core :as tbot])
```

In your application

```clojure
(ns my-app.core
  (:require [telegrambot-lib.core :as tbot]))
```

### Use the Library

Pre-Reqs:

- You have [created a Telegram bot](https://core.telegram.org/bots#3-how-do-i-create-a-bot) and received an auth token.
- The library is installed/included as indicated above.
- Examples below assume ":as tbot" was followed in require command.

The bot api auth token can be given to the bot instance by either:

- Environment variable
- Function parameter

#### Create a bot - with a token env var

This method looks for "bot-token" or "BOT_TOKEN" from the environment.

```clojure
(def mybot (tbot/create))
```

#### Create a bot - with a token parameter

This method requires passing the token as a parameter at creation time.

```clojure
(def mybot (tbot/create my-token))
```

#### Verify a working bot

Verify your bot instance is working with the "get-me" function.

```clojure
(tbot/get-me mybot)

;; returns something like this
{:ok true,
 :result
 {:id 123,
  :is_bot true,
  :first_name "mybot",
  :username "my_roboto",
  :can_join_groups true,
  :can_read_all_group_messages false,
  :supports_inline_queries false}}
```

## Available Functions

Current [ClojureDocs documentation is here][cljdoc-link].

All of the Telegram Bot API functions in this library are imported into the telegrambot-lib.core namespace.

This makes them available by their non-namespaced names and are all listed within the [import-vars function in telegram-lib.core](https://github.com/wdhowe/telegrambot-lib/blob/main/src/telegrambot_lib/core.clj#L46).

### Generic Function Call

Additionally, there is a generic 'call' function that may be used to send a request to any endpoint if the function does not exist yet.

```clojure
;; generic call example
(tbot/call mybot "getMe")

;; generic call example with content
(tbot/call mybot "sendMessage" {:chat_id 789 :text "Hello Bot World!"})
```

## Sending/Receiving Content

Most functions are multi-arity with the following options:

- Send all parameters in a 'content' map.
- Send only the required parameters as simple values.
- Send the required paraemters as simple values and then 'optional' parameters in a map.

Required parameters are named to match the Telegram API, so they should be self explanatory in most cases.

Refer to the function docstrings or the [Telegram Bot API Documentation](https://core.telegram.org/bots/api) for optional parameter content.

See the below 'How to send a chat message' section for examples on the different ways to pass parameters.

## How to send a chat message

- In the Telegram client: Add your bot to a channel or message it directly.
- From your app/repl: Have your bot get updates.
  - Recent chat messages on the server are returned, along with the chat id of the sender(s).
  - In this case, the sender is (from id) is the same as the chat id. (ie: this is a private direct message)

```clojure
;; some functions, such as get-updates only take the bot (this) as a parameter
(tbot/get-updates mybot)

;; response - parsed into a Clojure map
{:ok true, :result [{:update_id 761420707, :message {:message_id 9, :from {:id 789, :is_bot false, :first_name "Bill", :last_name "Howe", :username "myusername", :language_code "en"}, :chat {:id 789, :first_name "Bill", :last_name "Howe", :username "myusername", :type "private"}, :date 1602815917, :text "oh hi"}}]}
```

- Send your message to the target chat id.
  - Notice the incrementing message_id number. This is useful to keep track of message order and which messages have already been processed.

```clojure
;; Option 1: send all 'content' as a map
(tbot/send-message mybot {:chat_id 789 :text "oh hi yourself."})

;; Option 2: send only required parameters as simple values
(tbot/send-message mybot 789 "oh hi yourself.")

;; Option 3: send required parameters as simple values, optional values as a map
(tbot/send-message mybot 789 "oh hi yourself." {:disable_notification true})

;; response - parsed into a Clojure map
{:ok true, :result {:message_id 10, :from {:id 123, :is_bot true, :first_name "mybot", :username "my_roboto"}, :chat {:id 789, :first_name "Bill", :last_name "Howe", :username "myusername", :type "private"}, :date 1602816282, :text "oh hi yourself."}}
```

## License

Copyright © 2020 Bill Howe

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
