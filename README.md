# LoadBalancer
*Technology 
->Java 17+
->Use bundled maven
->H2 db

*Steps to run to run the project 
->Clone the repository 
->Open the project on intellij
->Make sure lombok plugin is installed also - File → Settings → Build, Execution, Deployment → Compiler → Annotation Processors is enabled
->Build the project --mvn install
->Run the DispatchLoadBalancerApplication file
->API to hit from postman refer the request body from assignment document 
POST http://localhost:8080/api/dispatch/orders
POST http://localhost:8080/api/dispatch/vehicles
GET http://localhost:8080/api/dispatch/plan
