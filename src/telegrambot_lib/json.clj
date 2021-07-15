(ns telegrambot-lib.json
  "Internal JSON mapping utilities."
  (:gen-class)
  (:require [clojure.string :as string]))

(defn- resolve-fn-var [ns var-name]
  (ns-resolve ns (symbol var-name)))

(def supported-json-mapping-libs
  [{:core-lib-ns 'cheshire.core
    :group-id+name "cheshire"
    :parser-fn-name "parse-string"
    :parser-builder (fn [parser-fn]
                      #(parser-fn % true))
    :generator-fn-name "generate-string"}

   {:core-lib-ns 'jsonista.core
    :group-id+name "metosin/jsonista"
    :parser-fn-name "read-value"
    :parser-builder (fn [parser-fn]
                      (let [object-mapper ((resolve-fn-var 'jsonista.core
                                                           "object-mapper")
                                           {:decode-key-fn true})]
                        #(parser-fn % object-mapper)))
    :generator-fn-name "write-value-as-string"}

   {:core-lib-ns 'clojure.data.json
    :group-id+name "org.clojure/data.json"
    :parser-fn-name "read-str"
    :parser-builder (fn [parser-fn]
                      #(parser-fn % :key-fn keyword))
    :generator-fn-name "write-str"}])

(defn- is-lib-present? [core-lib-ns]
  (try
    (require core-lib-ns)
    true
    (catch Throwable _ false)))

(def ^:private present-json-mapping-libs
  (filter #(is-lib-present? (:core-lib-ns %))
          supported-json-mapping-libs))

(when-not (seq present-json-mapping-libs)
  (throw (IllegalStateException.
           (format "No supported JSON mapper library supplied. Consider adding one of the %s to the project deps."
                   (->> supported-json-mapping-libs
                        (map #(str "[\"" (:group-id+name %) "\"]"))
                        (string/join ", "))))))

(defn- build-json-str-parser [lib]
  (let [parser-fn (resolve-fn-var (:core-lib-ns lib)
                                  (:parser-fn-name lib))]
    ((:parser-builder lib) parser-fn)))

(def ^:private json-str-parser-fn
  (->> present-json-mapping-libs
       first
       build-json-str-parser))

(defn parse-json-str
  "Parses (reads) a given JSON-encoded string into a Clojure object."
  [json-str]
  (json-str-parser-fn json-str))

(defn- build-json-str-generator [lib]
  (resolve-fn-var (:core-lib-ns lib)
                  (:generator-fn-name lib)))

(def ^:private json-str-generator-fn
  (->> present-json-mapping-libs
       first
       build-json-str-generator))

(defn generate-json-str
  "Generates (writes) a JSON-encoded string for a given Clojure object."
  [clj-obj]
  (json-str-generator-fn clj-obj))
