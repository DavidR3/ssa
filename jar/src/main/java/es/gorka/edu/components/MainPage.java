package es.gorka.edu.components;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.pages.InternalErrorPage;

/**
 * sample homepage
 *
 * @author Stefan Kloe
 *
 */
public class MainPage extends WebPage {

	public MainPage() {
		add(new Label("title", "Listado de errores incluidos"));
		add(new BookmarkablePageLink<String>("homeLink", HomePage.class));
		add(new BookmarkablePageLink<String>("boardLink1", BoardPage.class));
		add(new BookmarkablePageLink<String>("boardLink2", BoardPage.class));
		add(new BookmarkablePageLink<String>("boardLink3", BoardPage.class));

		add(new BookmarkablePageLink<String>("404_1", InternalErrorPage.class));
		add(new BookmarkablePageLink<String>("404_13", InternalErrorPage.class));
	}

}
