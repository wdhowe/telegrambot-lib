(ns telegrambot-lib.json
  "Internal JSON mapping utilities."
  (:gen-class)
  (:require [clojure.string :as string]))

(def supported-json-mapping-libs
  [{:core-lib-ns 'cheshire.core
    :group-id+name "cheshire"}
   {:core-lib-ns 'jsonista.core
    :group-id+name "metosin/jsonista"}
   {:core-lib-ns 'clojure.data.json
    :group-id+name "org.clojure/data.json"}])

(defn- is-lib-present? [core-lib-ns]
  (try
    (require core-lib-ns)
    true
    (catch Throwable _ false)))

(when-not (some is-lib-present? (map :core-lib-ns supported-json-mapping-libs))
  (throw (IllegalStateException.
           (format "No supported JSON mapper library supplied. Consider adding one of the %s to the project deps."
                   (->> supported-json-mapping-libs
                        (map #(str "[\"" (:group-id+name %) "\"]"))
                        (string/join ", "))))))

(defn- resolve-fn-var [ns var-name]
  (ns-resolve ns (symbol var-name)))

(def ^:private json-encode-fn
  (cond
    (is-lib-present? 'cheshire.core)
    (let [parser-fn-var (resolve-fn-var 'cheshire.core "parse-string")]
      #(parser-fn-var % true))

    (is-lib-present? 'jsonista.core)
    (let [parser-fn-var (resolve-fn-var 'jsonista.core "read-value")
          object-mapper ((resolve-fn-var 'jsonista.core "object-mapper")
                         {:decode-key-fn true})]
      #(parser-fn-var % object-mapper))

    (is-lib-present? 'clojure.data.json)
    (let [parser-fn-var (resolve-fn-var 'clojure.data.json "read-str")]
      #(parser-fn-var % :key-fn keyword))))

(defn parse-json-str
  "Parses (reads) a given JSON-encoded string into a Clojure object."
  [json-str]
  (json-encode-fn json-str))

(def ^:private json-decode-fn
  (cond
    (is-lib-present? 'cheshire.core)
    (resolve-fn-var 'cheshire.core "generate-string")

    (is-lib-present? 'jsonista.core)
    (resolve-fn-var 'jsonista.core "write-value-as-string")

    (is-lib-present? 'clojure.data.json)
    (resolve-fn-var 'clojure.data.json "write-str")))

(defn generate-json-str
  "Generates (writes) a JSON-encoded string for a given Clojure object."
  [clj-obj]
  (json-decode-fn clj-obj))
