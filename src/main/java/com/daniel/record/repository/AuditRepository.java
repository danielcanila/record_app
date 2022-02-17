package com.daniel.record.repository;

import com.daniel.record.model.Audit;
import org.springframework.data.repository.CrudRepository;

public interface AuditRepository extends CrudRepository<Audit,Integer> {
}
