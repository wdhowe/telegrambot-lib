(ns telegrambot-lib.http
  "Formats and sends the http request to the Telegram Bot API."
  (:gen-class)
  (:require [clojure.core.async :as a]
            [cheshire.core :as json]
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

  ([http-method url req & [respond raise]]
   (log/debug "http"
              (string/upper-case (name http-method))
              (str "/" (last (string/split url #"/")))
              (:body req))
   (let [req (merge {:content-type :auto} req)]
     (clj-http/post url req respond raise))))

(defmethod client :default
  [http-method & _]
  (log/error http-method "http method not supported"))

(def bot-api "Telegram Bot API URL." "https://api.telegram.org/bot")

(defn gen-url
  "Generate the url to use for the http call, given the method `path`."
  [this path]
  (str bot-api (:bot-token this) "/" path))

(defn parse-resp
  "Parse the JSON response body into a keywordized map."
  [resp]
  (try
    (-> resp :body (json/parse-string true))
    (catch Exception parsing-ex
      {:error parsing-ex})))

(defn ex->parsed-resp
  "Uniformly transform an `Exception` into a map to return."
  [ex]
  (let [body (parse-resp (ex-data ex))]
    ;; NB: This may overwrite the parsing exception, if any,
    ;;     but it's totally fine, since the original `ex` is
    ;;     more important.
    (assoc body :error ex)))

(defmulti request
  "Send the request to the Telegram Bot API `path` with an optional `content`,
   either sync- or asynchronously, depending on the value of the `:async` key
   of `this` (the passed bot instance). A sync version returns an \"invocation
   result\", while an async one returns a one-off channel with an \"invocation
   result\" which is then closed.

   The \"invocation result\" is either:
   - the response body in case the Telegram Bot API request was successful, or
   - a map composed of the response body and the `[:error ex]` entry when the
     request was unsuccessful (in terms of the Telegram Bot API), or
   - just an `{:error ex}` map in any other exceptional situation when we are
     unable to retrieve the response body."
  (fn [this & _]
    (true? (:async this))))

(defmethod request true
  ([this path]
   (request this path nil))

  ([this path content]
   (let [url (gen-url this path)
         req {:body (json/generate-string content)
              :content-type :json
              :async? true}
         resp-channel (a/chan)
         on-success (fn [resp]
                      (let [parsed-resp (parse-resp resp)]
                        (a/put! resp-channel parsed-resp))
                      (a/close! resp-channel))
         on-failure (fn [ex]
                      (let [parsed-resp (ex->parsed-resp ex)]
                        (a/put! resp-channel parsed-resp))
                      (a/close! resp-channel))]
     (client :post url req on-success on-failure)
     resp-channel)))

(defmethod request false
  ([this path]
   (request this path nil))

  ([this path content]
   (let [url (gen-url this path)
         req {:body (json/generate-string content)
              :content-type :json}]
     (try
       (let [resp (client :post url req)]
         (parse-resp resp))
       (catch Throwable ex
         (ex->parsed-resp ex))))))
