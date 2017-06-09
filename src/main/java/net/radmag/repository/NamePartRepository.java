package net.radmag.repository;

import net.radmag.model.NamePart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Stephen on 6/9/2017.
 */
public interface NamePartRepository extends CrudRepository<NamePart, Long> {

    //List<NamePart> findByTags(String tag);
}
