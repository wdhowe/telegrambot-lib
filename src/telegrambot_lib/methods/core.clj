(ns telegrambot-lib.methods.core
  "Telegram Bot API Methods - function implementations.

   * https://core.telegram.org/bots/api#available-methods

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

(defn call
  "A generic function to call any `endpoint` with optional `content`.
   Provided in the case where methods are added to the API and
   are not available in this library yet."
  ([this endpoint]
   (call this endpoint nil))

  ([this endpoint content]
   (http/request this endpoint content)))

(defn get-me
  "A simple method for testing your bot's auth token.
   Requires no parameters.
   Returns basic information about the bot in form of a User object."
  [this]
  (http/request this "getMe"))

(defn log-out
  "Use this method to log out from the cloud Bot API server before launching
   the bot locally. You must log out the bot before running it locally,
   otherwise there is no guarantee that the bot will receive updates.
   Returns True on success. Requires no parameters."
  [this]
  (http/request this "logOut"))

(defn close
  "Use this method to close the bot instance before moving it from one local
   server to another. You need to delete the webhook before calling this method
   to ensure that the bot isn't launched again after server restart. The method
   will return error 429 in the first 10 minutes after the bot is launched.
   Returns True on success. Requires no parameters."
  [this]
  (http/request this "close"))

(defn send-message
  "Use this method to send text messages.
   On success, the sent Message is returned.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   text ; message to send
   ;; Optional
   parse_mode ; entity parsing in message
   entities ; list of MessageEntity - can use instead of parse_mode
   disable_web_page_preview ; disable link previews
   disable_notification ; send silently
   reply_to_message_id ; id of the original message
   reply_markup ; additional interface options"
  ([this content]
   (http/request this "sendMessage" content))

  ([this chat_id text]
   (let [content {:chat_id chat_id
                  :text text}]
     (send-message this content)))

  ([this chat_id text & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :text text})]
     (send-message this content))))

(defn forward-message
  "Use this method to forward messages of any kind.
   On success, the sent Message is returned.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   from_chat_id ; id of chat from original message
   message_id ; message id in chat specified in 'from_chat_id'
   ;; Optional
   disable_notification ; send silently"
  ([this content]
   (http/request this "forwardMessage" content))

  ([this chat_id from_chat_id message_id]
   (let [content {:chat_id chat_id
                  :from_chat_id from_chat_id
                  :message_id message_id}]
     (forward-message this content)))

  ([this chat_id from_chat_id message_id & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :from_chat_id from_chat_id
                         :message_id message_id})]
     (forward-message this content))))

(defn copy-message
  "Use this method to copy messages of any kind.
   The method is analogous to the method forwardMessages, but the copied
   message doesn't have a link to the original message.
   Returns the MessageId of the sent message on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   from_chat_id ; id of chat from original message
   message_id ; message id in chat specified in 'from_chat_id'
   ;; Optional
   disable_notification ; send silently"
  ([this content]
   (http/request this "copyMessage" content))

  ([this chat_id from_chat_id message_id]
   (let [content {:chat_id chat_id
                  :from_chat_id from_chat_id
                  :message_id message_id}]
     (copy-message this content)))

  ([this chat_id from_chat_id message_id & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :from_chat_id from_chat_id
                         :message_id message_id})]
     (copy-message this content))))

(defn send-photo
  "Use this method to send photos.
   On success, the sent Message is returned.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   photo ; 'file_id' of photo to send that exists on Telegram servers or url
   ;; Optional
   caption ; photo caption
   parse_mode ; entity parsing in photo caption
   caption_entities ; list of MessageEntity - can use instead of parse_mode
   disable_notification ; send silently
   reply_to_message_id ; id of the original message
   reply_markup ; additional interface options"
  ([this content]
   (http/request this "sendPhoto" content))

  ([this chat_id photo]
   (let [content {:chat_id chat_id
                  :photo photo}]
     (send-photo this content)))

  ([this chat_id photo & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :photo photo})]
     (send-photo this content))))

(defn send-audio
  "Use this method to send audio files, if you want Telegram clients to
   display them in the music player. Your audio must be in the .MP3 or
   .M4A format.
   On success, the sent Message is returned.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   audio ; 'file_id' of audio to send that exists on Telegram servers or url
   ;; Optional
   caption ; audio caption
   parse_mode ; entity parsing in audio caption
   caption_entities ; list of MessageEntity - can use instead of parse_mode
   duration ; duration of audio in seconds
   performer ; audio performer
   title ; audio track title
   thumb ; thumbnail of the file sent
   disable_notification ; send silently
   reply_to_message_id ; id of the original message
   reply_markup ; additional interface options"
  ([this content]
   (http/request this "sendAudio" content))

  ([this chat_id audio]
   (let [content {:chat_id chat_id
                  :audio audio}]
     (send-audio this content)))

  ([this chat_id audio & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :audio audio})]
     (send-audio this content))))

(defn send-document
  "Use this method to send general files.
   On success, the sent Message is returned.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   document ; 'file_id' of document to send that exists on Telegram servers or url
   ;; Optional
   caption ; document caption
   parse_mode ; entity parsing in document caption
   caption_entities ; list of MessageEntity - can use instead of parse_mode
   thumb ; thumbnail of the file sent
   disable_content_type_detection ; disable auto content type detection for files uploaded
   disable_notification ; send silently
   reply_to_message_id ; id of the original message
   reply_markup ; additional interface options"
  ([this content]
   (http/request this "sendDocument" content))

  ([this chat_id document]
   (let [content {:chat_id chat_id
                  :document document}]
     (send-document this content)))

  ([this chat_id document & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :document document})]
     (send-document this content))))

(defn send-video
  "Use tffhis method to send video files, Telegram clients support mp4 videos
   other formats may be sent as Document).
   On success, the sent Message is returned.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   video ; 'file_id' of video to send that exists on Telegram servers or url
   ;; Optional
   duration ; duration of video in seconds
   width
   height
   caption ; video caption
   parse_mode ; entity parsing in video caption
   caption_entities ; list of MessageEntity - can use instead of parse_mode
   thumb ; thumbnail of file sent
   supports_streaming ; true if uploaded video is ok for streaming
   disable_notification ; send silently
   reply_to_message_id ; id of the original message
   reply_markup ; additional interface options"
  ([this content]
   (http/request this "sendVideo" content))

  ([this chat_id video]
   (let [content {:chat_id chat_id
                  :video video}]
     (send-video this content)))

  ([this chat_id video & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :video video})]
     (send-video this content))))

(defn send-animation
  "Use this method to send animation files (GIF or H.264/MPEG-4 AVC video
   without sound).
   On success, the sent Message is rbeturned.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   animation ; 'file_id' of animation to send that exists on Telegram servers or url
   ;; Optional
   duration ; duration of animation in seconds
   width
   height
   caption ; animation caption
   parse_mode ; entity parsing in animation caption
   caption_entities ; list of MessageEntity - can use instead of parse_mode
   thumb ; thumbnail of file sent
   disable_notification ; send silently
   reply_to_message_id ; id of the original message
   reply_markup ; additional interface options"
  ([this content]
   (http/request this "sendAnimation" content))

  ([this chat_id animation]
   (let [content {:chat_id chat_id
                  :animation animation}]
     (send-animation this content)))

  ([this chat_id animation & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :animation animation})]
     (send-animation this content))))

(defn send-voice
  "Use this method to send audio files, if you want Telegram clients to
   display the file as a playable voice message. For this to work, your
   audio must be in an .OGG file encoded with OPUS (other formats may be
   sent as Audio or Document).
   On success, the sent Message is returned.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   voice ; 'file_id' of audio file that exists on Telegram servers or url
   ;; Optional
   duration ; duration of voice message in seconds
   caption ; voice message caption
   parse_mode ; entity parsing in voice message caption
   caption_entities ; list of MessageEntity - can use instead of parse_mode
   disable_notification ; send silently
   reply_to_message_id ; id of the original message
   reply_markup ; additional interface options"
  ([this content]
   (http/request this "sendVoice" content))

  ([this chat_id voice]
   (let [content {:chat_id chat_id
                  :voice voice}]
     (send-voice this content)))

  ([this chat_id voice & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :voice voice})]
     (send-voice this content))))

(defn send-video-note
  "Use this method to send video messages.
     On success, the sent Message is returned.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   video_note ; 'file_id' of video note that exists on Telegram servers or url
   ;; Optional
   duration ; duration of video in seconds
   length ; video width and height
   thumb ; thumbnail of the file sent
   disable_notification ; send silently
   reply_to_message_id ; id of the original message
   reply_markup ; additional interface options"
  ([this content]
   (http/request this "sendVideoNote" content))

  ([this chat_id video_note]
   (let [content {:chat_id chat_id
                  :video_note video_note}]
     (send-video-note this content)))

  ([this chat_id video_note & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :video_note video_note})]
     (send-video-note this content))))

(defn send-media-group
  "Use this method to send a group of photos or videos as an album.
   On success, an array of the sent Messages is returned.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   media ; json array describing photos/videos to be sent
   ;; Optional
   disable_notification ; send silently
   reply_to_message_id ; id of the original message"
  ([this content]
   (http/request this "sendMediaGroup" content))

  ([this chat_id media]
   (let [content {:chat_id chat_id
                  :media media}]
     (send-media-group this content)))

  ([this chat_id media & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :media media})]
     (send-media-group this content))))

(defn send-location
  "Use this method to send point on the map.
   On success, the sent Message is returned.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   latitude ; lat of location
   longitude ; long of location
   ;; Optional
   horizontal_accuracy ; 1-1500 meters radius of uncertainty
   live_period ; seconds for which location will be updated (60-86400)
   heading ; 1-360 degrees direction user is moving
   proximity_alert_radius ; 1-100000 meters max distance for proximity alerts
   disable_notification ; send silently
   reply_to_message_id ; id of the original message
   reply_markup ; additional interface options"
  ([this content]
   (http/request this "sendLocation" content))

  ([this chat_id latitude longitude]
   (let [content {:chat_id chat_id
                  :latitude latitude
                  :longitude longitude}]
     (send-location this content)))

  ([this chat_id latitude longitude & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :latitude latitude
                         :longitude longitude})]
     (send-location this content))))

(defn edit-message-live-location
  "Use this method to edit live location messages.
   A location can be edited until its live_period expires or editing is
   explicitly disabled by a call to stopMessageLiveLocation.
   On success, if the edited message was sent by the bot, the edited
   Message is returned, otherwise True is returned.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   message_id ; id of the message to edit
   latitude ; lat of new location
   longitude ; long of new location
   ;; Optional
   horizontal_accuracy ; 1-1500 meters radius of uncertainty
   heading ; 1-360 degrees direction user is moving
   proximity_alert_radius ; 1-100000 meters max distance for proximity alerts
   reply_markup ; additional interface options"
  ([this content]
   (http/request this "editMessageLiveLocation" content))

  ([this chat_id message_id latitude longitude]
   (let [content {:chat_id chat_id
                  :message_id message_id
                  :latitude latitude
                  :longitude longitude}]
     (edit-message-live-location this content)))

  ([this chat_id message_id latitude longitude & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :message_id message_id
                         :latitude latitude
                         :longitude longitude})]
     (edit-message-live-location this content))))

(defn edit-message-live-location-inline
  "Use this method to edit live location inline messages.
   A location can be edited until its live_period expires or editing is
   explicitly disabled by a call to stopMessageLiveLocation.
   On success, if the edited message was sent by the bot, the edited
   Message is returned, otherwise True is returned.
   Parameters
   ;; Required
   inline_message_id ; id of the inline message to edit
   latitude ; lat of new location
   longitude ; long of new location
   ;; Optional
   reply_markup ; additional interface options"
  ([this content]
   (http/request this "editMessageLiveLocation" content))

  ([this inline_message_id latitude longitude]
   (let [content {:inline_message_id inline_message_id
                  :latitude latitude
                  :longitude longitude}]
     (edit-message-live-location-inline this content)))

  ([this inline_message_id latitude longitude & optional]
   (let [content (merge (first optional)
                        {:inline_message_id inline_message_id
                         :latitude latitude
                         :longitude longitude})]
     (edit-message-live-location-inline this content))))

(defn stop-message-live-location
  "Use this method to stop updating a live location message before live_period expires.
   On success, if the message was sent by the bot, the sent
   Message is returned, otherwise True is returned.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   message_id ; id of the message to edit
   ;; Optional
   reply_markup ; additional interface options"
  ([this content]
   (http/request this "stopMessageLiveLocation" content))

  ([this chat_id message_id]
   (let [content {:chat_id chat_id
                  :message_id message_id}]
     (stop-message-live-location this content)))

  ([this chat_id message_id & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :message_id message_id})]
     (stop-message-live-location this content))))

(defmulti stop-message-live-location-inline
  "Use this method to stop updating a live location inline message before
   live_period expires.
   On success, if the message was sent by the bot, the sent
   Message is returned, otherwise True is returned.
   Parameters
   ;; Required
   inline_message_id ; id of the inline message to edit
   ;; Optional
   reply_markup ; additional interface options"
  content-map?)

(defmethod stop-message-live-location-inline true
  [this content]
  (http/request this "stopMessageLiveLocation" content))

(defmethod stop-message-live-location-inline false
  ([this inline_message_id]
   (let [content {:inline_message_id inline_message_id}]
     (stop-message-live-location-inline this content)))

  ([this inline_message_id & optional]
   (let [content (merge (first optional)
                        {:inline_message_id inline_message_id})]
     (stop-message-live-location-inline this content))))

(defn send-venue
  "Use this method to send information about a venue.
   On success, the sent Message is returned.
   Parameters
   ;; Required
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
   reply_markup ; additional interface options"
  ([this content]
   (http/request this "sendVenue" content))

  ([this chat_id latitude longitude title address]
   (let [content {:chat_id chat_id
                  :latitude latitude
                  :longitude longitude
                  :title title
                  :address address}]
     (send-venue this content)))

  ([this chat_id latitude longitude title address & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :latitude latitude
                         :longitude longitude
                         :title title
                         :address address})]
     (send-venue this content))))

(defn send-contact
  "Use this method to send phone contacts.
   On success, the sent Message is returned.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   phone_number ; contact phone number
   first_name
   ;; Optional
   last_name
   vcard ; 'vCard' formatted additional data
   disable_notification ; send silently
   reply_to_message_id ; id of the original message
   reply_markup ; additional interface options"
  ([this content]
   (http/request this "sendContact" content))

  ([this chat_id phone_number first_name]
   (let [content {:chat_id chat_id
                  :phone_number phone_number
                  :first_name first_name}]
     (send-contact this content)))

  ([this chat_id phone_number first_name last_name]
   (let [content {:chat_id chat_id
                  :phone_number phone_number
                  :first_name first_name
                  :last_name last_name}]
     (send-contact this content)))

  ([this chat_id phone_number first_name last_name & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :phone_number phone_number
                         :first_name first_name
                         :last_name last_name})]
     (send-contact this content))))

(defn send-poll
  "Use this method to send a native poll.
   On success, the sent Message is returned.
   Parameters
   ;; Required
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
   explanation_entities ; list of MessageEntity - can use instead of parse_mode
   open_period ; 5-600 seconds - time poll will be active
   close_date ; unix timestamp when poll will be auto closed (5-600 secs in future)
   is_closed ; true if poll needs to be immediately closed
   disable_notification ; send silently
   reply_to_message_id ; id of the original message
   reply_markup ; additional interface options"
  ([this content]
   (http/request this "sendPoll" content))

  ([this chat_id question options]
   (let [content {:chat_id chat_id
                  :question question
                  :options options}]
     (send-poll this content)))

  ([this chat_id question options & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :question question
                         :options options})]
     (send-poll this content))))


(defmulti send-dice
  "Use this method to send an animated emoji that will display a random value.
   On success, the sent Message is returned.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   ;; Optional
   emoji ; image for dice animation (default: dice)
   disable_notification ; send silently
   reply_to_message_id ; id of the original message
   reply_markup ; additional interface options"
  content-map?)

(defmethod send-dice true
  [this content]
  (http/request this "sendDice" content))

(defmethod send-dice false
  ([this chat_id]
   (let [content {:chat_id chat_id}]
     (send-dice this content)))

  ([this chat_id & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id})]
     (send-dice this content))))

(defn send-chat-action
  "Use this method when you need to tell the user that something is happening
   on the bot's side. The status is set for 5 seconds or less (when a message
   arrives from your bot, Telegram clients clear its typing status).
   Returns True on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   action ; type of action to broadcast (typing, upload_photo, record_video, upload_video,
          ; record_audio, upload_audio, upload_document, find_location,
          ; record_video_note, upload_video_note)"
  ([this content]
   (http/request this "sendChatAction" content))

  ([this chat_id action]
   (let [content {:chat_id chat_id
                  :action action}]
     (send-chat-action this content)))

  ([this chat_id action & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :action action})]
     (send-chat-action this content))))

(defmulti get-user-profile-photos
  "Use this method to get a list of profile pictures for a user.
   Returns a UserProfilePhotos object.
   Parameters
   ;; Required
   user_id ; id of target user
   ;; Optional
   offset ; number of first photo returned
   limit ; limit number of photos retrieved (1-100, default: 100)"
  content-map?)

(defmethod get-user-profile-photos true
  [this content]
  (http/request this "getUserProfilePhotos" content))

(defmethod get-user-profile-photos false
  ([this user_id]
   (let [content {:user_id user_id}]
     (get-user-profile-photos this content)))

  ([this user_id & optional]
   (let [content (merge (first optional)
                        {:user_id user_id})]
     (get-user-profile-photos this content))))

(defmulti get-file
  "Use this method to get basic info about a file and prepare it for downloading.
   For the moment, bots can download files of up to 20MB in size.
   On success, a File object is returned. The file can then be downloaded via
   the link https://api.telegram.org/file/bot<token>/<file_path>,
   where <file_path> is taken from the response.
   It is guaranteed that the link will be valid for at least 1 hour.
   When the link expires, a new one can be requested by calling getFile again.
   Parameters
   ;; Required
   file_id ; file id to get info about"
  content-map?)

(defmethod get-file true
  [this content]
  (http/request this "getFile" content))

(defmethod get-file false
  [this file_id]
  (let [content {:file_id file_id}]
    (get-file this content)))

(defn kick-chat-member
  "Use this method to kick a user from a group, a supergroup or a channel.
   In the case of supergroups and channels, the user will not be able to
   return to the group on their own using invite links, etc., unless unbanned first.
   The bot must be an administrator in the chat for this to work and must have the
   appropriate admin rights.
   Returns True on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   user_id ; id of target user
   ;; Optional
   until_date ; unix time when user is unbanned. 30 seconds - 366 days.
              ; if less or more, user is banned forever."
  ([this content]
   (http/request this "kickChatMember" content))

  ([this chat_id user_id]
   (let [content {:chat_id chat_id
                  :user_id user_id}]
     (kick-chat-member this content)))

  ([this chat_id user_id & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :user_id user_id})]
     (kick-chat-member this content))))

(defn unban-chat-member
  "Use this method to unban a previously kicked user in a supergroup or channel.
   The user will not return to the group or channel automatically, but will be able
   to join via link, etc. The bot must be an administrator for this to work.
   Returns True on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   user_id ; id of target user
   ;; Optional
   only_if_banned ; Do nothing if the user is not banned"
  ([this content]
   (http/request this "unbanChatMember" content))

  ([this chat_id user_id]
   (let [content {:chat_id chat_id
                  :user_id user_id}]
     (unban-chat-member this content)))

  ([this chat_id user_id & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :user_id user_id})]
     (unban-chat-member this content))))

(defn restrict-chat-member
  "Use this method to restrict a user in a supergroup.
   The bot must be an administrator in the supergroup for this to work and
   must have the appropriate admin rights. Pass True for all permissions to lift
   restrictions from a user.
   Returns True on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   user_id ; id of target user
   permissions ; json object 'ChatPermissions' for new user permissions
   ;; Optional
   until_date ; unix time when user is unbanned. 30 seconds - 366 days.
              ; if less or more, user is banned forever."
  ([this content]
   (http/request this "restrictChatMember" content))

  ([this chat_id user_id permissions]
   (let [content {:chat_id chat_id
                  :user_id user_id
                  :permissions permissions}]
     (restrict-chat-member this content)))

  ([this chat_id user_id permissions & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :user_id user_id
                         :permissions permissions})]
     (restrict-chat-member this content))))

(defn promote-chat-member
  "Use this method to promote or demote a user in a supergroup or a channel.
   The bot must be an administrator in the chat for this to work and must
   have the appropriate admin rights.
   Pass False for all boolean parameters to demote a user.
   Returns True on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   user_id ; id of target user
   ;; Optional
   is_anonymous ; true if admin presence in chat is hidden
   can_change_info ; true if admin can change chat title, photo, other settings
   can_post_messages ; true if admin can create channel posts
   can_edit_messages ; true if admin can edit messages of other users, pin messages
   can_delete_messages ; true if admin can delete messages of other users
   can_invite_users ; true if admin can invite new users to chat
   can_restrict_members ; true if admin can restrict, ban, unban members
   can_pin_messages ; true if admin can pin messages
   can_promote_members ; true if admin can add new admins"
  ([this content]
   (http/request this "promoteChatMember" content))

  ([this chat_id user_id]
   (let [content {:chat_id chat_id
                  :user_id user_id}]
     (promote-chat-member this content)))

  ([this chat_id user_id & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :user_id user_id})]
     (promote-chat-member this content))))

(defn set-chat-administrator-custom-title
  "Use this method to set a custom title for an administrator in a supergroup
   promoted by the bot.
   Returns True on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   user_id ; id of target user
   custom_title ; new custom title for the admin"
  ([this content]
   (http/request this "setChatAdministratorCustomTitle" content))

  ([this chat_id user_id custom_title]
   (let [content {:chat_id chat_id
                  :user_id user_id
                  :custom_title custom_title}]
     (set-chat-administrator-custom-title this content))))

(defn set-chat-permissions
  "Use this method to set default chat permissions for all members.
   The bot must be an administrator in the group or a supergroup for this to work
   and must have the can_restrict_members admin rights.
   Returns True on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   permissions ; new default chat permissions"
  ([this content]
   (http/request this "setChatPermissions" content))

  ([this chat_id permissions]
   (let [content {:chat_id chat_id
                  :permissions permissions}]
     (set-chat-permissions this content))))

(defmulti export-chat-invite-link
  "Use this method to generate a new invite link for a chat; any previously generated
   link is revoked. The bot must be an administrator in the chat for this to work and
   must have the appropriate admin rights.
   Returns the new invite link as String on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)"
  content-map?)

(defmethod export-chat-invite-link true
  [this content]
  (http/request this "exportChatInviteLink" content))

(defmethod export-chat-invite-link false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (export-chat-invite-link this content)))

(defn set-chat-photo
  "Use this method to set a new profile photo for the chat.
   Photos can't be changed for private chats.
   The bot must be an administrator in the chat for this to work and
   must have the appropriate admin rights.
   Returns True on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   photo ; new chat photo"
  ([this content]
   (http/request this "setChatPhoto" content))

  ([this chat_id photo]
   (let [content {:chat_id chat_id
                  :photo photo}]
     (set-chat-photo this content))))

(defmulti delete-chat-photo
  "Use this method to delete a chat photo. Photos can't be changed for private chats.
   The bot must be an administrator in the chat for this to work and
   must have the appropriate admin rights.
   Returns True on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)"
  content-map?)

(defmethod delete-chat-photo true
  [this content]
  (http/request this "deleteChatPhoto" content))

(defmethod delete-chat-photo false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (delete-chat-photo this content)))

(defn set-chat-title
  "Use this method to change the title of a chat.
   Titles can't be changed for private chats.
   The bot must be an administrator in the chat for this to work and
   must have the appropriate admin rights.
   Returns True on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   title ; new chat title"
  ([this content]
   (http/request this "setChatTitle" content))

  ([this chat_id title]
   (let [content {:chat_id chat_id
                  :title title}]
     (set-chat-title this content))))

(defn set-chat-description
  "Use this method to change the description of a group, a supergroup or a channel.
   The bot must be an administrator in the chat for this to work and
   must have the appropriate admin rights.
   Returns True on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   description ; new chat description"
  ([this content]
   (http/request this "setChatDescription" content))

  ([this chat_id description]
   (let [content {:chat_id chat_id
                  :description description}]
     (set-chat-description this content))))

(defn pin-chat-message
  "Use this method to pin a message in a group, a supergroup, or a channel.
   The bot must be an administrator in the chat for this to work and
   must have the 'can_pin_messages' admin right in the supergroup or
   'can_edit_messages' admin right in the channel.
   Returns True on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   message_id ; id of message to pin
   ;; Optional
   disable_notification ; true to pin silently. default true in channels."
  ([this content]
   (http/request this "pinChatMessage" content))

  ([this chat_id message_id]
   (let [content {:chat_id chat_id
                  :message_id message_id}]
     (pin-chat-message this content)))

  ([this chat_id message_id & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :message_id message_id})]
     (pin-chat-message this content))))

(defmulti unpin-chat-message
  "Use this method to unpin a message in a group, a supergroup, or a channel.
   The bot must be an administrator in the chat for this to work and
   must have the 'can_pin_messages' admin right in the supergroup or
   'can_edit_messages' admin right in the channel.
   Returns True on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   ;; Optional
   message_id ; id of message to unpin (unpins most recent if not specified)"
  content-map?)

(defmethod unpin-chat-message true
  [this content]
  (http/request this "unpinChatMessage" content))

(defmethod unpin-chat-message false
  ([this chat_id]
   (let [content {:chat_id chat_id}]
     (unpin-chat-message this content)))

  ([this chat_id & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id})]
     (unpin-chat-message this content))))

(defmulti unpin-all-chat-messages
  "Use this method to clear the list of pinned messages in a chat.
   If the chat is not a private chat, the bot must be an administrator in
   the chat for this to work and must have the 'can_pin_messages' admin
   right in a supergroup or 'can_edit_messages' admin right in a channel.
   Returns True on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)"
  content-map?)

(defmethod unpin-all-chat-messages true
  [this content]
  (http/request this "unpinAllChatMessages" content))

(defmethod unpin-all-chat-messages false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (unpin-all-chat-messages this content)))

(defmulti leave-chat
  "Use this method for your bot to leave a group, supergroup or channel.
   Returns True on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)"
  content-map?)

(defmethod leave-chat true
  [this content]
  (http/request this "leaveChat" content))

(defmethod leave-chat false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (leave-chat this content)))

(defmulti get-chat
  "Use this method to get up to date information about the chat
   (current name of the user for one-on-one conversations,
   current username of a user, group or channel, etc.).
   Returns a Chat object on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)"
  content-map?)

(defmethod get-chat true
  [this content]
  (http/request this "getChat" content))

(defmethod get-chat false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (get-chat this content)))

(defmulti get-chat-administrators
  "Use this method to get a list of administrators in a chat.
   On success, returns an Array of ChatMember objects that contains information about
   all chat administrators except other bots.
   If the chat is a group or a supergroup and no administrators were appointed,
   only the creator will be returned.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)"
  content-map?)

(defmethod get-chat-administrators true
  [this content]
  (http/request this "getChatAdministrators" content))

(defmethod get-chat-administrators false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (get-chat-administrators this content)))

(defmulti get-chat-members-count
  "Use this method to get the number of members in a chat.
   Returns Int on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)"
  content-map?)

(defmethod get-chat-members-count true
  [this content]
  (http/request this "getChatMembersCount" content))

(defmethod get-chat-members-count false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (get-chat-members-count this content)))

(defn get-chat-member
  "Use this method to get information about a member of a chat.
   Returns a ChatMember object on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   user_id ; id of target user"
  ([this content]
   (http/request this "getChatMember" content))

  ([this chat_id user_id]
   (let [content {:chat_id chat_id
                  :user_id user_id}]
     (get-chat-member this content))))

(defn set-chat-sticker-set
  "Use this method to set a new group sticker set for a supergroup.
   The bot must be an administrator in the chat for this to work and
   must have the appropriate admin rights.
   Use the field can_set_sticker_set optionally returned in getChat requests to
   check if the bot can use this method.
   Returns True on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   sticker_set_name ; name of sticker set"
  ([this content]
   (http/request this "setChatStickerSet" content))

  ([this chat_id sticker_set_name]
   (let [content {:chat_id chat_id
                  :sticker_set_name sticker_set_name}]
     (set-chat-sticker-set this content))))

(defmulti delete-chat-sticker-set
  "Use this method to delete a group sticker set from a supergroup.
   The bot must be an administrator in the chat for this to work and
   must have the appropriate admin rights.
   Use the field can_set_sticker_set optionally returned in getChat requests to
   check if the bot can use this method.
   Returns True on success.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)"
  content-map?)

(defmethod delete-chat-sticker-set true
  [this content]
  (http/request this "deleteChatStickerSet" content))

(defmethod delete-chat-sticker-set false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (delete-chat-sticker-set this content)))

(defmulti answer-callback-query
  "Use this method to send answers to callback queries sent from inline keyboards.
   The answer will be displayed to the user as a notification at the top of the
   chat screen or as an alert.
   On success, True is returned.
   Parameters
   ;; Required
   chat_id ; target chat or username (@user)
   ;; Optional
   text ; text of the notification
   show_alert ; if true, show alert instead of a notification
   url ; url that will be opened by the user's client.
   cache_time ; max time in seconds for caching callback query. (default: 0)"
  content-map?)

(defmethod answer-callback-query true
  [this content]
  (http/request this "answerCallbackQuery" content))

(defmethod answer-callback-query false
  ([this chat_id]
   (let [content {:chat_id chat_id}]
     (answer-callback-query this content)))

  ([this chat_id & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id})]
     (answer-callback-query this content))))

(defmulti set-my-commands
  "Use this method to change the list of the bot's commands.
   Returns True on success.
   Parameters
   ;; Required
   commands ; json array/list of 'BotCommand'"
  content-map?)

(defmethod set-my-commands true
  [this content]
  (http/request this "setMyCommands" content))

(defmethod set-my-commands false
  [this commands]
  (let [content {:commands commands}]
    (set-my-commands this content)))

(defn get-my-commands
  "Use this method to get the current list of the bot's commands.
   Requires no parameters.
   Returns Array of BotCommand on success."
  [this]
  (http/request this "getMyCommands"))

(def behavior
  "Map for extending the core TBot record with functions."
  {:call call
   :get-me get-me
   :log-out log-out
   :close close
   :send-message send-message
   :forward-message forward-message
   :copy-message copy-message
   :send-photo send-photo
   :send-audio send-audio
   :send-document send-document
   :send-video send-video
   :send-animation send-animation
   :send-voice send-voice
   :send-video-note send-video-note
   :send-media-group send-media-group
   :send-location send-location
   :edit-message-live-location edit-message-live-location
   :edit-message-live-location-inline edit-message-live-location-inline
   :stop-message-live-location stop-message-live-location
   :stop-message-live-location-inline stop-message-live-location-inline
   :send-venue send-venue
   :send-contact send-contact
   :send-poll send-poll
   :send-dice send-dice
   :send-chat-action send-chat-action
   :get-user-profile-photos get-user-profile-photos
   :get-file get-file
   :kick-chat-member kick-chat-member
   :unban-chat-member unban-chat-member
   :restrict-chat-member restrict-chat-member
   :promote-chat-member promote-chat-member
   :set-chat-administrator-custom-title set-chat-administrator-custom-title
   :set-chat-permissions set-chat-permissions
   :export-chat-invite-link export-chat-invite-link
   :set-chat-photo set-chat-photo
   :delete-chat-photo delete-chat-photo
   :set-chat-title set-chat-title
   :set-chat-description set-chat-description
   :pin-chat-message pin-chat-message
   :unpin-chat-message unpin-chat-message
   :unpin-all-chat-messages unpin-all-chat-messages
   :leave-chat leave-chat
   :get-chat get-chat
   :get-chat-administrators get-chat-administrators
   :get-chat-members-count get-chat-members-count
   :get-chat-member get-chat-member
   :set-chat-sticker-set set-chat-sticker-set
   :delete-chat-sticker-set delete-chat-sticker-set
   :answer-callback-query answer-callback-query
   :set-my-commands set-my-commands
   :get-my-commands get-my-commands})
