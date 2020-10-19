(ns telegrambot-lib.stickers.params
  "Telegram Stickers - parameter definitions."
  (:gen-class))

(defrecord send-sticker-params
           [;; Required
            chat_id ; target chat or username (@user)
            sticker ; sticker to send, file_id for existing on Telegram servers
            ;; Optional
            disable_notification ; true to send message silently
            reply_to_message_id ; id of original message if reply
            reply_markup ; additional interface options
            ])

(defrecord get-sticker-set-params
           [;; Required
            name ; name of the sticker set
            ])

(defrecord upload-sticker-file-params
           [;; Required
            user_id ; id of sticker file owner
            png_sticker ; PNG image with the sticker
            ])

(defrecord create-new-sticker-set-params
           [;; Required
            user_id ; id of created sticker set owner
            name ; short name of sticker set for use in URLs
            title ; sticker set title
            emojis ; one or more emoji corresponding to the sticker
            ;; Optional
            png_sticker ; PNG image with the sticker
            tgs_sticker ; TGS animation with the sticker
            contains_masks ; true to create a set of mask stickers
            mask_position ; json object for where the mask should be placed on faces
            ])

(defrecord add-sticker-to-set-params
           [;; Required
            user_id ; id of sticker set owner
            name ; sticker set name
            emojis ; one or more emoji corresponding to the sticker
            ;; Optional
            png_sticker ; PNG image with the sticker
            tgs_sticker ; TGS animation with the sticker
            mask_position ; json object for where the mask should be placed on faces
            ])

(defrecord set-sticker-position-in-set-params
           [;; Required
            sticker ; file id of the sticker
            position ; new sticker position in the set
            ])

(defrecord delete-sticker-from-set-params
           [;; Required
            sticker ; file id of the sticker
            ])

(defrecord set-sticker-set-thumb-params
           [;; Required
            name ; sticker set name
            user_id ; id of the sticker set owner
            ;; Optional
            thumb ; PNG image with the thumbnail
            ])
