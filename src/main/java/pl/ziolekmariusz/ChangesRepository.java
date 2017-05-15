package pl.ziolekmariusz;

import org.springframework.data.repository.CrudRepository;
import pl.ziolekmariusz.model.Changes;

import java.util.Date;

public interface ChangesRepository extends CrudRepository<Changes, Long> {

    Iterable<Changes> findAllByModificationTimeGreaterThan(Date modificationTime);

}
