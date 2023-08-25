(ns telegrambot-lib.http
  "Formats and sends the HTTP request to the Telegram Bot API."
  (:gen-class)
  (:require [clj-http.client :as clj-http]
            [clojure.core.async :as a]
            [clojure.string :as string]
            [clojure.tools.logging :as log]
            [telegrambot-lib.json :as json]))

(defn- upper-case-name
  "Transform the passed in value to an all upper cased name."
  [x]
  (string/upper-case (name x)))

(defmulti client
  "Send HTTP requests to the URL. Methods supported:
   - POST"
  (fn [http-method & _] http-method))

(defmethod client :post
  ([http-method url]
   (client http-method url nil))

  ([http-method url req & [respond raise]]
   (log/debugf "HTTP %s /%s %s"
               (upper-case-name http-method)
               (last (string/split url #"/"))
               req)
   (clj-http/post url req respond raise)))

(defmethod client :default
  [http-method & _]
  (log/errorf "HTTP method '%s' not supported"
              (upper-case-name http-method)))

(defn gen-url
  "Generate the url to use for the http call, given the method `path`."
  [this path]
  (format "%s%s/%s" (:bot-api this) (:bot-token this) path))

(defn parse-resp
  "Parse the JSON response body into a keywordized map."
  [resp]
  (try
    (-> resp :body json/parse-str)
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

(defn map->multipart
  "Transform a map's key/value pairs into a multipart map,
   with a list of maps with new keys.
   Eg: {:multipart [{:name key, :content value}] }"
  [m]
   (when (some? m)
     {:multipart (mapv #(assoc {} :name (name (first %)) :content (second %))
                       m)}))

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
         req (merge (map->multipart content)
                    {:async? true})
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
         req (map->multipart content)]
     (try
       (let [resp (client :post url req)]
         (parse-resp resp))
       (catch Throwable ex
         (ex->parsed-resp ex))))))
