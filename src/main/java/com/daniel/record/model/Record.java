package com.daniel.record.model;

import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;

@Entity
@Table(name = "t_record")
@Data
public class Record {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "id", columnDefinition = "uuid")
	@CreatedBy
	private UUID id;

	@Column(name = "record_type")
	private String recordType;

	@Column(name = "device_id")
	private String deviceId;

	@Column(name = "event_date_time")
	private ZonedDateTime eventDateTime;

	@Column(name = "field_a")
	private Long fieldA;

	@Column(name = "field_b")
	private String fieldB;

	@Column(name = "field_c")
	private Double fieldC;

}
