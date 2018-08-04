package com.careerday.backenddeveloper.jobapplicants;

import com.careerday.backenddeveloper.jobapplicants.support.JobApplicant;
import com.careerday.backenddeveloper.jobapplicants.support.JobApplicantRepository;
import com.careerday.backenddeveloper.jobapplicants.support.JobApplicantService;
import com.careerday.backenddeveloper.jobs.support.Job;
import com.careerday.backenddeveloper.jsend.Response;
import com.careerday.backenddeveloper.jsend.SuccessResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/***
 * Controller that will expose the job applicant resource.
 * All errors that occur will be handled gracefully
 * by {@link com.careerday.backenddeveloper.jsend.ResponseExceptionHandler}
 */

@Api(
        tags = {"job-applicants"},
        description = "Manage Job applicants",
        produces = "application/json")

@RequestMapping(path = "v1/job-applicants",
        headers = "Accept=application/json",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class JobApplicantsController {

    private JobApplicantService jobApplicantService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<Response> getAllJobApplicants() {
        Iterable<JobApplicant> jobApplicants = jobApplicantService.getAllJobApplicants();
        return new SuccessResponse("jobApplicants", jobApplicants)
                .send(HttpStatus.OK);
    }

    @GetMapping("/{job_applicant_id}")
    public ResponseEntity<Response> getSingleJob(@PathVariable("job_applicant_id") String jobApplicantId) throws Exception {
        JobApplicant jobApplicant = jobApplicantService.getSingleJobApplicant(jobApplicantId);
        return new SuccessResponse("jobApplicant", jobApplicant)
                .send(HttpStatus.OK);
    }

    @DeleteMapping("/{job_applicant_id}")
    public ResponseEntity<Response> deleteJob(@PathVariable("job_applicant_id") String jobApplicantId) throws Exception {
        jobApplicantService.deleteJobApplicant(jobApplicantId);
        return new SuccessResponse("job applicant deleted")
                .send(HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<Response> createJobApplicant(@Valid @RequestBody JobApplicant jobApplicant) {
        JobApplicant newJobApplicant = jobApplicantService.createJobApplicant(jobApplicant);
        return new SuccessResponse("jobApplicant", newJobApplicant)
                .send(HttpStatus.CREATED);
    }

    @PutMapping("/{job_applicant_id}")
    public ResponseEntity<Response> updateBook(@PathVariable("job_applicant_id") String jobApplicantId, @Valid @RequestBody JobApplicant jobApplicant) throws Exception {
        JobApplicant updatedJobApplicant = null;
        updatedJobApplicant = jobApplicantService.updateJobApplicant(jobApplicantId, jobApplicant);
        return new SuccessResponse("job", updatedJobApplicant)
                .send(HttpStatus.OK);
    }

    @GetMapping("/{job_applicant_id}/jobs")
    public ResponseEntity<Response> getJobApplicantJobs(@PathVariable("job_applicant_id") String jobApplicantId) throws Exception {
        Set<Job> jobApplicantJobs = jobApplicantService.getJobApplicantJobs(jobApplicantId);
        return new SuccessResponse("jobApplicantJobs", jobApplicantJobs)
                .send(HttpStatus.OK);
    }

    @PutMapping("/{job_applicant_id}/jobs")
    public ResponseEntity<Response> addJob(@PathVariable("job_applicant_id") String jobApplicantId, @Valid Job job) throws Exception {
        jobApplicantService.addJobInterview(jobApplicantId, job);
        return new SuccessResponse("job added")
                .send(HttpStatus.OK);
    }

    @DeleteMapping("/{job_applicant_id}/jobs")
    public ResponseEntity<Response> removeJob(@PathVariable("job_applicant_id") String jobApplicantId, @Valid Job job) throws Exception {
        jobApplicantService.removeJobInterview(jobApplicantId, job);
        return new SuccessResponse("job removed")
                .send(HttpStatus.OK);
    }
}