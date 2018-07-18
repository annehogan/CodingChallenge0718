package simpleatm.model;

/**
 * This is a DTO so remname perhaps
 */

import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

@JsonRootName("notesDispensed")
public class BanknotePile implements Serializable {
    private long noteValue;
    private long noteCount;

    public BanknotePile(long noteValue, long noteCount) {
        this.noteValue = noteValue;
        this.noteCount = noteCount;
    }

    public long getNoteValue() {
        return noteValue;
    }

    public void setNoteValue(long noteValue) {
        this.noteValue = noteValue;
    }

    public long getNoteCount() {
        return noteCount;
    }

    public void setNoteCount(long noteCount) {
        this.noteCount = noteCount;
    }
}
