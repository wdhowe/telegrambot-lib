# Getting and Sending Messages

- [Home](index.md)
- [Advanced](Advanced.md)
- [Available Functions](Available-Functions.md)
- Getting and Sending Messages
- [In the Wild](In-the-Wild.md)
- [Miscellaneous](Miscellaneous.md)

## Bot Prep

In the Telegram client:

- Add your bot to a group chat/channel or message it directly, in a private chat.
- If your bot needs to read all messages, even if the message is not sent to it (directly or via slash commands), make sure to disable the
  [privacy mode](https://core.telegram.org/bots#privacy-mode) or make the bot an "admin" first.

On the side of your bot:

- The bot can [get updates](https://core.telegram.org/bots/api/#getting-updates) either via [long polling](https://core.telegram.org/bots/api/#getupdates) or a [webhook](https://core.telegram.org/bots/api/#setwebhook).

## Long Polling

Long polling is simply checking for new updates from the telegram bot servers every so often.

Here is an example of what a polling function could look like:

```clojure
(def config
  {:timeout 10}) ;the bot api timeout is in seconds

(defn poll-updates
  "Long poll for recent chat messages from Telegram."
  ([bot]
   (poll-updates bot nil))

  ([bot offset]
   (let [resp (tbot/get-updates bot {:offset offset
                                     :timeout (:timeout config)})]
     (if (contains? resp :error)
       (log/error "tbot/get-updates error:" (:error resp))
       resp))))
```

When using the poll method of getting updates, you would probably have some sort of application loop.

- poll for updates
- handle each message
- track the update-id (in order to not process the same message on the next poll)
- optionally sleep/wait a bit before polling again

That could look something like this:

```clojure
(def config
  {:sleep 10000}) ;thread/sleep is in milliseconds

(defonce update-id (atom nil))

(defn set-id!
  "Sets the update id to process next as the the passed in `id`."
  [id]
  (reset! update-id id))

(defn app
  "Retrieve and process chat messages."
  [bot]
  (log/info "bot service started.")

  (loop []
    (log/debug "checking for chat updates.")
    (let [updates (poll-updates bot @update-id)
          messages (:result updates)]

      ;; Check all messages, if any, for commands/keywords.
      (doseq [msg messages]
        ;(some-handle-msg-fn bot msg) ; your fn that decides what to do with each message.

        ;; Increment the next update-id to process.
        (-> msg
            :update_id
            inc
            set-id!))

      ;; Wait a while before checking for updates again.
      (Thread/sleep (:sleep config)))
    (recur)))
```

## Webhook

See the Telegram BOT API documentation for details on [setting up a webhook](https://core.telegram.org/bots/api/#setwebhook).

**Uploading a file, such as this example below, requires the content-type to be multipart.**

```clojure
;; Custom cert webhook setting example
(set-webhook bot {:url "https://my.url.com"
                  :certificate (clojure.java.io/file "/path/to/cert.pem")
                  :content-type :multipart})
```

Once setup, your bot will need to listen for updates sending a POST to it.

An example using [Compojure](https://github.com/weavejester/compojure):

{% raw %}

```clojure
(ns grossbuchbot.web-handler
  (:require [compojure.core :as cmpj :refer [POST]]
            [ring.middleware
             [defaults :as rmd]
             [json :refer [wrap-json-body wrap-json-response]]]
            <other requirements like `config` or `log`>))

;...

;; NB: The main fn for an incoming `Update` object processing.
(defn handle [update]
  (log/debug "Received update:" update)
  <handle the update and return some `op-result`>)

;...

(def api-routes
  (cmpj/context "/api" []
    (POST "/:token" {{token :token} :route-params
                     update :body}
      (if (= token (config/get-prop :bot-api-token))
        (let [op-result (handle update)]
          <translate the `op-result` to a response map>)
        <return a `404` response map>))))

;...

(cmpj/defroutes
  app-routes

  (-> (rmd/wrap-defaults api-routes rmd/api-defaults)
      (wrap-json-body {:keywords? true})
      (wrap-json-response))

  <other web app routes>)
```

{% endraw %}

## Determining the Chat and User

Regardless of what method (long polling/webhook) you use to get updates, you will get [update objects](https://core.telegram.org/bots/api/#update) back.

This is an example of the `get-updates` function return value.

```clojure
(tbot/get-updates mybot)
=>
{:ok true
 :result [{:update_id 760000018
           :message {:message_id 9
                     :from {:id 280000000, :is_bot false, :first_name "Bill", :last_name "Howe", :username "myusername", :language_code "en"}
                     :chat {:id 280000000, :first_name "Bill", :last_name "Howe", :username "myusername", :type "private"}
                     :date 1602815917
                     :text "Oh hi bot!"}}
          ;.....
          {:update_id 760000010
           :message {:message_id 1
                     :from {:id 123, :is_bot true, :first_name "mybot", :username "my_roboto"}
                     :chat {:id 280000000, :first_name "Bill", :last_name "Howe", :username "myusername", :type "private"}
                     :date 1602813645
                     :text "Hello Bot World!"}}]}
```

In this case, since we are in a private chat with the bot, the sender (the `message` > `from` > `id`)
is either the bot itself or the same as the message chat (the `message` > `chat` > `id`).

## Sending a Message into a Chat

Send your message to the target `chat_id`. Details on the different [parameter options here](https://github.com/wdhowe/telegrambot-lib/wiki/Available-Functions#functions-contract).

```clojure
;; Option 1: send all `content` as a map
(tbot/send-message mybot {:chat_id 280000000 :text "Oh hi yourself."})

;; Option 2: send only required parameters as simple values
(tbot/send-message mybot 280000000 "Oh hi yourself.")

;; Option 3: send required params as simple values + `optional` params as a map
(tbot/send-message mybot 280000000 "Oh hi yourself." {:disable_notification true})
```

In all three cases you'll get a similar response parsed into a Clojure map:

```clojure
=>
{:ok true
 :result {:message_id 10
          :from {:id 123, :is_bot true, :first_name "mybot", :username "my_roboto"}
          :chat {:id 280000000, :first_name "Bill", :last_name "Howe", :username "myusername", :type "private"}
          :date 1602816282
          :text "Oh hi yourself."}}
```

Notice the incrementing `message_id` number. This is useful for keeping track of the message order and which messages
have already been processed. Save it and use it later as the `get-updates` optional `offset` parameter.
