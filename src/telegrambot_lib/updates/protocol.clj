(ns telegrambot-lib.updates.protocol
  "Getting updates - protocol definitions.
   
   * https://core.telegram.org/bots/api#getupdates")

(defprotocol Updates
  "Getting bot message updates."

  (get-updates [this] [this content]
    "Use this method to receive incoming updates using long polling.
     An Array of Update objects is returned.")

  (set-webhook [this url]
    [this url & optional]
    "Use this method to specify a url and receive incoming updates via
     an outgoing webhook. Whenever there is an update for the bot, we
     will send an HTTPS POST request to the specified url, containing
     a JSON-serialized Update. In case of an unsuccessful request, we
     will give up after a reasonable amount of attempts.
     Returns True on success.")

  (delete-webhook [this] [this content]
    "Use this method to remove webhook integration if you decide to
     switch back to getUpdates.
     Returns True on success.")

  (get-webhook [this]
    "Use this method to get current webhook status.
     Requires no parameters.
     On success, returns a WebhookInfo object.
     If the bot is using getUpdates, will return an object with the
     url field empty."))
