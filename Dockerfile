FROM bellsoft/liberica-openjdk-alpine:17.0.8
RUN apk add curl jq
WORKDIR /tmp/docker-selenium
COPY target/docker-resources ./
COPY runner.sh ./runner.sh
ENTRYPOINT ["sh","runner.sh"]