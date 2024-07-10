ARG POSTGRES_VERSION=13
FROM postgres:${POSTGRES_VERSION}-bookworm

LABEL maintainer="awoestmann@intevation.de"

ARG POSTGRES_VERSION
ARG POSTGIS_VERSION=3

# Postgres password
ENV POSTGRES_PASSWORD secret
#DB name
ENV DB_NAME fisumwelt
#AUIK user and password
ENV USERNAME auikadmin
ENV USER_PW secret

RUN apt-get update && apt-get install -y postgresql-${POSTGRES_VERSION}-postgis-${POSTGIS_VERSION}

USER postgres

#Copy init script
COPY docker/init_db.sh /docker-entrypoint-initdb.d/

ADD data/db /opt/auik_db/
WORKDIR /opt/auik_db/

CMD ["/usr/local/bin/docker-entrypoint.sh","postgres"]
