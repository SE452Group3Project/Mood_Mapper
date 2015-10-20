/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Geoff Brown
 */
@Entity
@Table(name = "MOOD_STATUSES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MoodStatusEntity.findAll", query = "SELECT m FROM MoodStatusEntity m"),
    @NamedQuery(name = "MoodStatusEntity.findById", query = "SELECT m FROM MoodStatusEntity m WHERE m.id = :id"),
    @NamedQuery(name = "MoodStatusEntity.findByTimestamp", query = "SELECT m FROM MoodStatusEntity m WHERE m.time_stamp = :timestamp"),
    @NamedQuery(name = "MoodStatusEntity.findByPleasantnessRating", query = "SELECT m FROM MoodStatusEntity m WHERE m.pleasantnessRating = :pleasantnessRating"),
    @NamedQuery(name = "MoodStatusEntity.findByDescriptiveWord", query = "SELECT m FROM MoodStatusEntity m WHERE m.descriptiveWord = :descriptiveWord"),
    @NamedQuery(name = "MoodStatusEntity.findByReflectiveParagraph", query = "SELECT m FROM MoodStatusEntity m WHERE m.reflectiveParagraph = :reflectiveParagraph"),
    @NamedQuery(name = "MoodStatusEntity.findByEnergyRating", query = "SELECT m FROM MoodStatusEntity m WHERE m.energyRating = :energyRating"),
    @NamedQuery(name = "MoodStatusEntity.findByIsPrivate", query = "SELECT m FROM MoodStatusEntity m WHERE m.isPrivate = :isPrivate")})
public class MoodStatusEntity extends MMEntityService implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date time_stamp;
    
    @Column(name = "pleasantness_rating")
    private Integer pleasantnessRating;
    
    @Size(max = 32)
    @Column(name = "descriptive_word")
    private String descriptiveWord;
    
    @Size(max = 255)
    @Column(name = "reflective_paragraph")
    private String reflectiveParagraph;
    
    @Column(name = "energy_rating")
    private Integer energyRating;
    
    @Column(name = "is_private")
    private Boolean isPrivate;
    
    @OneToMany(orphanRemoval = true, mappedBy = "moodStatus")
    private Set<CommentEntity> comments;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.MERGE,  optional = false)
    private UserEntity user;

    public MoodStatusEntity() {
    }

    public MoodStatusEntity(Integer id) {
        this.id = id;
    }
    
    public MoodStatusEntity(Integer id, UserEntity userId, Integer pleasantnessRating, String descrWord, String rParagraph, Integer eRating, Boolean isPrivate) {
        this.id = id;
        this.user = userId;
//        this.time_stamp = new Date();
        this.pleasantnessRating = pleasantnessRating;
        this.descriptiveWord = descrWord;
        this.reflectiveParagraph = rParagraph;
        this.energyRating = eRating;
        this.isPrivate = isPrivate; 
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimeStamp() {
        return time_stamp;
    }

    public void setTimeStamp(Date time_stamp) {
        this.time_stamp = time_stamp;
    }

    public Integer getPleasantnessRating() {
        return pleasantnessRating;
    }

    public void setPleasantnessRating(Integer pleasantnessRating) {
        this.pleasantnessRating = pleasantnessRating;
    }

    public String getDescriptiveWord() {
        return descriptiveWord;
    }

    public void setDescriptiveWord(String descriptiveWord) {
        this.descriptiveWord = descriptiveWord;
    }

    public String getReflectiveParagraph() {
        return reflectiveParagraph;
    }

    public void setReflectiveParagraph(String reflectiveParagraph) {
        this.reflectiveParagraph = reflectiveParagraph;
    }

    public Integer getEnergyRating() {
        return energyRating;
    }

    public void setEnergyRating(Integer energyRating) {
        this.energyRating = energyRating;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    @XmlTransient
    public Set<CommentEntity> getComments() {
        return comments;
    }
    
    public void addComment(CommentEntity comment){
        if (!getComments().contains(comment)){
            this.comments.add(comment); 
            comment.setMoodStatus(this);
        }
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
        user.addMoodStatus(this); 
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
        if (!(object instanceof MoodStatusEntity)) {
            return false;
        }
        MoodStatusEntity other = (MoodStatusEntity) object;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "components.MoodStatusesEntity[ id=" + id 
                + " pleasantnessRating=" + pleasantnessRating 
                + " energyRating=" + energyRating
                + " descriptiveWord=" + descriptiveWord
                + " reflectiveParagraph=" + reflectiveParagraph
                + " timeStamp=" + time_stamp + "]";
    }
    
}
