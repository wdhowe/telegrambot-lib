(ns telegrambot-lib.config
  (:gen-class)
  (:require [environ.core :as environ]))

(def default-settings
  "Default settings map for the TBot."
  {:log-level :info
   :token nil})

(defn custom-settings
  "Create a merged settings map.
   Custom fields over ride defaults."
  [custom]
  (merge default-settings custom))

(comment
  (custom-settings {:token "test123"})
  (custom-settings {:token "test123"
                    :log-level :debug}))

(defn get-token
  "Attempt to load the bot api token from the environment."
  []
  (:bot-token environ/env))
