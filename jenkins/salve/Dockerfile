FROM registry.cn-hangzhou.aliyuncs.com/google-containers/jnlp-slave:alpine

# File Author / Maintainer
MAINTAINER lvyangjun

ENV KUBECTL_VERSION=v1.7.0

USER root

# Install kubectl
ADD kubectl kubectl
RUN chmod +x kubectl \
  && mv kubectl /usr/local/bin/kubectl

ARG dockerGid=999
RUN echo "docker:x:${dockerGid}:jenkins" >> /etc/group \
USER jenkins



