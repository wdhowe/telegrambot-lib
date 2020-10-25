(ns telegrambot-lib.updates.core
  "Getting updates - function implementations.
   
   * https://core.telegram.org/bots/api#getupdates

   Most functions are multi-arity with the following options:

   - Send all parameters in a 'content' map.
   - Send only the required parameters as simple values.
   - Send the required paraemters as simple values and then 'optional' parameters in a map."

  (:gen-class)
  (:require [telegrambot-lib.http :as http]))

(defn content-map?
  "Used throughout the multi-methods in order to check if content is a map or not."
  [_ content & _]
  (map? content))

(defn get-updates
  "Use this method to receive incoming updates using long polling.
   An Array of Update objects is returned."
  ([this]
   (get-updates this nil))
  ([this content]
   (http/request this "getUpdates" content)))

(defmulti set-webhook
  "Use this method to specify a url and receive incoming updates via
   an outgoing webhook. Whenever there is an update for the bot, we
   will send an HTTPS POST request to the specified url, containing
   a JSON-serialized Update. In case of an unsuccessful request, we
   will give up after a reasonable amount of attempts.
   Returns True on success."
  content-map?)

(defmethod set-webhook true
  [this content]
  (http/request this "setWebhook" content))

(defmethod set-webhook false
  ([this url]
   (let [content {:url url}]
     (set-webhook this content)))

  ([this url & optional]
   (let [content (merge (first optional)
                        {:url url})]
     (set-webhook this content))))

(defn delete-webhook
  "Use this method to remove webhook integration if you decide to
   switch back to getUpdates.
   Returns True on success.
   Requires no parameters."
  [this]
  (http/request this "deleteWebhook"))

(defn get-webhook
  "Use this method to get current webhook status.
   Requires no parameters.
   On success, returns a WebhookInfo object.
   If the bot is using getUpdates, will return an object with the
   url field empty."
  [this]
  (http/request this "getWebhook"))

(def behavior
  "Map for extending the core TBot record with functions."
  {:get-updates get-updates
   :set-webhook set-webhook
   :delete-webhook delete-webhook
   :get-webhook get-webhook})
