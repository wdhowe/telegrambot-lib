(ns telegrambot-lib.inline.protocol
  "Telegram Bot API Inline Mode - protocol definitions.
   
   * https://core.telegram.org/bots/api#inline-mode")

(defprotocol Inline
  "The following methods and objects allow your bot to work in inline mode."

  (answer-inline-query [this content]
    "Use this method to send answers to an inline query. On success, True is returned.
     No more than 50 results per query are allowed."))
