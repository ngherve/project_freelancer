package services;


import models.OwnerResult;
import models.Project;
import models.Query;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static play.inject.Bindings.bind;

import play.Application;
import play.inject.Injector;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceInjectorBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
/**
 *  Test for FreelancerApiServiceMock class
 *  @author Seung Hyun Hong, Nastaran Naseri, Herve Ngomseu Fosting
 */

public class FreelancerApiServiceMockTest {

    private static Injector testApp;
    private static IApiService freeService ;
    /**
     * Sets up initial configuration required for the test cases
     * Binds the FreelancerApiServiceMock class to IApiService Interface for testing
     * Mocks the methods for HomeController class
     */
    @BeforeClass
    public static void initTestApp() {
        testApp = new GuiceInjectorBuilder()
                .overrides(bind(IApiService.class).to(FreelancerApiServiceMock.class))
                .build();
        freeService = testApp.instanceOf(IApiService.class);
    }
    /**
     * Test for getProject in FreelancerApiService by using FreelancerApiServiceMock class
     * When the method getProjects() called, from FreelancerApiServiceMock class the result will returned
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void getProjects() throws ExecutionException, InterruptedException {
       List<Query> queries = new ArrayList<>();
       List<Project> projects = freeService.getProjects(queries,"").toCompletableFuture().get();
       assertTrue(projects.size()==10);
    }
    /**
     * Test for getOwnerResult in FreelancerApiService by using FreelancerApiServiceMock class
     * When the method getOwnerResult() called, from FreelancerApiServiceMock class the result will returned
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void getOwnerResult() throws ExecutionException, InterruptedException {
        OwnerResult ownerResult = freeService.getOwnerResult("61228312").toCompletableFuture().get();
        assertEquals(ownerResult.getUsers().get("61228312").getId(),"61228312");
    }
    /**
     * Test for getIDProjects in FreelancerApiService by using FreelancerApiServiceMock class
     * When the method getIDProjects() called, from FreelancerApiServiceMock class the result will returned
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void getIDProjects()throws ExecutionException, InterruptedException {
        List<Query> queries = new ArrayList<>();
        Project project = freeService.getIDProjects(queries,"").toCompletableFuture().get();
        assertEquals(project.getProjId(),"33251454");
    }
    /**
     * Test for throw exception in method
     */
    @Test
    public void throwTest(){
        List<Query> queries = new ArrayList<>();
        try {
            freeService.getIDProjects(queries, "exception");
        }catch(Exception e){
            Assert.fail();
        }
    }
    /**
     * test for setBaseUrl method
     */
    @Test
    public void setBaseUrl() {
        freeService.setBaseUrl("");
        assertEquals("",freeService.getBaseUrl());
    }
    /**
     * test for setBaseUrl method
     */
    @Test
    public void getBaseUrl() {
        freeService.setBaseUrl("");
        assertEquals("",freeService.getBaseUrl());
    }
}