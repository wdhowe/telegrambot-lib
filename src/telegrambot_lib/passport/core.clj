(ns telegrambot-lib.passport.core
  "Telegram Passport - function implementations.
   
   * https://core.telegram.org/bots/api#telegram-passport"
  (:gen-class)
  (:require [telegrambot-lib.http :as http]))

(defn set-passport-data-errors
  "Informs a user that some of the Telegram Passport elements they provided contains errors."
  [this content]
  (http/request this "setPassportDataErrors" content))

(def behavior
  "Map for extending the core TBot record with functions."
  {:set-passport-data-errors set-passport-data-errors})
