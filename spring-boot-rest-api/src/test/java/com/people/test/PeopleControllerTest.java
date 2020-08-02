package com.people.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.people.controller.PeopleController;
import com.people.exception.ResourceNotFoundException;
import com.people.model.People;
import com.people.repository.PeopleRepository;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class PeopleControllerTest {

	@InjectMocks
	PeopleController peopleController;

	@Mock
	PeopleRepository peopleRepository;

	@Test
	public void testAddPeople() {
		try {

			MockHttpServletRequest request = new MockHttpServletRequest();
			RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

			when(peopleRepository.save(any(People.class)));

			People people = new People("Gitesh Kumar", "2000-10-10", "gitesh@motorola.biz", "country",
					"https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50");
			People createdPeople = peopleController.createPeople(people);

			assertThat(createdPeople.getName().equals("Gitesh Kumar"));
			assertThat(createdPeople.getEmail().equals("gitesh@motorola.biz"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindAll() {
		// given
		People people1 = new People("Gitesh Kumar", "2000-10-10", "gitesh@motorola.biz", "country",
				"https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50");
		People people2 = new People("Gitesh Kumar2", "2000-10-10", "gitesh@motorola.biz", "country",
				"https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50");
		People peoples = new People();

		peopleController.createPeople(people1);
		peopleController.createPeople(people2);

		// when
		List<People> peoplList = peopleController.getAllPeople();

		// then
		assertThat(peoplList.size()).isEqualTo(2);

		assertThat(peoplList.get(0).getEmail()).isEqualTo(people1.getEmail());

		assertThat(peoplList.get(1).getEmail()).isEqualTo(people2.getEmail());
	}

	@Test
	public void testFindParticular() {
		// given
		People people1 = new People("Gitesh Kumar", "2000-10-10", "gitesh@motorola.biz", "country",
				"https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50");

		peopleController.createPeople(people1);

		// when
		ResponseEntity<People> responseEntity = null;
		try {
			responseEntity = peopleController.getPeopleById((long) 1);
			assertThat(responseEntity.getBody().getName()).isEqualTo("Gitesh Kumar");
		} catch (ResourceNotFoundException e) {
	
		}
	}

}
