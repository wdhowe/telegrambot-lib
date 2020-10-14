(ns telegrambot-lib.edit.core
  "Telegram Bot API Updating/Modifying Messages - function implementations.
   
   * https://core.telegram.org/bots/api#updating-messages"
  (:gen-class)
  (:require [telegrambot-lib.http :as http]))

(defn edit-message-text
  "Use this method to edit text and game messages.
   On success, if edited message is sent by the bot, the edited Message is
   returned, otherwise True is returned."
  [this content]
  (http/request this "editMessageText" content))

(defn edit-message-caption
  "Use this method to edit captions of messages.
   On success, if edited message is sent by the bot, the edited Message is
   returned, otherwise True is returned."
  [this content]
  (http/request this "editMessageCaption" content))

(defn edit-message-media
  "Use this method to edit animation, audio, document, photo, or video messages.
   If a message is a part of a message album, then it can be edited only to a
   photo or a video. Otherwise, message type can be changed arbitrarily.
   When inline message is edited, new file can't be uploaded.
   Use previously uploaded file via its file_id or specify a URL.
   On success, if the edited message was sent by the bot, the edited Message
   is returned, otherwise True is returned."
  [this content]
  (http/request this "editMessageMedia" content))

(defn edit-message-reply-markup
  "Use this method to edit only the reply markup of messages.
   On success, if edited message is sent by the bot, the edited Message is
   returned, otherwise True is returned."
  [this content]
  (http/request this "editMessageReplyMarkup" content))

(defn stop-poll
  "Use this method to stop a poll which was sent by the bot.
   On success, the stopped Poll with the final results is returned."
  [this content]
  (http/request this "stopPoll" content))

(defn delete-message
  "Use this method to delete a message, including service messages.
   Returns True on success."
  [this content]
  (http/request this "deleteMessage" content))

(def behavior
  "Map for extending the core TBot record with functions."
  {:edit-message-text edit-message-text
   :edit-message-caption edit-message-caption
   :edit-message-media edit-message-media
   :edit-message-reply-markup edit-message-reply-markup
   :stop-poll stop-poll
   :delete-message delete-message})
