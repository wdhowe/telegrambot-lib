(ns telegrambot-lib.api.games
  "Telegram Bot API Games - function implementations.
   - <https://core.telegram.org/bots/api#games>

   Most functions are multi-arity with the following options:
   - Send all parameters in a 'content' map.
   - Send only the required parameters as simple values.
   - Send the required paraemters as simple values and then 'optional' parameters in a map."
  (:gen-class)
  (:require [telegrambot-lib.http :as http]))

(defn send-game
  "Use this method to send a game.
   On success, the sent Message is returned.

   Required
   - this ; a bot instance
   - chat_id ; target chat id
   - game_short_name ; serves as unique id for the game

   Optional
   - message_thread_id ; id of the target thread of the forum.
   - disable_notification ; send message silently
   - protect_content ; protect content from forwarding/saving
   - reply_to_message_id ; id of original message
   - allow_sending_without_reply ; true to send message even if replied-to message is not found
   - reply_markup ; inline keyboard markup"
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
   than the user's current score in the chat and force is False.

   Required
   - this ; a bot instance
   - chat_id ; id of target chat
   - message_id ; id of the sent message
   - user_id ; user identifier
   - score ; new score, non-negative

   Optional
   - force ; true if high score is allowed to decrease
   - disable_edit_message ; true if game msg should not be auto edited to include the scoreboard"
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
   than the user's current score in the chat and force is False.

   Required
   - this ; a bot instance
   - inline_message_id ; id of the inline message
   - user_id ; user identifier
   - score ; new score, non-negative

   Optional
   - force ; true if high score is allowed to decrease
   - disable_edit_message ; true if game msg should not be auto edited to include the scoreboard"
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
   On success, returns an Array of GameHighScore objects.

   Required
   - this ; a bot instance
   - chat_id ; id of the target chat
   - message_id ; id of the sent message
   - user_id ; target user"
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
   On success, returns an Array of GameHighScore objects.

   Required
   - this ; a bot instance
   - inline_message_id ; id of the sent message
   - user_id ; target user"
  ([this content]
   (http/request this "getGameHighScores" content))

  ([this inline_message_id user_id]
   (let [content {:inline_message_id inline_message_id
                  :user_id user_id}]
     (get-game-high-scores-inline this content))))
