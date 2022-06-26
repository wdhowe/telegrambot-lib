(defproject telegrambot-lib "2.1.0"
  :description "A library for interacting with the Telegram Bot API."
  :url "https://github.com/wdhowe/telegrambot-lib"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[clj-http "3.12.3"]
                 [environ "1.2.0"]
                 [org.clojure/clojure "1.11.1"]
                 [org.clojure/core.async "1.5.648"]
                 [org.clojure/tools.logging "1.2.4"]
                 [potemkin "0.4.5"]]
  :repl-options {:init-ns telegrambot-lib.core}
  :profiles {:dev [:project/dev :profiles/dev]
             :test [:project/test :profiles/test]
             ;; only edit :profiles/* in profiles.clj
             :profiles/dev {}
             :profiles/test {}
             :project/dev {:dependencies [[ch.qos.logback/logback-classic "1.2.11"]]
                           :plugins [[lein-environ "1.1.0"]]
                           :source-paths ["env/dev/clj"]
                           :resource-paths ["env/dev/resources"]}
             :project/test {:plugins [[lein-environ "1.1.0"]]
                            :resource-paths ["env/test/resources"]}
             :cheshire [:test {:dependencies [[cheshire "5.11.0"]]}]
             :jsonista [:test {:dependencies [[metosin/jsonista "0.3.6"]]}]
             :data.json [:test {:dependencies [[org.clojure/data.json "2.4.0"]]}]}
  :test-selectors {:default (complement :json)
                   :json :json})
