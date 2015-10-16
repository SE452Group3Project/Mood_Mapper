/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
/**
 *
 * @author Dave Messer
 */
@Entity
@Table(name="SCHOOLS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SchoolEntity.findAll", query = "SELECT c FROM SchoolEntity c"),
    @NamedQuery(name = "SchoolEntity.findById", query = "SELECT c FROM SchoolEntity c WHERE c.id = :id"),
    @NamedQuery(name = "SchoolEntity.findBySchoolName", query = "SELECT c from SchoolEntity c WHERE c.schoolName = :schoolName"),
    @NamedQuery(name = "SchoolEntity.findByCity", query = "SELECT c from SchoolEntity c WHERE c.city = :city"),
    @NamedQuery(name = "SchoolEntity.findByState", query = "SELECT c from SchoolEntity c WHERE c.state = :state"),
    @NamedQuery(name = "SchoolEntity.findByZip", query = "SELECT c from SchoolEntity c WHERE c.zip = :zip"),
    })

public class SchoolEntity extends MMEntityService implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @Column(name = "school_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer schoolID;
    
    @Basic(optional = false)
    @NotNull
    @Size(min=1, max=45)
    @Column(name = "school_name")
    private String schoolName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min=1, max=45)
    @Column(name = "city")
    private String city;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "state", length=2)
    private String state;
    
    @Basic(optional = false)
    @NotNull
    @Min(0)
    @Max(99999)
    @Column(name = "zip")
    private Integer zip;
    
    @OneToMany(orphanRemoval = true, mappedBy = "school_id")
    private Collection<UserEntity> schoolUsers;

    public SchoolEntity(){
        this.schoolUsers = new ArrayList<>();
    }
    
    public SchoolEntity(Integer id){
        this.schoolID = id;
        this.schoolUsers = new ArrayList<>();
    }
    
    public SchoolEntity(Integer schoolID, String schoolName, String city, String state, Integer zip){
        this.schoolID = schoolID;
        this.schoolName = schoolName;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.schoolUsers = new ArrayList<>();
    }
    
    public Integer getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(Integer id) {
        this.schoolID = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (schoolID != null ? schoolID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SchoolEntity)) {
            return false;
        }
        SchoolEntity other = (SchoolEntity) object;
        if ((this.schoolID == null && other.schoolID != null) || (this.schoolID != null && !this.schoolID.equals(other.schoolID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.moodmapper.entity.SchoolEntity[ id=" + schoolID + " ]";
    }

    /**
     * @return the schoolName
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * @param schoolName the schoolName to set
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zip
     */
    public Integer getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(Integer zip) {
        this.zip = zip;
    }
    
    /**
     * @return the schoolUsers
     */
    public Collection<UserEntity> getSchoolUsers(){
        return schoolUsers;
    }
}
