#  _____  _____  _____  
# |  __ \|  __ \|  __ \ 
# | |__) | |__) | |  | |
# |  ___/|  _  /| |  | |
# | |    | | \ \| |__| |
# |_|    |_|  \_\_____/
#  

FROM alpine:3.21

########################################
## Argumentos/Parametros
########################################

ARG build_job_name
ARG build_job_number
ARG build_date

########################################
## Ambiente
########################################

ENV TZ=America/Sao_Paulo

########################################
## Network
########################################

EXPOSE 443/tcp

########################################
## Custom Labels
########################################

LABEL author="Jean Bezerra"
LABEL version=${build_job_number}
LABEL releaseDate=${build_date}
LABEL description="This project is the property of Jean Bezerra and is protected by copyright laws. Any use, reproduction, modification, or distribution of this content without explicit authorization from Jean Bezerra is strictly prohibited. All rights reserved."
LABEL website="https://jeanbezerra.com"
LABEL registry="https://jeanbezerra.azurecr.io"
LABEL org.opencontainers.image.title="Spring Boot Performance Simulation API"
LABEL org.opencontainers.image.vendor="jeanbezerra.com"
LABEL org.opencontainers.image.licenses="All Rights Reserved"
LABEL org.opencontainers.image.created=${build_date}

########################################
## Contexto do Container
########################################

RUN apk update && apk upgrade
RUN apk add vim wget zip git logtail paxctl tzdata

########################################
### Pastas
########################################

RUN mkdir -p /opt/software/
RUN mkdir -p /opt/software/java/
RUN mkdir -p /opt/software/logs/
RUN mkdir -p /opt/software/configs/
RUN mkdir -p /opt/software/jmeter/

########################################
### Workdir
########################################

WORKDIR /opt/software/

########################################
### Configuracao do Java
########################################

RUN apk update
RUN apk upgrade
RUN apk add ca-certificates
RUN update-ca-certificates
RUN apk add --update coreutils && rm -rf /var/cache/apk/*
RUN apk add --update openjdk21 tzdata curl unzip bash
RUN apk add --no-cache nss
RUN rm -rf /var/cache/apk/*

########################################
### Adicionar Softwares
########################################

COPY target/app.jar /opt/software/java/

########################################
### Execution
########################################

#CMD ["/bin/bash","-c","tail -f /dev/null"]
CMD ["/bin/sh","-c","java -jar /opt/software/java/app.jar --spring.profiles.active=prod -DXms768mb -DXmx1024mb"]