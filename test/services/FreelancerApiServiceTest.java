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
import play.inject.guice.GuiceApplicationBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FreelancerApiServiceTest {
    private static Application testApp;

    FreelancerApiService freeService = testApp.injector().instanceOf(FreelancerApiService.class);

    @BeforeClass
    public static void initTestApp() {
        testApp = new GuiceApplicationBuilder()
                .overrides(bind(IApiService.class).to(FreelancerApiServiceMock.class)).build();
    }

    @Test
    public void getProjects() throws ExecutionException, InterruptedException {
        List<Query> queries = new ArrayList<>();
        List<Project> proj = new ArrayList<>();
        List<Project> projects = freeService.getProjects(queries,"").toCompletableFuture().get();
        assertEquals(projects,proj);
    }

    @Test
    public void getOwnerResult() throws ExecutionException, InterruptedException {
        OwnerResult ownerResult = freeService.getOwnerResult("61228312").toCompletableFuture().get();
        assertEquals(ownerResult.getUsers().get("61228312").getId(),"61228312");
    }

    @Test
    public void getIDProjects()throws ExecutionException, InterruptedException {
        List<Query> queries = new ArrayList<>();
        Project proj = new Project();
        Project project = freeService.getIDProjects(queries,"").toCompletableFuture().get();
        assertEquals(project.getProjId(),proj.getProjId());
    }

    @Test
    public void throwTest(){
        List<Query> queries = new ArrayList<>();
        try {
            freeService.getIDProjects(queries, "exception");
        }catch(Exception e){
            Assert.fail();
        }
    }

    @Test
    public void setBaseUrl() {
        freeService.setBaseUrl("");
        assertEquals("",freeService.getBaseUrl());
    }

    @Test
    public void getBaseUrl() {
        freeService.setBaseUrl("");
        assertEquals("",freeService.getBaseUrl());
    }
}