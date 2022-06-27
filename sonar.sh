#!/bin/sh
mvn clean verify sonar:sonar \
 -Dsonar.projectKey=pl-test-jovanab \
 -Dsonar.host.url=http://soc-build:9000 \
 -Dsonar.login=8b14afa94b9e7a484ca3adaa861b569383989057
