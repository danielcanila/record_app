create table t_record (
    id  varchar(36) not null,
    device_id varchar(255),
    record_type varchar(255),
    event_date_time timestamptz,
    field_a bigint,
    field_b varchar(65535),
    field_c double precision,
    primary key (id)
);

create table t_audit (
    id  varchar(36) not null,
    request_time timestamptz,
    request_ip varchar(255),
    request_url varchar(65535),
    primary key (id)
);