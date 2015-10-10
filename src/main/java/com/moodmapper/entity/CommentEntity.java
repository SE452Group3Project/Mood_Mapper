/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Geoff Brown
 */
@Entity
@Table(name = "COMMENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommentEntity.findAll", query = "SELECT c FROM CommentEntity c"),
    @NamedQuery(name = "CommentEntity.findById", query = "SELECT c FROM CommentEntity c WHERE c.id = :id"),
    @NamedQuery(name = "CommentEntity.findByContent", query = "SELECT c FROM CommentEntity c WHERE c.content = :content"),
    @NamedQuery(name = "CommentEntity.findByTimestamp", query = "SELECT c FROM CommentEntity c WHERE c.time_stamp = :timestamp")})
public class CommentEntity extends MMEntityService implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Size(max = 255)
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date time_stamp;
    
    @JoinColumn(name = "mood_status_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private MoodStatusEntity moodStatus;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private UserEntity user;

    public CommentEntity() {
    }

    public CommentEntity(Integer id) {
        this.id = id;
    }
    
    public CommentEntity(Integer id, String content, MoodStatusEntity moodStatusId, UserEntity userId) {
        this.id = id;
        this.content = content;
        this.moodStatus = moodStatusId;
        this.user = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return time_stamp;
    }

    public void setTimeStamp(Date timestamp) {
        this.time_stamp = timestamp;
    }

    public MoodStatusEntity getMoodStatus() {
        return moodStatus;
    }

    public void setMoodStatus(MoodStatusEntity moodStatus) {
        this.moodStatus = moodStatus;
        moodStatus.addComment(this);
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
        user.addComment(this);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommentEntity)) {
            return false;
        }
        CommentEntity other = (CommentEntity) object;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "components.CommentEntity[ id=" + id + " ]";
    }
    
}
