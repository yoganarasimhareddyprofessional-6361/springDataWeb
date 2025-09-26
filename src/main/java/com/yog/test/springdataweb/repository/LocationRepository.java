package com.yog.test.springdataweb.repository;

import com.yog.test.springdataweb.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Integer> {
}