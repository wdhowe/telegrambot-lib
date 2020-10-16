(defproject telegrambot-lib "0.1.0"
  :description "A library for interacting with the Telegram Bot API."
  :url "https://github.com/wdhowe/telegrambot-lib"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[cheshire "5.10.0"]
                 [clj-http "3.10.3"]
                 [com.taoensso/timbre "5.1.0"]
                 [environ "1.2.0"]
                 [org.clojure/clojure "1.10.0"]
                 [potemkin "0.4.5"]]
  :plugins [[lein-codox "0.10.7"]]
  :codox {:output-path "doc"}
  :repl-options {:init-ns telegrambot-lib.core}
  :profiles {:dev [:project/dev :profiles/dev]
             :test [:project/test :profiles/test]
             ;; only edit :profiles/* in profiles.clj
             :profiles/dev {}
             :profiles/test {}
             :project/dev {:env {:log-level :debug}
                           :plugins [[lein-environ "1.1.0"]]}
             :project/test {:env {:log-level :info}
                            :plugins [[lein-environ "1.1.0"]]}})