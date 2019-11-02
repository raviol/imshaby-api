#!/bin/sh

kubectl -n imsha-by apply -f ../helm/mongodb/k8s-pvc.yaml
helm install --name mongodb --namespace imsha-by -f ../helm/mongodb/values-local.yaml stable/mongodb
helm install --name imshaby-api --namespace imsha-by -f ../helm/imshaby-api/values-local.yaml ../helm/imshaby-api
sleep 10
kubectl port-forward --namespace imsha-by svc/mongodb --address=0.0.0.0 27017:27017 &> /dev/null &
kubectl port-forward --namespace imsha-by svc/imshaby-api --address=0.0.0.0 3000:3000 &> /dev/null &
