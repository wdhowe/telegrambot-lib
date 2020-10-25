(ns telegrambot-lib.stickers.core
  "Telegram Stickers - function implementations.
   
   * https://core.telegram.org/bots/api#stickers

   Most functions are multi-arity with the following options:

   - Send all parameters in a 'content' map.
   - Send only the required parameters as simple values.
   - Send the required paraemters as simple values and then 'optional' parameters in a map."

  (:gen-class)
  (:require [telegrambot-lib.http :as http]))

(defn content-map?
  "Used throughout the multi-methods in order to check if content is a map or not."
  [_ content & _]
  (map? content))

(defn send-sticker
  "Use this method to send static .WEBP or animated .TGS stickers.
   On success, the sent Message is returned."
  ([this content]
   (http/request this "sendSticker" content))

  ([this chat_id sticker]
   (let [content {:chat_id chat_id
                  :sticker sticker}]
     (send-sticker this content)))

  ([this chat_id sticker & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :sticker sticker})]
     (send-sticker this content))))

(defmulti get-sticker-set
  "Use this method to get a sticker set.
   On success, a StickerSet object is returned."
  content-map?)

(defmethod get-sticker-set true
  [this content]
  (http/request this "getStickerSet" content))

(defmethod get-sticker-set false
  [this name]
  (let [content {:name name}]
    (get-sticker-set this content)))

(defn upload-sticker-file
  "Use this method to upload a .PNG file with a sticker for later use
   in createNewStickerSet and addStickerToSet methods (can be used multiple times).
   Returns the uploaded File on success."
  ([this content]
   (http/request this "uploadStickerFile" content))

  ([this user_id png_sticker]
   (let [content {:user_id user_id
                  :png_sticker png_sticker}]
     (upload-sticker-file this content))))

(defn create-new-sticker-set
  "Use this method to create a new sticker set owned by a user. The bot will
   be able to edit the sticker set thus created.
   You must use exactly one of the fields png_sticker or tgs_sticker.
   Returns True on success."
  ([this content]
   (http/request this "createNewStickerSet" content))

  ([this user_id name title emojis]
   (let [content {:user_id user_id
                  :name name
                  :title title
                  :emojis emojis}]
     (create-new-sticker-set this content)))

  ([this user_id name title emojis & optional]
   (let [content (merge (first optional)
                        {:user_id user_id
                         :name name
                         :title title
                         :emojis emojis})]
     (create-new-sticker-set this content))))

(defn add-sticker-to-set
  "Use this method to add a new sticker to a set created by the bot.
   You must use exactly one of the fields png_sticker or tgs_sticker.
   Animated stickers can be added to animated sticker sets and only to them.
   Animated sticker sets can have up to 50 stickers.
   Static sticker sets can have up to 120 stickers.
   Returns True on success."
  ([this content]
   (http/request this "addStickerToSet" content))

  ([this user_id name emojis]
   (let [content {:user_id user_id
                  :name name
                  :emojis emojis}]
     (add-sticker-to-set this content)))

  ([this user_id name emojis & optional]
   (let [content (merge (first optional)
                        {:user_id user_id
                         :name name
                         :emojis emojis})]
     (add-sticker-to-set this content))))

(defn set-sticker-position-in-set
  "Use this method to move a sticker in a set created by the bot to
   a specific position.
   Returns True on success."
  ([this content]
   (http/request this "setStickerPositionInSet" content))

  ([this sticker position]
   (let [content {:sticker sticker
                  :position position}]
     (set-sticker-position-in-set this content))))

(defmulti delete-sticker-from-set
  "Use this method to delete a sticker from a set created by the bot.
   Returns True on success."
  content-map?)

(defmethod delete-sticker-from-set true
  [this content]
  (http/request this "deleteStickerFromSet" content))

(defmethod delete-sticker-from-set false
  [this sticker]
  (let [content {:sticker sticker}]
    (delete-sticker-from-set this content)))

(defn set-sticker-set-thumb
  "Use this method to set the thumbnail of a sticker set.
   Animated thumbnails can be set for animated sticker sets only.
   Returns True on success."
  ([this content]
   (http/request this "setStickerSetThumb" content))

  ([this name user_id]
   (let [content {:name name
                  :user_id user_id}]
     (set-sticker-set-thumb this content)))

  ([this name user_id & optional]
   (let [content (merge (first optional)
                        {:name name
                         :user_id user_id})]
     (set-sticker-set-thumb this content))))

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