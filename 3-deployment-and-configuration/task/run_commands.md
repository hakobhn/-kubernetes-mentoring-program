## Run and deploy guide

## Run cluster
minikube start --driver=docker

minikube tunnel

# Cluster info
kubectl cluster-info

# show the dashboard
minikube dashboard

## Deploying the app into minikube
kubectl apply -f .\k8s

## For checking # For emptying the cluster
kubectl get deployments --namespace=k8s-program

## For checking # For emptying the cluster
kubectl get deployments --namespace=k8s-program

kubectl expose deployment users-depl --namespace=k8s-program --type=LoadBalancer --port=8080
kubectl expose deployment posts-depl --namespace=k8s-program --type=LoadBalancer --port=8081

## Open and process http://localhost:8081/swagger-ui/index.html#/post-controller/get

## Process Rolling Updates
kubectl apply -f .\k8s-V2

## Refresh http://localhost:8081/swagger-ui/index.html#/post-controller/get

## Deployment rollout status
kubectl rollout status deployment/posts-depl --namespace=k8s-program

# View the rollout history of a posts-ms deployment
kubectl rollout history deployment/posts-depl --namespace=k8s-program

# Rollback to the previous deployment
kubectl rollout undo deployment/posts-depl --namespace=k8s-program

## For emptying the cluster
kubectl delete all --all --namespace=k8s-program

## Delete minikube with cached images
minikube delete
