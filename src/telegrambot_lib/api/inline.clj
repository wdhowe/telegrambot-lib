(ns telegrambot-lib.api.inline
  "Telegram Bot API Inline Mode - function implementations.
   - <https://core.telegram.org/bots/api#inline-mode>

   Most functions are multi-arity with the following options:
   - Send all parameters in a 'content' map.
   - Send only the required parameters as simple values.
   - Send the required paraemters as simple values and then 'optional' parameters in a map."
  (:gen-class)
  (:require [telegrambot-lib.http :as http]))

(defn answer-inline-query
  "Use this method to send answers to an inline query.
   On success, True is returned.
   No more than 50 results per query are allowed.

   Required
   - this ; a bot instance
   - inline_query_id ; id for the answered query
   - results ; json array of results for inline query

   Optional
   - cache_time ; max time in seconds
   - is_personal ; true if query results are cached only for individuals
   - next_offset ; Pagination offset a client should use for more results
   - switch_pm_text ; display a button that allows clients to start a private chat with the bot
   - switch_pm_parameter ; 'deep-linking' param for /start sent to bot during switch"
  ([this content]
   (http/request this "answerInlineQuery" content))

  ([this inline_query_id results]
   (let [content {:inline_query_id inline_query_id
                  :results results}]
     (answer-inline-query this content)))

  ([this inline_query_id results & optional]
   (let [content (merge (first optional)
                        {:inline_query_id inline_query_id
                         :results results})]
     (answer-inline-query this content))))

(defn answer-web-app-query
  "Use this method to set the result of an interaction with a Web App and
   send a corresponding message on behalf of the user to the chat from
   which the query originated.
   On success, a SentWebAppMessage object is returned.

   Required
   - this ; a bot instance
   - web_app_query_id ; id for the answered query
   - result ; json object describing message to be sent"
  {:added "1.5.0"}
  ([this content]
   (http/request this "answerWebAppQuery" content))

  ([this web_app_query_id result]
   (let [content {:web_app_query_id web_app_query_id
                  :result result}]
     (answer-inline-query this content))))
