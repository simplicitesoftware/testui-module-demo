#!/bin/bash

# Run tests as in CI pipeline
# ====================
sudo docker run -it --rm -v $PWD:/usr/src -w /usr/src markhobson/maven-chrome:jdk-16 mvn test

# Run tests without auto-quit container (debug purposes)
# ====================
# sudo docker run -dt --name mvnchr -v $PWD:/usr/src -w /usr/src markhobson/maven-chrome:jdk-16 /bin/bash
# sudo docker exec -it mvnchr /bin/bash
