#!/bin/sh

#docker run -it --rm -p 8080:8080 -v  $PWD/ossim-demo-data:/ossim-demo-data --entrypoint=sh nexus-docker-public-hosted.ossim.io/ossim-demo:latest 
docker run -it --rm -p 8080:8080 -v $PWD/ossim-demo-data:/ossim-demo-data  nexus-docker-public-hosted.ossim.io/ossim-demo:latest 
