(ns telegrambot-lib.stickers.core
  "Telegram Stickers - function implementations.
   
   * https://core.telegram.org/bots/api#stickers"
  (:gen-class)
  (:require [telegrambot-lib.http :as http]))

(defn send-sticker
  "Use this method to send static .WEBP or animated .TGS stickers.
   On success, the sent Message is returned."
  [this content]
  (http/request this "sendSticker" content))

(defn get-sticker-set
  "Use this method to get a sticker set.
   On success, a StickerSet object is returned."
  [this content]
  (http/request this "getStickerSet" content))

(defn upload-sticker-file
  "Use this method to upload a .PNG file with a sticker for later use
   in createNewStickerSet and addStickerToSet methods (can be used multiple times).
   Returns the uploaded File on success."
  [this content]
  (http/request this "uploadStickerFile" content))

(defn create-new-sticker-set
  "Use this method to create a new sticker set owned by a user. The bot will
   be able to edit the sticker set thus created.
   You must use exactly one of the fields png_sticker or tgs_sticker.
   Returns True on success."
  [this content]
  (http/request this "createNewStickerSet" content))

(defn add-sticker-to-set
  "Use this method to add a new sticker to a set created by the bot.
   You must use exactly one of the fields png_sticker or tgs_sticker.
   Animated stickers can be added to animated sticker sets and only to them.
   Animated sticker sets can have up to 50 stickers.
   Static sticker sets can have up to 120 stickers.
   Returns True on success."
  [this content]
  (http/request this "addStickerToSet" content))

(defn set-sticker-position-in-set
  "Use this method to move a sticker in a set created by the bot to
   a specific position.
   Returns True on success."
  [this content]
  (http/request this "setStickerPositionInSet" content))

(defn delete-sticker-from-set
  "Use this method to delete a sticker from a set created by the bot.
   Returns True on success."
  [this content]
  (http/request this "deleteStickerFromSet" content))

(defn set-sticker-set-thumb
  "Use this method to set the thumbnail of a sticker set.
   Animated thumbnails can be set for animated sticker sets only.
   Returns True on success."
  [this content]
  (http/request this "setStickerSetThumb" content))

(def behavior
  "Map for extending the core TBot record with functions."
  {:send-sticker send-sticker
   :get-sticker-set get-sticker-set
   :upload-sticker-file upload-sticker-file
   :create-new-sticker-set create-new-sticker-set
   :add-sticker-to-set add-sticker-to-set
   :set-sticker-position-in-set set-sticker-position-in-set
   :delete-sticker-from-set delete-sticker-from-set
   :set-sticker-set-thumb set-sticker-set-thumb})