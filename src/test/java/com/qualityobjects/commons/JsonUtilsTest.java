package com.qualityobjects.commons;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qualityobjects.commons.utils.JsonUtils;

class JsonUtilsTest {
	private static final String LINE_SEP = System.getProperty("line.separator");
	@Test
	void testSnakeCase() {
		String input = "firstName";
		String sc = JsonUtils.toSnakeCase(input);
		assertEquals("first_name", sc);
		
		input = "thisIsATest";
		sc = JsonUtils.toSnakeCase(input);
		assertEquals("this_is_a_test", sc);

		input = "ThisIsATest";
		sc = JsonUtils.toSnakeCase(input);
		assertEquals("this_is_a_test", sc);

		input = "this_is_a_test";
		sc = JsonUtils.toSnakeCase(input);
		assertEquals("this_is_a_test", sc);
	}
	
	@Test
	void testToJson() throws IOException {
		LocalDate date = LocalDate.now();
		Map<String, Object> input = Map.of("str", "Hola", "num", 23, "date", date);
		String json = JsonUtils.toJSON(input);
		
		assertTrue(json.contains("date") && json.contains("str") && json.contains("23") && json.contains("Hola"));
		@SuppressWarnings("unchecked")
		Map<String, Object> output = JsonUtils.parseJSON(json, Map.class);
		assertEquals(23, output.get("num"));
		assertEquals("Hola", output.get("str"));
		assertEquals(date, LocalDate.parse(output.get("date").toString()));
		assertTrue(json.contains("date") && json.contains("str") && json.contains("23") && json.contains("Hola"));

		output = JsonUtils.parseJSON(json, new TypeReference<Map<String, Object>>() {});
		assertEquals(23, output.get("num"));
		assertEquals("Hola", output.get("str"));
		assertEquals(date, LocalDate.parse(output.get("date").toString()));
	}

	@Test
	void testParseJsonNull() throws IOException {
		
		Map<String, Object> output = JsonUtils.parseJSON(null, new TypeReference<Map<String, Object>>() {});
		
		Assert.assertNull(output);
		
	}
	
	@Test
	void testToJsonNull() throws IOException {
		
		String output = JsonUtils.toJSON(null);
		
		Assert.assertNull(output);
		
	}
	
	@Test
	void testParseJsonException() throws IOException {
		
		try {
			JsonUtils.parseJSON("holaklsghklasngklsanvklsangjklasdnasdjkjgasdklñgfjasdklñgjsadklñgnasdklgjklasdjgklasdjgklasdjgksdjagklsdjgklsdj", new TypeReference<>() {});
		} catch(IOException ee) {
			Assert.assertTrue(ee.getMessage().contains("Error formating JSON from object"));
		}
	}
	
	@Test
	void testToPrettyJSON() throws IOException {
		LocalDate date = LocalDate.of(2020, 06, 22);
		Map<String, Object> input = new HashMap<String, Object>();
		
		input.put("str", "Hola");
		input.put("num", 23);
		input.put("date", date);
		
		String json = JsonUtils.toPrettyJSON(input);
		
		Assert.assertEquals(json, String.format("{%s  \"str\" : \"Hola\",%s  \"date\" : \"2020-06-22\",%s  \"num\" : 23%s}", LINE_SEP, LINE_SEP, LINE_SEP, LINE_SEP));
	}
	
	@Test
	void testToPrettyJsonNull() throws IOException {
		
		String output = JsonUtils.toPrettyJSON(null);
		
		Assert.assertNull(output);
		
	}
	
	@Test
	void testToPrettyJsonNullNull() throws IOException {
		
		String output = JsonUtils.toPrettyJSON(null, Map.class);
		
		Assert.assertNull(output);
		
	}
	
	@Test
	void testToPrettyJsonObject() throws IOException {
		
		LocalDate date = LocalDate.of(2020, 06, 22);
		Map<String, Object> input = new HashMap<String, Object>();
		
		input.put("str", "Hola");
		input.put("num", 23);
		input.put("date", date);
		
		String json = JsonUtils.toPrettyJSON(input, Map.class);
	
		Assert.assertEquals(json, String.format("{%s  \"str\" : \"Hola\",%s  \"date\" : \"2020-06-22\",%s  \"num\" : 23%s}", LINE_SEP, LINE_SEP, LINE_SEP, LINE_SEP));
	}
	
	@Test
	void testToMap() throws IOException {
		
		String json = "{ \"f1\" : \"v1\" } ";

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonNode = objectMapper.readTree(json);
		
		Map<String, Object> mapa = JsonUtils.toMap(jsonNode);
	
		Assert.assertEquals(mapa.toString(), "{f1=v1}");
	}
}
