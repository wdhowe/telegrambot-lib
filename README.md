# telegrambot-lib

A Clojure library for interacting with the Telegram Bot API.

## Usage

Get started using the telegrambot-lib.

### Installation

Leiningen/Boot Project file

```clojure
[telegrambot-lib "0.1.0"]
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

Create a new bot instance - looks for "bot-token" or "BOT_TOKEN" from the environment.

```clojure
(def mybot (tbot/create))
```

Create a new bot instance - passing the token at creation time.

```clojure
(def mybot (tbot/create my-token))
```

Verify your instance is working

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

### Available Functions

All available Telegram Bot API functions are imported into the telegrambot-lib.core namespace.

They are available by their non-namespaced name and are all listed within the "import-vars" function.

Additionally, there is a generic 'call' function that may be used to send a request to any endpoint if the function does not exist yet.

```clojure
;; generic call example
(tbot/call mybot "getMe")

;; generic call example with content
(tbot/call mybot "sendMessage" {:chat_id 789 :text "Hello Bot World!"})
```

### Sending/Receiving Content

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
