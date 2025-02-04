ARG POSTGRES_VERSION=14
FROM postgres:${POSTGRES_VERSION}-bookworm

LABEL maintainer="awoestmann@intevation.de"

ARG POSTGRES_VERSION
ARG POSTGIS_VERSION=3

RUN apt-get update && apt-get install -y postgresql-${POSTGRES_VERSION}-postgis-${POSTGIS_VERSION}

#Copy init script
COPY docker/init_db.sh /docker-entrypoint-initdb.d/
