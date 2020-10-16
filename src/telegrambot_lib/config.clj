(ns telegrambot-lib.config
  (:gen-class)
  (:require [environ.core :as environ]))

(defn get-token
  "Attempt to load the bot api token from the environment."
  []
  (:bot-token environ/env))
