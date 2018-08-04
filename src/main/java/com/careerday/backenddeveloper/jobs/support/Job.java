package com.careerday.backenddeveloper.jobs.support;

import com.careerday.backenddeveloper.jobapplicants.support.EducationLevels;
import com.careerday.backenddeveloper.jobapplicants.support.JobApplicant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * JPA Entity representing an individual job pojo.
 */

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.AUTO)
    @Column(name = "JobId")
    public UUID id;

    private ZonedDateTime dateCreated;

    @NotNull(message = "Job name is required.")
    @Size(min = 2, message = "The job name needs to be at least 3 characters.")
    private String name;

    @NotNull(message = "Job description is required.")
    @Size(min = 2, message = "The job description needs to be at least 3 characters.")
    private String description;

    @Enumerated(EnumType.STRING)
    private JobType type;

    private Integer yearsOfExperience;

    @Enumerated(EnumType.STRING)
    private EducationLevels educationLevel;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            },
            mappedBy = "jobInterviews")
    private Set<JobApplicant> jobApplicants = new HashSet<>();

    private String status;

    private Date interviewDate;

    private LocalTime interviewStartTime;

    private LocalTime interviewEndTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JobType getType() {
        return type;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public LocalTime getInterviewStartTime() {
        return interviewStartTime;
    }

    public void setInterviewStartTime(LocalTime interviewStartTime) {
        this.interviewStartTime = interviewStartTime;
    }

    public LocalTime getInterviewEndTime() {
        return interviewEndTime;
    }

    public void setInterviewEndTime(LocalTime interviewEndTime) {
        this.interviewEndTime = interviewEndTime;
    }

    public EducationLevels getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevels educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Set<JobApplicant> getJobApplicants() {
        return jobApplicants;
    }

    public void setJobApplicants(Set<JobApplicant> jobApplicants) {
        this.jobApplicants = jobApplicants;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", yearsOfExperience=" + yearsOfExperience +
                ", educationLevel=" + educationLevel +
                ", status='" + status + '\'' +
                ", interviewDate=" + interviewDate +
                ", interviewStartTime=" + interviewStartTime +
                ", interviewEndTime=" + interviewEndTime +
                '}';
    }
}
