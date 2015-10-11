/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Suqing
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserEntity.findAll", query = "SELECT u FROM UserEntity u"),
    @NamedQuery(name = "UserEntity.findById", query = "SELECT u FROM UserEntity u WHERE u.id = :id"),
    @NamedQuery(name = "UserEntity.findByUsername", query = "SELECT u FROM UserEntity u WHERE u.username = :username"),
    @NamedQuery(name = "UserEntity.findByUsernameAndPassword", query = "SELECT u FROM UserEntity u WHERE u.username = :username and u.password = :password"),
    @NamedQuery(name = "UserEntity.findByEmail", query = "SELECT u FROM UserEntity u WHERE u.email = :email"),
    @NamedQuery(name = "UserEntity.findByEmailAndPassword", query = "SELECT u FROM UserEntity u WHERE u.email = :email and u.password = :password"),
    @NamedQuery(name = "UserEntity.findByPassword", query = "SELECT u FROM UserEntity u WHERE u.password = :password"),
    @NamedQuery(name = "UserEntity.findByFirstName", query = "SELECT u FROM UserEntity u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "UserEntity.findByLastName", query = "SELECT u FROM UserEntity u WHERE u.lastName = :lastName")})
public class UserEntity extends MMEntityService implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(unique=true, name = "username")
    private String username;
    
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(unique=true, name = "email")
    private String email;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    
    @Size(max = 45)
    @Column(name = "first_name")
    private String firstName;
    
    @Size(max = 45)
    @Column(name = "last_name")
    private String lastName;
    
    @OneToMany(orphanRemoval = true, mappedBy = "ownerId")
    private Collection<GroupEntity> groupsOwned;
    
    @OneToMany(orphanRemoval = true, mappedBy = "user")
    private Collection<CommentEntity> comments;
    
    @OneToMany(orphanRemoval = true, mappedBy = "user")
    private Collection<MoodStatusEntity> moodStatuses;
    
    @ManyToMany(mappedBy="groupMembers")
    private Collection<GroupEntity> groupsJoined;

    public UserEntity() {
        this.groupsOwned = new ArrayList<>(); 
        this.groupsJoined = new ArrayList<>(); 
    }

    public UserEntity(Integer id) {
        this.id = id;
        this.groupsOwned = new ArrayList<>();
        this.groupsJoined = new ArrayList<>(); 
    }

    public UserEntity(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.groupsOwned = new ArrayList<>();
        this.groupsJoined = new ArrayList<>(); 
    }
   
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlTransient
    public Collection<GroupEntity> getGroupsOwned() {
        return groupsOwned;
    }
    
    @XmlTransient
    public Collection<GroupEntity> getGroupsJoined() {
        return groupsJoined;
    }
    
    public Collection<CommentEntity> getComments() {
        return comments;
    }
    
    public void addComment(CommentEntity comment){
        if (!getComments().contains(comment)){
            this.comments.add(comment); 
            comment.setUser(this);
        }
    }
    
    
    public Collection<MoodStatusEntity> getMoodStatuses() {
        return moodStatuses;
    }
    
    public void addMoodStatus(MoodStatusEntity moodStatus){
        if (!getMoodStatuses().contains(moodStatus)){
            this.moodStatuses.add(moodStatus); 
            moodStatus.setUser(this);
        }
    }
    
    public void addGroupOwned(GroupEntity group){
        if (!getGroupsOwned().contains(group)){
            this.groupsOwned.add(group); 
            group.setOwner(this);
        }
    }
    
    public void addGroupJoined(GroupEntity group){
        if (!getGroupsJoined().contains(group)){
            this.groupsJoined.add(group); 
            group.addGroupMember(this);
        }
    }
            
    
    
    public boolean hasUniqueEmail(EntityManagerFactory emf){
         EntityManager em; 
        em = emf.createEntityManager();
         return em.createNamedQuery("UserEntity.findByEmail")
            .setParameter("email", this.email)
            .getResultList().isEmpty();
     }
    
    public boolean hasUniqueUsername(EntityManagerFactory emf){
         EntityManager em; 
        em = emf.createEntityManager();
         return em.createNamedQuery("UserEntity.findByUsername")
            .setParameter("username", this.username)
            .getResultList().isEmpty();
     }
    
    public static UserEntity loginByUserName(String username, String password, EntityManagerFactory emf){
        
        EntityManager em; 
        em = emf.createEntityManager();
        List<UserEntity> rs = em.createNamedQuery("UserEntity.findByUsernameAndPassword")
            .setParameter("username", username)
            .setParameter("password", password)
            .getResultList();
        
        return ((rs.isEmpty()) ? null : rs.get(0));
    }
    
    public static UserEntity loginByEmail(String email, String password, EntityManagerFactory emf){
        EntityManager em; 
        em = emf.createEntityManager();
        List<UserEntity> rs = em.createNamedQuery("UserEntity.findByEmailAndPassword")
            .setParameter("email", email)
            .setParameter("password", password)
            .getResultList();
        
        return ((rs.isEmpty()) ? null : rs.get(0));
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
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "com.moodmapper.entity.UserEntity[ id=" + id + " ]";
    }
    
}