package com.careerday.backenddeveloper.jobapplicants;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * Controller that will expose the job applicants resource.
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
public class JobApplicantsController {
}