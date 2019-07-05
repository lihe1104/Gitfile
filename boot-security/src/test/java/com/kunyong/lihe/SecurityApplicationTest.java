package com.kunyong.lihe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SecurityApplicationTest {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Test
	public void password() {
		assertEquals("88","88");
		System.out.println (11);
//		String string = passwordEncoder.encode("admin");
//		System.out.println(string);
//		System.out.println(string.length());
	}
}
