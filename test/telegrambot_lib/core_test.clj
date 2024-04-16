(ns telegrambot-lib.core-test
  (:require [clojure.test :refer [deftest is testing]]
            [telegrambot-lib.core :as lib-core]))

(deftest create-test-noparams
  (testing "Test bot creation - no params specified."
    (let [mybot (lib-core/create)]
      (is (= nil (:bot-token mybot)))
      (is (= false (:async mybot)))
      (is (= "https://api.telegram.org/bot" (:bot-api mybot)))
      (is (= {} (-> mybot :headers (apply [])))))))

(deftest create-test-token
  (testing "Test bot creation - token specified."
    (let [mybot (lib-core/create "123456")]
      (is (= "123456" (:bot-token mybot)))
      (is (= false (:async mybot)))
      (is (= "https://api.telegram.org/bot" (:bot-api mybot)))
      (is (= {} (-> mybot :headers (apply [])))))))

(deftest create-test-parammap
  (testing "Test bot creation - param map specified."
    (let [mybot (lib-core/create {:bot-token "123456" :headers (constantly {"ABC" "123"})})]
      (is (= "123456" (:bot-token mybot)))
      (is (= false (:async mybot)))
      (is (= "https://api.telegram.org/bot" (:bot-api mybot)))
      (is (= {"ABC" "123"} (-> mybot :headers (apply [])))))))
