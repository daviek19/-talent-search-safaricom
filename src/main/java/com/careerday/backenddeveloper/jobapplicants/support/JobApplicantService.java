package com.careerday.backenddeveloper.jobapplicants.support;

import com.careerday.backenddeveloper.jobs.support.Job;
import com.careerday.backenddeveloper.jobs.support.JobRepository;
import com.careerday.backenddeveloper.jobs.support.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Service providing high-level data access of @{@link JobApplicantRepository} to the H2 db
 */
@Service
public class JobApplicantService {

    @Autowired
    private JobApplicantRepository jobApplicantRepository;

    @Autowired
    private JobService jobService;

    public Iterable<JobApplicant> getAllJobApplicants() {
        return jobApplicantRepository.findAll();
    }

    public JobApplicant getSingleJobApplicant(String jobApplicantId) throws Exception {
        return jobApplicantRepository.findById(UUID.fromString(jobApplicantId))
                .orElseThrow(() -> new Exception("Job applicant not found:" + jobApplicantId));
    }

    public void deleteJobApplicant(String jobApplicantId) throws Exception {
        JobApplicant jobApplicant = this.getSingleJobApplicant(jobApplicantId);
        jobApplicantRepository.delete(jobApplicant);
    }

    public JobApplicant createJobApplicant(JobApplicant jobApplicant) {
        jobApplicant.setDateCreated(ZonedDateTime.now(ZoneId.systemDefault()));
        return jobApplicantRepository.save(jobApplicant);
    }

    public JobApplicant updateJobApplicant(String jobApplicantId, JobApplicant jobApplicant) throws Exception {
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
