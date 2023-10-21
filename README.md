# telegrambot-lib

[![Build Status][gh-actions-badge]][gh-actions] [![Clojars Project][clojars-badge]][clojars] [![Clojure version][clojure-v]](project.clj)

<!-- Put back in badges after fixing issue-127? [![Clojure Docs][cljdoc-badge]][cljdoc-link] -->

A Clojure library for interacting with the Telegram Bot API.

The goal of this library is to be a complete implementation of the Telegram Bot API in Clojure,
without adding extra bot logic.

Each API call is a Clojure function, with docstrings outlining required and optional parameters.

> **For more information on updates to the Telegram Bot API version, see the [Releases](https://github.com/wdhowe/telegrambot-lib/releases).**

## Usage

See the [Wiki for documentation](https://github.com/wdhowe/telegrambot-lib/wiki).

## License

Copyright © 2020-2023 Bill Howe and contributors

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
[clojure-v]: https://img.shields.io/badge/clojure-1.11.1-blue.svg
[clojars]: https://clojars.org/telegrambot-lib
[clojars-badge]: https://img.shields.io/clojars/v/telegrambot-lib.svg
