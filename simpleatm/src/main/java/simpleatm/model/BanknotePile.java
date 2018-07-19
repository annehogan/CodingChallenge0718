package simpleatm.model;

/**
 * BanknotePile - POJO class for passing notes data to the front end
 *
 * @Author Anne Hogan
 * @Copyright 2018
 */

import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BanknotePile that = (BanknotePile) o;
        return noteValue == that.noteValue &&
                noteCount == that.noteCount;
    }

    @Override
    public int hashCode() {

        return Objects.hash(noteValue, noteCount);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BanknotePile{");
        sb.append("noteValue=").append(noteValue);
        sb.append(", noteCount=").append(noteCount);
        sb.append('}');
        return sb.toString();
    }
}
