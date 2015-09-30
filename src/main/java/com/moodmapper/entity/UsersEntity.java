/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    @NamedQuery(name = "UsersEntity.findAll", query = "SELECT u FROM UsersEntity u"),
    @NamedQuery(name = "UsersEntity.findById", query = "SELECT u FROM UsersEntity u WHERE u.id = :id"),
    @NamedQuery(name = "UsersEntity.findByUsername", query = "SELECT u FROM UsersEntity u WHERE u.username = :username"),
    @NamedQuery(name = "UsersEntity.findByEmail", query = "SELECT u FROM UsersEntity u WHERE u.email = :email"),
    @NamedQuery(name = "UsersEntity.findByPassword", query = "SELECT u FROM UsersEntity u WHERE u.password = :password"),
    @NamedQuery(name = "UsersEntity.findByFirstName", query = "SELECT u FROM UsersEntity u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "UsersEntity.findByLastName", query = "SELECT u FROM UsersEntity u WHERE u.lastName = :lastName")})
public class UsersEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "username")
    private String username;
    
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerId")
    private Collection<GroupsEntity> groupsOwned;
    
    @JoinTable(name = "GROUP_MEMBERS", joinColumns = {
        @JoinColumn(name = "group_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<GroupsEntity> groupsJoined;

    public UsersEntity() {
        this.groupsOwned = new ArrayList<>(); 
        this.groupsJoined = new ArrayList<>(); 
    }

    public UsersEntity(Integer id) {
        this.id = id;
        this.groupsOwned = new ArrayList<>();
        this.groupsJoined = new ArrayList<>(); 
    }

    public UsersEntity(Integer id, String username, String email, String password) {
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
    public Collection<GroupsEntity> getGroupsOwned() {
        return groupsOwned;
    }
    
    @XmlTransient
    public Collection<GroupsEntity> getGroupsJoined() {
        return groupsJoined;
    }
    
    
    public void addGroupOwned(GroupsEntity group){
        if (!getGroupsOwned().contains(group)){
            this.groupsOwned.add(group); 
            group.setOwner(this);
        }
    }
    
    public void addGroupJoined(GroupsEntity group){
        if (!getGroupsJoined().contains(group)){
            this.groupsJoined.add(group); 
            group.addGroupMember(this);
        }
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
        if (!(object instanceof UsersEntity)) {
            return false;
        }
        UsersEntity other = (UsersEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.moodmapper.entity.UsersEntity[ id=" + id + " ]";
    }
    
}
