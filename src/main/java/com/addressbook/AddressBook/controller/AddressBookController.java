package com.addressbook.AddressBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addressbook.AddressBook.beans.AddResponse;
import com.addressbook.AddressBook.beans.Person;
import com.addressbook.AddressBook.services.AddressBookService;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController 
{
	@Autowired
	AddressBookService addressBookService;
	
	/**
	 * This method return the list of all person details in JSON format
	 * @return
	 */
	@GetMapping("/getAllPersons")
	public List<Person> getAllPersons()
	{
		return addressBookService.getAllPersonas();
	}
	
	/**
	 * This method take person data in JSON format and add into the database.
	 * It will check for the first name not null because it is not null into the table, and if it is null then it will throw the exception.
	 * method return person details in the form of object
	 * @param person
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/addPerson")
	public ResponseEntity addPerson(@RequestBody Person person) throws Exception
	{
		try
		{
			return new ResponseEntity<Person>(addressBookService.addPerson(person), HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Resource not found");
		}
	}
	
	/**
	 * This method take a person id and object as a parameter and check for the person is available in database or not.
	 * If its not then it will throw the exception
	 * If record available in database then it will update the data in table and return the updated person object
	 * @param id
	 * @param person
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PutMapping("updatePerson/{id}")
	public ResponseEntity updatePerson(@PathVariable(value="id") int id, @RequestBody Person person)
	{
		try
		{
			Person existingPerson = addressBookService.getPersonById(id);
			
			existingPerson.setFirstName(person.getFirstName());
			existingPerson.setLastName(person.getLastName());
			existingPerson.setPhone(person.getPhone());
			existingPerson.setAddress(person.getAddress());

			return new ResponseEntity<Person>(addressBookService.updatePerson(existingPerson), HttpStatus.OK);
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conflict in updating person");
		}
	}
	
	/**
	 * This method take person id for delete the person data.
	 * If person is not available in database for passed id then it will throw the exception and return response message
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deletePerson/{id}")
	public AddResponse deletePerson(@PathVariable(value="id") int id)
	{
		try
		{
			return addressBookService.deletePerson(id);
		}
		catch(Exception e)
		{
			AddResponse addResponse = new AddResponse();
			addResponse.setMsg("Conflict in deleting person");
			return addResponse;
		}
	}
}
