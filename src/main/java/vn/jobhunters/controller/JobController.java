package vn.jobhunters.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.jobhunters.domain.Job;
import vn.jobhunters.domain.User;
import vn.jobhunters.service.JobService;

@RestController
@RequestMapping("/api/5")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // get all job
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJob() {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.jobService.fetchAllJob());
    }

    // create all job
    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(Job cJob) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.jobService.handleCreateJob(cJob));
    }

    // get job by id
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.jobService.fetchJobById(id));
    }

    // update job
    @PutMapping("/jobs")
    public ResponseEntity<Job> updateJob(@RequestBody Job putJob) {
        return ResponseEntity.status(HttpStatus.OK).body(this.jobService.handleUpdateJobById(putJob));
    }

    // delete job by id
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable("id") long id) {
        this.jobService.DeleteJob(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete succes");
    }

}
