(ns telegrambot-lib.methods.protocol
  "Telegram Bot API Methods - protocol definitions.
   
   * https://core.telegram.org/bots/api#available-methods")

(defprotocol Methods
  "Bot API available method definitions."

  (call [this] [this content]
    "A generic function to call any endpoint that may not have been added yet.")

  (get-me [this]
    "A simple method for testing your bot's auth token. Requires no parameters.
     Returns basic information about the bot in form of a User object.")

  (send-message [this content]
    "Use this method to send text messages.
     On success, the sent Message is returned.")

  (forward-message [this content]
    "Use this method to forward messages of any kind.
     On success, the sent Message is returned.")

  (send-photo [this content]
    "Use this method to send photos.
     On success, the sent Message is returned.")

  (send-audio [this content]
    "Use this method to send audio files, if you want Telegram clients to
     display them in the music player. Your audio must be in the .MP3 or
     .M4A format.
     On success, the sent Message is returned.")

  (send-document [this content]
    "Use this method to send general files.
     On success, the sent Message is returned.")

  (send-video [this content]
    "Use tffhis method to send video files, Telegram clients support mp4 videos
     other formats may be sent as Document).
     On success, the sent Message is returned.")

  (send-animation [this content]
    "Use this method to send animation files (GIF or H.264/MPEG-4 AVC video
     without sound).
     On success, the sent Message is rbeturned.")

  (send-voice [this content]
    "Use this method to send audio files, if you want Telegram clients to
     display the file as a playable voice message. For this to work, your
     audio must be in an .OGG file encoded with OPUS (other formats may be
     sent as Audio or Document).
     On success, the sent Message is returned.")

  (send-video-note [this content]
    "Use this method to send video messages.
     On success, the sent Message is returned.")

  (send-media-group [this content]
    "Use this method to send a group of photos or videos as an album.
     On success, an array of the sent Messages is returned.")

  (send-location [this content]
    "Use this method to send point on the map.
     On success, the sent Message is returned.")

  (edit-message-live-location [this content]
    "Use this method to edit live location messages.
     A location can be edited until its live_period expires or editing is
     explicitly disabled by a call to stopMessageLiveLocation.
     On success, if the edited message was sent by the bot, the edited
     Message is returned, otherwise True is returned.")

  (stop-message-live-location [this content]
    "Use this method to stop updating a live location message before live_period expires.
     On success, if the message was sent by the bot, the sent
     Message is returned, otherwise True is returned.")

  (send-venue [this content]
    "Use this method to send information about a venue.
     On success, the sent Message is returned.")

  (send-contact [this content]
    "Use this method to send phone contacts.
     On success, the sent Message is returned.")

  (send-poll [this content]
    "Use this method to send a native poll.
     On success, the sent Message is returned.")

  (send-dice [this content]
    "Use this method to send an animated emoji that will display a random value.
     On success, the sent Message is returned.")

  (send-chat-action [this content]
    "Use this method when you need to tell the user that something is happening
     on the bot's side. The status is set for 5 seconds or less (when a message
     arrives from your bot, Telegram clients clear its typing status).
     Returns True on success.")

  (get-user-profile-photos [this content]
    "Use this method to get a list of profile pictures for a user.
     Returns a UserProfilePhotos object.")

  (get-file [this content]
    "Use this method to get basic info about a file and prepare it for downloading.
     For the moment, bots can download files of up to 20MB in size.
     On success, a File object is returned. The file can then be downloaded via
     the link https://api.telegram.org/file/bot<token>/<file_path>,
     where <file_path> is taken from the response.
     It is guaranteed that the link will be valid for at least 1 hour.
     When the link expires, a new one can be requested by calling getFile again.")

  (kick-chat-member [this content]
    "Use this method to kick a user from a group, a supergroup or a channel.
     In the case of supergroups and channels, the user will not be able to
     return to the group on their own using invite links, etc., unless unbanned first.
     The bot must be an administrator in the chat for this to work and must have the
     appropriate admin rights.
     Returns True on success.")

  (unban-chat-member [this content]
    "Use this method to unban a previously kicked user in a supergroup or channel.
     The user will not return to the group or channel automatically, but will be able
     to join via link, etc. The bot must be an administrator for this to work.
     Returns True on success.")

  (restrict-chat-member [this content]
    "Use this method to restrict a user in a supergroup.
     The bot must be an administrator in the supergroup for this to work and
     must have the appropriate admin rights. Pass True for all permissions to lift
     restrictions from a user.
     Returns True on success.")

  (promote-chat-member [this content]
    "Use this method to promote or demote a user in a supergroup or a channel.
     The bot must be an administrator in the chat for this to work and must
     have the appropriate admin rights.
     Pass False for all boolean parameters to demote a user.
     Returns True on success.")

  (set-chat-administrator-custom-title [this content]
    "Use this method to set a custom title for an administrator in a supergroup
     promoted by the bot.
     Returns True on success.")

  (set-chat-permissions [this content]
    "Use this method to set default chat permissions for all members.
     The bot must be an administrator in the group or a supergroup for this to work
     and must have the can_restrict_members admin rights.
     Returns True on success.")

  (export-chat-invite-link [this content]
    "Use this method to generate a new invite link for a chat; any previously generated
     link is revoked. The bot must be an administrator in the chat for this to work and
     must have the appropriate admin rights.
     Returns the new invite link as String on success.")

  (set-chat-photo [this content]
    "Use this method to set a new profile photo for the chat.
     Photos can't be changed for private chats.
     The bot must be an administrator in the chat for this to work and
     must have the appropriate admin rights.
     Returns True on success.")

  (delete-chat-photo [this content]
    "Use this method to delete a chat photo. Photos can't be changed for private chats.
     The bot must be an administrator in the chat for this to work and
     must have the appropriate admin rights.
     Returns True on success.")

  (set-chat-title [this content]
    "Use this method to change the title of a chat.
     Titles can't be changed for private chats.
     The bot must be an administrator in the chat for this to work and
     must have the appropriate admin rights.
     Returns True on success.")

  (set-chat-description [this content]
    "Use this method to change the description of a group, a supergroup or a channel.
     The bot must be an administrator in the chat for this to work and
     must have the appropriate admin rights.
     Returns True on success.")

  (pin-chat-message [this content]
    "Use this method to pin a message in a group, a supergroup, or a channel.
     The bot must be an administrator in the chat for this to work and
     must have the 'can_pin_messages' admin right in the supergroup or
     'can_edit_messages' admin right in the channel.
     Returns True on success.")

  (unpin-chat-message [this content]
    "Use this method to unpin a message in a group, a supergroup, or a channel.
     The bot must be an administrator in the chat for this to work and
     must have the 'can_pin_messages' admin right in the supergroup or
     'can_edit_messages' admin right in the channel.
     Returns True on success.")

  (leave-chat [this content]
    "Use this method for your bot to leave a group, supergroup or channel.
     Returns True on success.")

  (get-chat [this content]
    "Use this method to get up to date information about the chat
     (current name of the user for one-on-one conversations,
     current username of a user, group or channel, etc.).
     Returns a Chat object on success.")

  (get-chat-administrators [this content]
    "Use this method to get a list of administrators in a chat.
     On success, returns an Array of ChatMember objects that contains information about
     all chat administrators except other bots.
     If the chat is a group or a supergroup and no administrators were appointed,
     only the creator will be returned.")

  (get-chat-members-count [this content]
    "Use this method to get the number of members in a chat.
     Returns Int on success.")

  (get-chat-member [this content]
    "Use this method to get information about a member of a chat.
     Returns a ChatMember object on success.")

  (set-chat-sticker-set [this content]
    "Use this method to set a new group sticker set for a supergroup.
     The bot must be an administrator in the chat for this to work and
     must have the appropriate admin rights.
     Use the field can_set_sticker_set optionally returned in getChat requests to
     check if the bot can use this method.
     Returns True on success.")

  (delete-chat-sticker-set [this content]
    "Use this method to delete a group sticker set from a supergroup.
     The bot must be an administrator in the chat for this to work and
     must have the appropriate admin rights.
     Use the field can_set_sticker_set optionally returned in getChat requests to
     check if the bot can use this method.
     Returns True on success.")

  (answer-callback-query [this content]
    "Use this method to send answers to callback queries sent from inline keyboards.
     The answer will be displayed to the user as a notification at the top of the
     chat screen or as an alert.
     On success, True is returned.")

  (set-my-commands [this content]
    "Use this method to change the list of the bot's commands.
     Returns True on success.")

  (get-my-commands [this]
    "Use this method to get the current list of the bot's commands.
     Requires no parameters.
     Returns Array of BotCommand on success."))
