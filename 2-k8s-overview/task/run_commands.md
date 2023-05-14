## Run and deploy guide

## Run cluster
minikube start --driver=docker

minikube tunnel -d

## Deploying the app into minikube
 kubectl apply -f .\k8s

## For checking # For emptying the cluster
kubectl get deployments --namespace=k8s-program

## For checking # For emptying the cluster
kubectl get deployments --namespace=k8s-program

kubectl expose deployment users-depl --namespace=k8s-program --type=LoadBalancer --port=8080
kubectl expose deployment posts-depl --namespace=k8s-program --type=LoadBalancer --port=8081

## For emptying the cluster
kubectl delete all --all --namespace=k8s-program