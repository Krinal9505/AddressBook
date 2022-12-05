package com.addressbook.AddressBook;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class ControllerIntegrationTests 
{
	@Test
	@Order(1)
	void getAllPersonsIntegrationTest()
	{
		TestRestTemplate restTemp = new TestRestTemplate();
	}
}
