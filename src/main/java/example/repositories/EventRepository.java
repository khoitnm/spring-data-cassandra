package example.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import example.domain.Event;


public interface EventRepository extends CassandraRepository<Event> {

    @Query("select * from event where type = ?0 and bucket=?1")
    Iterable<Event> findByTypeAndBucket(String type, String bucket);
}
