Refer the readme.docs file attached for detailed instruction

Run the application : mvn spring-boot:run

Run supporting services : docker-compose up


------------------
![image](https://user-images.githubusercontent.com/17970459/157604730-3a264a2b-da15-40f9-a193-91015dac3848.png)

 


1. Spring Boot Actuator module includes a number of built-in endpoints that exposes app metrics. 

2. Spring Boot Actuator provides dependency management and auto-configuration for Micrometer.

 ![image](https://user-images.githubusercontent.com/17970459/157604949-f99bf3c3-b5ef-4dbd-854c-ce069770fb2a.png)


 3. Micrometer, an application metrics facade that supports numerous monitoring systems, including: prometheus

4. we are going to leverage HTTP endpoints to see metrics. 

5. Micrometer job is to expose our application metrics to external monitoring systems while prometheus job is to scape and store metrics information exposed by it.

![image](https://user-images.githubusercontent.com/17970459/157604979-d6ff800e-f47d-4e90-9c3b-0c7f2a659264.png)



6. If multiple applications publish metrics data, Spring Boot can use a tag to identify the application name. It does it by registering MeterRegistry.

 ![image](https://user-images.githubusercontent.com/17970459/157605003-7e484051-5bde-4383-bec4-1ddfaab98dc5.png)


7. The micrometer framework provides several types of the custom metrics or meters such as Counter, Gauge, Timer, DistributionSummary that you can use to create custom metrics
![image](https://user-images.githubusercontent.com/17970459/157605039-838ac5bd-5971-4b9c-8fe5-0837035c5c64.png)

• Count the number of courses created using the Counter metric
• Count the number of courses created using the Gauge metric. 
• Capture the time taken to create the course using the Timer metric
• Capture the distribution summary of the course ratings using the     DistributionSummary metric

 



Prometheus is a monitoring system and time-series database that allows us to store time-series data, which includes the metrics of an application over time, a simple way to visualize the metrics, or setting up alerts on different metrics.

We will now configure Prometheus to scrape Spring Boot metrics. To do that, open the prometheus.yml file and add the following job_name in the scrape_configs section:

 ![image](https://user-images.githubusercontent.com/17970459/157605113-086ddbce-c93e-47e6-8b05-b67517b2a576.png)




Following Prometheus URL to see all registered endpoints: http://localhost:9090/targets

![image](https://user-images.githubusercontent.com/17970459/157605155-e4ee1655-4c32-4c1a-bd89-85823075a7d6.png)

 

Capturing Spring Boot Metrics
We can collection various metrics exposed by Spring Boot application:
![image](https://user-images.githubusercontent.com/17970459/157605197-2c2aaa59-1aad-483a-9247-06983cd657dc.png)

 

Within the Path “actuator/prometheus” there are many interesting metrics

 ![image](https://user-images.githubusercontent.com/17970459/157605244-7b5ede9b-7d9c-40b6-9730-374078c047f5.png)

![image](https://user-images.githubusercontent.com/17970459/157605277-379925ab-6cf2-4bb7-a56c-668c7bdb917f.png)

![image](https://user-images.githubusercontent.com/17970459/157605299-0b71500f-ed0d-4427-ac41-6f56e19bc5be.png)






WE can use PQL to narrow our specific metric data: 
 ![image](https://user-images.githubusercontent.com/17970459/157605321-bf3dd71a-2932-46a5-a419-b1f07d2ccfa2.png)

![image](https://user-images.githubusercontent.com/17970459/157605348-c9cefd49-874b-4fdd-b9de-04b9e82dbc7a.png)

![image](https://user-images.githubusercontent.com/17970459/157605383-de986504-9dbb-4545-b756-62eccd16fabc.png)

 


 

Grafana allows you to obtain data from various data sources such as Prometheus and visualize it through exquisite graphics

 ![image](https://user-images.githubusercontent.com/17970459/157605407-faf05fa6-511a-4761-8b6d-f9f6bce50c59.png)

 




Alertmanager manages alerts sent by Prometheus server. It takes care of grouping alerts, and routing them to the correct receiver. For example an email receiver, pager, webhook, sms etc.

Alerts are generally included in a separate file, in our case it’s rules.yml

 ![image](https://user-images.githubusercontent.com/17970459/157605469-86531a99-c0be-40e4-a153-a74a3c983396.png)




Using the Alert Manager to signal Spring Boot metrics
You can reach Prometheus Alert UI from Prometheus dashboard and see the configured alert
![image](https://user-images.githubusercontent.com/17970459/157605526-cc7afa4e-dbcb-4d17-92ac-616ecd5e73e6.png)

 


In case our Spring boot application is down alert agent triggers the alert to appropriate route.

 ![image](https://user-images.githubusercontent.com/17970459/157605555-04df45b0-6b56-4423-ae67-1822b1faaf3b.png)




Routing Alerts via email
Finally, we can configure the Alert Manager to send mails whenever an alert reaches the firing state. To do that, we need to add the below configuration in alertmanager.yml
 
![image](https://user-images.githubusercontent.com/17970459/157605611-bb8f61cf-902e-42ef-8ab9-ebe17325a51c.png)
![image](https://user-images.githubusercontent.com/17970459/157605636-71ddfe73-57e5-4f80-80ff-d7f24708da4e.png)

 



Another important component is the Alert Manager: that shows the list of alerts that were fired,
 
![image](https://user-images.githubusercontent.com/17970459/157605655-cc3c11a0-8624-46d7-b37d-c2e173194e3b.png)



Spring Boot’s Prometheus Metrics: http://localhost:8080/actuator/prometheus

Prometheus Target Endpoints: http://localhost:9090/targets

Prometheus Alerts: http://localhost:9090/alerts

Grafana server: http://localhost:3000

Alert Manager: http://localhost:9093

GitHub Repo: https://github.com/royalespn/SpringBootPrometheus

Run the application : mvn spring-boot:run
Run services : docker-compose up
 

Reference: 
Prometheus: Up & Running by Brian Brazil by by Brian Brazil, ISBN: 9781492034148

Spring Boot Actuautor: https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.metrics

Extra : configure short lived job.



