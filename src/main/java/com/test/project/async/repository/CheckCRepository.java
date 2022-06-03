package com.test.project.async.repository;

import com.test.project.async.table.CheckC;
import com.test.project.async.table.Keys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckCRepository extends JpaRepository<CheckC, Keys> {
}
