(ns telegrambot-lib.http-test
  (:require [clojure.test :refer [deftest is testing]]
            [clj-http.fake :as fake]
            [telegrambot-lib.http :as http]))

(def test-bot
  "A bot data structure for running tests."
  {:bot-api "https://api.telegram.org/bot"
   :bot-token "1234"
   :async false})

(deftest gen-url-test
  (testing "Verify URL generation."
    (is (= "https://api.telegram.org/bot1234/myPath"
           (http/gen-url test-bot
                         "myPath")))))

(deftest map->multipart-test
  (testing "Request content map transformation test."
    (is (= (http/map->multipart {:url "https://mybotwebhook.me"
                                 :certificate "/home/bot/certs/mybot.pem"})
           {:multipart
            [{:name "url", :content "https://mybotwebhook.me"}
             {:name "certificate", :content "/home/bot/certs/mybot.pem"}]}))
    (is (= (http/map->multipart nil)
           nil))))

(def ok-resp
  {:cached nil
   :request-time 545
   :repeatable? false
   :protocol-version {:name "HTTP", :major 1, :minor 1}
   :streaming? true
   :chunked? false
   :reason-phrase "OK"
   :headers
   {"Access-Control-Expose-Headers" "Content-Length,Content-Type,Date,Server,Connection"
    "Server" "nginx/1.18.0"
    "Content-Type" "application/json"
    "Access-Control-Allow-Origin" "*"
    "Content-Length" "196"
    "Strict-Transport-Security" "max-age=31536000; includeSubDomains; preload"
    "Connection" "close"
    "Access-Control-Allow-Methods" "GET, POST, OPTIONS"
    "Date" "Sat, 23 Jul 2022 22:37:36 GMT"}
   :orig-content-encoding nil
   :status 200
   :length 196
   :body
   "{\"ok\":true,\"result\":{\"id\":123,\"is_bot\":true,\"first_name\":\"mybot\",\"username\":\"my_roboto\",\"can_join_groups\":true,\"can_read_all_group_messages\":true,\"supports_inline_queries\":false}}"
   :trace-redirects []})

(deftest request-ok-test
  (testing "HTTP request to a valid endpoint with an ok response."
    (fake/with-fake-routes {"https://api.telegram.org/bot1234/getMe"
                            (fn [_] ok-resp)}
      (let [resp (http/request test-bot "getMe")]

        (is (= resp {:ok true,
                     :result
                     {:id 123,
                      :is_bot true,
                      :first_name "mybot",
                      :username "my_roboto",
                      :can_join_groups true,
                      :can_read_all_group_messages true,
                      :supports_inline_queries false}}))))))

(def not-found-resp
  {:cached nil
   :request-time 545
   :repeatable? false
   :protocol-version {:name "HTTP", :major 1, :minor 1}
   :streaming? true
   :chunked? false
   :reason-phrase "Not Found"
   :headers
   {"Access-Control-Expose-Headers" "Content-Length,Content-Type,Date,Server,Connection"
    "Server" "nginx/1.18.0"
    "Content-Type" "application/json"
    "Access-Control-Allow-Origin" "*"
    "Content-Length" "196"
    "Strict-Transport-Security" "max-age=31536000; includeSubDomains; preload"
    "Connection" "close"
    "Access-Control-Allow-Methods" "GET, POST, OPTIONS"
    "Date" "Sat, 23 Jul 2022 22:37:36 GMT"}
   :orig-content-encoding nil
   :status 404
   :length 55
   :body
   "{\"ok\":false,\"error_code\":404,\"description\":\"Not Found\"}"
   :trace-redirects []})

(deftest request-not-found-test
  (testing "HTTP request to an invalid endpoint."
    (fake/with-fake-routes {"https://api.telegram.org/bot1234/doesNotExist"
                            (fn [_] not-found-resp)}
      (let [resp (http/request test-bot "doesNotExist")]

        (is (= (:ok resp) false))
        (is (= (:error_code resp) 404))
        (is (= (:description resp) "Not Found"))
        (is (:error resp))))))
