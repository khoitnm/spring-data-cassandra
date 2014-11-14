package example;

import java.util.HashMap;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.cql.CqlIdentifier;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import example.domain.Event;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CassandraConfiguration.class)
//@ContextConfiguration(locations = { "/example/xmlconfig/CassandraConfig.xml"})
public abstract class BaseIntegrationTest {

    @Autowired
    private CassandraAdminOperations adminTemplate;
    
    @Before 
    public void resetKeySpace() {
    	System.out.println("Drop table Event...");
        adminTemplate.dropTable(CqlIdentifier.cqlId("event"));
        System.out.println("Create table Event...");
        adminTemplate.createTable(true, CqlIdentifier.cqlId("event"), Event.class, new HashMap<String, Object>());
    }
}
