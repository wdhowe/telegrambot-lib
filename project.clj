(defproject telegrambot-lib "2.11.0"
  
  ;;; Project Metadata
  :description "A library for interacting with the Telegram Bot API."
  :url "https://github.com/wdhowe/telegrambot-lib"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  
  ;;; Dependencies, Plugins
  :dependencies [[clj-http "3.12.3"]
                 [environ "1.2.0"]
                 [org.clojure/clojure "1.11.1"]
                 [org.clojure/core.async "1.6.681"]
                 [org.clojure/tools.logging "1.2.4"]
                 [potemkin "0.4.6"]]
  
  ;;; Profiles
  ;; logback-classic must be 1.3.x due to jdk8 support.
  ;; See: https://github.com/wdhowe/telegrambot-lib/pull/120/commits/015d31621a3fd5a7f69dcf7c230d76d55f7a47c1
  :profiles {;; REPL Jack-in with :dev,:local for development.
             :dev {:dependencies [[ch.qos.logback/logback-classic "1.3.5" :upgrade :logback]
                                  [cheshire "5.12.0"]]
                   :plugins [[lein-environ "1.2.0"]]
                   :resource-paths ["env/dev/resources"]}
             
             ;; Makes the 'local' profile available during REPL jack-in.
             ;; Loads from profiles.clj (git ignored).
             :local {}
             
             :test {:dependencies [[cheshire "5.12.0"]
                                   [clj-http-fake/clj-http-fake "1.0.4"]]
                    :plugins [[lein-environ "1.2.0"]]
                    :resource-paths ["env/test/resources"]}
             
             ;; Profiles for testing json libraries.
             :cheshire {:dependencies [[cheshire "5.12.0"]]}
             
             :jsonista {:dependencies [[metosin/jsonista "0.3.8"]]}
             
             :data.json {:dependencies [[org.clojure/data.json "2.4.0"]]}}
  
  ;;; Testing
  :test-selectors {:default (complement :json)
                   :json :json}
  
  ;;; Repl
  :repl-options {:init-ns telegrambot-lib.core})
