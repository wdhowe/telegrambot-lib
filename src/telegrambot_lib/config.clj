(ns telegrambot-lib.config
  "Global configuration/settings."
  (:gen-class)
  (:require [environ.core :as environ]
            [taoensso.timbre :as log]))

(defn get-token
  "Attempt to load the bot api token (BOT_TOKEN) from the environment."
  []
  (:bot-token environ/env))

(defn set-log-level
  "Set the log level from the environment or use the default value (debug)."
  []
  (log/set-level! (or (:log-level environ/env) :debug)))
