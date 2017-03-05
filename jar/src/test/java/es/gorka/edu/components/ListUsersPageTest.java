package es.gorka.edu.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import es.gorka.edu.WicketWebApplication;
import es.gorka.edu.dto.UserDTO;
import es.gorka.edu.repository.UserRepository;
import es.gorka.edu.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ListUsersPageTest {

	private WicketTester tester;

	@Autowired
	protected WicketWebApplication wicketApplication;

	@InjectMocks
	@Autowired
	private UserService serviceMock;

	@Mock
	private UserRepository repositoryMock;

	private ListUsersPage startPage;

	@Test
	public void testRenderPage() {
		tester.assertRenderedPage(ListUsersPage.class);
	}

	@Test
	public void testLabels() {
		tester.assertLabel("title", "SQL injection");
	}

	@Test
	public void testFormIsrendered() {
		tester.assertEnabled("code-btn-group");
		tester.assertEnabled("formBoard:name");
		tester.assertComponent("formBoard:name", TextField.class);
		tester.assertEnabled("mainPageLink");

	}

	@Test
	public void testFormIsClickedAndFeedbackAppears() {
		FormTester formTester = tester.newFormTester("formBoard", false);
		formTester.setValue("name", "user");
		formTester.submit();
		tester.assertRenderedPage(ListUsersPage.class);
	}

	@Before
	public void setUp() {
		tester = new WicketTester(wicketApplication);
		List<UserDTO> fakeList = getFakeList();
		Mockito.when(repositoryMock.searchAll(Mockito.any(UserDTO.class))).thenReturn(fakeList);
		tester.startPage(ListUsersPage.class);

	}

	private List<UserDTO> getFakeList() {
		List<UserDTO> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			UserDTO userDTO = new UserDTO();
			userDTO.setName("user" + i);
			list.add(userDTO);
		}
		return list;
	}

}
