/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.entity;

import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author faithfulokoye
 */
@Entity
@Table(name="GROUPS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupEntity.findAll", query = "SELECT u FROM GroupEntity u"), 
    @NamedQuery(name = "GroupEntity.findById", query = "SELECT u from GroupEntity u WHERE u.id = :id"), 
    @NamedQuery(name = "GroupEntity.findByJoinCode", query = "SELECT u from GroupEntity u WHERE u.joinCode = :joinCode"), 
})
public class GroupEntity extends MMEntityService implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "name")
    private String name; 
    
    @Basic(optional = false)
    @NotNull 
    @Size(min = 1, max = 45)
    @Column(name = "join_code")
    private String joinCode; 
    
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.MERGE,  optional = false)
    private UserEntity ownerId; 
    
       

    @JoinTable(name = "GROUP_MEMBERS", joinColumns = {
        @JoinColumn(name = "group_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.MERGE)
    private Collection<UserEntity> groupMembers;
  
    public GroupEntity() {
        this.groupMembers = new ArrayList<>(); 
        this.joinCode = newJoinCode();

    }
    
    public GroupEntity(Integer id){
        this.groupMembers = new ArrayList<>(); 
        this.joinCode = newJoinCode();

    }
    
    public GroupEntity(Integer id, String name) {
        this.id = id; 
        this.name = name; 
        this.joinCode = newJoinCode();
        this.groupMembers = new ArrayList<>(); 
    }
    
    public GroupEntity(Integer id, String name, UserEntity OwnerId) {
        this.id = id; 
        this.name = name; 
        this.joinCode = newJoinCode();
        this.ownerId = OwnerId; 
        this.groupMembers = new ArrayList<>(); 
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name){
        this.name = name; 
    }
    
    public String getJoinCode(){
        return this.joinCode;
    }
    
    public void setJoinCode(String joinCode){
        this.joinCode = joinCode;
    }
    
    public UserEntity getOwner(){
        return this.ownerId;
    }
    
    public void setOwner(UserEntity owner) {
        this.ownerId = owner; 
        owner.addGroupOwned(this);
    }
    
    @XmlTransient
    public Collection<UserEntity> getGroupMembers() {
        return groupMembers;
    }
    
    public void addGroupMember(UserEntity group_member){
        if (!getGroupMembers().contains(group_member)){
            this.groupMembers.add(group_member); 
            group_member.addGroupJoined(this);
            
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
        if (!(object instanceof GroupEntity)) {
            return false;
        }
        GroupEntity other = (GroupEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.moodmapper.entity.GroupEntity[ id=" + id + " ]";
    }

    private String newJoinCode() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }
    
}
