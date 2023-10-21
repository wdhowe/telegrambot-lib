# telegrambot-lib

- Home
- [Advanced](Advanced.md)
- [Available Functions](Available-Functions.md)
- [Getting and Sending Messages](Getting-and-Sending-Messages.md)
- [In the Wild](In-the-Wild.md)
- [Miscellaneous](Miscellaneous.md)

## Getting Started

Get started using the `telegrambot-lib`.

## Adding Dependencies

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
[telegrambot-lib "2.10.0"]
[cheshire "5.10.1"]
```

- **Note**: The above version numbers are an example and may not be the latest. See each project's github or clojars page.

## Requiring the Library

In the REPL:

```clojure
(require '[telegrambot-lib.core :as tbot])
```

In your application codebase:

```clojure
(ns my-app.core
  (:require [telegrambot-lib.core :as tbot]))
```

## Using the Library

Pre-Reqs:

- You have [created a Telegram bot](https://core.telegram.org/bots#3-how-do-i-create-a-bot) and received an auth token.
- The library is added as a project dependency.
- The library is required as indicated above, with a `tbot` alias for the `core` namespace.
  - Note: The `tbot` alias is only important for following the example code in the wiki.

The Bot API auth token can be passed to the bot by either:

- **Environment variable** —
  The lib will look for `BOT_TOKEN` in the environment. \
  The token could be in project.clj, profiles.clj or an environment variable. \
  See [environ](https://github.com/weavejester/environ#usage) for more details.

  ```clojure
  (def mybot (tbot/create))
  ```

or

- **Function argument - simple value** —
  The `create` function has an optional parameter to pass an auth token.

  ```clojure
  (def mybot (tbot/create my-token))
  ```

or

- **Function argument - map** —
  The `create` function has an optional parameter to pass a map of config values.

  The advantage of using a map is the ability to change other configuration such as [async](https://github.com/wdhowe/telegrambot-lib/wiki/Advanced#making-asynchronous-calls) or the api endpoint url.

  ```clojure
  (def mybot (tbot/create {:bot-token my-token}))
  ```

Verify your bot is working by firing the [`getMe`](https://core.telegram.org/bots/api/#getme)
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

<!-- Named page links below: /-->
[clojars]: https://clojars.org/telegrambot-lib