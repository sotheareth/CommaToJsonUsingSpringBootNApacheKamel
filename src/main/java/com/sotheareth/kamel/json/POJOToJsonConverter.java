package com.sotheareth.kamel.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class POJOToJsonConverter {
	
	public static <T> String convert(T input) throws JsonProcessingException {
		// Create ObjectMapper
	      ObjectMapper mapper = new ObjectMapper();
	      mapper.enable(SerializationFeature.INDENT_OUTPUT);

	      // Convert object to JSON string
	      String json = mapper.writeValueAsString(input);

	      return json;
	}
}
