(ns telegrambot-lib.passport.params
  "Telegram Passport - parameter definitions."
  (:gen-class))

(defrecord set-passport-data-errors
           [;; Required
            user_id ; user identifier
            errors ; json array of 'PassportElementError' describing the errors
            ])
