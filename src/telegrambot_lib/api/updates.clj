(ns telegrambot-lib.api.updates
  "Getting updates - function implementations.
   - <https://core.telegram.org/bots/api#getupdates>

   Most functions are multi-arity with the following options:
   - Send all parameters in a 'content' map.
   - Send only the required parameters as simple values.
   - Send the required paraemters as simple values and then 'optional' parameters in a map."
  (:gen-class)
  (:require [telegrambot-lib.http :as http]
            [telegrambot-lib.predicates :refer [content-map?]]))

(defn get-updates
  "Use this method to receive incoming updates using long polling.
   An Array of Update objects is returned.

   Required
   - this ; a bot instance

   Optional - map of the following
   - offset ; id of first update to return
   - limit ; num of updates to retrieve (1-100). default: 100
   - timeout ; timeout in seconds. default: 0 (short poll)
   - allowed_updates ; json array of update types bot will receive"
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
   Returns True on success.

   Required
   - this ; a bot instance
   - url ; https url to send updates to. empty string to remove webhook

   Optional
   - certificate ; upload public key cert
   - ip_address ; use this IP instead of resolving URL in DNS
   - max_connections ; max allowed simultaneous https connections for updates. (1-100) default: 40
   - allowed_updates ; json array of update types bot will receive
   - drop_pending_updates ; Pass True to drop all pending updates
   - secret_token ; 1-256 chars. Sent in header (X-Telegram-Bot-Api-Secret-Token)
                    to ensure request comes from your webhook."
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

   Required
   - this ; a bot instance

   Optional
   - drop_pending_updates ; Pass True to drop all pending updates"
  ([this]
   (delete-webhook this nil))

  ([this content]
   (http/request this "deleteWebhook" content)))

(defn get-webhook-info
  "Use this method to get current webhook status.
   On success, returns a WebhookInfo object.
   If the bot is using getUpdates, will return an object with the
   url field empty.

   Required
   - this ; a bot instance"
  [this]
  (http/request this "getWebhookInfo"))
