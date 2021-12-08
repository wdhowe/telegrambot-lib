(ns telegrambot-lib.methods.protocol
  "Telegram Bot API Methods - protocol definitions.
   
   - <https://core.telegram.org/bots/api#available-methods>")

(defprotocol Methods
  "Bot API available method definitions."

  (call [this endpoint]
    [this endpoint content]
    "A generic function to call any endpoint that may not have been added yet.")

  (get-me [this]
    "A simple method for testing your bot's auth token.
     Returns basic information about the bot in form of a User object.")

  (log-out [this]
    "Use this method to log out from the cloud Bot API server before launching
     the bot locally. You must log out the bot before running it locally,
     otherwise there is no guarantee that the bot will receive updates.
     Returns True on success.")

  (close [this]
    "Use this method to close the bot instance before moving it from one local
     server to another. You need to delete the webhook before calling this method
     to ensure that the bot isn't launched again after server restart. The method
     will return error 429 in the first 10 minutes after the bot is launched.
     Returns True on success.=")

  (send-message [this content]
    [this chat_id text]
    [this chat_id text & optional]
    "Use this method to send text messages.
     On success, the sent Message is returned.")

  (forward-message [this content]
    [this chat_id from_chat_id message_id]
    [this chat_id from_chat_id message_id & optional]
    "Use this method to forward messages of any kind.
     On success, the sent Message is returned.")

  (copy-message [this content]
    [this chat_id from_chat_id message_id]
    [this chat_id from_chat_id message_id & optional]
    "Use this method to copy messages of any kind.
     The method is analogous to the method forwardMessages, but the copied
     message doesn't have a link to the original message.
     Returns the MessageId of the sent message on success.")

  (send-photo [this content]
    [this chat_id photo]
    [this chat_id photo & optional]
    "Use this method to send photos.
     On success, the sent Message is returned.")

  (send-audio [this content]
    [this chat_id audio]
    [this chat_id audio & optional]
    "Use this method to send audio files, if you want Telegram clients to
     display them in the music player. Your audio must be in the .MP3 or
     .M4A format.
     On success, the sent Message is returned.")

  (send-document [this content]
    [this chat_id document]
    [this chat_id document & optional]
    "Use this method to send general files.
     On success, the sent Message is returned.")

  (send-video [this content]
    [this chat_id video]
    [this chat_id video & optional]
    "Use tffhis method to send video files, Telegram clients support mp4 videos
     other formats may be sent as Document).
     On success, the sent Message is returned.")

  (send-animation [this content]
    [this chat_id animation]
    [this chat_id animation & optional]
    "Use this method to send animation files (GIF or H.264/MPEG-4 AVC video
     without sound).
     On success, the sent Message is rbeturned.")

  (send-voice [this content]
    [this chat_id voice]
    [this chat_id voice & optional]
    "Use this method to send audio files, if you want Telegram clients to
     display the file as a playable voice message. For this to work, your
     audio must be in an .OGG file encoded with OPUS (other formats may be
     sent as Audio or Document).
     On success, the sent Message is returned.")

  (send-video-note [this content]
    [this chat_id video_note]
    [this chat_id video_note & optional]
    "Use this method to send video messages.
     On success, the sent Message is returned.")

  (send-media-group [this content]
    [this chat_id media]
    [this chat_id media & optional]
    "Use this method to send a group of photos or videos as an album.
     On success, an array of the sent Messages is returned.")

  (send-location [this content]
    [this chat_id latitude longitude]
    [this chat_id latitude longitude & optional]
    "Use this method to send point on the map.
     On success, the sent Message is returned.")

  (edit-message-live-location [this content]
    [this chat_id message_id latitude longitude]
    [this chat_id message_id latitude longitude & optional]
    "Use this method to edit live location messages.
     A location can be edited until its live_period expires or editing is
     explicitly disabled by a call to stopMessageLiveLocation.
     On success, if the edited message was sent by the bot, the edited
     Message is returned, otherwise True is returned.")

  (edit-message-live-location-inline [this content]
    [this inline_message_id latitude longitude]
    [this inline_message_id latitude longitude & optional]
    "Use this method to edit live location inline messages.
     A location can be edited until its live_period expires or editing is
     explicitly disabled by a call to stopMessageLiveLocation.
     On success, if the edited message was sent by the bot, the edited
     Message is returned, otherwise True is returned.")

  (stop-message-live-location [this content]
    [this chat_id message_id]
    [this chat_id message_id & optional]
    "Use this method to stop updating a live location message before live_period expires.
     On success, if the message was sent by the bot, the sent
     Message is returned, otherwise True is returned.")

  (stop-message-live-location-inline [this inline_message_id]
    [this inline_message_id & optional]
    "Use this method to stop updating a live location inline message before live_period expires.
     On success, if the message was sent by the bot, the sent
     Message is returned, otherwise True is returned.")

  (send-venue [this content]
    [this chat_id latitude longitude title address]
    [this chat_id latitude longitude title address & optional]
    "Use this method to send information about a venue.
     On success, the sent Message is returned.")

  (send-contact [this content]
    [this chat_id phone_number first_name]
    [this chat_id phone_number first_name last_name]
    [this chat_id phone_number first_name last_name & optional]
    "Use this method to send phone contacts.
     On success, the sent Message is returned.")

  (send-poll [this content]
    [this chat_id question options]
    [this chat_id question options & optional]
    "Use this method to send a native poll.
     On success, the sent Message is returned.")

  (send-dice [this chat_id]
    [this chat_id & optional]
    "Use this method to send an animated emoji that will display a random value.
     On success, the sent Message is returned.")

  (send-chat-action [this content]
    [this chat_id action]
    [this chat_id action & optional]
    "Use this method when you need to tell the user that something is happening
     on the bot's side. The status is set for 5 seconds or less (when a message
     arrives from your bot, Telegram clients clear its typing status).
     Returns True on success.")

  (get-user-profile-photos [this user_id]
    [this user_id & optional]
    "Use this method to get a list of profile pictures for a user.
     Returns a UserProfilePhotos object.")

  (get-file [this file_id]
    "Use this method to get basic info about a file and prepare it for downloading.
     For the moment, bots can download files of up to 20MB in size.
     On success, a File object is returned. The file can then be downloaded via
     the link https://api.telegram.org/file/bot`token`/`file_path`,
     where `file_path` is taken from the response.
     It is guaranteed that the link will be valid for at least 1 hour.
     When the link expires, a new one can be requested by calling getFile again.")

  (kick-chat-member [this content]
    [this chat_id user_id]
    [this chat_id user_id & optional]
    "DEPRECATED: Use this method to kick a user from a group, a supergroup or a channel.
     In the case of supergroups and channels, the user will not be able to
     return to the group on their own using invite links, etc., unless unbanned first.
     The bot must be an administrator in the chat for this to work and must have the
     appropriate admin rights.
     Returns True on success.")

  (ban-chat-member [this content]
    [this chat_id user_id]
    [this chat_id user_id & optional]
    "Use this method to ban a user from a group, a supergroup or a channel.
     In the case of supergroups and channels, the user will not be able to
     return to the group on their own using invite links, etc., unless unbanned first.
     The bot must be an administrator in the chat for this to work and must have the
     appropriate admin rights.
     Returns True on success.")

  (unban-chat-member [this content]
    [this chat_id user_id]
    [this chat_id user_id & optional]
    "Use this method to unban a previously kicked user in a supergroup or channel.
     The user will not return to the group or channel automatically, but will be able
     to join via link, etc. The bot must be an administrator for this to work.
     Returns True on success.")

  (restrict-chat-member [this content]
    [this chat_id user_id permissions]
    [this chat_id user_id permissions & optional]
    "Use this method to restrict a user in a supergroup.
     The bot must be an administrator in the supergroup for this to work and
     must have the appropriate admin rights. Pass True for all permissions to lift
     restrictions from a user.
     Returns True on success.")

  (promote-chat-member [this content]
    [this chat_id user_id]
    [this chat_id user_id & optional]
    "Use this method to promote or demote a user in a supergroup or a channel.
     The bot must be an administrator in the chat for this to work and must
     have the appropriate admin rights.
     Pass False for all boolean parameters to demote a user.
     Returns True on success.")

  (set-chat-administrator-custom-title [this content]
    [this chat_id user_id custom_title]
    "Use this method to set a custom title for an administrator in a supergroup
     promoted by the bot.
     Returns True on success.")

  (ban-chat-sender-chat [this content]
    [this chat_id sender_chat_id]
    [this chat_id sender_chat_id & optional]
    "Use this method to ban a channel chat in a supergroup or a channel.
    The owner of the chat will not be able to send messages and join live streams on
    behalf of the chat, unless it is unbanned first. The bot must be an administrator
    in the supergroup or channel for this to work and must have the appropriate administrator rights.
    Returns True on success.")

  (unban-chat-sender-chat [this content]
    [this chat_id sender_chat_id]
    "Use this method to unban a previously banned channel chat in a supergroup or channel.
    The bot must be an administrator for this to work and must have the appropriate administrator rights.
    Returns True on success.")

  (set-chat-permissions [this content]
    [this chat_id permissions]
    "Use this method to set default chat permissions for all members.
     The bot must be an administrator in the group or a supergroup for this to work
     and must have the can_restrict_members admin rights.
     Returns True on success.")

  (export-chat-invite-link [this chat_id]
    "Use this method to generate a new invite link for a chat; any previously generated
     link is revoked. The bot must be an administrator in the chat for this to work and
     must have the appropriate admin rights.
     Returns the new invite link as String on success.")

  (create-chat-invite-link [this chat_id]
    [this chat_id & optional]
    "Use this method to create an additional invite link for a chat.
     The bot must be an administrator in the chat for this to work and must have
     the appropriate admin rights. The link can be revoked using the method revokeChatInviteLink.
     Returns the new invite link as ChatInviteLink object.")

  (edit-chat-invite-link [this content]
    [this chat_id invite_link]
    [this chat_id invite_link & optional]
    "Use this method to edit a non-primary invite link created by the bot.
     The bot must be an administrator in the chat for this to work and must have the appropriate admin rights.
     Returns the edited invite link as a ChatInviteLink object.")

  (revoke-chat-invite-link [this content]
    [this chat_id invite_link]
    "Use this method to revoke an invite link created by the bot.
     If the primary link is revoked, a new link is automatically generated.
     The bot must be an administrator in the chat for this to work and must have the appropriate admin rights.
     Returns the revoked invite link as ChatInviteLink object.")

  (approve-chat-join-request [this content]
    [this chat_id user_id]
    "Use this method to approve a chat join request.
     The bot must be an administrator in the chat for this to work and
     must have the can_invite_users administrator right.
     Returns True on success.")

  (decline-chat-join-request [this content]
    [this chat_id user_id]
    "Use this method to decline a chat join request.
     The bot must be an administrator in the chat for this to work and
     must have the can_invite_users administrator right.
     Returns True on success.")

  (set-chat-photo [this content]
    [this chat_id photo]
    "Use this method to set a new profile photo for the chat.
     Photos can't be changed for private chats.
     The bot must be an administrator in the chat for this to work and
     must have the appropriate admin rights.
     Returns True on success.")

  (delete-chat-photo [this chat_id]
    "Use this method to delete a chat photo. Photos can't be changed for private chats.
     The bot must be an administrator in the chat for this to work and
     must have the appropriate admin rights.
     Returns True on success.")

  (set-chat-title [this content]
    [this chat_id title]
    "Use this method to change the title of a chat.
     Titles can't be changed for private chats.
     The bot must be an administrator in the chat for this to work and
     must have the appropriate admin rights.
     Returns True on success.")

  (set-chat-description [this content]
    [this chat_id description]
    "Use this method to change the description of a group, a supergroup or a channel.
     The bot must be an administrator in the chat for this to work and
     must have the appropriate admin rights.
     Returns True on success.")

  (pin-chat-message [this content]
    [this chat_id message_id]
    [this chat_id message_id & optional]
    "Use this method to pin a message in a group, a supergroup, or a channel.
     The bot must be an administrator in the chat for this to work and
     must have the 'can_pin_messages' admin right in the supergroup or
     'can_edit_messages' admin right in the channel.
     Returns True on success.")

  (unpin-chat-message [this chat_id]
    [this chat_id & optional]
    "Use this method to unpin a message in a group, a supergroup, or a channel.
     The bot must be an administrator in the chat for this to work and
     must have the 'can_pin_messages' admin right in the supergroup or
     'can_edit_messages' admin right in the channel.
     Returns True on success.")

  (unpin-all-chat-messages [this chat_id]
    "Use this method to clear the list of pinned messages in a chat.
     If the chat is not a private chat, the bot must be an administrator in
     the chat for this to work and must have the 'can_pin_messages' admin
     right in a supergroup or 'can_edit_messages' admin right in a channel.
     Returns True on success.")

  (leave-chat [this chat_id]
    "Use this method for your bot to leave a group, supergroup or channel.
     Returns True on success.")

  (get-chat [this chat_id]
    "Use this method to get up to date information about the chat
     (current name of the user for one-on-one conversations,
     current username of a user, group or channel, etc.).
     Returns a Chat object on success.")

  (get-chat-administrators [this chat_id]
    "Use this method to get a list of administrators in a chat.
     On success, returns an Array of ChatMember objects that contains information about
     all chat administrators except other bots.
     If the chat is a group or a supergroup and no administrators were appointed,
     only the creator will be returned.")

  (get-chat-members-count [this chat_id]
    "DEPRECATED: Use this method to get the number of members in a chat.
     Returns Int on success.")

  (get-chat-member-count [this chat_id]
    "Use this method to get the number of members in a chat.
     Returns Int on success.")

  (get-chat-member [this content]
    [this chat_id user_id]
    "Use this method to get information about a member of a chat.
     Returns a ChatMember object on success.")

  (set-chat-sticker-set [this content]
    [this chat_id sticker_set_name]
    "Use this method to set a new group sticker set for a supergroup.
     The bot must be an administrator in the chat for this to work and
     must have the appropriate admin rights.
     Use the field can_set_sticker_set optionally returned in getChat requests to
     check if the bot can use this method.
     Returns True on success.")

  (delete-chat-sticker-set [this chat_id]
    "Use this method to delete a group sticker set from a supergroup.
     The bot must be an administrator in the chat for this to work and
     must have the appropriate admin rights.
     Use the field can_set_sticker_set optionally returned in getChat requests to
     check if the bot can use this method.
     Returns True on success.")

  (answer-callback-query [this callback_query_id]
    [this callback_query_id & optional]
    "Use this method to send answers to callback queries sent from inline keyboards.
     The answer will be displayed to the user as a notification at the top of the
     chat screen or as an alert.
     On success, True is returned.")

  (set-my-commands [this commands]
    [this commands & optional]
    "Use this method to change the list of the bot's commands.
     Returns True on success.")

  (delete-my-commands [this]
    [this content]
    "Use this method to delete the list of the bot's commands for the given scope and user language.
     After deletion, higher level commands will be shown to affected users.
     Returns True on success.")

  (get-my-commands [this]
    [this content]
    "Use this method to get the current list of the bot's commands.
     Returns Array of BotCommand on success."))
