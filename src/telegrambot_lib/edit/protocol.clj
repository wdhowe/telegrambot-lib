(ns telegrambot-lib.edit.protocol
  "Telegram Bot API Updating/Modifying Messages - protocol definitions.
   - <https://core.telegram.org/bots/api#updating-messages>")

(defprotocol Edit
  "The following methods allow you to change an existing message in the message
   history instead of sending a new one with a result of an action.
   This is most useful for messages with inline keyboards using callback queries,
   but can also help reduce clutter in conversations with regular chat bots."

  (edit-message-text [this content]
    [this chat_id message_id text]
    [this chat_id message_id text & optional]
    "Use this method to edit text and game messages.
     On success, if edited message is sent by the bot, the edited Message is
     returned, otherwise True is returned.")

  (edit-message-text-inline [this content]
    [this inline_message_id text]
    [this inline_message_id text & optional]
    "Use this method to edit inline text and game messages.
     On success, if edited message is sent by the bot, the edited Message is
     returned, otherwise True is returned.")

  (edit-message-caption [this content]
    [this chat_id message_id caption]
    [this chat_id message_id caption & optional]
    "Use this method to edit captions of messages.
     On success, if edited message is sent by the bot, the edited Message is
     returned, otherwise True is returned.")

  (edit-message-caption-inline [this content]
    [this inline_message_id caption]
    [this inline_message_id caption & optional]
    "Use this method to edit captions of inline messages.
     On success, if edited message is sent by the bot, the edited Message is
     returned, otherwise True is returned.")

  (edit-message-media [this content]
    [this chat_id message_id media]
    [this chat_id message_id media & optional]
    "Use this method to edit animation, audio, document, photo, or video messages.
     If a message is a part of a message album, then it can be edited only to a
     photo or a video. Otherwise, message type can be changed arbitrarily.
     When inline message is edited, new file can't be uploaded.
     Use previously uploaded file via its file_id or specify a URL.
     On success, if the edited message was sent by the bot, the edited Message
     is returned, otherwise True is returned.")

  (edit-message-media-inline [this content]
    [this inline_message_id media]
    [this inline_message_id media & optional]
    "Use this method to edit inline animation, audio, document, photo, or video messages.
     If a message is a part of a message album, then it can be edited only to a
     photo or a video. Otherwise, message type can be changed arbitrarily.
     When inline message is edited, new file can't be uploaded.
     Use previously uploaded file via its file_id or specify a URL.
     On success, if the edited message was sent by the bot, the edited Message
     is returned, otherwise True is returned.")

  (edit-message-reply-markup [this content]
    [this chat_id message_id reply_markup]
    "Use this method to edit only the reply markup of messages.
     On success, if edited message is sent by the bot, the edited Message is
     returned, otherwise True is returned.")

  (edit-message-reply-markup-inline [this content]
    [this inline_message_id reply_markup]
    "Use this method to edit only the reply markup of messages.
     On success, if edited message is sent by the bot, the edited Message is
     returned, otherwise True is returned.")

  (stop-poll [this content]
    [this chat_id message_id]
    [this chat_id message_id & optional]
    "Use this method to stop a poll which was sent by the bot.
     On success, the stopped Poll with the final results is returned.")

  (delete-message [this content]
    [this chat_id message_id]
    "Use this method to delete a message, including service messages.
     Returns True on success."))
