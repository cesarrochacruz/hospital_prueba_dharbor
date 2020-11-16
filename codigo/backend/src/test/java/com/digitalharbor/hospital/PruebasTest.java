package com.digitalharbor.hospital;

import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;

@Transactional
class PruebasTest {
	
	@Test
	void prueba() {
		boolean respueta = true;
		assertTrue(respueta);
	}
}
