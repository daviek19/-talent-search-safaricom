package com.careerday.backenddeveloper.jobs;

import com.careerday.backenddeveloper.jobs.support.Job;
import com.careerday.backenddeveloper.jobs.support.JobService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JobTests {
    private JobService jobService;


    @Before
    public void setup() {
        jobService = new JobService();
        Job job = new Job();
        job.setName("Developer Job Application");
        job.setYearsOfExperience(5);
        job.setDescription("This is a developer job application");
        jobService.createJob(job);
    }

    @Test
    public void hasJobs() {
        // given
        Iterable<Job> allJobs = jobService.getAllJobs();

        // when
        Integer jobsCount = Arrays.asList(allJobs).size();

        // then
        assertThat(jobsCount, greaterThan(1));
    }
}
