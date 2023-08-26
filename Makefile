.PHONY: test deploy fresh-check fresh pom debug package

test:
	@echo "JSON with cheshire."
	lein with-profiles +cheshire test :json
	@echo "JSON with jsonista."
	lein with-profiles +jsonista test :json
	@echo "JSON with data.json."
	lein with-profiles +data.json test :json
	@echo "All other tests."
	lein with-profiles +cheshire test

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

clean:
	@echo "Cleaning up built targets."
	lein clean
