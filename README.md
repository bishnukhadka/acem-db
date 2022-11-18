# ACEM DB

> If your using DotEnvImpl config as follows

1. Create a .env file inside the resource folder
2. Add these contents

```
IP_ADDRESS=localhost
PORT=3306
NAME=COLLEGE
USERNAME=root
PASSWORD=<YOUR_PASSWORD>
```

> If your using FileImpl config as follows

1. Create a db_credentials file inside the resource folder
2. Add these contents

```
IP_ADDRESS=localhost
PORT=3306
NAME=COLLEGE
USERNAME=root
PASSWORD=<YOUR_PASSWORD>
```

> Package and deploy to payara micro server

```
mvn clean package && java -jar payara-micro.jar --deploy target/*.war --nocluster

```

> Postman collection link: https://www.getpostman.com/collections/d6ac7c9e1ceae06c3921

## JPA

http://www.mastertheboss.com/hibernate-jpa/jpa-configuration/jpa-vs-hibernate-in-a-nutshell/

> hibernate.cfg.xml is for Hibernate; 

> persistence.xml is for JPA.
 
> If you do Hibernate without JPA, you don't need the latter. If you do JPA, you have to have a provider implementation, which means Hibernate, EclipseLink, etc. (There may be other legit JPA implementations, but I don't have time to check right now.)


## Errors
localhost:4848 already running error. 
Then in terminal(for windows): 
````
netstat -aon 
taskkill \f \pid <pid of the process using port no. 4848>
````

Class name is wrong or classpath is not set for : com.mysql.cj.jdbc.MysqlDataSource 
```
Download the MySQL JDBC driver from https://dev.mysql.com/downloads/connector/j/, 
then add the jar to Payara Server's GlassFish\lib folder
```

