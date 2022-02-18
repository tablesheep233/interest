package org.table.aop.expose.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.table.aop.expose.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
