package com.magicallibrary.app.modules.user;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;

// An Entity Class is a class that matches a table in the database
@Entity
@Table(name = "user", schema = "public")
@SecondaryTable(name="borrow", 
    pkJoinColumns={
        @PrimaryKeyJoinColumn(name="user_id")
    }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String usertype;

    @SuppressWarnings("unused")
    private String password;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // Entity classes should have an empty constructor, both Hibernate and JPA require this.
    // It can have other constructors as well.
    public User() {}

    public User(
        String firstName,
        String lastName,
        String email,
        String usertype,
        String password
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.usertype = usertype;
        this.password = password;
    }

    public long getId() {
        return this.id;
    }

    public String getFullname() {
        return this.firstName + " " + this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
            """
                id: %d,
                firstName: %s,
                lastName: %s,
                email: %s,
                usertype: %s
            """,
            this.id,
            this.firstName,
            this.lastName,
            this.email,
            this.usertype
        );
    }
}
