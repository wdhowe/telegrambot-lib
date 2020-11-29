(ns telegrambot-lib.http
  "Formats and sends the http request to the Telegram Bot API."
  (:gen-class)
  (:require [cheshire.core :as json]
            [clj-http.client :as clj-http]
            [clojure.string :as string]
            [taoensso.timbre :as log]))

(defmulti client
  "Send http requests to the url. Methods supported:
   - post"
  (fn [http-method & _] http-method))

(defmethod client :post
  ([http-method url]
   (client http-method url nil))

  ([http-method url content]
   (client http-method url content :auto))

  ([http-method url content content-type]
   (log/debug "http"
              (string/upper-case (name http-method))
              (str "/" (last (string/split url #"/")))
              content)
   (clj-http/post url
                  {:body content
                   :content-type content-type})))

(defmethod client :default
  [http-method & _]
  (log/error http-method "http method not supported"))

(def bot-api "Telegram Bot API URL." "https://api.telegram.org/bot")

(defn gen-url
  "Generate the url to use for the http call, given the method `path`."
  [this path]
  (str bot-api (:bot-token this) "/" path))

(defn parse-resp
  "Parse the json response body into a keywordized map."
  [resp]
  (-> resp
      (:body)
      (json/parse-string true)))

(defn request
  "Send the request to the http `path` with optional `content`."
  ([this path]
   (request this path nil))

  ([this path content]
   (let [url (gen-url this path)
         resp (client :post url (json/generate-string content) :json)]
     (parse-resp resp))))
