package controllers;
import static org.junit.Assert.*;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

import actors.FreeActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import models.Project;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import play.Application;

import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Http.RequestBuilder;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;
import scala.compat.java8.FutureConverters;
import scala.concurrent.ExecutionContextExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
/**
 * Test Class for HomeCOntrollerTest
 * @author Seung Hyun Hong, Nastaran Naseri, Herve Ngomseu Fosting
 */
public class HomeControllerTest extends WithApplication{
    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }
    static ActorSystem system;

    @Before
    public void setup() {
        system = ActorSystem.create();
    }

    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    /**
     * Validate test for index()
     */
    @Test
    public void index() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");
        Result result = route(app, request);
        assertNotNull(result.status());
    }
    /**
     * Validate test for free()
     */
    @Test
    public void free() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/free");
        Result result = route(app, request);
        assertNotNull(result.status());
    }
    /**
     * Validate test for owner()
     */
    @Test
    public void owner() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/owner");
        Result result = route(app, request);
        assertNotNull(result.status());
    }
    /**
     * Validate test for skills()
     */
    @Test
    public void skills() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/skills");
        Result result = route(app, request);
        assertNotNull(result.status());
    }
    /**
     * Validate test for globalStat()
     */
    @Test
    public void globalStat() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/globalStat");
        Result result = route(app, request);
        assertNotNull(result.status());
    }
    /**
     * Validate test for getProjectIDStat()
     */
    @Test
    public void getProjectIDStat() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/getProjectIDStat");
        Result result = route(app, request);
        assertNotNull(result.status());
    }


    @Test
    public void freeWS() throws ExecutionException, InterruptedException {
    }

    @Test
    public void ownerWS() {
    }

    @Test
    public void skillWS() {
    }

    @Test
    public void globalStatActorWS() {
    }

    @Test
    public void getProjectIDStatWS() {
    }



}