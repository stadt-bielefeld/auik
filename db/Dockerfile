FROM ubuntu:18.04
LABEL maintainer="awoestmann@intevation.de"

# Set environment variables
ENV DEBIAN_FRONTEND noninteractive
ENV POSTGRESVERSION 11
ENV POSTGISVERSION 2.5
ENV PGCONF /etc/postgresql/$POSTGRESVERSION/main/postgresql.conf
ENV PGDATA /var/lib/postgresql/$POSTGRESVERSION/main

# Add PostgreSQL repository and install postgres/postgis
RUN apt-get update && apt-get install -y curl ca-certificates gnupg lsb-release\
    && curl https://www.postgresql.org/media/keys/ACCC4CF8.asc | apt-key add - \
    && touch /etc/apt/sources.list.d/pgdg.list \
    && sh -c 'echo "deb https://apt.postgresql.org/pub/repos/apt `lsb_release -cs`-pgdg main" > /etc/apt/sources.list.d/pgdg.list' \
    && apt-get update \
    && apt-get install -y postgresql-$POSTGRESVERSION-postgis-$POSTGISVERSION postgresql-$POSTGRESVERSION-postgis-$POSTGISVERSION-scripts

# Continue as postgres user
USER postgres

# Add sources
ADD . /opt/auik_db/
WORKDIR /opt/auik_db/

# Add postgres config
RUN echo "host all  all    0.0.0.0/0  md5" >> \
    /etc/postgresql/$POSTGRESVERSION/main/pg_hba.conf
RUN echo "listen_addresses='*'" >> $PGCONF &&\
    echo "shared_buffers = 1024MB" >> $PGCONF && \
    echo "maintenance_work_mem = 512MB" >> $PGCONF

# Init db
RUN /usr/lib/postgresql/$POSTGRESVERSION/bin/pg_ctl start -wo "--config_file=$PGCONF" && \
    /opt/auik_db/init_db.sh && \
    /usr/lib/postgresql/$POSTGRESVERSION/bin/pg_ctl stop

EXPOSE 5432

# Start service
CMD ["/usr/lib/postgresql/11/bin/postgres", \
     "--config_file=/etc/postgresql/11/main/postgresql.conf"]
