(ns telegrambot-lib.methods.core
  "Telegram Bot API Methods - function implementations.
   
   * https://core.telegram.org/bots/api#available-methods"
  (:gen-class)
  (:require [telegrambot-lib.http :as http]))

(defn call
  "A generic function to call any endpoint with optional content that may not
   have been added to this library yet to."
  ([this endpoint]
   (call this endpoint nil))
  ([this endpoint content]
   (http/request this endpoint content)))

(defn get-me
  "A simple method for testing your bot's auth token. Requires no parameters.
   Returns basic information about the bot in form of a User object."
  [this]
  (http/request this "getMe"))

(defn send-message
  "Use this method to send text messages.
   On success, the sent Message is returned."
  [this content]
  (http/request this "sendMessage" content))

(defn forward-message
  "Use this method to forward messages of any kind.
   On success, the sent Message is returned."
  [this content]
  (http/request this "forwardMessage" content))

(defn send-photo
  "Use this method to send photos.
   On success, the sent Message is returned."
  [this content]
  (http/request this "sendPhoto" content))

(defn send-audio
  "Use this method to send audio files, if you want Telegram clients to
   display them in the music player. Your audio must be in the .MP3 or
   .M4A format.
   On success, the sent Message is returned."
  [this content]
  (http/request this "sendAudio" content))

(defn send-document
  "Use this method to send general files.
   On success, the sent Message is returned."
  [this content]
  (http/request this "sendDocument" content))

(defn send-video
  "Use tffhis method to send video files, Telegram clients support mp4 videos
   other formats may be sent as Document).
   On success, the sent Message is returned."
  [this content]
  (http/request this "sendVideo" content))

(defn send-animation
  "Use this method to send animation files (GIF or H.264/MPEG-4 AVC video
   without sound).
   On success, the sent Message is rbeturned."
  [this content]
  (http/request this "sendAnimation" content))

(defn send-voice
  "Use this method to send audio files, if you want Telegram clients to
   display the file as a playable voice message. For this to work, your
   audio must be in an .OGG file encoded with OPUS (other formats may be
   sent as Audio or Document).
   On success, the sent Message is returned."
  [this content]
  (http/request this "sendVoice" content))

(defn send-video-note
  "Use this method to send video messages.
     On success, the sent Message is returned."
  [this content]
  (http/request this "sendVideoNote" content))

(defn send-media-group
  "Use this method to send a group of photos or videos as an album.
   On success, an array of the sent Messages is returned."
  [this content]
  (http/request this "sendMediaGroup" content))

(defn send-location
  "Use this method to send point on the map.
   On success, the sent Message is returned."
  [this content]
  (http/request this "sendLocation" content))

(defn edit-message-live-location
  "Use this method to edit live location messages.
   A location can be edited until its live_period expires or editing is
   explicitly disabled by a call to stopMessageLiveLocation.
   On success, if the edited message was sent by the bot, the edited
   Message is returned, otherwise True is returned."
  [this content]
  (http/request this "editMessageLiveLocation" content))

(defn stop-message-live-location
  "Use this method to stop updating a live location message before live_period expires.
   On success, if the message was sent by the bot, the sent
   Message is returned, otherwise True is returned."
  [this content]
  (http/request this "stopMessageLiveLocation" content))

(defn send-venue
  "Use this method to send information about a venue.
   On success, the sent Message is returned."
  [this content]
  (http/request this "sendVenue" content))

(defn send-contact
  "Use this method to send phone contacts.
   On success, the sent Message is returned."
  [this content]
  (http/request this "sendContact" content))

(defn send-poll
  "Use this method to send a native poll.
   On success, the sent Message is returned."
  [this content]
  (http/request this "sendPoll" content))

(defn send-dice
  "Use this method to send an animated emoji that will display a random value.
   On success, the sent Message is returned."
  [this content]
  (http/request this "sendDice" content))

(defn send-chat-action
  "Use this method when you need to tell the user that something is happening
   on the bot's side. The status is set for 5 seconds or less (when a message
   arrives from your bot, Telegram clients clear its typing status).
   Returns True on success."
  [this content]
  (http/request this "sendChatAction" content))

(defn get-user-profile-photos
  "Use this method to get a list of profile pictures for a user.
   Returns a UserProfilePhotos object."
  [this content]
  (http/request this "getUserProfilePhotos" content))

(defn get-file
  "Use this method to get basic info about a file and prepare it for downloading.
   For the moment, bots can download files of up to 20MB in size.
   On success, a File object is returned. The file can then be downloaded via
   the link https://api.telegram.org/file/bot<token>/<file_path>,
   where <file_path> is taken from the response.
   It is guaranteed that the link will be valid for at least 1 hour.
   When the link expires, a new one can be requested by calling getFile again."
  [this content]
  (http/request this "getFile" content))

(defn kick-chat-member
  "Use this method to kick a user from a group, a supergroup or a channel.
   In the case of supergroups and channels, the user will not be able to
   return to the group on their own using invite links, etc., unless unbanned first.
   The bot must be an administrator in the chat for this to work and must have the
   appropriate admin rights.
   Returns True on success."
  [this content]
  (http/request this "kickChatMember" content))

(defn unban-chat-member
  "Use this method to unban a previously kicked user in a supergroup or channel.
   The user will not return to the group or channel automatically, but will be able
   to join via link, etc. The bot must be an administrator for this to work.
   Returns True on success."
  [this content]
  (http/request this "unbanChatMember" content))

(defn restrict-chat-member
  "Use this method to restrict a user in a supergroup.
   The bot must be an administrator in the supergroup for this to work and
   must have the appropriate admin rights. Pass True for all permissions to lift
   restrictions from a user.
   Returns True on success."
  [this content]
  (http/request this "restrictChatMember" content))

(defn promote-chat-member
  "Use this method to promote or demote a user in a supergroup or a channel.
   The bot must be an administrator in the chat for this to work and must
   have the appropriate admin rights.
   Pass False for all boolean parameters to demote a user.
   Returns True on success."
  [this content]
  (http/request this "promoteChatMember" content))

(defn set-chat-administrator-custom-title
  "Use this method to set a custom title for an administrator in a supergroup
   promoted by the bot.
   Returns True on success."
  [this content]
  (http/request this "setChatAdministratorCustomTitle" content))

(defn set-chat-permissions
  "Use this method to set default chat permissions for all members.
   The bot must be an administrator in the group or a supergroup for this to work
   and must have the can_restrict_members admin rights.
   Returns True on success."
  [this content]
  (http/request this "setChatPermissions" content))

(defn export-chat-invite-link
  "Use this method to generate a new invite link for a chat; any previously generated
   link is revoked. The bot must be an administrator in the chat for this to work and
   must have the appropriate admin rights.
   Returns the new invite link as String on success."
  [this content]
  (http/request this "exportChatInviteLink" content))

(defn set-chat-photo
  "Use this method to set a new profile photo for the chat.
   Photos can't be changed for private chats.
   The bot must be an administrator in the chat for this to work and
   must have the appropriate admin rights.
   Returns True on success."
  [this content]
  (http/request this "setChatPhoto" content))

(defn delete-chat-photo
  "Use this method to delete a chat photo. Photos can't be changed for private chats.
   The bot must be an administrator in the chat for this to work and
   must have the appropriate admin rights.
   Returns True on success."
  [this content]
  (http/request this "deleteChatPhoto" content))

(defn set-chat-title
  "Use this method to change the title of a chat.
   Titles can't be changed for private chats.
   The bot must be an administrator in the chat for this to work and
   must have the appropriate admin rights.
   Returns True on success."
  [this content]
  (http/request this "setChatTitle" content))

(defn set-chat-description
  "Use this method to change the description of a group, a supergroup or a channel.
   The bot must be an administrator in the chat for this to work and
   must have the appropriate admin rights.
   Returns True on success."
  [this content]
  (http/request this "setChatDescription" content))

(defn pin-chat-message
  "Use this method to pin a message in a group, a supergroup, or a channel.
   The bot must be an administrator in the chat for this to work and
   must have the 'can_pin_messages' admin right in the supergroup or
   'can_edit_messages' admin right in the channel.
   Returns True on success."
  [this content]
  (http/request this "pinChatMessage" content))

(defn unpin-chat-message
  "Use this method to unpin a message in a group, a supergroup, or a channel.
   The bot must be an administrator in the chat for this to work and
   must have the 'can_pin_messages' admin right in the supergroup or
   'can_edit_messages' admin right in the channel.
   Returns True on success."
  [this content]
  (http/request this "unpinChatMessage" content))

(defn leave-chat
  "Use this method for your bot to leave a group, supergroup or channel.
   Returns True on success."
  [this content]
  (http/request this "leaveChat" content))

(defn get-chat
  "Use this method to get up to date information about the chat
   (current name of the user for one-on-one conversations,
   current username of a user, group or channel, etc.).
   Returns a Chat object on success."
  [this content]
  (http/request this "getChat" content))

(defn get-chat-administrators
  "Use this method to get a list of administrators in a chat.
   On success, returns an Array of ChatMember objects that contains information about
   all chat administrators except other bots.
   If the chat is a group or a supergroup and no administrators were appointed,
   only the creator will be returned."
  [this content]
  (http/request this "getChatAdministrators" content))

(defn get-chat-members-count
  "Use this method to get the number of members in a chat.
   Returns Int on success."
  [this content]
  (http/request this "getChatMembersCount" content))

(defn get-chat-member
  "Use this method to get information about a member of a chat.
   Returns a ChatMember object on success."
  [this content]
  (http/request this "getChatMember" content))

(defn set-chat-sticker-set
  "Use this method to set a new group sticker set for a supergroup.
   The bot must be an administrator in the chat for this to work and
   must have the appropriate admin rights.
   Use the field can_set_sticker_set optionally returned in getChat requests to
   check if the bot can use this method.
   Returns True on success."
  [this content]
  (http/request this "setChatStickerSet" content))

(defn delete-chat-sticker-set
  "Use this method to delete a group sticker set from a supergroup.
   The bot must be an administrator in the chat for this to work and
   must have the appropriate admin rights.
   Use the field can_set_sticker_set optionally returned in getChat requests to
   check if the bot can use this method.
   Returns True on success."
  [this content]
  (http/request this "deleteChatStickerSet" content))

(defn answer-callback-query
  "Use this method to send answers to callback queries sent from inline keyboards.
   The answer will be displayed to the user as a notification at the top of the
   chat screen or as an alert.
   On success, True is returned."
  [this content]
  (http/request this "answerCallbackQuery" content))

(defn set-my-commands
  "Use this method to change the list of the bot's commands.
   Returns True on success."
  [this content]
  (http/request this "setMyCommands" content))

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
   :send-message send-message
   :forward-message forward-message
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
   :stop-message-live-location stop-message-live-location
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
