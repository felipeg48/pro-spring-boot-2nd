package io.pivotal.workshop.web.repository;

import org.springframework.data.repository.CrudRepository;

import io.pivotal.workshop.web.domain.Person;

public interface DirectoryRepository extends CrudRepository<Person,String> {

	public Person findByEmail(String email);
}
