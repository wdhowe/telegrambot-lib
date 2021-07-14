(defproject telegrambot-lib "0.3.5"
  :description "A library for interacting with the Telegram Bot API."
  :url "https://github.com/wdhowe/telegrambot-lib"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[cheshire "5.10.0"]
                 [clj-http "3.12.2"]
                 [environ "1.2.0"]
                 [org.clojure/clojure "1.10.3"]
                 [org.clojure/core.async "1.3.618"]
                 [org.clojure/tools.logging "1.1.0"]
                 [potemkin "0.4.5"]]
  :repl-options {:init-ns telegrambot-lib.core}
  :profiles {:dev [:project/dev :profiles/dev]
             :test [:project/test :profiles/test]
             ;; only edit :profiles/* in profiles.clj
             :profiles/dev {}
             :profiles/test {}
             :project/dev {:dependencies [[ch.qos.logback/logback-classic "1.2.3"]]
                           :plugins [[lein-environ "1.1.0"]]
                           :source-paths ["env/dev/clj"]
                           :resource-paths ["env/dev/resources"]}
             :project/test {:plugins [[lein-environ "1.1.0"]]
                            :resource-paths ["env/test/resources"]}})
