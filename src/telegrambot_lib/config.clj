(ns telegrambot-lib.config
  "Global configuration/settings."
  (:gen-class)
  (:require [environ.core :as environ]))

(defn cfg
  "The default configuration data structure for a bot."
  []
  {:async false
   :bot-api (or (:bot-api environ/env) "https://api.telegram.org/bot")
   :bot-token (:bot-token environ/env)})
