package com.qualityobjects.commons;

import com.qualityobjects.commons.utils.HashHelper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.equalTo;


@ExtendWith(MockitoExtension.class)
public class HashHelperTest {

	@Test
	public void hashSHA256Test() {

		String output = HashHelper.hashSHA256("Test");

		Assert.assertThat(output, equalTo("532eaabd9574880dbf76b9b8cc00832c20a6ec113d682299550d7a6e0f345e25"));
	}

	@Test
	public void hashSHA256NullTest() {

		String output = HashHelper.hashSHA256(null);

		Assert.assertThat(output, equalTo(null));

	}

	@Test
	public void hashExceptionTest() {

		String output ="";

		output = HashHelper.hash("prueba", "dolores");
		Assert.assertThat(output, equalTo(null));

	}

	@Test
	public void hashMD5Test() {

		String output = HashHelper.hashMD5("Test");

		Assert.assertThat(output, equalTo("0cbc6611f5540bd0809a388dc95a615b"));
	}

	@Test
	public void hashMD5NullTest() {

		String output = HashHelper.hashMD5(null);

		Assert.assertThat(output, equalTo(null));

	}
}
