clean:
	make -C app clean

build:
	make -C app build

test:
	make -C app test

report:
	make -C app report

sonar:
	make -C app sonar

run:
	make -C app run

lint:
	make -C app lint