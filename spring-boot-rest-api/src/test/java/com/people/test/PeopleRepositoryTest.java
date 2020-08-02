package com.people.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.people.model.People;
import com.people.repository.PeopleRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PeopleRepositoryTest {

	@Autowired
	public PeopleRepository peopleRepository;
	
	  @Test
	    public void testSaveEmployee() {

		  People people = new People("Gitesh Kumar", "2000-10-10", "gitesh@motorola.biz", "country",
					"https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50");
		  peopleRepository.save(people);
		  Optional<People> people2 = peopleRepository.findById((long) 1);
	        assertNotNull(people2);
	    }

	    @Test
	    public void testGetEmployee() {

	    	  People people = new People("Gitesh Kumar", "2000-10-10", "gitesh@motorola.biz", "country",
						"https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50");
			  peopleRepository.save(people);
	        People people2 = peopleRepository.getOne((long) 1);
	        assertNotNull(people2);
	    }

	    @Test
	    public void testDeleteEmployee() {
	    	 People people = new People("Gitesh Kumar", "2000-10-10", "gitesh@motorola.biz", "country",
						"https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50");
			  peopleRepository.save(people);
			  peopleRepository.delete(people);
	    }

	    @Test
	    public void findAllEmployees() {
	    	 People people = new People("Gitesh Kumar", "2000-10-10", "gitesh@motorola.biz", "country",
						"https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50");
			  peopleRepository.save(people);
	        assertNotNull(peopleRepository.findAll());
	    }

	    @Test
	    public void deletByEmployeeIdTest() {
	    	People people = new People("Gitesh Kumar", "2000-10-10", "gitesh@motorola.biz", "country",
					"https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50");
		 People people2= peopleRepository.save(people);
		 peopleRepository.deleteById(people2.getId());
	    }
}
