package com.app.socialmediaapp.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "user")
@Data
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String imageName;
    private String imageType;
    
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageData;

}
