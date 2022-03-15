Refer the readme.docs file attached for detailed instruction

Run the application : mvn spring-boot:run

Run supporting services : docker-compose up


------------------
![image](https://user-images.githubusercontent.com/17970459/158304230-0b3727fd-d12b-4db4-a3b2-bb19fc31a8a4.png)


![image](https://user-images.githubusercontent.com/17970459/158304286-85e68079-4812-498a-b762-85a1f16a0c7f.png)



 


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

 
Prometheus Push Gateway : The Prometheus Pushgateway exists to allow ephemeral and batch jobs to expose their metrics to Prometheus. Since these kinds of jobs may not exist long enough to be scraped, they can instead push their metrics to a Pushgateway. The Pushgateway then exposes these metrics to Prometheus.

![image](https://user-images.githubusercontent.com/17970459/158304429-74bbe18b-8613-40f2-917a-6829b56f63dc.png)


![image](https://user-images.githubusercontent.com/17970459/158304446-90af2fa4-5e6d-4d6e-ab64-c199c0b09894.png)


![image](https://user-images.githubusercontent.com/17970459/158304469-870d048d-ae79-4926-9fcf-93c5719d49ae.png)







**prometheus-simpleclient_log4j2** : It’s a Metrics collector for log4j2 appender logging. 

To register the Logback collector can be added to the root level in log4j2-spring.xml file.

![image](https://user-images.githubusercontent.com/17970459/158304583-416612fe-2dba-4416-89cb-e308a38c0a42.png)

![image](https://user-images.githubusercontent.com/17970459/158304596-370b2b41-b124-4a4e-8dd7-425e596d2293.png)


![image](https://user-images.githubusercontent.com/17970459/158304612-7e3b2b62-93f6-4ae6-9cad-1e09079d44f5.png)















**Alertmanager manages** alerts sent by Prometheus server. It takes care of grouping alerts, and routing them to the correct receiver. For example an email receiver, pager, webhook, sms etc.

Alerts are generally included in a separate file, in our case it’s rules.yml

 ![image](https://user-images.githubusercontent.com/17970459/157605469-86531a99-c0be-40e4-a153-a74a3c983396.png)


**RabbitMQ metric exporter to Prometheus:**

While RabbitMQ management UI also provides access to a subset of metrics, it by design doesn't try to be a long term metric collection solution. 

As of 3.8.0, RabbitMQ ships with built-in Prometheus & Grafana support.

With the rabbitmq_prometheus plugin, rabbitMq can ship metrics to default URL URL15692/metrics

![image](https://user-images.githubusercontent.com/17970459/158304735-080e469b-f6c2-485a-a712-71532d5af47a.png)




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



