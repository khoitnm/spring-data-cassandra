package example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.mapping.Table;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.driver.auth.DseAuthProvider;

@Configuration
@PropertySource(value = { "classpath:cassandra.properties" })
@EnableCassandraRepositories(basePackages = { "example" })
public class CassandraConfiguration extends AbstractCassandraConfiguration {

	private static final Logger LOG = LoggerFactory
			.getLogger(CassandraConfiguration.class);

	@Autowired
	private Environment environment;

	@Bean
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints(environment
				.getProperty("cassandra.contactpoints"));
		cluster.setPort(Integer.parseInt(environment
				.getProperty("cassandra.port")));

		//Authentication
		cluster.setAuthProvider(new DseAuthProvider());
		cluster.setUsername(environment.getProperty("cassandra.username"));
		cluster.setPassword(environment.getProperty("cassandra.password"));
		return cluster;
	}

	@Override
	protected String getKeyspaceName() {
		return environment.getProperty("cassandra.keyspace");
	}

//	@Override
//	public SchemaAction getSchemaAction() {
//		return SchemaAction.RECREATE_DROP_UNUSED;
//	}

	@Override
	/**
	 * The base packages to scan for entities annotated with {@link Table} annotations. By default, returns the package
	 * name of {@literal this} (<code>this.getClass().getPackage().getName()</code>). This method must never return null.
	 */
	public String[] getEntityBasePackages() {
		return new String[] { "example.domain" };
	}

	@Bean
	public CassandraMappingContext cassandraMapping()
			throws ClassNotFoundException {
		return new BasicCassandraMappingContext();
	}
}
