(ns telegrambot-lib.config
  "Global configuration/settings."
  (:gen-class)
  (:require [environ.core :as environ]))

(defn cfg
  "The default configuration data structure for a bot."
  {:added "2.9.0"}
  []
  {:bot-token (:bot-token environ/env)
   :async     false
   :bot-api   (or (:bot-api environ/env) "https://api.telegram.org/bot")})
