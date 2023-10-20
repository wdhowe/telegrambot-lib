(ns telegrambot-lib.core
  "Calva Fiddle File - core functionality
   This fiddle file was made to work with Calva, a vscode extension.
   It assumes a \"profiles.clj\" exists in the project root (git ignored) with:
   {:local {:env {:bot-token \"MY-BOT-TOKEN-HERE\"
                  :chat-id \"MY-CHAT-ID-HERE\"
                  :cert-url \"MY-CERT-URL-HERE\"
                  :cert-file \"/PATH/TO/CERT/FILE\"}}}"
  {:clj-kondo/config
   '{:linters {:unresolved-symbol {:level :off}}}}
  (:require [environ.core :as environ]
            [clojure.java.io]
            [clojure.pprint]))

; Assumes bot-token is available from the environ or local profiles.clj.
(println "---------- Create bot ----------")
(def bot (create))

(println "\n---------- Call function: get-me ----------")
(clojure.pprint/pprint (get-me bot))

; Assumes chat-id is available from the environ or local profiles.clj.
(println "\n---------- Call function: send-message ----------")
(clojure.pprint/pprint
 (send-message bot (:chat-id environ/env)
               (format "Test message - %s." (str (java.time.LocalDateTime/now)))))

(println "\n---------- Call function: get-updates (long check) ----------")
(clojure.pprint/pprint
 (get-updates bot {:timeout 10}))

(println "\n---------- Call function: get-updates (nil check) ----------")
(clojure.pprint/pprint
 (get-updates bot {:offset nil
                   :timeout 10}))

; Assumes cert-url and cert-file are available from the environ or local profiles.clj.
(println "\n---------- Call function: set-webhook ----------")
(clojure.pprint/pprint
 (set-webhook bot {:url (:cert-url environ/env)
                   :certificate (clojure.java.io/file (:cert-file environ/env))
                   :content-type :multipart}))

(println "\n---------- Call function: get-webhook ----------")
(clojure.pprint/pprint
 (get-webhook-info bot))

(println "\n---------- Call function: set-webhook nil ----------")
(clojure.pprint/pprint
 (set-webhook bot ""))
