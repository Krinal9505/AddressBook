package com.addressbook.AddressBook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.addressbook.AddressBook.beans.Person;

@Repository
public interface AddressBookRepository extends JpaRepository<Person, Integer>{

}
