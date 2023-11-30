package com.example.Firstdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp; // Change the import
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "app_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_SEQ")
    @SequenceGenerator(name = "app_user_SEQ", sequenceName = "app_user_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank(message = "First name must not be empty")
    private String firstName;

    @NotBlank(message = "Last name must not be empty")
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @NotBlank(message = "Email must not be empty")
    @Column(nullable = false)
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Gender must not be empty")
    private String gender;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password must not be empty")
    private String password;

    @NotBlank(message = "Date of Birth must not be empty")
    private String dateOfBirth;  // Consider using a date-related data type

    @NotBlank(message = "Address must not be empty")
    private String address;

    @NotBlank(message = "State of Origin must not be empty")
    private String stateOfOrigin;

    @NotBlank(message = "Account number must not be empty")
    private String accountNumber;

    @NotBlank(message = "Phone number must not be empty")
    private String phoneNumber;

    private String status;

    @CreationTimestamp
    private Timestamp createdOn; // Change to Timestamp

    @UpdateTimestamp
    private Timestamp modifiedAt; // Change to Timestamp
}
