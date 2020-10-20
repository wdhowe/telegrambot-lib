(ns telegrambot-lib.updates.params
  "Getting updates - parameter definitions."
  (:gen-class))

(defrecord get-updates-params
           [;; Optional
            offset ; id of first update to return
            limit ; num of updates to retrieve (1-100). default: 100
            timeout ; timeout in seconds. default: 0 (short poll)
            allowed_updates ; json array of update types bot will receive
            ])

(defrecord set-webhook-params
           [;; Required
            url ; https url to send updates to. empty string to remove webhook
            ;; Optional
            certificate ; upload public key cert
            max_connections ; max allowed simultaneous https connections for updates. (1-100) default: 40
            allowed_updates ; json array of update types bot will receive
            ])
