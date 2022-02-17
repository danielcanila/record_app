package com.daniel.record.controller;

import com.daniel.record.dto.RecordDTO;
import com.daniel.record.service.RecordService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/record")
public class RecordController {

	private final RecordService recordService;

	public RecordController(RecordService recordService) {
		this.recordService = recordService;
	}

	@PostMapping(value = "/nocontent", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> noContent(@Valid @RequestBody RecordDTO recordDTO) {
		recordService.noContent(recordDTO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/echo", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecordDTO> echo(@Valid @RequestBody RecordDTO recordDTO) {
		var toReturn = recordService.echo(recordDTO);
		return new ResponseEntity<>(toReturn, HttpStatus.OK);
	}

	@PostMapping(value = "/device", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> device(@Valid @RequestBody RecordDTO recordDTO) {
		var toReturn = recordService.device(recordDTO);
		return new ResponseEntity<>(toReturn, HttpStatus.OK);
	}

}
