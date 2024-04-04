.PHONY: test deploy fresh-check fresh pom debug package

test:
	@echo "JSON with cheshire."
	lein with-profile cheshire test :json
	@echo "JSON with jsonista."
	lein with-profile jsonista test :json
	@echo "JSON with data.json."
	lein with-profile data.json test :json
	@echo "All other tests."
	lein test

deploy: test
	lein with-profile -dev deploy clojars

fresh-check:
	lein ancient :check-clojure

fresh:
	lein ancient upgrade :no-tests :check-clojure :exclude logback

pom:
	lein with-profile -dev pom

debug:
	@echo "Java: `java -version 2>&1 | head -1`"
	@echo "Lein: `lein version`"

package:
	@echo "Download dependencies."
	lein deps
	@echo "Compile clojure into class files."
	lein compile
	@echo "Create uberjar."
	lein uberjar

# Pre-req: clojure -Ttools install io.github.cljdoc/cljdoc-analyzer '{:git/tag "RELEASE"}' :as cljdoc
# Analyze cljdoc api imports locally.
cljdoc-analyze: pom package
	@PROJECT_VERSION=$$(awk '/defproject/ {print $$3}' project.clj | tr -d '"') ; \
	clojure -Tcljdoc analyze \
	:project '"telegrambot-lib/telegrambot-lib"' \
	:version '"'$${PROJECT_VERSION}'"' \
	:jarpath '"'./target/telegrambot-lib-$${PROJECT_VERSION}.jar'"' \
	:pompath '"./pom.xml"' \
	:extra-repo '["clojars https://repo.clojars.org/"]'

clean:
	@echo "Cleaning up built targets."
	lein clean
