(ns telegrambot-lib.inline.params
  "Telegram Bot API Inline Mode - parameter definitions."
  (:gen-class))

(defrecord answer-inline-query-params
           [;; Required
            inline_query_id ; id for the answered query
            results ; json array of results for inline query
            ;; Optional
            cache_time ; max time in seconds
            is_personal ; true if query results are cached only for individuals
            next_offset ; Pagination offset a client should use for more results
            switch_pm_text ; display a button that allows clients to start a private chat with the bot
            switch_pm_parameter ; 'deep-linking' param for /start sent to bot during switch
            ])
