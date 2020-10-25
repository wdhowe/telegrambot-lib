(ns telegrambot-lib.inline.core
  "Telegram Bot API Inline Mode - function implementations.
   
   * https://core.telegram.org/bots/api#inline-mode"
  (:gen-class)
  (:require [telegrambot-lib.http :as http]))

(defn answer-inline-query
  "Use this method to send answers to an inline query.
   On success, True is returned.
   No more than 50 results per query are allowed."
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
