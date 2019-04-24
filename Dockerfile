FROM oracle/graalvm-ce:1.0.0-rc15 as graalvm
COPY . /home/app/micronaut-postgres-reactive
WORKDIR /home/app/micronaut-postgres-reactive
RUN native-image --no-server -cp build/libs/micronaut-postgres-reactive-*.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/micronaut-postgres-reactive .
ENTRYPOINT ["./micronaut-postgres-reactive"]
