(ns telegrambot-lib.config
  "Global configuration/settings."
  (:gen-class)
  (:require [environ.core :as environ]))

(defn get-token
  "Attempt to load the bot API token (BOT_TOKEN) from the environment."
  []
  (:bot-token environ/env))

(defn cfg
  "The default configuration."
  []
  {:async false
   :bot-api (or (:bot-api environ/env) "https://api.telegram.org/bot")
   :bot-token (:bot-token environ/env)})
