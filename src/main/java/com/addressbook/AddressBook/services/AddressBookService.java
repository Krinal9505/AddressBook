package com.addressbook.AddressBook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.addressbook.AddressBook.beans.AddResponse;
import com.addressbook.AddressBook.beans.Person;
import com.addressbook.AddressBook.repositories.AddressBookRepository;

@Component
@Service
public class AddressBookService
{
	@Autowired
	AddressBookRepository addressBookRepository;
	
	/**
	 * Method return list of persons
	 * @return
	 */
	public List<Person> getAllPersonas()
	{
		return addressBookRepository.findAll();
	}
	
	/**
	 * Method take person object, add into the database and return the added object
	 * if object is with first name null then it will throw exception
	 * @param person
	 * @return
	 * @throws Exception
	 */
	public Person addPerson(Person person) throws Exception
	{
		if(person.getFirstName() == null)
		{
			throw new Exception("FirstNameNull");
		}
		else
		{
			addressBookRepository.save(person);
			return person;
		}
	}
	
	/**
	 * Take person object as a parameter, update record into the database and return updated person object
	 * @param person
	 * @return
	 */
	public Person updatePerson(Person person)
	{
		addressBookRepository.save(person);
		return person;
	}
	
	/**
	 * Method get person single person by its id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Person getPersonById(int id) throws Exception
	{
		if(id == 0)
		{
			throw new Exception("IDNULL");
		}
		return addressBookRepository.findById(id).get();
	}
	
	/**
	 * Method take id to delete particular person object
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AddResponse deletePerson(int id)throws Exception
	{
		if(id == 0)
		{
			throw new Exception("IDNULL");
		}
		addressBookRepository.deleteById(id);
		AddResponse res = new AddResponse();
		res.setId(id);
		res.setMsg("Person Deleted");
		return res;
	}
}
