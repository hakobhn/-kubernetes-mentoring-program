

kubectl apply -f .\k8s

minikube tunnel -d

kubectl get deployments --namespace=k8s-program

kubectl expose deployment users-depl --namespace=k8s-program --type=LoadBalancer --port=8080
kubectl expose deployment posts-depl --namespace=k8s-program --type=LoadBalancer --port=8081

kubectl delete all --all --namespace=k8s-program