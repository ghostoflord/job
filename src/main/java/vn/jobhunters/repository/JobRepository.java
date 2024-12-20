package vn.jobhunters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.jobhunters.domain.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
