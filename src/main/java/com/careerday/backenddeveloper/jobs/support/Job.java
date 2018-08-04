package com.careerday.backenddeveloper.jobs.support;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.AUTO)
    public UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime dateCreated;

    
}
