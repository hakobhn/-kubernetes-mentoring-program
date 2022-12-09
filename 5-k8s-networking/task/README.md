Create an Ingress controller. Ingress must have a rule that forwards all requests from /epamapp/{student name}/* (student name is your name)

Host should be k8s.homework.user-service and  k8s.homework.post-service (2 hosts for java services)

Example: GET  http://k8s.homework.user-service//epamapp/{student name}/users/{id} should return user’s data
