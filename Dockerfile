FROM postgres:latest

ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=admin
ENV POSTGRES_DB=interview-union

COPY init.sql /docker-entrypoint-initdb.d/

EXPOSE 5432