(ns telegrambot-lib.games.core
  "Telegram Bot API Games - function implementations.
   
   * https://core.telegram.org/bots/api#games"
  (:gen-class)
  (:require [telegrambot-lib.http :as http]))

(defn send-game
  "Use this method to send a game. On success, the sent Message is returned."
  [this content]
  (http/request this "sendGame" content))

(defn set-game-score
  "Use this method to set the score of the specified user in a game.
   On success, if the message was sent by the bot, returns the edited Message,
   otherwise returns True. Returns an error, if the new score is not greater
   than the user's current score in the chat and force is False."
  [this content]
  (http/request this "setGameScore" content))

(defn get-game-high-scores
  "Use this method to get data for high score tables.
   Will return the score of the specified user and several of their neighbors in a game.
   On success, returns an Array of GameHighScore objects."
  [this content]
  (http/request this "getGameHighScores" content))

(def behavior
  "Map for extending the core TBot record with functions."
  {:send-game send-game
   :set-game-score set-game-score
   :get-game-high-scores get-game-high-scores})
