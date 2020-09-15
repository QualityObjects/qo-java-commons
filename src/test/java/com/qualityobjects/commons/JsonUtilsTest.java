package com.qualityobjects.commons;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qualityobjects.commons.utils.JsonUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class JsonUtilsTest {
	private static final String LINE_SEP = System.getProperty("line.separator");
	@Test
	public void testSnakeCase() {
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
	public void testToJson() throws IOException {
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
	public void testParseJsonNull() throws IOException {
		
		Map<String, Object> output = JsonUtils.parseJSON(null, new TypeReference<Map<String, Object>>() {});
		
		Assert.assertThat(output, equalTo(null));
		
	}
	
	@Test
	public void testToJsonNull() throws IOException {
		
		String output = JsonUtils.toJSON(null);
		
		Assert.assertThat(output, equalTo(null));
		
	}
	
	@Test
	public void testParseJsonException() throws IOException {
		
		try {
			Map<String, Object> output = JsonUtils.parseJSON("holaklsghklasngklsanvklsangjklasdnasdjkjgasdklñgfjasdklñgjsadklñgnasdklgjklasdjgklasdjgklasdjgksdjagklsdjgklsdj", new TypeReference<>() {});
		} catch(IOException ee) {
			Assert.assertThat(ee.getMessage(), equalTo("Error formating JSON from object: {} holaklsghklasngklsanvklsangjklasdnasdjkjgasdklñgfj..."));
		}
	}
	
	@Test
	public void testToPrettyJSON() throws IOException {
		LocalDate date = LocalDate.of(2020, 06, 22);
		Map<String, Object> input = new HashMap<String, Object>();
		
		input.put("str", "Hola");
		input.put("num", 23);
		input.put("date", date);
		
		String json = JsonUtils.toPrettyJSON(input);
		
		Assert.assertThat(json, equalTo(String.format("{%s  \"str\" : \"Hola\",%s  \"date\" : \"2020-06-22\",%s  \"num\" : 23%s}", LINE_SEP, LINE_SEP, LINE_SEP, LINE_SEP)));
	}
	
	@Test
	public void testToPrettyJsonNull() throws IOException {
		
		String output = JsonUtils.toPrettyJSON(null);
		
		Assert.assertThat(output, equalTo(null));
		
	}
	
	@Test
	public void testToPrettyJsonNullNull() throws IOException {
		
		String output = JsonUtils.toPrettyJSON(null, Map.class);
		
		Assert.assertThat(output, equalTo(null));
		
	}
	
	@Test
	public void testToPrettyJsonObject() throws IOException {
		
		LocalDate date = LocalDate.of(2020, 06, 22);
		Map<String, Object> input = new HashMap<String, Object>();
		
		input.put("str", "Hola");
		input.put("num", 23);
		input.put("date", date);
		
		String json = JsonUtils.toPrettyJSON(input, Map.class);
	
		Assert.assertThat(json, equalTo(String.format("{%s  \"str\" : \"Hola\",%s  \"date\" : \"2020-06-22\",%s  \"num\" : 23%s}", LINE_SEP, LINE_SEP, LINE_SEP, LINE_SEP)));
	}
	
	@Test
	public void testToMap() throws IOException {
		
		String json = "{ \"f1\" : \"v1\" } ";

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonNode = objectMapper.readTree(json);
		
		Map<String, Object> mapa = JsonUtils.toMap(jsonNode);
	
		Assert.assertThat(mapa.toString(), equalTo("{f1=v1}"));
	}
}
