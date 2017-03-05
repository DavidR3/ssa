package es.gorka.edu.components;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import es.gorka.edu.WicketWebApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SignUpPageTest {

	private WicketTester tester;

	@Autowired
	protected WicketWebApplication wicketApplication;

	@Before
	public void setUp() {
		tester = new WicketTester(wicketApplication);
		tester.startPage(SignUpPage.class);
	}

	@Test
	public void testRenderPage() {
		tester.assertRenderedPage(SignUpPage.class);
	}

	@Test
	public void testLabels() {
		tester.assertLabel("title", "Pantalla de registro");
		tester.assertLabel("formLogin:nameLabel", "Usuario:");
		tester.assertLabel("formLogin:passwordLabel", "Contraseña:");
	}

	@Test
	public void testFormIsrendered() {
		tester.assertEnabled("formLogin");
		tester.assertEnabled("title");
		tester.assertEnabled("homeLink");
		tester.assertEnabled("formLogin:button");
		tester.assertEnabled("formLogin:name");
		tester.assertEnabled("formLogin:password");

	}

	@Test
	public void testFormIsClickedAndFeedbackAppears() {
		tester.assertVisible("feedbackMessage");
		FormTester formTester = tester.newFormTester("formLogin", false);
		tester.executeAjaxEvent("formLogin:button", "click");
		tester.assertVisible("feedbackMessage");
	}
}