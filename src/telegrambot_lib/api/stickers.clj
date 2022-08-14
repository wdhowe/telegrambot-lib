(ns telegrambot-lib.api.stickers
  "Telegram Stickers - function implementations.
   - <https://core.telegram.org/bots/api#stickers>

   Most functions are multi-arity with the following options:
   - Send all parameters in a 'content' map.
   - Send only the required parameters as simple values.
   - Send the required paraemters as simple values and then 'optional' parameters in a map."
  (:gen-class)
  (:require [telegrambot-lib.http :as http]
            [telegrambot-lib.predicates :refer [content-map?]]))

(defn send-sticker
  "Use this method to send static .WEBP or animated .TGS stickers.
   On success, the sent Message is returned.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - sticker ; sticker to send, file_id for existing on Telegram servers

   Optional
   - disable_notification ; true to send message silently
   - protect_content ; protect content from forwarding/saving
   - reply_to_message_id ; id of original message if reply
   - allow_sending_without_reply ; true to send message even if replied-to message is not found
   - reply_markup ; additional interface options"
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
   On success, a StickerSet object is returned.

   Required
   - this ; a bot instance
   - name ; name of the sticker set"
  content-map?)

(defmethod get-sticker-set true
  [this content]
  (http/request this "getStickerSet" content))

(defmethod get-sticker-set false
  [this name]
  (let [content {:name name}]
    (get-sticker-set this content)))

(defmulti get-custom-emoji-stickers
  "Use this method to get information about custom emoji stickers by their identifiers.
   Returns an Array of Sticker objects.

   Required
   - this ; a bot instance
   - custom_emoji_ids ; a list of emoji identifiers"
  content-map?)

(defmethod get-custom-emoji-stickers true
  [this content]
  (http/request this "getCustomEmojiStickers" content))

(defmethod get-custom-emoji-stickers false
  [this custom_emoji_ids]
  (let [content {:custom_emoji_ids custom_emoji_ids}]
    (get-custom-emoji-stickers this content)))

(defn upload-sticker-file
  "Use this method to upload a .PNG file with a sticker for later use
   in createNewStickerSet and addStickerToSet methods (can be used multiple times).
   Returns the uploaded File on success.

   Required
   - this ; a bot instance
   - user_id ; id of sticker file owner
   - png_sticker ; PNG image with the sticker"
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
   Returns True on success.

   Required
   - this ; a bot instance
   - user_id ; id of created sticker set owner
   - name ; short name of sticker set for use in URLs
   - title ; sticker set title
   - emojis ; one or more emoji corresponding to the sticker

   Optional
   - png_sticker ; PNG image with the sticker
   - tgs_sticker ; TGS animation with the sticker
   - webm_sticker ; WEBM video with the sticker, uploaded using multipart/form-data
   - sticker_type ; regular(default) or mask
   - mask_position ; json object for where the mask should be placed on faces"
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
   Returns True on success.

   Required
   - this ; a bot instance
   - user_id ; id of sticker set owner
   - name ; sticker set name
   - emojis ; one or more emoji corresponding to the sticker

   Optional
   - png_sticker ; PNG image with the sticker
   - tgs_sticker ; TGS animation with the sticker
   - webm_sticker ; WEBM video with the sticker, uploaded using multipart/form-data
   - mask_position ; json object for where the mask should be placed on faces"
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
   Returns True on success.

   Required
   - this ; a bot instance
   - sticker ; file id of the sticker
   - position ; new sticker position in the set"
  ([this content]
   (http/request this "setStickerPositionInSet" content))

  ([this sticker position]
   (let [content {:sticker sticker
                  :position position}]
     (set-sticker-position-in-set this content))))

(defmulti delete-sticker-from-set
  "Use this method to delete a sticker from a set created by the bot.
   Returns True on success.

   Required
   - this ; a bot instance
   - sticker ; file id of the sticker"
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
   Returns True on success.

   Required
   - this ; a bot instance
   - name ; sticker set name
   - user_id ; id of the sticker set owner

   Optional
   - thumb ; PNG image with the thumbnail"
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
