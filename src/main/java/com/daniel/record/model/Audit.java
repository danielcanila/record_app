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
@Table(name = "t_audit")
@Data
public class Audit {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "id", columnDefinition = "uuid")
	@CreatedBy
	private UUID id;

	@Column(name = "request_time")
	private ZonedDateTime requestTime;

	@Column(name = "request_ip")
	private String requestIp;

	@Column(name = "request_url")
	private String requestUrl;
}
