(ns telegrambot-lib.http
  (:gen-class)
  (:require [cheshire.core :as json]
            [clj-http.client :as clj-http]
            [taoensso.timbre :as log]))

(defmulti client
  "Send http requests to the url endpoint."
  (fn [http-method & more] http-method))

(defmethod client :post
  ([http-method url]
   (client http-method url nil))

  ([http-method url content]
   (client http-method url content :auto))

  ([http-method url content content-type]
   (log/debug "request:" (name http-method) content)
   (clj-http/post url
                  {:body content
                   :content-type content-type})))

(defmethod client :default
  [http-method & more]
  (log/error http-method "http method not supported"))

(def bot-api "https://api.telegram.org/bot")

(defn gen-url
  "Generate the url to use for the http call."
  [this endpoint]
  (str bot-api (:token this) "/" endpoint))

(defn parse-resp
  "Parse the endpoint response."
  [resp]
  (-> resp
      (:body)
      (json/parse-string true)))

(defn request
  "Send the request to the http endpoint."
  ([this endpoint]
   (request this endpoint nil))

  ([this endpoint content]
   (let [url (gen-url this endpoint)
         resp (client :post url (json/generate-string content) :json)]
     (parse-resp resp))))
