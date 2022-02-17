package com.daniel.record.service.impl;

import com.daniel.record.dto.RecordDTO;
import com.daniel.record.model.Record;
import com.daniel.record.repository.RecordRepository;
import com.daniel.record.service.RecordService;
import com.daniel.record.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

	private final RecordRepository recordRepository;
	private final ModelMapper modelMapper;

	public RecordServiceImpl(RecordRepository recordRepository, ModelMapper modelMapper) {
		this.recordRepository = recordRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void noContent(RecordDTO recordDTO) {
		validateAndSave(recordDTO);
	}

	@Override
	public RecordDTO echo(RecordDTO recordDTO) {
		validateAndSave(recordDTO);
		return recordDTO;
	}

	@Override
	public String device(RecordDTO recordDTO) {
		validateAndSave(recordDTO);
		return recordDTO.getDeviceId();
	}

	private void validateAndSave(RecordDTO recordDTO) {
		if (recordDTO == null) {
			throw new RuntimeException(Constants.RECORD_CANNOT_BE_NULL_MESSAGE);
		}
		var record = modelMapper.map(recordDTO, Record.class);
		recordRepository.save(record);
	}
}
