package simpleatm.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "NoteHolder.findAvailableFundsOrderByNoteValueDesc", query = "SELECT c FROM NoteHolder c WHERE note_quantity > 0 order by note_value desc ")
public class NoteHolder {
    @Id
    @GeneratedValue
    private Long id;
    private Long noteValue;
    private Long noteQuantity;

    public NoteHolder() {
        // empty constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoteValue() {
        return noteValue;
    }

    public void setNoteValue(Long noteValue) {
        this.noteValue = noteValue;
    }

    public Long getNoteQuantity() {
        return noteQuantity;
    }

    public void setNoteQuantity(Long noteQuantity) {
        this.noteQuantity = noteQuantity;
    }
}
