package controllers;

import models.Project;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Matchers;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;
import services.FreelancerApiService;
import services.IApiService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.junit.Assert.*;
import static play.mvc.Http.Status.OK;
import static org.mockito.Mockito.*;


public class HomeControllerTest extends WithApplication {
    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testIndex() throws Exception {
        // Arrange
        FreelancerApiService service = mock(FreelancerApiService.class);
        when(service.getProjects(ArgumentMatchers.anyList(), ArgumentMatchers.anyString())).thenReturn(ArgumentMatchers.any());

        // Act
        CompletionStage<Result> completionStage = new HomeController(service).index();
        CompletableFuture<Result> future = completionStage.toCompletableFuture();
        assertTrue(future.isDone());

//        Result result = future.get();
//
//        // Assert
//        assertTrue(future.isDone());
//        assertEquals(OK, result.status());
//        assertEquals("text/html", result.contentType().get());
//        assertEquals("utf-8", result.charset().get());
    }
}
