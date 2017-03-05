package es.gorka.edu.components;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import es.gorka.edu.WicketWebApplication;
import es.gorka.edu.dto.UserDTO;
import es.gorka.edu.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HomePageTest {

	private WicketTester tester;

	@Autowired
	protected WicketWebApplication wicketApplication;

	@Mock
	UserService service;

	private HomePage startPage;

	@Before
	public void setUp() {
		tester = new WicketTester(wicketApplication);
		startPage = tester.startPage(HomePage.class);
		startPage.setUserService(service);
	}

	@Test
	public void testRenderPage() {
		tester.assertRenderedPage(HomePage.class);
	}

	@Test
	public void testLabels() {
		tester.assertLabel("title", "Pantalla de Login");
		tester.assertLabel("formLogin:nameLabel", "Usuario:");
		tester.assertLabel("formLogin:passwordLabel", "Contraseña:");
	}

	@Test
	public void testFormIsrendered() {
		tester.assertEnabled("formLogin");
		tester.assertEnabled("title");
		tester.assertEnabled("signUpLink");
		tester.assertEnabled("formLogin:name");
		tester.assertEnabled("formLogin:password");

	}

	@Test
	public void testFormFieldsAreRequiered() {
		FormTester formTester = tester.newFormTester("formLogin", false);
		formTester.setValue("name", null);
		formTester.setValue("password", null);
		formTester.submit();
		tester.assertEnabled("feedbackMessage");
		tester.assertErrorMessages("El campo 'name' es obligatorio.", "El campo 'Contraseña:' es obligatorio.");
	}

	@Test
	public void testFormIsFilledBad() {
		Mockito.when(service.userExist(Mockito.any(UserDTO.class))).thenReturn(false);
		FormTester formTester = tester.newFormTester("formLogin", false);
		formTester.setValue("name", "badName");
		formTester.setValue("password", "badPassword");
		formTester.submit();
		tester.assertEnabled("feedbackMessage");
		tester.assertErrorMessages("El usuario no existe");
	}

	@Test
	public void testFormIsFilledGood() {
		Mockito.when(service.userExist(Mockito.any(UserDTO.class))).thenReturn(true);
		FormTester formTester = tester.newFormTester("formLogin", false);
		formTester.setValue("name", "goodName");
		formTester.setValue("password", "goodPassword");
		formTester.submit();
		tester.assertRenderedPage(MainPage.class);
	}
}
