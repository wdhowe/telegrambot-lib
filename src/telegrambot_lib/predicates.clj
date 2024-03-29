(ns telegrambot-lib.predicates
  "General functions that return true/false."
  (:gen-class))

(defn content-map?
  "Used throughout the multi-methods in order to check if content is a map or not."
  {:added "2.0.0"}
  [_ content & _]
  (map? content))
