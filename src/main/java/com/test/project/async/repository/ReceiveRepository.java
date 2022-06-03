package com.test.project.async.repository;

import com.test.project.async.table.Keys;
import com.test.project.async.table.Receive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiveRepository extends JpaRepository<Receive, Keys> {

    // 일반 SQL쿼리
    @Query(value = "select coalesce(max(r.com_Number) , 0 ) + 1 from Receive r where r.date = ?1", nativeQuery = true)
    public Long selectMaxNumber(String date);

}
