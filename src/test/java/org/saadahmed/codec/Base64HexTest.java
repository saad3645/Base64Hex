/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.saadahmed.codec;

import org.apache.commons.codec.binary.StringUtils;

import org.junit.Test;
import static org.junit.Assert.*;


public class Base64HexTest {


	/**
	 * Test the Base64 implementation
	 */
	@Test
	public void testBase64() {
		final String content = "Hello World";
		String encodedContent;
		byte[] encodedBytes = Base64Hex.encodeBase64Hex(StringUtils.getBytesUtf8(content));
		encodedContent = StringUtils.newStringUtf8(encodedBytes);
		assertEquals("encoding Hello World", "I6LiR6yWLszoR6G", encodedContent);

		Base64Hex b64Hex = new Base64Hex(BaseNCodec.MIME_CHUNK_SIZE, null);  // null lineSeparator same as saying no-chunking
		encodedBytes = b64Hex.encode(StringUtils.getBytesUtf8(content));
		encodedContent = StringUtils.newStringUtf8(encodedBytes);
		assertEquals("encoding Hello World", "I6LiR6yWLszoR6G", encodedContent);

		b64Hex = new Base64Hex(0, null);  // null lineSeparator same as saying no-chunking
		encodedBytes = b64Hex.encode(StringUtils.getBytesUtf8(content));
		encodedContent = StringUtils.newStringUtf8(encodedBytes);
		assertEquals("encoding Hello World", "I6LiR6yWLszoR6G", encodedContent);

		// bogus characters to decode (to skip actually) {e-acute*6}
		final byte[] decode = b64Hex.decode("I6LiR6yWLszoR6G");
		String decodeString = StringUtils.newStringUtf8(decode);
		assertEquals("decode Hello World", "Hello World", decodeString);
	}


}
