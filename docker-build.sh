#!/bin/sh
docker build . -t micronaut-postgres-reactive
echo
echo
echo "To run the docker container execute:"
echo "    $ docker run --network host micronaut-postgres-reactive"
