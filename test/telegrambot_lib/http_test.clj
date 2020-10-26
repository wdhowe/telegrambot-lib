(ns telegrambot-lib.http-test
  (:require [clojure.test :refer [deftest is testing]]
            [telegrambot-lib.http :as http]))

(defrecord faux-bot [bot-token])

(deftest gen-url-test
  (testing "Verify URL generation."
    (let [mybot (map->faux-bot {:bot-token "1234"})
          url (http/gen-url mybot "myTest")]
      (is (= "https://api.telegram.org/bot1234/myTest" url)))))
