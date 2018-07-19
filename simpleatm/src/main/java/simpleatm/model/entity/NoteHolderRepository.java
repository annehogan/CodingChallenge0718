package simpleatm.model.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * NoteHolderRepository - a JPA repository to manage {@link NoteHolder}
 *
 * @Author Anne Hogan
 * @Copyright 2018
 */

@Repository
public interface NoteHolderRepository extends JpaRepository<NoteHolder, Long> {

    /**
     * Finds all the available NoteHolder entities and sorts them based on the note value
     *
     * @return A list of NoteHolder entities sorted to have the highest banknotes at the top of the list
     * If no NoteHolder is found, this method returns null.
     */
    List<NoteHolder> findAvailableFundsOrderByNoteValueDesc();

}
