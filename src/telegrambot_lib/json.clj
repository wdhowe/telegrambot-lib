(ns telegrambot-lib.json
  "Internal JSON mapping utilities."
  (:gen-class)
  (:require [clojure.string :as string]
            [clojure.tools.logging :as log]))

(defn- resolve-fn-var
  "Returns the var to which a `var-name` will be resolved in the `ns` namespace
   (unless found in the environment), else `nil`. Also checks that the returned
   var dereferences to a function."
  [ns var-name]
  (let [resolved-var (ns-resolve ns (symbol var-name))]
    (if (and (some? resolved-var) (not (fn? @resolved-var)))
      (log/warnf "The resolved var '%s/%s' do not reference a function" ns var-name)
      resolved-var)))

(def supported-mapping-libs
  "The JSON mapping libraries supported in the current implementation."
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

(let [lock (Object.)]
  (defn- is-lib-present?
    "Checks for an optional dependency presence in classpath by resolving
     its core ns."
    [core-lib-ns]
    (locking lock
      (try
        (require core-lib-ns)
        true
        (catch Throwable _ false)))))

(def ^:private present-mapping-libs
  "A subset of the supported JSON mapping libraries that are currently present
   in classpath."
  (filter #(is-lib-present? (:core-lib-ns %))
          supported-mapping-libs))

(when-not (seq present-mapping-libs)
  (throw (IllegalStateException.
           (format "No supported JSON mapper library supplied. Consider adding one of the %s to the project deps."
                   (->> supported-mapping-libs
                        (map #(str "[\"" (:group-id+name %) "\"]"))
                        (string/join ", "))))))

(defn- build-json-str-parser
  "Builds a JSON string parser fn — the one that takes a JSON-encoded string
   and parses a Clojure object out of it."
  [lib]
  (let [parser-fn (resolve-fn-var (:core-lib-ns lib)
                                  (:parser-fn-name lib))]
    ((:parser-builder lib) parser-fn)))

(def ^:private json-str-parser
  (->> present-mapping-libs
       first
       build-json-str-parser))

(defn parse-str
  "Parses (reads) a given JSON-encoded string into a Clojure object."
  [json-str]
  (json-str-parser json-str))

(defn- build-json-str-generator
  "Builds a JSON string generator fn — the one that takes a Clojure object
   and generates its JSON-encoded string representation."
  [lib]
  (resolve-fn-var (:core-lib-ns lib)
                  (:generator-fn-name lib)))

(def ^:private json-str-generator
  (->> present-mapping-libs
       first
       build-json-str-generator))

(defn generate-str
  "Generates (writes) a JSON-encoded string for a given Clojure object."
  [clj-obj]
  (json-str-generator clj-obj))
