package businesslogic;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import dataaccess.NoteDB;
import domainmodel.Note;
import java.util.List;

public class NoteService {

    private NoteDB noteDB;

    public NoteService() {
        noteDB = new NoteDB();
    }

    public Note get(int noteId) throws Exception {
        return noteDB.getNote(noteId);
    }

    public List<Note> getAll() throws Exception {
        List<Note> test = noteDB.getAll();
        return test;
    }

    public void update(int noteId, String contents) throws Exception {  
        java.util.Date date = new java.util.Date();
        Note note = new Note(noteId, dataaccess.NoteDB.toSQlDate(date), contents);
        noteDB.update(note);
    }

    public void delete(int noteId) throws Exception {
        
        Note deletedNote = noteDB.getNote(noteId);
        noteDB.delete(deletedNote);
    }

    public void insert(String contents) throws Exception {
        
        java.util.Date date = new java.util.Date();
        Note note = new Note(date, contents);
        noteDB.insert(note);
    }
}
