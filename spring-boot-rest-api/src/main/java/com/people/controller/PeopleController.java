package com.people.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.people.exception.ResourceNotFoundException;
import com.people.model.People;
import com.people.repository.PeopleRepository;

@RestController
@RequestMapping("/api")
public class PeopleController {

	@Autowired
	  private PeopleRepository peopleRepository;

	  /**
	   * Get all people list.
	   *
	   * @return the list
	   */
	  @GetMapping("/People")
	  public List<People> getAllPeople() {
	    return peopleRepository.findAll();
	  }

	  /**
	   * Gets people by id.
	   *
	   * @param peopleId the people id
	   * @return the people by id
	   * @throws ResourceNotFoundException the resource not found exception
	   */
	  @GetMapping("/People/{id}")
	  public ResponseEntity<People> getPeopleById(@PathVariable(value = "id") Long peopleId)
	      throws ResourceNotFoundException {
	    People people =
	    		peopleRepository
	            .findById(peopleId)
	            .orElseThrow(() -> new ResourceNotFoundException("People not found on :: " + peopleId));
	    return ResponseEntity.ok().body(people);
	  }

	  /**
	   * Create people.
	   *
	   * @param people the peopler
	   * @return the people
	   */
	  @PostMapping("/People")
	  public @Valid People createPeople(@Valid @RequestBody People people) {
	    return peopleRepository.save(people);
	  }

	  /**
	   * Update people response entity.
	   *
	   * @param peopleId the people id
	   * @param peopleDetails the people details
	   * @return the response entity
	   * @throws ResourceNotFoundException the resource not found exception
	   */
	  @PatchMapping("/People/{id}")
	  public ResponseEntity<People> updatePeople(
	      @PathVariable(value = "id") Long peopleId, @Valid @RequestBody People peopleDetails)
	      throws ResourceNotFoundException {

	    People people =
	        peopleRepository
	            .findById(peopleId)
	            .orElseThrow(() -> new ResourceNotFoundException("People not found on :: " + peopleId));

	    people.setName(peopleDetails.getName());
	    people.setDob(peopleDetails.getDob());
	    people.setEmail(peopleDetails.getEmail());
	    people.setCountry(peopleDetails.getCountry());
	    people.setAvatar(peopleDetails.getAvatar());
	    final People updatedPeople = peopleRepository.save(people);
	    return ResponseEntity.ok(updatedPeople);
	  }

}
