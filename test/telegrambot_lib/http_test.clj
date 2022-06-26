(ns telegrambot-lib.http-test
  (:require [clojure.test :refer [deftest is testing]]
            [telegrambot-lib.http :as http]))

(deftest gen-url-test
  (testing "Verify URL generation."
    (is (= "https://api.telegram.org/bot1234/myTest"
           (http/gen-url {:bot-token "1234"} "myTest")))))

;; todo - test for a good url response with clj-http-fake
;;        (for showing this project's expected http return data structures)

;; todo - test for a bad url response with clj-http-fake
