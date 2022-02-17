package com.daniel.record.repository;

import com.daniel.record.model.Record;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<Record,Integer> {
}
