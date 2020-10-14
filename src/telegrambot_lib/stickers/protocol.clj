(ns telegrambot-lib.stickers.protocol
  "Telegram Stickers - protocol definitions.
   
   * https://core.telegram.org/bots/api#stickers")

(defprotocol Stickers
  "The following methods allow your bot to handle stickers and sticker sets."

  (send-sticker [this content]
    "Use this method to send static .WEBP or animated .TGS stickers.
     On success, the sent Message is returned.")

  (get-sticker-set [this content]
    "Use this method to get a sticker set.
     On success, a StickerSet object is returned.")

  (upload-sticker-file [this content]
    "Use this method to upload a .PNG file with a sticker for later use
     in createNewStickerSet and addStickerToSet methods (can be used multiple times).
     Returns the uploaded File on success.")

  (create-new-sticker-set [this content]
    "Use this method to create a new sticker set owned by a user. The bot will
     be able to edit the sticker set thus created.
     You must use exactly one of the fields png_sticker or tgs_sticker.
     Returns True on success.")

  (add-sticker-to-set [this content]
    "Use this method to add a new sticker to a set created by the bot.
     You must use exactly one of the fields png_sticker or tgs_sticker.
     Animated stickers can be added to animated sticker sets and only to them.
     Animated sticker sets can have up to 50 stickers.
     Static sticker sets can have up to 120 stickers.
     Returns True on success.")

  (set-sticker-position-in-set [this content]
    "Use this method to move a sticker in a set created by the bot to
     a specific position.
     Returns True on success.")

  (delete-sticker-from-set [this content]
    "Use this method to delete a sticker from a set created by the bot.
     Returns True on success.")

  (set-sticker-set-thumb [this content]
    "Use this method to set the thumbnail of a sticker set.
     Animated thumbnails can be set for animated sticker sets only.
     Returns True on success."))
