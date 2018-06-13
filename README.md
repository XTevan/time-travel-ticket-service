# Time Travel Ticket Service

### Overview
This is the API for a Time Travel system with the following features:
- List all recorded/booked travels
- Show information of specific travels
- Book new travels
  - Input validation
  - Time paradox validation
 
 ---
 
 ### How to run
 - On the command line execute:       
   
   ```bash
   $ gradle bootRun
   #or 
   $ ./gradlew bootRun
   ```
  
 - Deploy to a docker container:
 
 ```bash
 $ docker build -t openjdk-8/time-travel-ticket-service .
 ``` 
 
 **(don't forget the period '.' at the end of the command)*
 
 ```bash
 $ docker run -p 8080:8080 openjdk-8/time-travel-ticket-service
 ```
 - Finally go to [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
    
 
