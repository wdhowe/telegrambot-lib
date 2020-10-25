(ns telegrambot-lib.inline.core
  "Telegram Bot API Inline Mode - function implementations.

   * https://core.telegram.org/bots/api#inline-mode

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
   Parameters
   ;; Required
   inline_query_id ; id for the answered query
   results ; json array of results for inline query
   ;; Optional
   cache_time ; max time in seconds
   is_personal ; true if query results are cached only for individuals
   next_offset ; Pagination offset a client should use for more results
   switch_pm_text ; display a button that allows clients to start a private chat with the bot
   switch_pm_parameter ; 'deep-linking' param for /start sent to bot during switch"
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

(def behavior
  "Map for extending the core TBot record with functions."
  {:answer-inline-query answer-inline-query})
