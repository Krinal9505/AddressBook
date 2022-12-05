package com.addressbook.AddressBook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.addressbook.AddressBook.beans.Person;
import com.addressbook.AddressBook.repositories.AddressBookRepository;
import com.addressbook.AddressBook.services.AddressBookService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {AddressBookTests.class})
public class AddressBookTests 
{
	@Mock
	AddressBookRepository addressBookRep;
	
	@InjectMocks
	AddressBookService addressBookService;
	
	@Test
	@Order(1)
	public void test_getAllPersons()
	{
		List<Person> personList = new ArrayList<Person>();
		personList.add(new Person("Ram","Kapoor","0987654321","Mumbai"));
		personList.add(new Person("Priya","Kapoor","1234567890","Mumbai"));
		personList.add(new Person("Kabir","Singh","5678904321","Gujarat"));
		
		when(addressBookRep.findAll()).thenReturn(personList);
		
		assertEquals(3,addressBookService.getAllPersonas().size());
	}
	
	@Test
	@Order(2)
	public void test_addPersonWithNullFirstName() throws Exception
	{
		Person person = new Person(null,"Kaoor","0987654321","Mumbai");		
		when(addressBookRep.save(person)).thenReturn(person);
		Throwable exception = assertThrows(Exception.class, () -> addressBookService.addPerson(person));
	    assertEquals("FirstNameNull", exception.getMessage());
	}
	
	@Test
	@Order(3)
	public void test_addPerson()
	{
		Person person = new Person("Ram","Kaoor","0987654321","Mumbai");
		
		when(addressBookRep.save(person)).thenReturn(person);
		try {
			assertEquals(person, addressBookService.addPerson(person));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Order(4)
	public void test_updatePerson()
	{
		Person person = new Person("Priya","Kapoor","1234567890","Mumbai");
		
		when(addressBookRep.save(person)).thenReturn(person);
		try {
			assertEquals(person, addressBookService.updatePerson(person));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(5)
	public void test_getPersonByIdZero()
	{
		Throwable exception = assertThrows(Exception.class, () -> addressBookService.getPersonById(0));
	    assertEquals("IDNULL", exception.getMessage());
	}
	
	@Test
	@Order(6)
	public void test_deletePersonByIdZero()
	{
		Throwable exception = assertThrows(Exception.class, () -> addressBookService.deletePerson(0));
	    assertEquals("IDNULL", exception.getMessage());
	}
}
