package com.example.demo.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(name = "name")
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    @Override
    public String getAuthority() {
        return getName();
    }
    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
/*
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
*/
    public Role(){

    }
    @Override
    public String toString() {
        return name.replace("ROLE_", "");
    }
}
