package com.cancunsleep.restful.repository;

import com.cancunsleep.restful.model.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateRepository extends JpaRepository<Date, Long> {
}
