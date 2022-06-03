package com.test.project.async.repository;

import com.test.project.async.table.CheckA;
import com.test.project.async.table.Keys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckARepository extends JpaRepository<CheckA, Keys> {
}
