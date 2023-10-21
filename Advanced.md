# Advanced

- [Home](index.md)
- Advanced
- [Available Functions](Available-Functions.md)
- [Getting and Sending Messages](Getting-and-Sending-Messages.md)
- [In the Wild](In-the-Wild.md)
- [Miscellaneous](Miscellaneous.md)

## Error Handling

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
> there is no sense in re-throwing exceptions. See the next section for details on how to make
> an async Telegram Bot API request with the `telegrambot-lib`.

## Making Asynchronous Calls

Basically, you have to pass an `:async true` parameter along with the bot to make any function end up
with firing an asynchronous request. The only difference is that the function returns immediately, with a
regular `core.async` channel to take the response (or [error result](#error-handling)) from later on.

Here's how such a call may look like:

```clojure
;; Option 1: temporarily modify the bot map for a particular async request
(tbot/send-message (assoc mybot :async true)
                   {:chat_id 280000000 :text "Drop-and-run!"})

;; Option 2: use a global/shared bot map for all asynchronous requests
(def my-async-bot (tbot/create {:async true}))

(tbot/send-message my-async-bot 280000000 "Drop-and-run!")
```

In either case you'll get a similar response — a `core.async` channel to take from later on:

```clojure
=>
#object[clojure.core.async.impl.channels.ManyToManyChannel 0x3d059861 "clojure.core.async.impl.channels.ManyToManyChannel@3d059861"]
```