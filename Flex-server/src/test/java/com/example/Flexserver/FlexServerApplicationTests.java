package com.example.Flexserver;

import com.example.Flexserver.domain.model.User;
import com.example.Flexserver.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class FlexServerApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testUserCreate() {
		User user = new User();
		user.setUsername("isira.uditha");
		user.setName("Isira Uditha");
		user.setRole("manager");
		user.setPassword("123");
		assertThat(userRepository.createUser(user));



	}

}
