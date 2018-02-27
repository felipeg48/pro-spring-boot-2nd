package io.pivotal.workshop.web.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import io.pivotal.workshop.web.repository.DirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.pivotal.workshop.web.controller.util.ApiResource;
import io.pivotal.workshop.web.domain.Person;

@Controller
@RequestMapping("/")
public class DirectoryController {

	private final DirectoryRepository repo;
	private final RequestMappingHandlerMapping mappings;

	@Autowired
	public DirectoryController(DirectoryRepository repo, RequestMappingHandlerMapping mappings) {
		this.repo = repo;
		this.mappings = mappings;
	}

	@GetMapping
	public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request) {
		List<ApiResource> methods = new ArrayList<>();
		mappings.getHandlerMethods().keySet().forEach(info -> {
			methods.add(new ApiResource(info.getMethodsCondition().toString(), info.getPatternsCondition().toString(),
					info.getParamsCondition().toString(),
					info.getPatternsCondition().getPatterns().stream().collect(Collectors.joining())));
		});
		modelAndView.setViewName("index");
		modelAndView.addObject("methods", methods);
		return modelAndView;
	}

	@RequestMapping(value = "/directory", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE})
	public ResponseEntity<Iterable<Person>> getPersons(@RequestHeader HttpHeaders headers) {
		return new ResponseEntity<Iterable<Person>>(this.repo.findAll(), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/directory/{id}", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE  })
	public ResponseEntity<Person> getPersonById(@RequestHeader HttpHeaders headers, @PathVariable String id) {

		return new ResponseEntity<Person>(this.repo.findOne(id), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/directory/search", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Person> getPersonByEmail(@RequestHeader HttpHeaders headers, @RequestParam("email") String email) {

		return new ResponseEntity<Person>(this.repo.findByEmail(email), headers, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/directory/{id}", method = { RequestMethod.PUT })
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Person> updatePerson(@RequestBody final Person person, @PathVariable String id) {

		Person _person = this.repo.findOne(id);
		
		if (_person != null) {
			
			if (person.getEmail() != null) _person.setEmail(person.getEmail());
			if (person.getName() != null) _person.setName(person.getName());
			if (person.getPhone() != null) _person.setPhone(person.getPhone());
			if (person.getBirthday() != null) _person.setBirthday(person.getBirthday());
			
			
			this.repo.save(_person);
			final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/directory/{id}").build()
					.expand(_person.getId()).toUri();
			return ResponseEntity.created(location).body(_person);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@RequestMapping(value = "/directory/{id}", method = { RequestMethod.DELETE })
	public ResponseEntity<Void> deletePerson(@PathVariable String id) {
		this.repo.delete(this.repo.findOne(id));
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/directory", method = { RequestMethod.POST })
	public ResponseEntity<Person> newPerson(@RequestBody final Person person) {

		this.repo.save(person);

		final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/directory/{id}").build()
				.expand(person.getId()).toUri();

		return ResponseEntity.created(location).body(person);

	}

}
