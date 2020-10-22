(ns telegrambot-lib.methods.params
  "Telegram Bot API Methods - parameter definitions."
  (:gen-class))

(defrecord send-message-params
           [;; Required
            chat_id ; target chat or username (@user)
            text ; message to send
            ;; Optional
            parse_mode ; entity parsing in message
            disable_web_page_preview ; disable link previews
            disable_notification ; send silently
            reply_to_message_id ; id of the original message
            reply_markup ; additional interface options
            ])

(defrecord forward-message-params
           [;; Required
            chat_id ; target chat or username (@user)
            from_chat_id ; id of chat from original message
            message_id ; message id in chat specified in 'from_chat_id'
            ;; Optional
            disable_notification ; send silently
            ])

(defrecord send-photo-params
           [;; Required
            chat_id ; target chat or username (@user)
            photo ; 'file_id' of photo to send that exists on Telegram servers or url
            ;; Optional
            caption ; photo caption
            parse_mode ; entity parsing in photo caption
            disable_notification ; send silently
            reply_to_message_id ; id of the original message
            reply_markup ; additional interface options
            ])

(defrecord send-audio-params
           [;; Required
            chat_id ; target chat or username (@user)
            audio ; 'file_id' of audio to send that exists on Telegram servers or url
            ;; Optional
            caption ; audio caption
            parse_mode ; entity parsing in audio caption
            duration ; duration of audio in seconds
            performer ; audio performer
            title ; audio track title
            thumb ; thumbnail of the file sent
            disable_notification ; send silently
            reply_to_message_id ; id of the original message
            reply_markup ; additional interface options
            ])

(defrecord send-document-params
           [;; Required
            chat_id ; target chat or username (@user)
            document ; 'file_id' of document to send that exists on Telegram servers or url
            ;; Optional
            caption ; document caption
            parse_mode ; entity parsing in document caption
            thumb ; thumbnail of the file sent
            disable_notification ; send silently
            reply_to_message_id ; id of the original message
            reply_markup ; additional interface options
            ])

(defrecord send-video-params
           [;; Required
            chat_id ; target chat or username (@user)
            video ; 'file_id' of video to send that exists on Telegram servers or url
            ;; Optional
            duration ; duration of video in seconds
            width
            height
            caption ; video caption
            parse_mode ; entity parsing in video caption
            thumb ; thumbnail of file sent
            supports_streaming ; true if uploaded video is ok for streaming
            disable_notification ; send silently
            reply_to_message_id ; id of the original message
            reply_markup ; additional interface options
            ])

(defrecord send-animation-params
           [;; Required
            chat_id ; target chat or username (@user)
            animation ; 'file_id' of animation to send that exists on Telegram servers or url
            ;; Optional
            duration ; duration of animation in seconds
            width
            height
            caption ; animation caption
            parse_mode ; entity parsing in animation caption
            thumb ; thumbnail of file sent
            disable_notification ; send silently
            reply_to_message_id ; id of the original message
            reply_markup ; additional interface options
            ])

(defrecord send-voice-params
           [;; Required
            chat_id ; target chat or username (@user)
            voice ; 'file_id' of audio file that exists on Telegram servers or url
            ;; Optional
            duration ; duration of voice message in seconds
            caption ; voice message caption
            parse_mode ; entity parsing in voice message caption
            disable_notification ; send silently
            reply_to_message_id ; id of the original message
            reply_markup ; additional interface options
            ])

(defrecord send-video-note-params
           [;; Required
            chat_id ; target chat or username (@user)
            video_note ; 'file_id' of video note that exists on Telegram servers or url
            ;; Optional
            duration ; duration of video in seconds
            length ; video width and height
            thumb ; thumbnail of the file sent
            disable_notification ; send silently
            reply_to_message_id ; id of the original message
            reply_markup ; additional interface options
            ])

(defrecord send-media-group-params
           [;; Required
            chat_id ; target chat or username (@user)
            media ; json array describing photos/videos to be sent
            ;; Optional
            disable_notification ; send silently
            reply_to_message_id ; id of the original message
            ])

(defrecord send-location-params
           [;; Required
            chat_id ; target chat or username (@user)
            latitude ; lat of location
            longitude ; long of location
            ;; Optional
            live_period ; seconds for which location will be updated (60-86400)
            disable_notification ; send silently
            reply_to_message_id ; id of the original message
            reply_markup ; additional interface options
            ])

(defrecord edit-message-live-location-params
           [;; Required
            chat_id ; target chat or username (@user)
            message_id ; id of the message to edit
            latitude ; lat of new location
            longitude ; long of new location
            ;; Optional
            reply_markup ; additional interface options
            ])

(defrecord edit-message-live-location-inline-params
           [;; Required
            inline_message_id ; id of the inline message to edit
            latitude ; lat of new location
            longitude ; long of new location
            ;; Optional
            reply_markup ; additional interface options
            ])

(defrecord stop-message-live-location-params
           [;; Required
            chat_id ; target chat or username (@user)
            message_id ; id of the message to edit
            ;; Optional
            reply_markup ; additional interface options
            ])

(defrecord stop-message-live-location-inline-params
           [;; Required
            inline_message_id ; id of the inline message to edit
            ;; Optional
            reply_markup ; additional interface options
            ])

(defrecord send-venue-params
           [;; Required
            chat_id ; target chat or username (@user)
            latitude ; lat of venue
            longitude ; long of venue
            title ; name of venue
            address ; address of venue
            ;; Optional
            foursquare_id ; foursquare id of venue
            foursquare_type ; foursquare type of venue
            disable_notification ; send silently
            reply_to_message_id ; id of the original message
            reply_markup ; additional interface options
            ])

(defrecord send-contact-params
           [;; Required
            chat_id ; target chat or username (@user)
            phone_number ; contact phone number
            first_name
            ;; Optional
            last_name
            vcard ; 'vCard' formatted additional data
            disable_notification ; send silently
            reply_to_message_id ; id of the original message
            reply_markup ; additional interface options
            ])

(defrecord send-poll-params
           [;; Required
            chat_id ; target chat or username (@user)
            question ; poll question
            options ; array/list of answer options
            ;; Optional
            is_anonymous ; true if poll is anonymous
            type ; 'quiz' or 'regular' (default: regular)
            allows_multiple_answers ; true poll allows multiple answers
            correct_option_id ; 0-based id of correct answer. Required for quiz mode
            explanation ; shown when user chooses incorrect answer
            explanation_parse_mode ; parsing entities in explanation
            open_period ; 5-600 seconds - time poll will be active
            close_date ; unix timestamp when poll will be auto closed (5-600 secs in future)
            is_closed ; true if poll needs to be immediately closed
            disable_notification ; send silently
            reply_to_message_id ; id of the original message
            reply_markup ; additional interface options
            ])

(defrecord send-dice-params
           [;; Required
            chat_id ; target chat or username (@user)
            ;; Optional
            emoji ; image for dice animation (default: dice)
            disable_notification ; send silently
            reply_to_message_id ; id of the original message
            reply_markup ; additional interface options
            ])

(defrecord send-chat-action-params
           [;; Required
            chat_id ; target chat or username (@user)
            action ; type of action to broadcast (typing, upload_photo, record_video, upload_video,
                   ; record_audio, upload_audio, upload_document, find_location,
                   ; record_video_note, upload_video_note)
            ])

(defrecord get-user-profile-photos-params
           [;; Required
            user_id ; id of target user
            ;; Optional
            offset ; number of first photo returned
            limit ; limit number of photos retrieved (1-100, default: 100)
            ])

(defrecord get-file-params
           [;; Required
            file_id ; file id to get info about
            ])

(defrecord kick-chat-member-params
           [;; Required
            chat_id ; target chat or username (@user)
            user_id ; id of target user
            ;; Optional
            until_date ; unix time when user is unbanned. 30 seconds - 366 days.
                       ; if less or more, user is banned forever.
            ])

(defrecord unban-chat-member-params
           [;; Required
            chat_id ; target chat or username (@user)
            user_id ; id of target user
            ])

(defrecord restrict-chat-member-params
           [;; Required
            chat_id ; target chat or username (@user)
            user_id ; id of target user
            permissions ; json object 'ChatPermissions' for new user permissions
            ;; Optional
            until_date ; unix time when user is unbanned. 30 seconds - 366 days.
                       ; if less or more, user is banned forever.
            ])

(defrecord promote-chat-member-params
           [;; Required
            chat_id ; target chat or username (@user)
            user_id ; id of target user
            ;; Optional
            can_change_info ; true if admin can change chat title, photo, other settings
            can_post_messages ; true if admin can create channel posts
            can_edit_messages ; true if admin can edit messages of other users, pin messages
            can_delete_messages ; true if admin can delete messages of other users
            can_invite_users ; true if admin can invite new users to chat
            can_restrict_members ; true if admin can restrict, ban, unban members
            can_pin_messages ; true if admin can pin messages
            can_promote_members ; true if admin can add new admins
            ])

(defrecord set-chat-administrator-custom-title-params
           [;; Required
            chat_id ; target chat or username (@user)
            user_id ; id of target user
            custom_title ; new custom title for the admin
            ])

(defrecord set-chat-permissions-params
           [;; Required
            chat_id ; target chat or username (@user)
            permissions ; new default chat permissions
            ])

(defrecord export-chat-invite-link-params
           [;; Required
            chat_id ; target chat or username (@user)
            ])

(defrecord set-chat-photo-params
           [;; Required
            chat_id ; target chat or username (@user)
            photo ; new chat photo
            ])

(defrecord delete-chat-photo-params
           [;; Required
            chat_id ; target chat or username (@user)
            ])

(defrecord set-chat-title-params
           [;; Required
            chat_id ; target chat or username (@user)
            title ; new chat title
            ])

(defrecord set-chat-description-params
           [;; Required
            chat_id ; target chat or username (@user)
            description ; new chat description
            ])

(defrecord pin-chat-message-params
           [;; Required
            chat_id ; target chat or username (@user)
            message_id ; id of message to pin
            ;; Optional
            disable_notification ; true to pin silently. default true in channels.
            ])

(defrecord unpin-chat-message-params
           [;; Required
            chat_id ; target chat or username (@user)
            ])

(defrecord leave-chat-params
           [;; Required
            chat_id ; target chat or username (@user)
            ])

(defrecord get-chat-params
           [;; Required
            chat_id ; target chat or username (@user)
            ])

(defrecord get-chat-administrators-params
           [;; Required
            chat_id ; target chat or username (@user)
            ])

(defrecord get-chat-members-count-params
           [;; Required
            chat_id ; target chat or username (@user)
            ])

(defrecord get-chat-member-params
           [;; Required
            chat_id ; target chat or username (@user)
            user_id ; id of target user
            ])

(defrecord set-chat-sticker-set-params
           [;; Required
            chat_id ; target chat or username (@user)
            sticker_set_name ; name of sticker set
            ])

(defrecord delete-chat-sticker-set-params
           [;; Required
            chat_id ; target chat or username (@user)
            ])

(defrecord answer-callback-query-params
           [;; Required
            chat_id ; target chat or username (@user)
            ;; Optional
            text ; text of the notification
            show_alert ; if true, show alert instead of a notification
            url ; url that will be opened by the user's client.
            cache_time ; max time in seconds for caching callback query. (default: 0)
            ])

(defrecord set-my-commands-params
           [;; Required
            commands ; json array/list of 'BotCommand'
            ])
