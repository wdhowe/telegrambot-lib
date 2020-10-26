(ns telegrambot-lib.passport.protocol
  "Telegram Passport - protocol definitions.
   
   * https://core.telegram.org/bots/api#telegram-passport")

(defprotocol Passport
  "Telegram Passport is a unified authorization method for services
   that require personal identification. Users can upload their
   documents once, then instantly share their data with services
   that require real-world ID (finance, ICOs, etc.)."

  (set-passport-data-errors [this content]
    [this user_id errors]
    "Informs a user that some of the Telegram Passport elements they
     provided contains errors."))
