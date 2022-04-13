package routes;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

import play.test.WithBrowser;

/**
 * Test class for Routes  
 * @author Seung Hyun Hong, Nastaran Naseri, Herve Ngomseu Fosting
 */
public class Routes extends WithBrowser  {
	/**
	 * Test to Validate browser for index() from Routes
	 **/
	@Test
	public void runInBrowserIndex() {
		browser.goTo("/");
		assertNotNull(browser.el("title").text());
	}
	/**
	 * Test to Validate browser for Free() from Routes
	 **/
	@Test
	public void runInBrowserFree() {
		browser.goTo("/free");
		assertNotNull(browser.el("title").text());
	}
	/**
	 * Test to Validate browser for Owner() from Routes
	 **/
	@Test
	public void runInBrowserOwner() {
		browser.goTo("/owner/:id");
		assertNotNull(browser.el("title").text());
	}
	/**
	 * Test to Validate browser for Skills() from Routes
	 **/
	@Test
	public void runInBrowserSkills() {
		browser.goTo("/skills/:skillId");
		assertNotNull(browser.el("title").text());
	}
	/**
	 * Test to Validate browser for GlobalStates() from Routes
	 **/
	@Test
	public void runInBrowserGlobalStates() {
		browser.goTo("/globalStats");
		assertNotNull(browser.el("title").text());
	}
	/**
	 * Test to Validate browser for ProjectIDStat() from Routes
	 **/
	@Test
	public void runInBrowserGetProjectIDStat() {
		browser.goTo("/getProjectIDStat/:projId");
		assertNotNull(browser.el("title").text());
	}



}

