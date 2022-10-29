# Table of Content

- [What to do](#what-to-do)
- [Sub-task 1: Enable k8s](#sub-task-1-enable-k8s)
- [Sub-task 2: Deploy containers in k8s](#sub-task-2-deploy-containers-in-k8s)

## What to do
In this module you will create infrastructure for your k8s cluster and deploy your microservices applications there.

## Sub-task 1: Enable k8s
To use Kubernetes go to Docker Desktop settings, choose Kubernetes and click checkbox 'Enable Kubernetes'. You will need to wait for the installation and restart docker.
To verify installation run the next command: `kubectl version`.
It is recommended to install Kubernetes Lens to relieve resources management.


## Sub-task 2: Deploy containers in k8s
In this subtask you need to create manifest `.yml` files with configuration for deployment. These files should contain the next objects:
- Namespace (f.e. k8s-program). All other objects will use this namespace;
- 4 Services (one for each service of your system);
- 4 Deployments (one for each service of your system). For apps deployments set `replicas: 2`. You should add environment variables for your applications here. <br />
_Note_: don't forget to specify namespace all objects.

To deploy, run `kubectl apply ./` in folders where yml files are stored.
To view all objects run `kubectl get all -n=<your_namespace>`.

Along with services asn deployments, this command outputs pods and replica-sets. **Find out why.**
