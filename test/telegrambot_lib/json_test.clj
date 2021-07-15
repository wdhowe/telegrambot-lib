(ns ^:json telegrambot-lib.json-test
  (:require [clojure.test :refer [deftest is testing]]
            [telegrambot-lib.json :as json]))

(def json-str "{\"name\":\"Murmansk\",\"founded-in\":1916,\"geo\":{\"lat\":68.58,\"long\":33.05},\"aurora-polaris\":true}")
(def json-obj {:name "Murmansk"
               :founded-in 1916
               :geo {:lat 68.58
                     :long 33.05}
               :aurora-polaris true})

(deftest parse-json-str
  (testing "JSON decoding"
    (is (= json-obj (json/parse-str json-str)))))

(deftest generate-json-str
  (testing "JSON encoding"
    (is (= json-str (json/generate-str json-obj)))))
