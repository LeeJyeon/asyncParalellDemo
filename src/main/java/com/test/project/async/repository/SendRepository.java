package com.test.project.async.repository;

import com.test.project.async.table.Keys;
import com.test.project.async.table.Send;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendRepository extends JpaRepository<Send, Keys> {
}
