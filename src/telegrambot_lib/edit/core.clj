(ns telegrambot-lib.edit.core
  "Telegram Bot API Updating/Modifying Messages - function implementations.
   - <https://core.telegram.org/bots/api#updating-messages>

   Most functions are multi-arity with the following options:
   - Send all parameters in a 'content' map.
   - Send only the required parameters as simple values.
   - Send the required paraemters as simple values and then 'optional' parameters in a map."
  (:gen-class)
  (:require [telegrambot-lib.http :as http]))

(defn edit-message-text
  "Use this method to edit text and game messages.
   On success, if edited message is sent by the bot, the edited Message is
   returned, otherwise True is returned.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - message_id ; id of message to edit
   - text ; new text of message

   Optional
   - parse_mode ; entity parsing in message
   - entities ; list of MessageEntity - can use instead of parse_mode
   - disable_web_page_preview ; disable link previews
   - reply_markup ; inline keyboard markup"
  ([this content]
   (http/request this "editMessageText" content))

  ([this chat_id message_id text]
   (let [content {:chat_id chat_id
                  :message_id message_id
                  :text text}]
     (edit-message-text this content)))

  ([this chat_id message_id text & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :message_id message_id
                         :text text})]
     (edit-message-text this content))))

(defn edit-message-text-inline
  "Use this method to edit inline text and game messages.
   On success, if edited message is sent by the bot, the edited Message is
   returned, otherwise True is returned.

   Required
   - this ; a bot instance
   - inline_message_id ; id of the inline message
   - text ; new text of message

   Optional
   - parse_mode ; entity parsing in message
   - disable_web_page_preview ; disable link previews
   - reply_markup ; inline keyboard markup"
  ([this content]
   (http/request this "editMessageText" content))

  ([this inline_message_id text]
   (let [content {:inline_message_id inline_message_id
                  :text text}]
     (edit-message-text-inline this content)))

  ([this inline_message_id text & optional]
   (let [content (merge (first optional)
                        {:inline_message_id inline_message_id
                         :text text})]
     (edit-message-text-inline this content))))

(defn edit-message-caption
  "Use this method to edit captions of messages.
   On success, if edited message is sent by the bot, the edited Message is
   returned, otherwise True is returned.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - message_id ; id of message to edit
   - caption ; new caption of message

   Optional
   - parse_mode ; entity parsing in message
   - caption_entities ; list of MessageEntity - can use instead of parse_mode
   - reply_markup ; inline keyboard markup"
  ([this content]
   (http/request this "editMessageCaption" content))

  ([this chat_id message_id caption]
   (let [content {:chat_id chat_id
                  :message_id message_id
                  :caption caption}]
     (edit-message-caption this content)))

  ([this chat_id message_id caption & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :message_id message_id
                         :caption caption})]
     (edit-message-caption this content))))

(defn edit-message-caption-inline
  "Use this method to edit captions of inline messages.
   On success, if edited message is sent by the bot, the edited Message is
   returned, otherwise True is returned.

   Required
   - this ; a bot instance
   - inline_message_id ; id of the inline message
   - caption ; new caption of message

   Optional
   - parse_mode ; entity parsing in message
   - reply_markup ; inline keyboard markup"
  ([this content]
   (http/request this "editMessageCaption" content))

  ([this inline_message_id caption]
   (let [content {:inline_message_id inline_message_id
                  :caption caption}]
     (edit-message-caption-inline this content)))

  ([this inline_message_id caption & optional]
   (let [content (merge (first optional)
                        {:inline_message_id inline_message_id
                         :caption caption})]
     (edit-message-caption-inline this content))))

(defn edit-message-media
  "Use this method to edit animation, audio, document, photo, or video messages.
   If a message is a part of a message album, then it can be edited only to a
   photo or a video. Otherwise, message type can be changed arbitrarily.
   When inline message is edited, new file can't be uploaded.
   Use previously uploaded file via its file_id or specify a URL.
   On success, if the edited message was sent by the bot, the edited Message
   is returned, otherwise True is returned.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - message_id ; id of message to edit
   - media ; json object for new media content

   Optional
   - reply_markup ; inline keyboard markup"
  ([this content]
   (http/request this "editMessageMedia" content))

  ([this chat_id message_id media]
   (let [content {:chat_id chat_id
                  :message_id message_id
                  :media media}]
     (edit-message-media this content)))

  ([this chat_id message_id media & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :message_id message_id
                         :media media})]
     (edit-message-media this content))))

(defn edit-message-media-inline
  "Use this method to edit inline animation, audio, document, photo, or video messages.
   If a message is a part of a message album, then it can be edited only to a
   photo or a video. Otherwise, message type can be changed arbitrarily.
   When inline message is edited, new file can't be uploaded.
   Use previously uploaded file via its file_id or specify a URL.
   On success, if the edited message was sent by the bot, the edited Message
   is returned, otherwise True is returned.

   Required
   - this ; a bot instance
   - inline_message_id ; id of the inline message
   - media ; json object for new media content

   Optional
   - reply_markup ; inline keyboard markup"
  ([this content]
   (http/request this "editMessageMedia" content))

  ([this inline_message_id media]
   (let [content {:inline_message_id inline_message_id
                  :media media}]
     (edit-message-media-inline this content)))

  ([this inline_message_id media & optional]
   (let [content (merge (first optional)
                        {:inline_message_id inline_message_id
                         :media media})]
     (edit-message-media-inline this content))))

(defn edit-message-reply-markup
  "Use this method to edit only the reply markup of messages.
   On success, if edited message is sent by the bot, the edited Message is
   returned, otherwise True is returned.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - message_id ; id of message to edit
   - reply_markup ; inline keyboard markup"
  ([this content]
   (http/request this "editMessageReplyMarkup" content))

  ([this chat_id message_id reply_markup]
   (let [content {:chat_id chat_id
                  :message_id message_id
                  :reply_markup reply_markup}]
     (edit-message-reply-markup this content))))

(defn edit-message-reply-markup-inline
  "Use this method to edit only the reply markup of inline messages.
   On success, if edited message is sent by the bot, the edited Message is
   returned, otherwise True is returned.

   Required
   - this ; a bot instance
   - inline_message_id ; id of the inline message
   - reply_markup ; inline keyboard markup"
  ([this content]
   (http/request this "editMessageReplyMarkup" content))

  ([this inline_message_id reply_markup]
   (let [content {:inline_message_id inline_message_id
                  :reply_markup reply_markup}]
     (edit-message-reply-markup-inline this content))))

(defn stop-poll
  "Use this method to stop a poll which was sent by the bot.
   On success, the stopped Poll with the final results is returned.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - message_id ; id of original message with the poll

   Optional
   - reply_markup ; inline keyboard markup"
  ([this content]
   (http/request this "stopPoll" content))

  ([this chat_id message_id]
   (let [content {:chat_id chat_id
                  :message_id message_id}]
     (stop-poll this content)))

  ([this chat_id message_id & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :message_id message_id})]
     (stop-poll this content))))

(defn delete-message
  "Use this method to delete a message, including service messages.
   Returns True on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - message_id ; id of message to delete"
  ([this content]
   (http/request this "deleteMessage" content))

  ([this chat_id message_id]
   (let [content {:chat_id chat_id
                  :message_id message_id}]
     (delete-message this content))))

(def behavior
  "Map for extending the core TBot record with functions."
  {:edit-message-text edit-message-text
   :edit-message-text-inline edit-message-text-inline
   :edit-message-caption edit-message-caption
   :edit-message-caption-inline edit-message-caption-inline
   :edit-message-media edit-message-media
   :edit-message-media-inline edit-message-media-inline
   :edit-message-reply-markup edit-message-reply-markup
   :edit-message-reply-markup-inline edit-message-reply-markup-inline
   :stop-poll stop-poll
   :delete-message delete-message})
