## Run and deploy guide

## Run cluster
minikube start --driver=docker

minikube tunnel

# Cluster info
kubectl cluster-info

# show the dashboard
minikube dashboard

# cd to ./helm

## Helm Get
helm ls --all --namespace=k8s-program

## Delete old cluster data if exists
helm uninstall users-ms --namespace=k8s-program
helm uninstall posts-ms --namespace=k8s-program
kubectl delete all --all --namespace=k8s-program
kubectl delete pvc --all --namespace=k8s-program
kubectl delete configmap --all --namespace=k8s-program
kubectl delete secret --all --namespace=k8s-program
helm uninstall demo-ingress --namespace k8s-program

## Helm Install
helm install users-ms ./users-ms --namespace=k8s-program
helm install posts-ms ./posts-ms --namespace=k8s-program

## Add ingress repo
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update demo-ingress

## Installing ingress controller
helm install demo-ingress ingress-nginx/ingress-nginx --namespace k8s-program

## Helm Upgrade
helm upgrade users-ms ./users-ms --namespace=k8s-program
helm upgrade posts-ms ./posts-ms --namespace=k8s-program

## Helm Rollback
helm rollback users-ms 1 --namespace=k8s-program
helm rollback posts-ms 1 --namespace=k8s-program

## Helm Package
helm package ./users-ms
helm package ./posts-ms

## Helm repo list
helm repo list

## Helm list
kubectl get svc,pods --namespace=k8s-program
helm ls

## Helm uninstall
helm uninstall users-ms --namespace=k8s-program
helm uninstall posts-ms --namespace=k8s-program
helm uninstall demo-ingress --namespace=k8s-program

## For checking # For emptying the cluster
kubectl get deployments --namespace=k8s-program

## Expose services to the outside of cluster
kubectl expose deployment users-ms --namespace=k8s-program --type=NodePort --port=8080
kubectl expose deployment posts-ms --namespace=k8s-program --type=NodePort --port=8081

## All exposed ports
kubectl describe service --namespace=k8s-program

## For emptying the cluster
kubectl delete all --all --namespace=k8s-program

## Remove persistent volumes
kubectl delete pvc --all --namespace=k8s-program

## Remove configmaps
kubectl delete configmap --all --namespace=k8s-program

## Remove secrets
kubectl delete secret --all --namespace=k8s-program

## Delete minikube with cached images
minikube delete
