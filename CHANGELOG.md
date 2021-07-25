# Change Log

All notable changes to this project will be documented in this file. This change log follows the conventions of [keepachangelog.com](http://keepachangelog.com/).

## [Unreleased]

- Minor documentation updates.

## [1.0.0] - 2021-07-18

### 1.0.0 Added

- Ability to send requests asynchronously.

### 1.0.0 Changed

- Exceptions are returned as an :error keyword in the response map instead of being thrown.
- Logging now uses [org.clojure/tools.logging](https://github.com/clojure/tools.logging).
- The JSON library dependency must now be provided by the implementing application.

## [0.3.5] - 2021-07-04

### 0.3.5 Fixed

- answerCallbackQuery parameter correction.
- getWebhookInfo method name correction.

## [0.3.4] - 2021-06-26

### 0.3.4 Added

- New optional parameters and updates in support of [Telegram Bot API 5.3](https://core.telegram.org/bots/api-changelog#june-25-2021).

### 0.3.4 Changed

- Removed version information from the README.
  - Bot API versions moved to changelog.
  - Removed library version info from the Installation section and now refers to Clojars.
- Dependency version bumps
  - clj-http

## [0.3.3] - 2021-04-30

### 0.3.3 Added

- New optional parameters and updates in support of [Telegram Bot API 5.2](https://core.telegram.org/bots/api-changelog#april-26-2021).

### 0.3.3 Changed

- Bumped project dependencies versions to the latest available.

## [0.3.2] - 2021-03-10

### 0.3.2 Added

- New methods and optional params in support of [Telegram Bot API 5.1](https://core.telegram.org/bots/api-changelog#march-9-2021).

### 0.3.2 Changed

- Minor developer centric project updates.
  - Stopped using lein codox
  - Updated README for CLJ/deps dependency command
  - JDK version changes in the ci/cd pipeline
  - gitignore updates

## [0.3.1] - 2020-11-29

### 0.3.1 Changed

- Updated all function docstrings to use markdown syntax.
  - Big improvement for generated cljdocs readability.

## [0.3.0] - 2020-11-06

### 0.3.0 Added

- New methods and optional params in support of [Telegram Bot API 5.0](https://core.telegram.org/bots/api-changelog#november-4-2020).

## [0.2.0] - 2020-10-25

### 0.2.0 Changed

- Multi-arity function parameters for API methods. (easier to see required/optional parameters)

## [0.1.1] - 2020-10-16

### 0.1.1 Changed

- Improved documentation and logging.
- Simplified configuration.

## 0.1.0 - 2020-10-15

### 0.1.0 Added

- Initial release of project, supporting [Telegram Bot API 4.9](https://core.telegram.org/bots/api-changelog#june-4-2020).
- Basic library functionality, tests, documentation, github pipeline.

[Unreleased]: https://github.com/wdhowe/telegrambot-lib/compare/1.0.0...HEAD
[1.0.0]: https://github.com/wdhowe/telegrambot-lib/compare/0.3.5...1.0.0
[0.3.5]: https://github.com/wdhowe/telegrambot-lib/compare/0.3.4...0.3.5
[0.3.4]: https://github.com/wdhowe/telegrambot-lib/compare/0.3.3...0.3.4
[0.3.3]: https://github.com/wdhowe/telegrambot-lib/compare/0.3.2...0.3.3
[0.3.2]: https://github.com/wdhowe/telegrambot-lib/compare/0.3.1...0.3.2
[0.3.1]: https://github.com/wdhowe/telegrambot-lib/compare/0.3.0...0.3.1
[0.3.0]: https://github.com/wdhowe/telegrambot-lib/compare/0.2.0...0.3.0
[0.2.0]: https://github.com/wdhowe/telegrambot-lib/compare/0.1.1...0.2.0
[0.1.1]: https://github.com/wdhowe/telegrambot-lib/compare/0.1.0...0.1.1

[comment]: # (Types of changes)
[comment]: # ('Added' for new features.)
[comment]: # ('Changed' for changes in existing functionality.)
[comment]: # ('Deprecated' for soon-to-be removed features.)
[comment]: # ('Removed' for now removed features.)
[comment]: # ('Fixed' for any bug fixes.)
[comment]: # ('Security' in case of vulnerabilities.)
