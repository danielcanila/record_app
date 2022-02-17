package com.daniel.record.service;

import com.daniel.record.dto.RecordDTO;

public interface RecordService {

	/**
	 * Stores the content without an answer. Will throw exception if recordDTO is null.
	 * @param recordDTO non null object.
	 */
	void noContent(RecordDTO recordDTO);

	/**
	 * Stores the content and returns the object as response. Will throw exception if recordDTO is null.
	 * @param recordDTO non null object.
	 * @return returns recordDTO
	 */
	RecordDTO echo(RecordDTO recordDTO);

	/**
	 * Stores the content and returns deviceId field from recordDTO. Will throw exception if recordDTO is null.
	 * @param recordDTO non null object.
	 * @return deviceId
	 */
	String device(RecordDTO recordDTO);
}
