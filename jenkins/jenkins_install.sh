#!/bin/sh
##doker for mac 安装kubectl dashboard
kubectl get namespaces
kubectl get pods --namespace kube-system
kubectl create -f https://raw.githubusercontent.com/kubernetes/dashboard/master/src/deploy/recommended/kubernetes-dashboard.yaml
kubectl get deployments --namespace kube-system
kubectl get services --namespace kube-system

kubectl proxy

#制作local_jenkins镜像
docker build -t lvyangjun/local_jenkins .

cd jenkins-yml

#创建ci namespace
kubectl apply -f namespace-ci.yml

#请求pv
kubectl apply -f pv.yml

#部署jenkins镜像
kubectl apply -f jenkins.yml

kubectl get pods -n ci

#查看容器日志
#docker ps
docker logs -f df711b226622