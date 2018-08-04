package com.careerday.backenddeveloper.jobs.support;

import com.careerday.backenddeveloper.jobapplicants.support.JobApplicant;
import com.careerday.backenddeveloper.jobapplicants.support.JobApplicantService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Service providing high-level data access of @{@link Job} to the H2 db
 */
@Service
public class JobService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);

    @Autowired
    private JobRepository jobRepository;

    public Iterable<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getSingleJob(String jobId) throws Exception {
        LOGGER.info("fetching single job id {}", jobId);
        return jobRepository.findById(UUID.fromString(jobId))
                .orElseThrow(() -> new Exception("Job not found:" + jobId));
    }

    public Job getSingleJob(UUID jobId) throws Exception {
        LOGGER.info("fetching single job id {}", jobId.toString());
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new Exception("Job not found: " + jobId.toString()));
    }

    public void deleteJob(String jobId) throws Exception {
        LOGGER.info("deleting single job id {}", jobId);
        Job job = this.getSingleJob(jobId);
        jobRepository.delete(job);
    }

    public Job createJob(Job job) {
        LOGGER.info("creating job id ", new Gson().toJson(job));
        job.setDateCreated(ZonedDateTime.now(ZoneId.systemDefault()));
        return jobRepository.save(job);
    }

    public Job updateJob(String jobId, Job job) throws Exception {
        Job existingJob = this.getSingleJob(jobId);
        existingJob.setName(job.getName());
        existingJob.setDescription(job.getDescription());
        existingJob.setEducationLevel(job.getEducationLevel());
        existingJob.setInterviewDate(job.getInterviewDate());
        existingJob.setInterviewStartTime(job.getInterviewStartTime());
        existingJob.setInterviewEndTime(job.getInterviewEndTime());
        existingJob.setYearsOfExperience(job.getYearsOfExperience());
        existingJob.setStatus(job.getStatus());
        existingJob.setType(job.getType());
        return jobRepository.save(existingJob);
    }

    public Set<JobApplicant> getJobApplicants(String jobId) throws Exception {
        Job job = this.getSingleJob(jobId);
        return job.getJobApplicants();
    }
}
