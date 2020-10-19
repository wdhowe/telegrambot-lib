(ns telegrambot-lib.passport.params
  "Telegram Passport - parameter definitions."
  (:gen-class))

(defrecord set-passport-data-errors-params
           [;; Required
            user_id ; user identifier
            errors ; json array of 'PassportElementError' describing the errors
            ])
