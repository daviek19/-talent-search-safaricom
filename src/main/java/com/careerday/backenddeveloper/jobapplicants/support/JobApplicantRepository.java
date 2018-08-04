package com.careerday.backenddeveloper.jobapplicants.support;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobApplicantRepository extends CrudRepository<JobApplicant, UUID> {
}
