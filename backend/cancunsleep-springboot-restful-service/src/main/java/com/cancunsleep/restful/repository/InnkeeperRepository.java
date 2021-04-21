package com.cancunsleep.restful.repository;

import com.cancunsleep.restful.model.Innkeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InnkeeperRepository extends JpaRepository<Innkeeper, Long> {
}
