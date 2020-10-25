(ns telegrambot-lib.games.core
  "Telegram Bot API Games - function implementations.
   
   * https://core.telegram.org/bots/api#games"
  (:gen-class)
  (:require [telegrambot-lib.http :as http]))

(defn send-game
  "Use this method to send a game. On success, the sent Message is returned."
  ([this content]
   (http/request this "sendGame" content))

  ([this chat_id game_short_name]
   (let [content {:chat_id chat_id
                  :game_short_name game_short_name}]
     (send-game this content)))

  ([this chat_id game_short_name & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :game_short_name game_short_name})]
     (send-game this content))))

(defn set-game-score
  "Use this method to set the score of the specified user in a game.
   On success, if the message was sent by the bot, returns the edited Message,
   otherwise returns True. Returns an error, if the new score is not greater
   than the user's current score in the chat and force is False."
  ([this content]
   (http/request this "setGameScore" content))

  ([this chat_id message_id user_id score]
   (let [content {:chat_id chat_id
                  :message_id message_id
                  :user_id user_id
                  :score score}]
     (set-game-score this content)))

  ([this chat_id message_id user_id score & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :message_id message_id
                         :user_id user_id
                         :score score})]
     (set-game-score this content))))

(defn set-game-score-inline
  "Use this method to set the inline score of the specified user in a game.
   On success, if the message was sent by the bot, returns the edited Message,
   otherwise returns True. Returns an error, if the new score is not greater
   than the user's current score in the chat and force is False."
  ([this content]
   (http/request this "setGameScore" content))

  ([this inline_message_id user_id score]
   (let [content {:inline_message_id inline_message_id
                  :user_id user_id
                  :score score}]
     (set-game-score-inline this content)))

  ([this inline_message_id user_id score & optional]
   (let [content (merge (first optional)
                        {:inline_message_id inline_message_id
                         :user_id user_id
                         :score score})]
     (set-game-score-inline this content))))

(defn get-game-high-scores
  "Use this method to get data for high score tables.
   Will return the score of the specified user and several of their neighbors in a game.
   On success, returns an Array of GameHighScore objects."
  ([this content]
   (http/request this "getGameHighScores" content))

  ([this chat_id message_id user_id]
   (let [content {:chat_id chat_id
                  :message_id message_id
                  :user_id user_id}]
     (get-game-high-scores this content))))

(defn get-game-high-scores-inline
  "Use this method to get data for inline high score tables.
   Will return the score of the specified user and several of their neighbors in a game.
   On success, returns an Array of GameHighScore objects."
  ([this content]
   (http/request this "getGameHighScores" content))

  ([this inline_message_id user_id]
   (let [content {:inline_message_id inline_message_id
                  :user_id user_id}]
     (get-game-high-scores-inline this content))))

(def behavior
  "Map for extending the core TBot record with functions."
  {:send-game send-game
   :set-game-score set-game-score
   :set-game-score-inline set-game-score-inline
   :get-game-high-scores get-game-high-scores
   :get-game-high-scores-inline get-game-high-scores-inline})
