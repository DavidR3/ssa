package es.gorka.edu.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import es.gorka.edu.dto.UserDTO;
import es.gorka.edu.repository.UserRepository;
import es.gorka.edu.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@InjectMocks
	@Autowired
	private UserService serviceMock;

	@Mock
	private UserRepository repositoryMock;

	@Before
	public void setUp() {
		Mockito.doNothing().when(repositoryMock).insert(Mockito.any());
	}

	@Test
	public void test() {
		UserDTO dto = new UserDTO();
		dto.setName("test");
		dto.setPassword("test");
		Boolean result = this.restTemplate.postForObject("/user/insert/new", dto, Boolean.class);
		Assert.assertTrue(result);
	}

}
