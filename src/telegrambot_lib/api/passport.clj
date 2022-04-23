(ns telegrambot-lib.api.passport
  "Telegram Passport - function implementations.
   - <https://core.telegram.org/bots/api#telegram-passport>

   Most functions are multi-arity with the following options:
   - Send all parameters in a 'content' map.
   - Send only the required parameters as simple values.
   - Send the required paraemters as simple values and then 'optional' parameters in a map."
  (:gen-class)
  (:require [telegrambot-lib.http :as http]))

(defn set-passport-data-errors
  "Informs a user that some of the Telegram Passport elements they provided contains errors.

   Required
   - this ; a bot instance
   - user_id ; user identifier
   - errors ; json array of 'PassportElementError' describing the errors"
  ([this content]
   (http/request this "setPassportDataErrors" content))

  ([this user_id errors]
   (let [content {:user_id user_id
                  :errors errors}]
     (set-passport-data-errors this content))))
