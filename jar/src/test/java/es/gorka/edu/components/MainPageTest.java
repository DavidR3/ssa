package es.gorka.edu.components;

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
public class MainPageTest {

	private WicketTester tester;

	@Autowired
	protected WicketWebApplication wicketApplication;

	@Before
	public void setUp() {
		tester = new WicketTester(wicketApplication);
		tester.startPage(MainPage.class);
	}

	@Test
	public void testRenderPage() {
		tester.assertRenderedPage(MainPage.class);
	}

	@Test
	public void testLabels() {
		tester.assertLabel("title", "Listado de errores incluidos");
	}

	@Test
	public void testFormIsrendered() {
		tester.assertEnabled("homeLink");
		tester.assertEnabled("boardLink1");
		tester.assertEnabled("boardLink2");
		tester.assertEnabled("boardLink3");
		tester.assertEnabled("ListUsersLink");

	}

}
