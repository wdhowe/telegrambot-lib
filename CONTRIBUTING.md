# Development

## API Changes

What changes in the [Telegram Bot API](https://core.telegram.org/bots/api) require changes in this library?

Changes required (methods/parameters)

- New method: updates in the category's api/namespace and import-vars in main project core.
- New/changed required parameters for a method: updates in the category's api/namespace and api/namespace docstring.
- New/changed optional parameter for a method: updates in the category's api/namespace docstring only.

No changes

- Changes to classes.
- Changes to fields in a class.

## Artifacts

The dev profile should be excluded when creating pom and clojar artifacts.

```bash
lein with-profile -dev pom
lein with-profile -dev deploy clojars
```
