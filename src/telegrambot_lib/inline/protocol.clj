(ns telegrambot-lib.inline.protocol
  "Telegram Bot API Inline Mode - protocol definitions.
   - <https://core.telegram.org/bots/api#inline-mode>")

(defprotocol Inline
  "The following methods and objects allow your bot to work in inline mode."

  (answer-inline-query [this content]
    [this inline_query_id results]
    [this inline_query_id results & optional]
    "Use this method to send answers to an inline query. On success, True is returned.
     No more than 50 results per query are allowed.")

  (answer-web-app-query [this content]
    [this web_app_query_id result]
    "Use this method to set the result of an interaction with a Web App and
     send a corresponding message on behalf of the user to the chat from
     which the query originated.
     On success, a SentWebAppMessage object is returned."))
