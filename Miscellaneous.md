# Miscellaneous

- [Home](index.md)
- [Advanced](Advanced.md)
- [Available Functions](Available-Functions.md)
- [Getting and Sending Messages](Getting-and-Sending-Messages.md)
- [In the Wild](In-the-Wild.md)
- Miscellaneous

## Logging

This library uses the [org.clojure/tools.logging](https://github.com/clojure/tools.logging), a standard facade library, for logging purposes.
Thus, any logger implementation you pick for your project is supported, and all the logs from the `telegrambot-lib`
will be redirected to it.

## Version Conflicts

The `telegrambot-lib` depends on a particular version of the `clj-http` library (which itself implicitly depends on a
particular JSON mapper version) that is known to cause version conflicts in end user projects from time to time.

There is a known fix for this type of issue: you either remove your own dependency on a particular `clj-http` version
or exclude the one that comes with the `telegrambot-lib` (with e.g. `:exclusions [clj-http]` for Leiningen projects).