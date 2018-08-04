package com.careerday.backenddeveloper.jobapplicants.support;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import com.careerday.backenddeveloper.jobs.support.Job;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


/**
 * JPA Entity representing an individual JobApplicant.
 */
@Entity
public class JobApplicant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.AUTO)
    @Column(name = "JobApplicantId")
    public UUID id;

    private ZonedDateTime dateCreated;

    @NotNull(message = "First name is required.")
    @Size(min = 2, message = "The first name needs to be at least 3 characters.")
    private String firstName;

    @NotNull(message = "Last name is required.")
    @Size(min = 2, message = "The last name needs to be at least 3 characters.")
    private String lastName;

    private String email;

    @NotNull(message = "phone number is required.")

    private String phone;

    @Enumerated(EnumType.STRING)
    private EducationLevels educationLevel;

    private Integer yearsOfExperience;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "job_applicants_jobs",
            joinColumns = {
                    @JoinColumn(name = "job_application_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "job_id")})
    private Set<Job> jobInterviews = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EducationLevels getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevels educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Set<Job> getJobInterviews() {
        return jobInterviews;
    }

    public void setJobInterviews(Set<Job> jobInterviews) {
        this.jobInterviews = jobInterviews;
    }

    @Override
    public String toString() {
        return "JobApplicant{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email=" + email +
                ", phone='" + phone + '\'' +
                ", educationLevel=" + educationLevel +
                ", yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}
