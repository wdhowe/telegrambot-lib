(ns telegrambot-lib.core-test
  (:require [clojure.test :refer :all]
            [telegrambot-lib.core :as lib-core]))

(deftest create-test-1
  (testing "Test TBot instance creation - token."
    (let [mybot (lib-core/create "123456")]
      (is (= "123456" (:bot-token mybot)))
      (is (= :info (:log-level mybot))))))

(deftest create-test-2
  (testing "Test TBot instance creation - token + log level."
    (let [mybot (lib-core/create "123456" :debug)]
      (is (= "123456" (:bot-token mybot)))
      (is (= :debug (:log-level mybot))))))
