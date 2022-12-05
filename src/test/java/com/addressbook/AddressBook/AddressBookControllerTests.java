package com.addressbook.AddressBook;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.addressbook.AddressBook.beans.AddResponse;
import com.addressbook.AddressBook.beans.Person;
import com.addressbook.AddressBook.controller.AddressBookController;
import com.addressbook.AddressBook.services.AddressBookService;

@SpringBootTest(classes= {AddressBookControllerTests.class})
public class AddressBookControllerTests 
{
	@Mock
	AddressBookService addressBookService;
	
	@InjectMocks
	AddressBookController addressBookController;
	
	@Test
	@Order(1)
	public void test_getAllPersons()
	{
		List<Person> personList = new ArrayList<Person>();
		personList.add(new Person("Ram","Kapoor","0987654321","Mumbai"));
		personList.add(new Person("Priya","Kapoor","1234567890","Mumbai"));
		personList.add(new Person("Kabir","Singh","5678904321","Gujarat"));
		
		when(addressBookService.getAllPersonas()).thenReturn(personList);
		
		assertEquals(3,addressBookService.getAllPersonas().size());
	}
	
	@Test
	@Order(2)
	public void test_addPerson() throws Exception
	{
		Person person = new Person("Ram","Kaoor","0987654321","Mumbai");	
		
		when(addressBookService.addPerson(person)).thenReturn(person);
		
		@SuppressWarnings("rawtypes")
		ResponseEntity res = addressBookController.addPerson(person);
		
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
		assertEquals(person, res.getBody());
	}
	
	@Test
	@Order(3)
	public void test_updatePerson() throws Exception
	{
		Person existingPerson = new Person("Piya","Kaoor","0987654321","Mumbai");	
		Person person = new Person("Piya","Kaoor","0987654321","Mumbai");
		
		when(addressBookService.getPersonById(1)).thenReturn(existingPerson);
		when(addressBookService.updatePerson(existingPerson)).thenReturn(person);
		
		@SuppressWarnings("rawtypes")
		ResponseEntity res = addressBookController.updatePerson(1,person);
		
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(person, res.getBody());
	}
	

	@Test
	@Order(4)
	public void test_deletePerson() throws Exception
	{
		AddResponse res = new AddResponse();
		res.setId(1);
		res.setMsg("deleted");
		when(addressBookService.deletePerson(1)).thenReturn(res);
		assertEquals("deleted",res.getMsg());
	}
}
