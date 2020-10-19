(ns telegrambot-lib.edit.params
  "Telegram Bot API Updating/Modifying Messages - parameter definitions."
  (:gen-class))

(defrecord edit-message-text-params
           [;; Required
            chat_id ; target chat or username (@user)
            message_id ; id of message to edit
            text ; new text of message
            ;; Opional
            parse_mode ; entity parsing in message
            disable_web_page_preview ; disable link previews
            reply_markup ; inline keyboard markup
            ])

(defrecord edit-message-text-inline-params
           [;; Required
            inline_message_id ; id of the inline message
            text ; new text of message
            ;; Optional
            parse_mode ; entity parsing in message
            disable_web_page_preview ; disable link previews
            reply_markup ; inline keyboard markup
            ])

(defrecord edit-message-caption-params
           [;; Required
            chat_id ; target chat or username (@user)
            message_id ; id of message to edit
            caption ; new caption of message
            ;; Optional
            parse_mode ; entity parsing in message
            reply_markup ; inline keyboard markup
            ])

(defrecord edit-message-caption-inline-params
           [;; Required
            inline_message_id ; id of the inline message
            caption ; new caption of message
            ;; Optional
            parse_mode ; entity parsing in message
            reply_markup ; inline keyboard markup
            ])

(defrecord edit-message-media-params
           [;; Required
            chat_id ; target chat or username (@user)
            message_id ; id of message to edit
            media ; json object for new media content
            ;; Optional
            reply_markup ; inline keyboard markup
            ])

(defrecord edit-message-media-inline-params
           [;; Required
            inline_message_id ; id of the inline message
            media ; json object for new media content
            ;; Optional
            reply_markup ; inline keyboard markup
            ])

(defrecord edit-message-reply-markup-params
           [;; Required
            chat_id ; target chat or username (@user)
            message_id ; id of message to edit
            reply_markup ; inline keyboard markup
            ])

(defrecord edit-message-reply-markup-inline-params
           [;; Required
            inline_message_id ; id of the inline message
            reply_markup ; inline keyboard markup
            ])

(defrecord stop-poll-params
           [;; Required
            chat_id ; target chat or username (@user)
            message_id ; id of original message with the poll
            ;; Optional
            reply_markup ; inline keyboard markup
            ])

(defrecord delete-message-params
           [;; Required
            chat_id ; target chat or username (@user)
            message_id ; id of message to delete
            ])
