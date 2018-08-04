package com.careerday.backenddeveloper.jobapplicants.support;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.UUID;


/**
 * JPA Entity representing an individual JobApplicant.
 */
@Entity
public class JobApplicant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.AUTO)
    public UUID id;

    private ZonedDateTime dateCreated;

    @NotNull(message = "First name is required.")
    @Size(min = 2, message = "The first name needs to be at least 3 characters.")
    private String firstName;

    @NotNull(message = "Last name is required.")
    @Size(min = 2, message = "The last name needs to be at least 3 characters.")
    private String lastName;

    private Email email;

    @NotNull(message = "phone number is required.")

    private String phone;

    @Enumerated(EnumType.STRING)
    private EducationLevels educationLevel;

    private Integer yearsOfExperience;
}
