package com.thinkitive.hr.repository;

import com.thinkitive.hr.model.Hr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrRepository extends JpaRepository<Hr, Long> {
}
