(ns telegrambot-lib.core
  "A Clojure library for interacting with the Telegram Bot API.
   https://github.com/wdhowe/telegrambot-lib#usage

   Implemented Telegram functions accurate as of 'Bot API 4.9':
   https://core.telegram.org/bots/api-changelog#june-4-2020"
  (:gen-class)
  (:require [telegrambot-lib.config :as conf]
            [telegrambot-lib.edit.core :as edit]
            [telegrambot-lib.edit.protocol :refer [Edit]]
            [telegrambot-lib.games.core :as games]
            [telegrambot-lib.games.protocol :refer [Games]]
            [telegrambot-lib.inline.core :as inline]
            [telegrambot-lib.inline.protocol :refer [Inline]]
            [telegrambot-lib.methods.core :as methods]
            [telegrambot-lib.methods.protocol :refer [Methods]]
            [telegrambot-lib.passport.core :as passport]
            [telegrambot-lib.passport.protocol :refer [Passport]]
            [telegrambot-lib.payments.core :as payments]
            [telegrambot-lib.payments.protocol :refer [Payments]]
            [telegrambot-lib.stickers.core :as stickers]
            [telegrambot-lib.stickers.protocol :refer [Stickers]]
            [telegrambot-lib.updates.core :as updates]
            [telegrambot-lib.updates.protocol :refer [Updates]]
            [potemkin :refer [import-vars]]))

(defrecord TBot [bot-token])

;; Add each part of the Telegram API to the TBot type.
(extend TBot Edit edit/behavior)
(extend TBot Games games/behavior)
(extend TBot Inline inline/behavior)
(extend TBot Methods methods/behavior)
(extend TBot Passport passport/behavior)
(extend TBot Payments payments/behavior)
(extend TBot Stickers stickers/behavior)
(extend TBot Updates updates/behavior)

(defn create
  "Create a new Telegram Bot API instance.
   No argument attempts to load the `bot-token` from the environment.
   1 argument will use the passed in `bot-token`."
  ([]
   (create (conf/get-token)))
  ([bot-token]
   (map->TBot {:bot-token bot-token})))

;; Make all Telegram functions available directly in this namespace.
(import-vars
 [telegrambot-lib.edit.core
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
 [telegrambot-lib.games.core
  send-game
  set-game-score
  set-game-score-inline
  get-game-high-scores
  get-game-high-scores-inline]
 [telegrambot-lib.inline.core
  answer-inline-query]
 [telegrambot-lib.methods.core
  call
  get-me
  send-message
  forward-message
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
  unban-chat-member
  restrict-chat-member
  promote-chat-member
  set-chat-administrator-custom-title
  set-chat-permissions
  export-chat-invite-link
  set-chat-photo
  delete-chat-photo
  set-chat-title
  set-chat-description
  pin-chat-message
  unpin-chat-message
  leave-chat
  get-chat
  get-chat-administrators
  get-chat-members-count
  get-chat-member
  set-chat-sticker-set
  delete-chat-sticker-set
  answer-callback-query
  set-my-commands
  get-my-commands]
 [telegrambot-lib.passport.core
  set-passport-data-errors]
 [telegrambot-lib.payments.core
  send-invoice
  answer-shipping-query-ok
  answer-shipping-query-error
  answer-precheckout-query-ok
  answer-precheckout-query-error]
 [telegrambot-lib.stickers.core
  send-sticker
  get-sticker-set
  upload-sticker-file
  create-new-sticker-set
  add-sticker-to-set
  set-sticker-position-in-set
  delete-sticker-from-set
  set-sticker-set-thumb]
 [telegrambot-lib.updates.core
  get-updates
  set-webhook
  delete-webhook
  get-webhook])
