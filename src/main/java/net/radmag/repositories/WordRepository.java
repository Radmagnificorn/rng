package net.radmag.repositories;

import net.radmag.model.Word;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WordRepository extends CrudRepository<Word, Long>{

    List<Word> findByType(String type);
}
