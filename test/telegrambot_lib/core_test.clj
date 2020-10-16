(ns telegrambot-lib.core-test
  (:require [clojure.test :refer :all]
            [telegrambot-lib.core :as lib-core]))

(deftest create-test-1
  (testing "Test TBot instance creation - token."
    (let [mybot (lib-core/create "123456")]
      (is (= "123456" (:bot-token mybot))))))
