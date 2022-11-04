package com.example.Flexserver;

import com.example.Flexserver.domain.model.User;
import com.example.Flexserver.repository.UserRepository;
import com.example.Flexserver.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class FlexServerApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void testUserCreate() {
		User user = new User();
		user.setUsername("sasika.chathura");
		user.setName("Sasika Chathura");
		user.setRole("worker");
		user.setPassword("123");
		assertThat(userService.createUser(user));

	}

}
