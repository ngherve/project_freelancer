package routes;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

import play.test.WithBrowser;

/**
 * Test class for Routes  
 * @author
 *@version 1.0
 *@since 2021-11-20
 */
public class Routes extends WithBrowser  {
	@Test
	public void runInBrowserIndex() {
		browser.goTo("/");
		assertNotNull(browser.el("title").text());
	}

	@Test
	public void runInBrowserFree() {
		browser.goTo("/free");
		assertNotNull(browser.el("title").text());
	}

	@Test
	public void runInBrowserOwner() {
		browser.goTo("/owner/:id");
		assertNotNull(browser.el("title").text());
	}
	@Test
	public void runInBrowserSkills() {
		browser.goTo("/skills/:skillId");
		assertNotNull(browser.el("title").text());
	}
	@Test
	public void runInBrowserGlobalStates() {
		browser.goTo("/globalStats");
		assertNotNull(browser.el("title").text());
	}

	@Test
	public void runInBrowserGetProjectIDStat() {
		browser.goTo("/getProjectIDStat/:projId");
		assertNotNull(browser.el("title").text());
	}



}

