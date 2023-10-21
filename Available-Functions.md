# Available Functions

- [Home](index.md)
- [Advanced](Advanced.md)
- Available Functions
- [Getting and Sending Messages](Getting-and-Sending-Messages.md)
- [In the Wild](In-the-Wild.md)
- [Miscellaneous](Miscellaneous.md)

All of the Telegram Bot API methods are represented by functions of the `telegrambot-lib.core` namespace.

Find the list of the available functions [near the bottom of `telegrambot-lib.core`](https://github.com/wdhowe/telegrambot-lib/blob/main/src/telegrambot_lib/core.clj#L48).

## Functions Contract

All library functions expect a bot map as their first argument.

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

## Request Format

All telegrambot_lib function calls format the API request to json by default.

In order to specify multipart, such as when you need to upload a file, simply include `:content-type :multipart` as part of your map when calling the function.

Set webhook example:

```clojure
(set-webhook bot {:url "https://my.url.com"
                  :certificate (clojure.java.io/file "/path/to/cert.pem")
                  :content-type :multipart})
```

## Generic Function Call

Additionally, there is a generic `call` function that may be used to send a request to any Bot API endpoint.
This will come in handy in the rare case when the Telegram Bot API already provides some method that this library
does not yet cover with a function.

```clojure
;; generic call example
(tbot/call mybot "getMe")

;; generic call example with content
(tbot/call mybot "sendMessage" {:chat_id 280000000 :text "Hello Bot World!"})
```