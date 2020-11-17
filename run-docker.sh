#!/bin/sh

#docker run -it --rm -p 8080:8080 -v $OSSIM_DATA:$OSSIM_DATA  --entrypoint=sh nexus-docker-public-hosted.ossim.io/ossim-demo:latest 
docker run -it --rm -p 8080:8080 -v $OSSIM_DATA:$OSSIM_DATA  nexus-docker-public-hosted.ossim.io/ossim-demo:latest 
