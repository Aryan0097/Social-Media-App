package com.app.socialmediaapp.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
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

    // A user following many other users
    @ManyToMany
    @JoinTable(
        name = "user_follows",
        joinColumns = @JoinColumn(name = "follower_id"),
        inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    @JsonIgnore
    private Set<User> following = new HashSet<>();

    // A user being followed by many other users (inverse relationship)
    @ManyToMany(mappedBy = "following")
    @JsonIgnore
    private Set<User> followers = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(id, username); // Exclude collections
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
               Objects.equals(username, user.username);
    }
}
