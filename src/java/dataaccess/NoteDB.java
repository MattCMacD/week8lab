package dataaccess;

import domainmodel.Note;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class NoteDB {  

    public static void insert(Note note) throws NotesDBException {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

       trans.begin();        
        try {
            em.persist(note);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    

    }

    public static void update(Note note) throws NotesDBException {

       EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
              
        try {
            trans.begin(); 
            em.merge(note);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }

    }

    public static Date toSQlDate(Date date) {
        long javaDateMilisec = date.getTime();
        java.sql.Date sqlDate = new java.sql.Date(javaDateMilisec);
        return sqlDate;
    }

    public List<Note> getAll() throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {

            List<Note> notes = em.createNamedQuery("Note.findAll", Note.class).getResultList();
            return notes;

        } catch (Exception ex) {

            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read notes", ex);
            throw new NotesDBException("Error getting Notes");

        } finally {
            em.close();
        }
    }


    public static void delete(Note note) throws NotesDBException {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge(note));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       

        
    }

    public static Note getNote(int noteId) throws NotesDBException {

         EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM Note u " +
                "WHERE u.noteId = :noteId";
        TypedQuery<Note> q = em.createQuery(qString, Note.class);
        q.setParameter("noteId", noteId);
        try {
            Note note = q.getSingleResult();
            return note;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    

    public java.sql.Date toSQLDate(java.util.Date date) {

        long javaDateMilisec = date.getTime();
        java.sql.Date sqlDate = new java.sql.Date(javaDateMilisec);
        return sqlDate;
    }

    public java.util.Date toJavaDate(java.sql.Date date) {

        long sqlDateMilisec = date.getTime();
        java.util.Date javaDate = new java.util.Date(sqlDateMilisec);
        return javaDate;
    }
}
