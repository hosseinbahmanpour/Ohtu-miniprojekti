
package bibtexpro.repository;

import bibtexpro.domain.Reference;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface ReferenceRepository extends MongoRepository<Reference, String> {

    public Reference findByRefId(String id);
    
    public Reference save(Reference ref);
    
}