# telegrambot-lib

[![Build Status][gh-actions-badge]][gh-actions] [![Clojars Project][clojars-badge]][clojars] [![Clojure Docs][cljdoc-badge]][cljdoc-link] [![Clojure version][clojure-v]](project.clj)

A Clojure library for interacting with the Telegram Bot API.

## Usage

Get started using the telegrambot-lib.

### Installation

Leiningen/Boot Project file

```clojure
[telegrambot-lib "0.1.1"]
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
- Passed argument

#### Create a bot - with a token env var

This method looks for "bot-token" or "BOT_TOKEN" from the environment.

```clojure
(def mybot (tbot/create))
```

#### Create a bot - with a token argument

This method requires passing the token as an argument at creation time.

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

This makes them available by their non-namespaced names and are all listed within the "import-vars" function (in telegram-lib.core).

Additionally, there is a generic 'call' function that may be used to send a request to any endpoint if the function does not exist yet.

```clojure
;; generic call example
(tbot/call mybot "getMe")

;; generic call example with content
(tbot/call mybot "sendMessage" {:chat_id 789 :text "Hello Bot World!"})
```

## Sending/Receiving Content

TLDR: Send a map, get a map.

Currently, if the functions require content passed, it is accepted in the form of a Clojure hash map.

This may change in the future with more explicit required/optional fields when calling the functions.

For now, refer to the [Telegram Bot API Documentation](https://core.telegram.org/bots/api) for which fields are required per API resource.

Example:

```clojure
(tbot/send-message mybot {:chat_id 789 :text "Hello Bot World!"})
```

Responses from the API are parsed back into a Clojure hash map.

Example:

```clojure
{:ok true,
 :result
 {:message_id 7,
  :from {:id 123, :is_bot true, :first_name "mybot", :username "my_roboto"},
  :chat {:id 789, :first_name "Bill", :last_name "Howe", :username "myusername", :type "private"},
  :date 1602717364,
  :text "Hello Bot World!"}}
```

## How to send a chat message

- In the Telegram client: Add your bot to a channel or message it directly.
- From your app/repl: Have your bot get updates.
  - Recent chat messages on the server are returned, along with the chat id of the sender(s).
  - In this case, the sender is (from id) is the same as the chat id. (ie: this is a private direct message)

```clojure
(tbot/get-updates mybot)

;; response
{:ok true, :result [{:update_id 761420707, :message {:message_id 9, :from {:id 789, :is_bot false, :first_name "Bill", :last_name "Howe", :username "myusername", :language_code "en"}, :chat {:id 789, :first_name "Bill", :last_name "Howe", :username "myusername", :type "private"}, :date 1602815917, :text "oh hi"}}]}
```

- Send your message to the target chat id.
  - Notice the incrementing message_id number. (this is useful to keep track of message order)

```clojure
(tbot/send-message mybot {:chat_id 789 :text "oh hi yourself."})

;; response
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
