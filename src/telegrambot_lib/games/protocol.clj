(ns telegrambot-lib.games.protocol
  "Telegram Bot API Games - protocol definitions.
   
   * https://core.telegram.org/bots/api#games")

(defprotocol Games
  "Bot API for HTML5 games on Telegram."

  (send-game [this content]
    [this chat_id game_short_name]
    [this chat_id game_short_name & optional]
    "Use this method to send a game. On success, the sent Message is returned.")

  (set-game-score [this content]
    [this chat_id message_id user_id score]
    [this chat_id message_id user_id score & optional]
    "Use this method to set the score of the specified user in a game.
     On success, if the message was sent by the bot, returns the edited Message,
     otherwise returns True. Returns an error, if the new score is not greater
     than the user's current score in the chat and force is False.")

  (set-game-score-inline [this content]
    [this inline_message_id user_id score]
    [this inline_message_id user_id score & optional]
    "Use this method to set the score of the specified user in a game.
     On success, if the message was sent by the bot, returns the edited Message,
     otherwise returns True. Returns an error, if the new score is not greater
     than the user's current score in the chat and force is False.")

  (get-game-high-scores [this content]
    [this chat_id message_id user_id]
    "Use this method to get data for high score tables.
     Will return the score of the specified user and several of their neighbors in a game.
     On success, returns an Array of GameHighScore objects.")

  (get-game-high-scores-inline [this content]
    [this inline_message_id user_id]
    "Use this method to get data for high score tables.
     Will return the score of the specified user and several of their neighbors in a game.
     On success, returns an Array of GameHighScore objects."))
