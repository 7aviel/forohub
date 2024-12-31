package org.oracleone.forohub.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perfil_id")
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "user_perfil",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    @JsonBackReference
    private List<User> userList;
    
}