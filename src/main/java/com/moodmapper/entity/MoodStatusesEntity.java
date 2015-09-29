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
 * @author faithfulokoye
 */
@Entity
@Table(name = "MOOD_STATUSES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MoodStatusesEntity.findAll", query = "SELECT m FROM MoodStatusesEntity m"),
    @NamedQuery(name = "MoodStatusesEntity.findById", query = "SELECT m FROM MoodStatusesEntity m WHERE m.id = :id"),
    @NamedQuery(name = "MoodStatusesEntity.findByTimestamp", query = "SELECT m FROM MoodStatusesEntity m WHERE m.time_stamp = :timestamp"),
    @NamedQuery(name = "MoodStatusesEntity.findByPleasantnessRating", query = "SELECT m FROM MoodStatusesEntity m WHERE m.pleasantnessRating = :pleasantnessRating"),
    @NamedQuery(name = "MoodStatusesEntity.findByDescriptiveWord", query = "SELECT m FROM MoodStatusesEntity m WHERE m.descriptiveWord = :descriptiveWord"),
    @NamedQuery(name = "MoodStatusesEntity.findByReflectiveParagraph", query = "SELECT m FROM MoodStatusesEntity m WHERE m.reflectiveParagraph = :reflectiveParagraph"),
    @NamedQuery(name = "MoodStatusesEntity.findByEnergyRating", query = "SELECT m FROM MoodStatusesEntity m WHERE m.energyRating = :energyRating"),
    @NamedQuery(name = "MoodStatusesEntity.findByIsPrivate", query = "SELECT m FROM MoodStatusesEntity m WHERE m.isPrivate = :isPrivate")})
public class MoodStatusesEntity implements Serializable {
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moodStatus")
    private Set<CommentsEntity> commentsEntity;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UsersEntity user;

    public MoodStatusesEntity() {
    }

    public MoodStatusesEntity(Integer id) {
        this.id = id;
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
    public Set<CommentsEntity> getComments() {
        return commentsEntity;
    }

    public void setComments(Set<CommentsEntity> commentsEntity) {
        this.commentsEntity = commentsEntity;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
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
        if (!(object instanceof MoodStatusesEntity)) {
            return false;
        }
        MoodStatusesEntity other = (MoodStatusesEntity) object;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "components.MoodStatusesEntity[ id=" + id + " timeStamp=" + time_stamp + "]";
    }
    
}
