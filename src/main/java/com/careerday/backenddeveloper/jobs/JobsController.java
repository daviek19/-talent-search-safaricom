package com.careerday.backenddeveloper.jobs;

import com.careerday.backenddeveloper.jobs.support.Job;
import com.careerday.backenddeveloper.jobs.support.JobService;
import com.careerday.backenddeveloper.jsend.ErrorResponse;
import com.careerday.backenddeveloper.jsend.Response;
import com.careerday.backenddeveloper.jsend.SuccessResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/***
 * Controller that will expose the job resource.
 * All errors that occur will be handled gracefully
 * by {@link com.careerday.backenddeveloper.jsend.ResponseExceptionHandler}
 */
@Api(
        tags = {"jobs"},
        description = "Manage Jobs",
        produces = "application/json")
@RequestMapping(path = "v1/jobs",
        headers = "Accept=application/json",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class JobsController {

    @Autowired
    private JobService jobService;

    @ApiOperation(value = "Get all books jobs")
    @GetMapping(value = {"", "/"})
    public ResponseEntity<Response> getAllJobs() {
        Iterable<Job> jobs = jobService.getAllJobs();
        return new SuccessResponse("jobs", jobs)
                .send(HttpStatus.OK);
    }

    @GetMapping("/{job_id}")
    public ResponseEntity<Response> getSingleJob(@PathVariable("job_id") String jobId) throws Exception {
        Job job = jobService.getSingleJob(jobId);
        return new SuccessResponse("job", job)
                .send(HttpStatus.OK);
    }

    @DeleteMapping("/{job_id}")
    public ResponseEntity<Response> deleteJob(@PathVariable("job_id") String jobId) throws Exception {
        jobService.deleteJob(jobId);
        return new SuccessResponse("job deleted")
                .send(HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<Response> createJob(@Valid @RequestBody Job job) {
        Job newJob = jobService.createJob(job);
        return new SuccessResponse("job", newJob)
                .send(HttpStatus.CREATED);
    }

    @PutMapping("/{job_id}")
    public ResponseEntity<Response> updateBook(@PathVariable("job_id") String jobId, @Valid @RequestBody Job job) throws Exception {
        Job updatedJob = null;
        updatedJob = jobService.updateJob(jobId, job);
        return new SuccessResponse("job", updatedJob)
                .send(HttpStatus.OK);
    }

}