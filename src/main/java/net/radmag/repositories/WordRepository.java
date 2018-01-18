package net.radmag.repositories;

import net.radmag.model.Word;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface WordRepository extends CrudRepository<Word, Long>{

    List<Word> findByType(String type);
    @Transactional
    Long deleteWordByTypeAndWord(String type, String word);
    @Transactional
    List<Word> deleteWordsByType(String type);
}
