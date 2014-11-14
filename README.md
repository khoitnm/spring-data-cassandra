spring-data-cassandra-example
=============================
This repository contains the source code for [Spring Data Cassandra Overview](http://www.opencredo.com/spring-data-cassandra-overview/) article published on [OpenCredo](http://www.opencredo.com/) blog.

We can configure project in two ways:
+ Config in example/CassandraConfiguration.java
+ Config in example/xmlconfig/CassandraConfig.xml
Both above files will load Cassandra configuration in src/main/resource/cassandra.properties


Run: 
Spring support many ways to query on Cassandra, run one of 3 test files:
+ CqlTemplateIntegrationTest (query by using CqlTemplate class)
+ CassandraTemplateIntegrationTest (query by using CassandraTemplate class, which is built on top of CqlTemplate)
+ EventRepositoryIntegrationTest (use EvenRepository class which automatically supports CRUD, we don't need to write Insert, Update, Delete query)

All of above test files inherit from BaseIntegrationTest.java, which will load Spring Data Cassandra configuration from Java or Xml file.


