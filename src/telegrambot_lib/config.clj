(ns telegrambot-lib.config
  (:gen-class)
  (:require [environ.core :as environ]
            [taoensso.timbre :as log]))

(defn get-token
  "Attempt to load the bot api token from the environment."
  []
  (:bot-token environ/env))

(defn set-log-level
  "Set the log level from the environment or use a default value."
  []
  (log/set-level! (or (:log-level environ/env) :debug)))
