/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainmodel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 728918
 */
@Entity
@Table(name = "note")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n")
    , @NamedQuery(name = "Note.findByNoteId", query = "SELECT n FROM Note n WHERE n.noteId = :noteId")
    , @NamedQuery(name = "Note.findByDatecreated", query = "SELECT n FROM Note n WHERE n.datecreated = :datecreated")
    , @NamedQuery(name = "Note.findByContent", query = "SELECT n FROM Note n WHERE n.content = :content")})
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "noteId")
    private Integer noteId;
    @Basic(optional = false)
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Basic(optional = false)
    @Column(name = "content")
    private String content;

    public Note() {
    }

    public Note(Integer noteId) {
        this.noteId = noteId;
    }

    public Note(Integer noteid, String content) {
        this.noteId = noteid;
        this.content = content;
    }
    
    public Note(Date dateCreated, String content) {
        
        this.datecreated = dateCreated;
        this.content = content;
    }
    
    public Note(Integer noteid, Date datecreated, String content) {
        this.noteId = noteid;
        this.datecreated = datecreated;
        this.content = content;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteid) {
        this.noteId = noteId;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noteId != null ? noteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.noteId == null && other.noteId != null) || (this.noteId != null && !this.noteId.equals(other.noteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domainmodel.Note[ noteId=" + noteId + " ]";
    }
    
}
