(ns telegrambot-lib.config-test
  (:require [clojure.test :refer :all]
            [telegrambot-lib.config :as conf]))

(deftest custom-settings-test-1
  (testing "Custom settings - token only."
    (let [c (conf/custom-settings {:bot-token "123"})]
      (is (= "123" (:bot-token c)))
      (is (= :info (:log-level c))))))

(deftest custom-settings-test-2
  (testing "Custom settings - token and log-level."
    (let [c (conf/custom-settings {:bot-token "123", :log-level :debug})]
      (is (= "123" (:bot-token c)))
      (is (= :debug (:log-level c))))))
