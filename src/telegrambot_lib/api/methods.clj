(ns telegrambot-lib.api.methods
  "Telegram Bot API Methods - function implementations.
   - <https://core.telegram.org/bots/api#available-methods>

   Most functions are multi-arity with the following options:
   - Send all parameters in a 'content' map.
   - Send only the required parameters as simple values.
   - Send the required paraemters as simple values and then 'optional' parameters in a map."
  (:gen-class)
  (:require [telegrambot-lib.http :as http]
            [telegrambot-lib.predicates :refer [content-map?]]))

(defn call
  "A generic function to call any `endpoint` with optional `content`.
   Provided in the case where methods are added to the API and
   are not available in this library yet.

   Required
   - this ; a bot instance
   - endpoint ; the api method path

   Optional
   - content ; parameters for the api method"
  {:added "0.1.0"}

  ([this endpoint]
   (call this endpoint nil))

  ([this endpoint content]
   (http/request this endpoint content)))

(defn get-me
  "A simple method for testing your bot's auth token.
   Returns basic information about the bot in form of a User object.

   Required
   - this ; a bot instance"
  {:added "0.1.0"}
  [this]
  (http/request this "getMe"))

(defn log-out
  "Use this method to log out from the cloud Bot API server before launching
   the bot locally. You must log out the bot before running it locally,
   otherwise there is no guarantee that the bot will receive updates.
   Returns True on success.

   Required
   - this ; a bot instance"
  {:added "0.3.0"}
  [this]
  (http/request this "logOut"))

(defn close
  "Use this method to close the bot instance before moving it from one local
   server to another. You need to delete the webhook before calling this method
   to ensure that the bot isn't launched again after server restart. The method
   will return error 429 in the first 10 minutes after the bot is launched.
   Returns True on success.

   Required
   - this ; a bot instance"
  {:added "0.3.0"}
  [this]
  (http/request this "close"))

(defn send-message
  "Use this method to send text messages.
   On success, the sent Message is returned.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - text ; message to send

   Optional
   - message_thread_id ; id of the target thread of the forum.
   - parse_mode ; entity parsing in message
   - entities ; list of MessageEntity - can use instead of parse_mode
   - link_preview_options ; link preview generation options.
   - disable_notification ; send silently
   - protect_content ; protect content from forwarding/saving
   - reply_parameters ; Description of the message to reply to
   - reply_markup ; additional interface options"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - from_chat_id ; id of chat from original message
   - message_id ; message id in chat specified in 'from_chat_id'

   Optional
   - message_thread_id ; id of the target thread of the forum.
   - disable_notification ; send silently
   - protect_content ; protect content from forwarding/saving"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - from_chat_id ; id of chat from original message
   - message_id ; message id in chat specified in 'from_chat_id'

   Optional
   - message_thread_id ; id of the target thread of the forum.
   - caption ; new caption for media
   - parse_mode ; entity parsing
   - caption_entities ; list of MessageEntity - can use instead of parse_mode
   - disable_notification ; send silently
   - protect_content ; protect content from forwarding/saving
   - reply_parameters ; Description of the message to reply to
   - reply_markup ; additional interface options"
  {:added "0.3.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - photo ; 'file_id' of photo to send that exists on Telegram servers or url

   Optional
   - message_thread_id ; id of the target thread of the forum.
   - caption ; photo caption
   - parse_mode ; entity parsing in photo caption
   - caption_entities ; list of MessageEntity - can use instead of parse_mode
   - has_spoiler ; true if photo needs to be covered with spoiler animation.
   - disable_notification ; send silently
   - protect_content ; protect content from forwarding/saving
   - reply_parameters ; Description of the message to reply to
   - reply_markup ; additional interface options"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - audio ; 'file_id' of audio to send that exists on Telegram servers or url

   Optional
   - message_thread_id ; id of the target thread of the forum.
   - caption ; audio caption
   - parse_mode ; entity parsing in audio caption
   - caption_entities ; list of MessageEntity - can use instead of parse_mode
   - duration ; duration of audio in seconds
   - performer ; audio performer
   - title ; audio track title
   - thumbnail ; thumbnail of the file sent
   - disable_notification ; send silently
   - protect_content ; protect content from forwarding/saving
   - reply_parameters ; Description of the message to reply to
   - reply_markup ; additional interface options"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - document ; 'file_id' of document to send that exists on Telegram servers or url

   Optional
   - message_thread_id ; id of the target thread of the forum.
   - caption ; document caption
   - parse_mode ; entity parsing in document caption
   - caption_entities ; list of MessageEntity - can use instead of parse_mode
   - thumbnail ; thumbnail of the file sent
   - disable_content_type_detection ; disable auto content type detection for files uploaded
   - disable_notification ; send silently
   - protect_content ; protect content from forwarding/saving
   - reply_parameters ; Description of the message to reply to
   - reply_markup ; additional interface options"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - video ; 'file_id' of video to send that exists on Telegram servers or url

   Optional
   - message_thread_id ; id of the target thread of the forum.
   - duration ; duration of video in seconds
   - width
   - height
   - caption ; video caption
   - parse_mode ; entity parsing in video caption
   - caption_entities ; list of MessageEntity - can use instead of parse_mode
   - has_spoiler ; true if video needs to be covered with spoiler animation.
   - thumbnail ; thumbnail of file sent
   - supports_streaming ; true if uploaded video is ok for streaming
   - disable_notification ; send silently
   - protect_content ; protect content from forwarding/saving
   - reply_parameters ; Description of the message to reply to
   - reply_markup ; additional interface options"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - animation ; 'file_id' of animation to send that exists on Telegram servers or url

   Optional
   - message_thread_id ; id of the target thread of the forum.
   - duration ; duration of animation in seconds
   - width
   - height
   - caption ; animation caption
   - parse_mode ; entity parsing in animation caption
   - caption_entities ; list of MessageEntity - can use instead of parse_mode
   - has_spoiler ; true if animation needs to be covered with spoiler animation.
   - thumbnail ; thumbnail of file sent
   - disable_notification ; send silently
   - protect_content ; protect content from forwarding/saving
   - reply_parameters ; Description of the message to reply to
   - reply_markup ; additional interface options"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - voice ; 'file_id' of audio file that exists on Telegram servers or url

   Optional
   - message_thread_id ; id of the target thread of the forum.
   - duration ; duration of voice message in seconds
   - caption ; voice message caption
   - parse_mode ; entity parsing in voice message caption
   - caption_entities ; list of MessageEntity - can use instead of parse_mode
   - disable_notification ; send silently
   - protect_content ; protect content from forwarding/saving
   - reply_parameters ; Description of the message to reply to
   - reply_markup ; additional interface options"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - video_note ; 'file_id' of video note that exists on Telegram servers or url

   Optional
   - message_thread_id ; id of the target thread of the forum.
   - duration ; duration of video in seconds
   - length ; video width and height
   - thumbnail ; thumbnail of the file sent
   - disable_notification ; send silently
   - protect_content ; protect content from forwarding/saving
   - reply_parameters ; Description of the message to reply to
   - reply_markup ; additional interface options"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - media ; json array describing photos/videos to be sent

   Optional
   - message_thread_id ; id of the target thread of the forum.
   - disable_notification ; send silently
   - protect_content ; protect content from forwarding/saving
   - reply_parameters ; Description of the message to reply to
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - latitude ; lat of location
   - longitude ; long of location

   Optional
   - message_thread_id ; id of the target thread of the forum.
   - horizontal_accuracy ; 1-1500 meters radius of uncertainty
   - live_period ; seconds for which location will be updated (60-86400)
   - heading ; 1-360 degrees direction user is moving
   - proximity_alert_radius ; 1-100000 meters max distance for proximity alerts
   - disable_notification ; send silently
   - protect_content ; protect content from forwarding/saving
   - reply_parameters ; Description of the message to reply to
   - reply_markup ; additional interface options"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - message_id ; id of the message to edit
   - latitude ; lat of new location
   - longitude ; long of new location

   Optional
   - horizontal_accuracy ; 1-1500 meters radius of uncertainty
   - heading ; 1-360 degrees direction user is moving
   - proximity_alert_radius ; 1-100000 meters max distance for proximity alerts
   - reply_markup ; additional interface options"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - inline_message_id ; id of the inline message to edit
   - latitude ; lat of new location
   - longitude ; long of new location

   Optional
   - reply_markup ; additional interface options"
  {:added "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - message_id ; id of the message to edit

   Optional
   - reply_markup ; additional interface options"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - inline_message_id ; id of the inline message to edit

   Optional
   - reply_markup ; additional interface options"
  {:added "0.2.0"}
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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - latitude ; lat of venue
   - longitude ; long of venue
   - title ; name of venue
   - address ; address of venue

   Optional
   - message_thread_id ; id of the target thread of the forum.
   - foursquare_id ; foursquare id of venue
   - foursquare_type ; foursquare type of venue
   - google_place_id ; Google Places id of venue
   - google_place_type ; Google Places type of venue
   - disable_notification ; send silently
   - protect_content ; protect content from forwarding/saving
   - reply_parameters ; Description of the message to reply to
   - reply_markup ; additional interface options"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - phone_number ; contact phone number
   - first_name

   Optional
   - message_thread_id ; id of the target thread of the forum.
   - last_name
   - vcard ; 'vCard' formatted additional data
   - disable_notification ; send silently
   - protect_content ; protect content from forwarding/saving
   - reply_parameters ; Description of the message to reply to
   - reply_markup ; additional interface options"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - question ; poll question
   - options ; array/list of answer options

   Optional
   - message_thread_id ; id of the target thread of the forum.
   - is_anonymous ; true if poll is anonymous
   - type ; 'quiz' or 'regular' (default: regular)
   - allows_multiple_answers ; true poll allows multiple answers
   - correct_option_id ; 0-based id of correct answer. Required for quiz mode
   - explanation ; shown when user chooses incorrect answer
   - explanation_parse_mode ; parsing entities in explanation
   - explanation_entities ; list of MessageEntity - can use instead of parse_mode
   - open_period ; 5-600 seconds - time poll will be active
   - close_date ; unix timestamp when poll will be auto closed (5-600 secs in future)
   - is_closed ; true if poll needs to be immediately closed
   - disable_notification ; send silently
   - protect_content ; protect content from forwarding/saving
   - reply_parameters ; Description of the message to reply to
   - reply_markup ; additional interface options"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)

   Optional
   - message_thread_id ; id of the target thread of the forum.
   - emoji ; image for dice animation (default: dice)
   - disable_notification ; send silently
   - protect_content ; protect content from forwarding/saving
   - reply_parameters ; Description of the message to reply to
   - reply_markup ; additional interface options"
  {:changed "0.2.0"}
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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - action ; type of action to broadcast (typing, upload_photo, record_video, upload_video,
              record_voice, upload_voice, upload_document, choose_sticker, find_location,
              record_video_note, upload_video_note)

   Optional
   - message_thread_id ; unique id for target message thread."
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - user_id ; id of target user

   Optional
   - offset ; number of first photo returned
   - limit ; limit number of photos retrieved (1-100, default: 100)"
  {:changed "0.2.0"}
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
   the link https://api.telegram.org/file/bot`token`/`file_path`,
   where `file_path` is taken from the response.
   It is guaranteed that the link will be valid for at least 1 hour.
   When the link expires, a new one can be requested by calling getFile again.

   Required
   - this ; a bot instance
   - file_id ; file id to get info about"
  {:changed "0.2.0"}
  content-map?)

(defmethod get-file true
  [this content]
  (http/request this "getFile" content))

(defmethod get-file false
  [this file_id]
  (let [content {:file_id file_id}]
    (get-file this content)))

(defn kick-chat-member
  "DEPRECATED: Use this method to kick a user from a group, a supergroup or a channel.
   In the case of supergroups and channels, the user will not be able to
   return to the group on their own using invite links, etc., unless unbanned first.
   The bot must be an administrator in the chat for this to work and must have the
   appropriate admin rights.
   Returns True on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - user_id ; id of target user

   Optional
   - until_date ; unix time when user is unbanned. 30 seconds - 366 days.
                  if less or more, user is banned forever.
   - revoke_messages ; true to delete all messages from chat for user being removed.
                       always true for supergroups and channels."
  {:deprecated "0.3.4"
   :use-instead (symbol 'ban-chat-member)}

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

(defn ban-chat-member
  "Use this method to ban a user from a group, a supergroup or a channel.
   In the case of supergroups and channels, the user will not be able to
   return to the group on their own using invite links, etc., unless unbanned first.
   The bot must be an administrator in the chat for this to work and must have the
   appropriate admin rights.
   Returns True on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - user_id ; id of target user

   Optional
   - until_date ; unix time when user is unbanned. 30 seconds - 366 days.
                  if less or more, user is banned forever.
   - revoke_messages ; true to delete all messages from chat for user being removed.
                       always true for supergroups and channels."
  {:added "0.3.4"}

  ([this content]
   (http/request this "banChatMember" content))

  ([this chat_id user_id]
   (let [content {:chat_id chat_id
                  :user_id user_id}]
     (ban-chat-member this content)))

  ([this chat_id user_id & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :user_id user_id})]
     (ban-chat-member this content))))

(defn unban-chat-member
  "Use this method to unban a previously kicked user in a supergroup or channel.
   The user will not return to the group or channel automatically, but will be able
   to join via link, etc. The bot must be an administrator for this to work.
   Returns True on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - user_id ; id of target user

   Optional
   - only_if_banned ; Do nothing if the user is not banned"
  {:changed "0.3.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - user_id ; id of target user
   - permissions ; json object 'ChatPermissions' for new user permissions

   Optional
   - use_independent_chat_permissions ; True if chat permissions are set independently.
                                        Otherwise, some permissions imply others.
   - until_date ; unix time when user is unbanned. 30 seconds - 366 days.
                  if less or more, user is banned forever."
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - user_id ; id of target user

   Optional
   - is_anonymous ; true if admin presence in chat is hidden
   - can_manage_chat ; true if admin can access logs, stats, members, etc.
   - can_delete_messages ; true if admin can delete messages of other users
   - can_manage_video_chats ; true if admin can manage video chats, supergroups only
   - can_restrict_members ; true if admin can restrict, ban, unban members
   - can_promote_members ; true if admin can add new admins
   - can_change_info ; true if admin can change chat title, photo, other settings
   - can_invite_users ; true if admin can invite new users to chat
   - can_post_messages ; true if admin can create channel posts
   - can_edit_messages ; true if admin can edit messages of other users, pin messages
   - can_pin_messages ; true if admin can pin messages
   - can_post_stories ; true if the administrator can post stories in the channel
   - can_edit_stories ; true if the administrator can edit stories posted by other users
   - can_delete_stories ; true if the administrator can delete stories posted by other users
   - can_manage_topics ; true if user is allowed to create,rename,close,reopen forum topics."
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - user_id ; id of target user
   - custom_title ; new custom title for the admin"
  {:changed "0.2.0"}

  ([this content]
   (http/request this "setChatAdministratorCustomTitle" content))

  ([this chat_id user_id custom_title]
   (let [content {:chat_id chat_id
                  :user_id user_id
                  :custom_title custom_title}]
     (set-chat-administrator-custom-title this content))))

(defn ban-chat-sender-chat
  "Use this method to ban a channel chat in a supergroup or a channel.
   The owner of the chat will not be able to send messages and join live streams on
   behalf of the chat, unless it is unbanned first. The bot must be an administrator
   in the supergroup or channel for this to work and must have the appropriate administrator rights.
   Returns True on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - sender_chat_id ; target sender chat

   Optional
   - until_date ; date in unix time when the sender chat will be unbanned"
  {:added "1.2.0"}

  ([this content]
   (http/request this "banChatSenderChat" content))

  ([this chat_id sender_chat_id]
   (let [content {:chat_id chat_id
                  :sender_chat_id sender_chat_id}]
     (ban-chat-sender-chat this content)))

  ([this chat_id sender_chat_id & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :sender_chat_id sender_chat_id})]
     (ban-chat-sender-chat this content))))

(defn unban-chat-sender-chat
  "Use this method to unban a previously banned channel chat in a supergroup or channel.
   The bot must be an administrator for this to work and must have the appropriate administrator rights.
   Returns True on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - sender_chat_id ; target sender chat"
  {:added "1.2.0"}

  ([this content]
   (http/request this "unbanChatSenderChat" content))

  ([this chat_id sender_chat_id]
   (let [content {:chat_id chat_id
                  :sender_chat_id sender_chat_id}]
     (unban-chat-sender-chat this content))))

(defn set-chat-permissions
  "Use this method to set default chat permissions for all members.
   The bot must be an administrator in the group or a supergroup for this to work
   and must have the can_restrict_members admin rights.
   Returns True on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - permissions ; new default chat permissions

   Optional
   - use_independent_chat_permissions ; True if chat permissions are set independently.
                                        Otherwise, some permissions imply others."
  {:changed "2.5.0"}

  ([this content]
   (http/request this "setChatPermissions" content))

  ([this chat_id permissions]
   (let [content {:chat_id chat_id
                  :permissions permissions}]
     (set-chat-permissions this content)))

  ([this chat_id permissions & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :permissions permissions})]
     (set-chat-permissions this content))))

(defmulti export-chat-invite-link
  "Use this method to generate a new invite link for a chat; any previously generated
   link is revoked. The bot must be an administrator in the chat for this to work and
   must have the appropriate admin rights.
   Returns the new invite link as String on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)"
  {:changed "0.2.0"}
  content-map?)

(defmethod export-chat-invite-link true
  [this content]
  (http/request this "exportChatInviteLink" content))

(defmethod export-chat-invite-link false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (export-chat-invite-link this content)))

(defmulti create-chat-invite-link
  "Use this method to create an additional invite link for a chat.
   The bot must be an administrator in the chat for this to work and must have
   the appropriate admin rights. The link can be revoked using the method revokeChatInviteLink.
   Returns the new invite link as ChatInviteLink object.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)

   Optional
   - name ; invite name link
   - expire_date ; unix timestamp when the link will expire
   - member_limit ; max num users that can be members simulatneously (1-99999)
   - creates_join_request ; true if users joining need to be approved"
  {:added "0.3.2"}
  content-map?)

(defmethod create-chat-invite-link true
  [this content]
  (http/request this "createChatInviteLink" content))

(defmethod create-chat-invite-link false
  ([this chat_id]
   (let [content {:chat_id chat_id}]
     (create-chat-invite-link this content)))

  ([this chat_id & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id})]
     (create-chat-invite-link this content))))

(defn edit-chat-invite-link
  "Use this method to edit a non-primary invite link created by the bot.
   The bot must be an administrator in the chat for this to work and must have the appropriate admin rights.
   Returns the edited invite link as a ChatInviteLink object.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - invite_link ; invite link to edit

   Optional
   - name ; invite name link
   - expire_date ; unix timestamp when the link will expire
   - member_limit ; max num users that can be members simulatneously (1-99999)
   - creates_join_request ; true if users joining need to be approved"
  {:added "0.3.2"}

  ([this content]
   (http/request this "editChatInviteLink" content))

  ([this chat_id invite_link]
   (let [content {:chat_id chat_id
                  :invite_link invite_link}]
     (edit-chat-invite-link this content)))

  ([this chat_id invite_link & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :invite_link invite_link})]
     (edit-chat-invite-link this content))))

(defn revoke-chat-invite-link
  "Use this method to revoke an invite link created by the bot.
   If the primary link is revoked, a new link is automatically generated.
   The bot must be an administrator in the chat for this to work and must have the appropriate admin rights.
   Returns the revoked invite link as ChatInviteLink object.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - invite_link ; invite link to revoke"
  {:added "0.3.2"}

  ([this content]
   (http/request this "revokeChatInviteLink" content))

  ([this chat_id invite_link]
   (let [content {:chat_id chat_id
                  :invite_link invite_link}]
     (revoke-chat-invite-link this content))))

(defn approve-chat-join-request
  "Use this method to approve a chat join request.
   The bot must be an administrator in the chat for this to work and
   must have the can_invite_users administrator right.
   Returns True on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - user_id ; id of target user"
  {:added "1.1.0"}

  ([this content]
   (http/request this "approveChatJoinRequest" content))

  ([this chat_id user_id]
   (let [content {:chat_id chat_id
                  :user_id user_id}]
     (revoke-chat-invite-link this content))))

(defn decline-chat-join-request
  "Use this method to decline a chat join request.
   The bot must be an administrator in the chat for this to work and
   must have the can_invite_users administrator right.
   Returns True on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - user_id ; id of target user"
  {:added "1.1.0"}

  ([this content]
   (http/request this "declineChatJoinRequest" content))

  ([this chat_id user_id]
   (let [content {:chat_id chat_id
                  :user_id user_id}]
     (revoke-chat-invite-link this content))))

(defn set-chat-photo
  "Use this method to set a new profile photo for the chat.
   Photos can't be changed for private chats.
   The bot must be an administrator in the chat for this to work and
   must have the appropriate admin rights.
   Returns True on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - photo ; new chat photo"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)"
  {:changed "0.2.0"}
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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - title ; new chat title"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - description ; new chat description"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - message_id ; id of message to pin

   Optional
   - disable_notification ; true to pin silently. default true in channels."
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)

   Optional
   - message_id ; id of message to unpin (unpins most recent if not specified)"
  {:changed "0.3.0"}
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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)"
  {:added "0.3.0"}
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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)"
  {:changed "0.2.0"}
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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)"
  {:changed "0.2.0"}
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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)"
  {:changed "0.2.0"}
  content-map?)

(defmethod get-chat-administrators true
  [this content]
  (http/request this "getChatAdministrators" content))

(defmethod get-chat-administrators false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (get-chat-administrators this content)))

(defmulti get-chat-members-count
  "DEPRECATED: Use this method to get the number of members in a chat.
   Returns Int on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)"
  {:deprecated "0.3.4"
   :use-instead (symbol 'get-chat-member-count)}
  content-map?)

(defmethod get-chat-members-count true
  [this content]
  (http/request this "getChatMembersCount" content))

(defmethod get-chat-members-count false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (get-chat-members-count this content)))

(defmulti get-chat-member-count
  "Use this method to get the number of members in a chat.
   Returns Int on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)"
  {:added "0.3.4"}
  content-map?)

(defmethod get-chat-member-count true
  [this content]
  (http/request this "getChatMemberCount" content))

(defmethod get-chat-member-count false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (get-chat-member-count this content)))

(defn get-chat-member
  "Use this method to get information about a member of a chat.
   Returns a ChatMember object on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - user_id ; id of target user"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - sticker_set_name ; name of sticker set"
  {:changed "0.2.0"}

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

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)"
  {:changed "0.2.0"}
  content-map?)

(defmethod delete-chat-sticker-set true
  [this content]
  (http/request this "deleteChatStickerSet" content))

(defmethod delete-chat-sticker-set false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (delete-chat-sticker-set this content)))

(defn get-forum-topic-icon-stickers
  "Use this method to get custom emoji stickers, which can be used as a forum topic icon by any user.
   Requires no parameters.
   Returns an Array of Sticker objects.

   Required
   - this ; a bot instance"
  {:added "2.3.0"}
  [this]
  (http/request this "getForumTopicIconStickers"))

(defn create-forum-topic
  "Use this method to create a topic in a forum supergroup chat.
   The bot must be an administrator in the chat for this to work and
   must have the can_manage_topics administrator rights.
   Returns information about the created topic as a ForumTopic object.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - name ; the topic name.

   Optional
   - icon_color ; integer color of the topic icon in RGB format.
   - icon_custom_emoji_id ; string id of custom emoji shown as topic icon."
  {:added "2.3.0"}

  ([this content]
   (http/request this "createForumTopic" content))

  ([this chat_id name]
   (let [content {:chat_id chat_id
                  :name name}]
     (create-forum-topic this content)))

  ([this chat_id name & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :name name})]
     (create-forum-topic this content))))

(defn edit-forum-topic
  "Use this method to edit name and icon of a topic in a forum supergroup chat.
   The bot must be an administrator in the chat for this to work and must have
   can_manage_topics administrator rights, unless it is the creator of the topic.
   Returns True on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - message_thread_id ; id of the target thread of the forum.

   Optional
   - name ; the topic name.
   - icon_custom_emoji_id ; string id of custom emoji shown as topic icon."
  {:changed "2.4.0"}

  ([this content]
   (http/request this "editForumTopic" content))

  ([this chat_id message_thread_id]
   (let [content {:chat_id chat_id
                  :message_thread_id message_thread_id}]
     (edit-forum-topic this content)))

  ([this chat_id message_thread_id & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :message_thread_id message_thread_id})]
     (edit-forum-topic this content))))

(defn close-forum-topic
  "Use this method to close an open topic in a forum supergroup chat.
   The bot must be an administrator in the chat for this to work and
   must have the can_manage_topics administrator rights, unless it is the creator of the topic.
   Returns True on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - message_thread_id ; id of the target thread of the forum."
  {:added "2.3.0"}

  ([this content]
   (http/request this "closeForumTopic" content))

  ([this chat_id message_thread_id]
   (let [content {:chat_id chat_id
                  :message_thread_id message_thread_id}]
     (close-forum-topic this content))))

(defn reopen-forum-topic
  "Use this method to reopen a closed topic in a forum supergroup chat.
   The bot must be an administrator in the chat for this to work and
   must have the can_manage_topics administrator rights, unless it is the creator of the topic.
   Returns True on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - message_thread_id ; id of the target thread of the forum."
  {:added "2.3.0"}

  ([this content]
   (http/request this "reopenForumTopic" content))

  ([this chat_id message_thread_id]
   (let [content {:chat_id chat_id
                  :message_thread_id message_thread_id}]
     (reopen-forum-topic this content))))

(defn delete-forum-topic
  "Use this method to delete a forum topic along with all its messages in a forum supergroup chat.
   The bot must be an administrator in the chat for this to work and
   must have the can_delete_messages administrator rights.
   Returns True on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - message_thread_id ; id of the target thread of the forum."
  {:added "2.3.0"}

  ([this content]
   (http/request this "deleteForumTopic" content))

  ([this chat_id message_thread_id]
   (let [content {:chat_id chat_id
                  :message_thread_id message_thread_id}]
     (delete-forum-topic this content))))

(defn unpin-all-forum-topic-messages
  "Use this method to clear the list of pinned messages in a forum topic.
   The bot must be an administrator in the chat for this to work and
   must have the can_pin_messages administrator right in the supergroup.
   Returns True on success.

   Required
   - this ; a bot instance
   - chat_id ; target chat or username (@user)
   - message_thread_id ; id of the target thread of the forum."
  {:added "2.3.0"}

  ([this content]
   (http/request this "unpinAllForumTopicMessages" content))

  ([this chat_id message_thread_id]
   (let [content {:chat_id chat_id
                  :message_thread_id message_thread_id}]
     (unpin-all-forum-topic-messages this content))))

(defn edit-general-forum-topic
  "Use this method to edit the name of the 'General' topic in a forum supergroup chat.
   The bot must be an administrator in the chat for this to work and must have
   can_manage_topics administrator rights.
   Returns True on success.

   Required
   - this ; A bot instance.
   - chat_id ; Target chat or username of supergroup (@user).
   - name ; The topic name."
  {:added "2.4.0"}

  ([this content]
   (http/request this "editGeneralForumTopic" content))

  ([this chat_id name]
   (let [content {:chat_id chat_id
                  :name name}]
     (edit-general-forum-topic this content))))

(defmulti close-general-forum-topic
  "Use this method to close an open 'General' topic in a forum supergroup chat.
   The bot must be an administrator in the chat for this to work and must have
   the can_manage_topics administrator rights.
   Returns True on success.

   Required
   - this ; A bot instance.
   - chat_id ; Target chat or username of supergroup (@user)."
  {:added "2.4.0"}
  content-map?)

(defmethod close-general-forum-topic true
  [this content]
  (http/request this "closeGeneralForumTopic" content))

(defmethod close-general-forum-topic false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (close-general-forum-topic this content)))

(defmulti reopen-general-forum-topic
  "Use this method to reopen a closed 'General' topic in a forum supergroup chat.
   The bot must be an administrator in the chat for this to work and must have
   the can_manage_topics administrator rights.
   Returns True on success.

   Required
   - this ; A bot instance.
   - chat_id ; Target chat or username of supergroup (@user)."
  {:added "2.4.0"}
  content-map?)

(defmethod reopen-general-forum-topic true
  [this content]
  (http/request this "reopenGeneralForumTopic" content))

(defmethod reopen-general-forum-topic false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (reopen-general-forum-topic this content)))

(defmulti hide-general-forum-topic
  "Use this method to hide the 'General' topic in a forum supergroup chat.
   The bot must be an administrator in the chat for this to work and must have
   the can_manage_topics administrator rights.
   Returns True on success.

   Required
   - this ; A bot instance.
   - chat_id ; Target chat or username of supergroup (@user)."
  {:added "2.4.0"}
  content-map?)

(defmethod hide-general-forum-topic true
  [this content]
  (http/request this "hideGeneralForumTopic" content))

(defmethod hide-general-forum-topic false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (hide-general-forum-topic this content)))

(defmulti unhide-general-forum-topic
  "Use this method to unhide the 'General' topic in a forum supergroup chat.
   The bot must be an administrator in the chat for this to work and must have
   the can_manage_topics administrator rights.
   Returns True on success.

   Required
   - this ; A bot instance.
   - chat_id ; Target chat or username of supergroup (@user)."
  {:added "2.4.0"}
  content-map?)

(defmethod unhide-general-forum-topic true
  [this content]
  (http/request this "unhideGeneralForumTopic" content))

(defmethod unhide-general-forum-topic false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (unhide-general-forum-topic this content)))

(defmulti unpin-all-general-forum-topic-messages
  "Use this method to clear the list of pinned messages in a General forum topic.
   The bot must be an administrator in the chat for this to work and
   must have the can_pin_messages administrator right in the supergroup.
   Returns True on success.

   Required
   - this ; A bot instance.
   - chat_id ; Target chat or username of supergroup (@user)."
  {:added "2.9.0"}
  content-map?)

(defmethod unpin-all-general-forum-topic-messages true
  [this content]
  (http/request this "unpinAllGeneralForumTopicMessages" content))

(defmethod unpin-all-general-forum-topic-messages false
  [this chat_id]
  (let [content {:chat_id chat_id}]
    (unpin-all-general-forum-topic-messages this content)))

(defmulti answer-callback-query
  "Use this method to send answers to callback queries sent from inline keyboards.
   The answer will be displayed to the user as a notification at the top of the
   chat screen or as an alert.
   On success, True is returned.

   Required
   - this ; a bot instance
   - callback_query_id ; unique identifier for the query to be answered

   Optional
   - text ; text of the notification
   - show_alert ; if true, show alert instead of a notification
   - url ; url that will be opened by the user's client.
   - cache_time ; max time in seconds for caching callback query. (default: 0)"
  {:changed "0.3.5"}
  content-map?)

(defmethod answer-callback-query true
  [this content]
  (http/request this "answerCallbackQuery" content))

(defmethod answer-callback-query false
  ([this callback_query_id]
   (let [content {:callback_query_id callback_query_id}]
     (answer-callback-query this content)))

  ([this callback_query_id & optional]
   (let [content (merge (first optional)
                        {:callback_query_id callback_query_id})]
     (answer-callback-query this content))))

(defmulti set-my-commands
  "Use this method to change the list of the bot's commands.
   Returns True on success.

   Required
   - this ; a bot instance
   - commands ; json array/list of 'BotCommand'

   Optional
   - scope ; JSON object, scope of users that commands are relevant for. (default: BotCommandScopeDefault)
   - language_code ; Two-letter ISO 639-1 lang code. If empty, commands applied to all users from given scope."
  {:changed "0.3.4"}
  content-map?)

(defmethod set-my-commands true
  [this content]
  (http/request this "setMyCommands" content))

(defmethod set-my-commands false
  ([this commands]
   (let [content {:commands commands}]
     (set-my-commands this content)))

  ([this commands & optional]
   (let [content (merge (first optional)
                        {:commands commands})]
     (set-my-commands this content))))

(defn delete-my-commands
  "Use this method to delete the list of the bot's commands for the given scope and user language.
   After deletion, higher level commands will be shown to affected users.
   Returns True on success.

   Required
   - this ; a bot instance

   Optional
   - scope ; JSON object, scope of users that commands are relevant for. (default: BotCommandScopeDefault)
   - language_code ; Two-letter ISO 639-1 lang code. If empty, commands applied to all users from given scope."
  {:added "0.3.4"}

  ([this]
   (http/request this "deleteMyCommands"))

  ([this content]
   (http/request this "deleteMyCommands" content)))

(defn get-my-commands
  "Use this method to get the current list of the bot's commands.
   Returns Array of BotCommand on success.

   Required
   - this ; a bot instance

   Optional
   - scope ; JSON object, scope of users that commands are relevant for. (default: BotCommandScopeDefault)
   - language_code ; Two-letter ISO 639-1 lang code. If empty, commands applied to all users from given scope."
  {:changed "0.3.4"}

  ([this]
   (http/request this "getMyCommands"))

  ([this content]
   (http/request this "getMyCommands" content)))

(defn set-my-name
  "Use this method to change the bot's name.
   Returns True on success.

   Required
   - this ; a bot instance

   Optional
   - name ; New bot name. Pass an empty string to remove the name.
   - language_code ; Two-letter ISO 639-1 lang code. If empty, name applied to all users."
  {:added "2.7.0"}

  ([this]
   (http/request this "setMyName"))

  ([this content]
   (http/request this "setMyName" content)))

(defn get-my-name
  "Use this method to get the current bot name for the given user language.
   Returns BotName on success.

   Required
   - this ; a bot instance

   Optional
   - language_code ; Two-letter ISO 639-1 lang code."
  {:added "2.7.0"}

  ([this]
   (http/request this "getMyName"))

  ([this content]
   (http/request this "getMyName" content)))

(defn set-my-description
  "Use this method to change the bot's description,
   which is shown in the chat with the bot if the chat is empty.
   Returns True on success.

   Required
   - this ; a bot instance

   Optional
   - description ; New bot description. Pass empty string to remove description.
   - language_code ; Two-letter ISO 639-1 lang code. If empty, description applied to all users."
  {:added "2.6.0"}

  ([this]
   (http/request this "setMyDescription"))

  ([this content]
   (http/request this "setMyDescription" content)))

(defn get-my-description
  "Use this method to get the current bot description for the given user language.
   Returns BotDescription on success.

   Required
   - this ; a bot instance

   Optional
   - language_code ; Two-letter ISO 639-1 lang code or an empty string."
  {:added "2.6.0"}

  ([this]
   (http/request this "getMyDescription"))

  ([this content]
   (http/request this "getMyDescription" content)))

(defn set-my-short-description
  "Use this method to change the bot's short description,
   which is shown on the bot's profile page and is sent together
   with the link when users share the bot.
   Returns True on success.

   Required
   - this ; a bot instance

   Optional
   - short_description ; New bot short description. Pass empty string to remove description.
   - language_code ; Two-letter ISO 639-1 lang code. If empty, description applied to all users."
  {:added "2.6.0"}

  ([this]
   (http/request this "setMyShortDescription"))

  ([this content]
   (http/request this "setMyShortDescription" content)))

(defn get-my-short-description
  "Use this method to get the current bot short description for the given user language.
   Returns BotShortDescription on success.

   Required
   - this ; a bot instance

   Optional
   - language_code ; Two-letter ISO 639-1 lang code or an empty string."
  {:added "2.6.0"}

  ([this]
   (http/request this "getMyShortDescription"))

  ([this content]
   (http/request this "getMyShortDescription" content)))

(defn set-chat-menu-button
  "Use this method to change the bot's menu button in a private chat, or the default menu button.
   Returns True on success.

   Required
   - this ; a bot instance

   Optional
   - chat_id ; target chat or username (@user)
   - menu_button ; A JSON-serialized object for the new bot's menu button. Defaults to MenuButtonDefault."
  {:added "1.5.0"}

  ([this]
   (http/request this "setChatMenuButton"))

  ([this content]
   (http/request this "setChatMenuButton" content)))

(defn get-chat-menu-button
  "Use this method to get the current value of the bot's menu button in a private chat, or the
   default menu button.
   Returns MenuButton on success.

   Required
   - this ; a bot instance

   Optional
   - chat_id ; target chat or username (@user)"
  {:added "1.5.0"}

  ([this]
   (http/request this "getChatMenuButton"))

  ([this content]
   (http/request this "getChatMenuButton" content)))

(defn set-my-default-administrator-rights
  "Use this method to change the default administrator rights requested by the bot when it's added
   as an administrator to groups or channels.
   These rights will be suggested to users, but they are are free to modify the list before adding the bot.
   Returns True on success.

   Required
   - this ; a bot instance

   Optional
   - rights ; A JSON-serialized object describing new default administrator rights.
              If not specified, the default administrator rights will be cleared.
   - for_channels ; Pass True to change the default administrator rights of the bot in channels.
                    Otherwise, the default administrator rights of the bot for groups and
                    supergroups will be changed."
  {:added "1.5.0"}

  ([this]
   (http/request this "setMyDefaultAdministratorRights"))

  ([this content]
   (http/request this "setMyDefaultAdministratorRights" content)))

(defn get-my-default-administrator-rights
  "Use this method to get the current default administrator rights of the bot.
   Returns ChatAdministratorRights on success.

   Required
   - this ; a bot instance

   Optional
   - for_channels ; Pass True to get default administrator rights of the bot in channels.
                    Otherwise, default administrator rights of the bot for groups and
                    supergroups will be returned."
  {:added "1.5.0"}

  ([this]
   (http/request this "getMyDefaultAdministratorRights"))

  ([this content]
   (http/request this "getMyDefaultAdministratorRights" content)))
