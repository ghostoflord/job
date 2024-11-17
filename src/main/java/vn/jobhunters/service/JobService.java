package vn.jobhunters.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import vn.jobhunters.domain.Job;
import vn.jobhunters.repository.JobRepository;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // create job
    public Job handleCreateJob(@RequestBody Job job) {
        return this.jobRepository.save(job);
    }

    // get job by id
    public Job fetchJobById(Long id) {
        Optional<Job> jobOptional = this.jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            return jobOptional.get();
        }
        return null;
    }

    // get all job
    public List<Job> fetchAllJob() {
        return this.jobRepository.findAll();

    }

    // update job
    public Job handleUpdateJobById(Job reqJob) {
        Job currentJob = this.fetchJobById(reqJob.getId());
        if (currentJob != null) {
            currentJob.setName(reqJob.getName());
            currentJob.setDescription(reqJob.getDescription());
            // update
            this.jobRepository.save(currentJob);
        }
        return null;
    }

    public void DeleteJob(Long id) {
        this.jobRepository.deleteById(id);
    }
}
