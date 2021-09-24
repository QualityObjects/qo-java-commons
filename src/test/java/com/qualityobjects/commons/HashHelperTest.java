package com.qualityobjects.commons;

import com.qualityobjects.commons.utils.HashHelper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


class HashHelperTest {

	@Test
	void hashSHA256Test() {

		String output = HashHelper.hashSHA256("Test");

		Assert.assertEquals(output, "532eaabd9574880dbf76b9b8cc00832c20a6ec113d682299550d7a6e0f345e25".toUpperCase());
	}

	@Test
	void hashSHA256NullTest() {

		String output = HashHelper.hashSHA256(null);

		Assert.assertNull(output);

	}

	@Test
	void hashExceptionTest() {

		String output ="";

		output = HashHelper.hash("prueba", "dolores");
		Assert.assertNull(output);

	}

	@Test
	void hashMD5Test() {

		String output = HashHelper.hashMD5("Test");

		Assert.assertEquals(output, "0cbc6611f5540bd0809a388dc95a615b".toUpperCase());
	}

	@Test
	void hashMD5NullTest() {

		String output = HashHelper.hashMD5(null);

		Assert.assertNull(output);

	}
}
