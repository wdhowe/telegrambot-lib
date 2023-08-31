(ns telegrambot-lib.core
  "A Clojure library for interacting with the Telegram Bot API.
   - [Getting started](https://github.com/wdhowe/telegrambot-lib#usage)"
  (:gen-class)
  (:require [telegrambot-lib.config :as conf]
            [telegrambot-lib.api.edit :as edit]
            [telegrambot-lib.api.games :as games]
            [telegrambot-lib.api.inline :as inline]
            [telegrambot-lib.api.methods :as methods]
            [telegrambot-lib.api.passport :as passport]
            [telegrambot-lib.api.payments :as payments]
            [telegrambot-lib.api.stickers :as stickers]
            [telegrambot-lib.api.updates :as updates]
            [potemkin :refer [import-vars]]))

(defmulti create
  "Create a new Telegram Bot data structure.
   - No parameters attempts to load the `bot-token` from the environment.
     - Example: `(create)`
   - 1 parameter will use the passed in `bot-token`.
     - Example: `(create \"12345\")`
   - Alternatively, parameters can be passed as a map instead.
     - Example: `(create {:bot-token \"12345\"})`
   
   Optional Parameters
   - bot-token ; The token id of your bot. (default: load from environment)
   - async ; Send API requests async or not. (default: false)
   - bot-api ; The Telegram Bot API URL. (default: official hosted API)
   
   Returns: A map data structure of a bot config."
  {:changed "2.10.0"}
  (fn
    ([] false)
    ([m] (map? m))))

(defmethod create true
  [m]
   (merge (conf/cfg) m))

(defmethod create false
  ([]
   (create {}))
  
  ([bot-token]
   (create {:bot-token bot-token})))

;; Make all API functions available directly in this namespace.
(import-vars
 [edit
  edit-message-text
  edit-message-text-inline
  edit-message-caption
  edit-message-caption-inline
  edit-message-media
  edit-message-media-inline
  edit-message-reply-markup
  edit-message-reply-markup-inline
  stop-poll
  delete-message]
 [games
  send-game
  set-game-score
  set-game-score-inline
  get-game-high-scores
  get-game-high-scores-inline]
 [inline
  answer-inline-query
  answer-web-app-query]
 [methods
  call
  get-me
  log-out
  close
  send-message
  forward-message
  copy-message
  send-photo
  send-audio
  send-document
  send-video
  send-animation
  send-voice
  send-video-note
  send-media-group
  send-location
  edit-message-live-location
  edit-message-live-location-inline
  stop-message-live-location
  stop-message-live-location-inline
  send-venue
  send-contact
  send-poll
  send-dice
  send-chat-action
  get-user-profile-photos
  get-file
  kick-chat-member
  ban-chat-member
  unban-chat-member
  restrict-chat-member
  promote-chat-member
  set-chat-administrator-custom-title
  ban-chat-sender-chat
  unban-chat-sender-chat
  set-chat-permissions
  export-chat-invite-link
  create-chat-invite-link
  edit-chat-invite-link
  revoke-chat-invite-link
  approve-chat-join-request
  decline-chat-join-request
  set-chat-photo
  delete-chat-photo
  set-chat-title
  set-chat-description
  pin-chat-message
  unpin-chat-message
  unpin-all-chat-messages
  leave-chat
  get-chat
  get-chat-administrators
  get-chat-members-count
  get-chat-member-count
  get-chat-member
  set-chat-sticker-set
  delete-chat-sticker-set
  get-forum-topic-icon-stickers
  create-forum-topic
  edit-forum-topic
  close-forum-topic
  reopen-forum-topic
  delete-forum-topic
  unpin-all-forum-topic-messages
  edit-general-forum-topic
  close-general-forum-topic
  reopen-general-forum-topic
  hide-general-forum-topic
  unhide-general-forum-topic
  unpin-all-general-forum-topic-messages
  answer-callback-query
  set-my-commands
  delete-my-commands
  get-my-commands
  set-my-name
  get-my-name
  set-my-description
  get-my-description
  set-my-short-description
  get-my-short-description
  set-chat-menu-button
  get-chat-menu-button
  set-my-default-administrator-rights
  get-my-default-administrator-rights]
 [passport
  set-passport-data-errors]
 [payments
  send-invoice
  create-invoice-link
  answer-shipping-query-ok
  answer-shipping-query-error
  answer-precheckout-query-ok
  answer-precheckout-query-error]
 [stickers
  send-sticker
  get-sticker-set
  get-custom-emoji-stickers
  upload-sticker-file
  create-new-sticker-set
  add-sticker-to-set
  set-sticker-position-in-set
  delete-sticker-from-set
  set-sticker-emoji-list
  set-sticker-keywords
  set-sticker-mask-position
  set-sticker-set-title
  set-sticker-set-thumb
  set-sticker-set-thumbnail
  set-custom-emoji-sticker-set-thumbnail
  delete-sticker-set]
 [updates
  get-updates
  set-webhook
  delete-webhook
  get-webhook-info])
