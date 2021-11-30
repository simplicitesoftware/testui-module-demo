#!/bin/bash
sudo docker run -it --rm -v $PWD:/usr/src -w /usr/src markhobson/maven-chrome:jdk-16 mvn test
