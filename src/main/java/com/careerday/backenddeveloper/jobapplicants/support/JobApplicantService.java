package com.careerday.backenddeveloper.jobapplicants.support;

import com.careerday.backenddeveloper.jobs.support.Job;
import com.careerday.backenddeveloper.jobs.support.JobRepository;
import com.careerday.backenddeveloper.jobs.support.JobService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Service providing high-level data access of @{@link JobApplicantRepository} to the H2 db
 */
@Service
public class JobApplicantService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobApplicantService.class);

    @Autowired
    private JobApplicantRepository jobApplicantRepository;

    @Autowired
    private JobService jobService;

    public Iterable<JobApplicant> getAllJobApplicants() {
        LOGGER.info("***fetching all job applicants");
        return jobApplicantRepository.findAll();
    }

    public JobApplicant getSingleJobApplicant(String jobApplicantId) throws Exception {
        LOGGER.info("*** fetching job application {}", jobApplicantId);
        return jobApplicantRepository.findById(UUID.fromString(jobApplicantId))
                .orElseThrow(() -> new Exception("Job applicant not found:" + jobApplicantId));
    }

    public void deleteJobApplicant(String jobApplicantId) throws Exception {
        LOGGER.info("*** deleting job application {}", jobApplicantId);
        JobApplicant jobApplicant = this.getSingleJobApplicant(jobApplicantId);
        jobApplicantRepository.delete(jobApplicant);
    }

    public JobApplicant createJobApplicant(JobApplicant jobApplicant) {
        LOGGER.info("*** creating job application {}", new Gson().toJson(jobApplicant));
        jobApplicant.setDateCreated(ZonedDateTime.now(ZoneId.systemDefault()));
        return jobApplicantRepository.save(jobApplicant);
    }

    public JobApplicant updateJobApplicant(String jobApplicantId, JobApplicant jobApplicant) throws Exception {
        LOGGER.info("*** updating job application id {} with {}", jobApplicantId, new Gson().toJson(jobApplicant));
        JobApplicant existingJobApplicant = this.getSingleJobApplicant(jobApplicantId);
        existingJobApplicant.setEducationLevel(jobApplicant.getEducationLevel());
        existingJobApplicant.setEmail(jobApplicant.getEmail());
        existingJobApplicant.setFirstName(jobApplicant.getFirstName());
        existingJobApplicant.setLastName(jobApplicant.getLastName());
        existingJobApplicant.setPhone(jobApplicant.getPhone());
        existingJobApplicant.setEducationLevel(jobApplicant.getEducationLevel());
        existingJobApplicant.setYearsOfExperience(jobApplicant.getYearsOfExperience());
        return jobApplicantRepository.save(existingJobApplicant);
    }

    public void addJobInterview(String jobApplicantId, Job job) throws Exception {
        LOGGER.info("*** adding job {} for applicant {}", new Gson().toJson(job), jobApplicantId);
        //find the job applicant
        JobApplicant jobApplicant = this.getSingleJobApplicant(jobApplicantId);

        //validate if the job being applied for exists
        Job newJobApplication = jobService.getSingleJob(job.getId());

        Set<Job> existingJobInterviews = jobApplicant.getJobInterviews();
        existingJobInterviews.add(newJobApplication);

        jobApplicant.setJobInterviews(existingJobInterviews);
        jobApplicantRepository.save(jobApplicant);
    }

    public void removeJobInterview(String jobApplicantId, Job job) throws Exception {
        LOGGER.info("*** removing job {} for applicant {}", new Gson().toJson(job), jobApplicantId);
        //find the job applicant
        JobApplicant jobApplicant = this.getSingleJobApplicant(jobApplicantId);

        //validate if the job being removed exists
        Job removeJobApplication = jobService.getSingleJob(job.getId());

        //get the current jobs the applicant has
        Set<Job> existingJobInterviews = jobApplicant.getJobInterviews();
        existingJobInterviews.remove(removeJobApplication);
        jobApplicant.setJobInterviews(existingJobInterviews);
        jobApplicantRepository.save(jobApplicant);
    }

    public Set<Job> getJobApplicantJobs(String jobApplicantId) throws Exception {
        JobApplicant jobApplicant = this.getSingleJobApplicant(jobApplicantId);
        return jobApplicant.getJobInterviews();
    }
}
