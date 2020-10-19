(ns telegrambot-lib.games.params
  "Telegram Bot API Games - parameter definitions."
  (:gen-class))

(defrecord send-game-params
           [;; Required
            chat_id ; target chat id
            game_short_name ; serves as unique id for the game
            ;; Optional
            disable_notification ; send message silently
            reply_to_message_id ; id of original message
            reply_markup ; inline keyboard markup
            ])

(defrecord set-game-score-params
           [;; Required
            chat_id ; id of target chat
            message_id ; id of the sent message
            user_id ; user identifier
            score ; new score, non-negative
            ;; Optional
            force ; true if high score is allowed to decrease
            disable_edit_message ; true if game msg should not be auto edited to include the scoreboard
            ])

(defrecord set-game-score-inline-params
           [;; Required
            inline_message_id ; id of the inline message
            user_id ; user identifier
            score ; new score, non-negative
            ;; Optional
            force ; true if high score is allowed to decrease
            disable_edit_message ; true if game msg should not be auto edited to include the scoreboard
            ])

(defrecord get-game-high-scores-params
           [;; Required
            chat_id ; id of the target chat
            message_id ; id of the sent message
            user_id ; target user
            ])

(defrecord get-game-high-scores-inline-params
           [;; Required
            inline_message_id ; id of the sent message
            user_id ; target user
            ])
