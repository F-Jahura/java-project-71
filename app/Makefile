.DEFAULT_GOAL := build-run

setup:
	./gradlew wrapper --gradle-version 8.5

clean:
	./gradlew clean

build:
	./gradlew clean build

install-dist:
	./gradlew clean installDist

run-dist:
	./build/install/app/bin/app

run-dist-h:
	./build/install/app/bin/app -h

run:
	./gradlew run --args="\"src/test/resources/filepath1.json\" \"src/test/resources/filepath2.json\""

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain

check-deps:
	./gradlew dependencyUpdates -Drevision=release


build-run: build run

.PHONY: build
