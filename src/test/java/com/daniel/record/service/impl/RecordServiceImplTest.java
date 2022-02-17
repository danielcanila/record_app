package com.daniel.record.service.impl;

import com.daniel.record.dto.RecordDTO;
import com.daniel.record.model.Record;
import com.daniel.record.repository.RecordRepository;
import com.daniel.record.util.Constants;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecordServiceImplTest {

	@Mock
	private RecordRepository recordRepositoryMock;
	@Mock
	private ModelMapper modelMapperMock;
	@Captor
	private ArgumentCaptor<Record> captor;

	private RecordServiceImpl cut;

	@BeforeEach
	public void setup() {
		cut = new RecordServiceImpl(recordRepositoryMock, modelMapperMock);
	}

	@Test
	void noContent_success() {
		//given
		var recordDTO = createRecordDTO();
		var record = createRecord();
		when(modelMapperMock.map(recordDTO, Record.class)).thenReturn(record);

		//when
		cut.noContent(recordDTO);

		//then
		verify(recordRepositoryMock, times(1)).save(captor.capture());
		var capturedRecord = captor.getValue();

		assertNotNull(capturedRecord);
		assertEquals(record, capturedRecord);
	}

	@Test
	void noContent_nullRecord() {
		//given
		RecordDTO recordDTO = null;

		//when
		var thrown = Assertions.assertThrows(RuntimeException.class, () -> {
			cut.noContent(recordDTO);
		});

		//then
		verify(recordRepositoryMock, times(0)).save(any());
		assertEquals(Constants.RECORD_CANNOT_BE_NULL_MESSAGE, thrown.getMessage());
	}

	@Test
	void echo_success() {
		//given
		var recordDTO = createRecordDTO();
		var record = createRecord();
		when(modelMapperMock.map(recordDTO, Record.class)).thenReturn(record);

		//when
		var result = cut.echo(recordDTO);

		//then
		verify(recordRepositoryMock, times(1)).save(captor.capture());
		var capturedRecord = captor.getValue();

		assertNotNull(capturedRecord);
		assertEquals(record, capturedRecord);
		assertEquals(recordDTO, result);
	}

	@Test
	void echo_nullRecord() {
		//given
		RecordDTO recordDTO = null;

		//when
		var thrown = Assertions.assertThrows(RuntimeException.class, () -> {
			cut.echo(recordDTO);
		});

		//then
		verify(recordRepositoryMock, times(0)).save(any());
		assertEquals(Constants.RECORD_CANNOT_BE_NULL_MESSAGE, thrown.getMessage());
	}

	@Test
	void device_success() {
		//given
		var recordDTO = createRecordDTO();
		var record = createRecord();
		when(modelMapperMock.map(recordDTO, Record.class)).thenReturn(record);

		//when
		var result = cut.device(recordDTO);

		//then
		verify(recordRepositoryMock, times(1)).save(captor.capture());
		var capturedRecord = captor.getValue();

		assertNotNull(capturedRecord);
		assertEquals(record, capturedRecord);
		assertEquals(recordDTO.getDeviceId(), result);
	}

	@Test
	void device_nullRecord() {
		//given
		RecordDTO recordDTO = null;

		//when
		var thrown = Assertions.assertThrows(RuntimeException.class, () -> {
			cut.device(recordDTO);
		});

		//then
		verify(recordRepositoryMock, times(0)).save(any());
		assertEquals(Constants.RECORD_CANNOT_BE_NULL_MESSAGE, thrown.getMessage());
	}

	private RecordDTO createRecordDTO() {
		return RecordDTO.builder()
				       .recordType("record_type")
				       .deviceId("device_id")
				       .eventDateTime(ZonedDateTime.parse("2014-05-12T05:09:48Z"))
				       .fieldA(100L)
				       .fieldB("field_b")
				       .fieldC(99.1)
				       .build();
	}

	private Record createRecord() {
		var record  = new Record();
		record.setRecordType("record_type");
		record.setDeviceId("device_id");
		record.setEventDateTime(ZonedDateTime.parse("2014-05-12T05:09:48Z"));
		record.setFieldA(100L);
		record.setFieldB("field_b");
		record.setFieldC(99.1);
		return record;
	}

}