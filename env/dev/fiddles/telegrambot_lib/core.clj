(ns telegrambot-lib.core
  "Calva Fiddle File - core functionality
   This fiddle file was made to work with Calva, a vscode extension.
   It assumes a \"profiles.clj\" exists in the project root (git ignored) with:
   {:local {:env {:bot-token \"MY-BOT-TOKEN-HERE\"
                  :chat-id \"MY-CHAT-ID-HERE\"}}}"
  {:clj-kondo/config
   '{:linters {:unresolved-symbol {:level :off}}}}
  (:require [environ.core :as environ]
            [clojure.pprint]))

; Assumes bot-token is available from the environ or local profiles.clj.
(println "Create bot")
(def bot (create))

(println "\nCall function: get-me")
(clojure.pprint/pprint (get-me bot))

; Assumes chat-id is available from the environ or local profiles.clj.
(println "\nCall function: send-message")
(clojure.pprint/pprint
 (send-message bot (:chat-id environ/env)
               (format "Test message - %s." (str (java.time.LocalDateTime/now)))))
