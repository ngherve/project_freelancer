package controllers;

import models.OwnerResult;
import models.Project;
import models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import play.Application;
import play.inject.Injector;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Result;
import services.FreelancerApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.mockito.Mockito.verify;
import static play.mvc.Http.Status.OK;

import static org.mockito.Mockito.when;
import static play.mvc.Results.ok;

/**
 * Implementation to test home controller
 * @author Seung Hyun Hong, Tamanna Jahin, Nastaran Naseri, Herve Ngomseu Fosting
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
    public static final RuntimeException TEST_EXCEPTION = new RuntimeException("Test exception");
    public static final String TEST_VALUE = "test";
    private HomeController factory;

    @Mock
    private Executor defaultExecutor;

    @Mock
    private Executor alternativeExecutor;

    @Mock
    private Supplier<String> supplier;

    @Mock
    private Runnable runnable;

    @Mock
    private Consumer<String> consumer;

    @Captor
    private ArgumentCaptor<Runnable> runnableCaptor;



    private static Injector testApp;
    @Mock
    FreelancerApiService freeService;
   // @Mock
   // HomeController homeController = new HomeController((freeService));

    final Result  projects = null;

    /**
     * Setting up controller before testing using mockito
     * Implementation to test home controller
     * @author Seung Hyun Hong, Tamanna Jahin, Nastaran Naseri, Herve Ngomseu Fosting
     */
    @Before
    public void setup(){
      //   homeController= Mockito.mock(HomeController.class);
      //   freeService = Mockito.mock(FreelancerApiService.class);
        // homeController.setService(freeService);
    }


    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testIndex()throws Exception {
//        CompletionStage<Result> stage = homeController.index().toCompletableFuture().get();
//        CompletableFuture<Result> future = stage.toCompletableFuture();
//        assertTrue(future.isDone());
       // assertEquals(null, future.get());

       // Result result = new HomeController().index().toCompletableFuture().get();
       // when(homeController.index()).thenReturn((freeService.getProjects(anyList(),anyString())));
                //thenApply(projects->{doReturn(ok((Content) projects));});

//    private static FreelancerApiService freeApiService;
//    private static HomeController homeController;
//    private static Injector testApp;
//    private static WSClient ws;
//
//    /**y
//     * Mock the Http Context before running the tests
//     * This is needed to render the template
//     */
//    @Before
//    public void initTestApp() {
//        play.mvc.Controller context = mock(HomeController.class);
//
//        freeApiService = new FreelancerApiService(ws);
//        HomeControllerTestImplementation homeController = new HomeControllerTestImplementation();
//
//        HttpExecutionContext ec = new GuiceApplicationBuilder().injector().instanceOf(HttpExecutionContext.class);
//        testApp = new GuiceInjectorBuilder()
//                .overrides(bind(IApiService.class).to(HomeControllerTestImplementation.class))
//                .build();
//       // freeApiService = testApp.instanceOf(FreelancerApiService.class);
//       // homeController = new HomeController(freeApiService );
//
//
//
//    }
//
//
//    protected Application provideApplication() {
//        return new GuiceApplicationBuilder().build();
//    }
//
//    @Test
//    public void index() throws ExecutionException, InterruptedException {
////
//         //Result result = new HomeController(freeApiService).index().toCompletableFuture().get();
//         Result result = homeController.index().toCompletableFuture().get();
////        assertEquals(OK, result.status());
////        assertEquals("text/html", result.contentType().get());
////        assertEquals("utf-8", result.charset().get());
////        assertTrue(contentAsString(result).contains("Welcome to the project of LabK-Group3."));
//        List<Project> projects = new ArrayList<>();
//        // Arrange
//        FreelancerApiService service = mock(FreelancerApiService.class);
//        when(service.getProjects(anyList(), anyString())).thenReturn((CompletionStage<List<Project>>) projects);
//
//        //Result result = HomeController.index().toCompletableFuture().get();
////
////        // Act
//          CompletionStage<Result> completionStage = new HomeController(service).index();
////          CompletableFuture<Result> future = completionStage.toCompletableFuture();
////        assertTrue(future.isDone());
////
////         Result result = future.get();
////
////        // Assert
////        assertTrue(future.isDone());
////        assertEquals(OK, result.status());
////        assertEquals("text/html", result.contentType().get());
////        assertEquals("utf-8", result.charset().get());
//    }
//
    }

    @Test
    public void captureSearchKeyword() {
       // when(homeController.captureSearchKeyword(any))

    }

    /**
     * testing the reverse map request
     * Implementation to test home controller
     * @author Seung Hyun Hong, Tamanna Jahin, Nastaran Naseri, Herve Ngomseu Fosting
     */
    @Test
    public void reverseMap( ) {
        LinkedHashMap<String,List<Project>> toReverse = new LinkedHashMap<>();
        List<Project> proj = new ArrayList<>();
        toReverse.put("1",proj);
        toReverse.put("2", proj);
        LinkedHashMap<String,List<Project>> reMap = new LinkedHashMap<>();
        reMap.put("2",proj);
        reMap.put("1", proj);
       // assertTrue(toReverse.equals(HomeController.reverseMap(reMap)));

    }

    @Test
    public void skills() {
    }
    /**
     * Implementation to test home controller
     * testing the aAPI request for getting ID
     * @author Seung Hyun Hong, Tamanna Jahin, Nastaran Naseri, Herve Ngomseu Fosting
     */
    @Test
    public void ownerIDSearch() throws IOException, ExecutionException, InterruptedException {
//        CompletionStage<OwnerResult> stage2 = freeService.getOwnerResult("");
//        CompletionStage<Result> stage = CompletableFuture.completedStage(null);
//        Result result = new HomeController(freeService).index().toCompletableFuture().get();
//        //CompletionStage<Result> stage = CompletableFuture.completedFuture(ok(Json.toJson(dbservice.getPeople().get())));
//        // CompletionStage<Result> stage = homeController.ownerIDSearch(Mockito.anyString());
//        when(homeController.ownerIDSearch(anyString())).thenReturn(stage);
//        when(freeService.getOwnerResult(anyString())).thenReturn(stage2);
//      //  assertEquals(stage,homeController.ownerIDSearch(""));
//        verify(homeController).ownerIDSearch("");
//        assertEquals(OK, result.status());

    }

    /**
     * Implementation to test home controller
     * tetsing global stat
     * @author Seung Hyun Hong, Tamanna Jahin, Nastaran Naseri, Herve Ngomseu Fosting
     */
    @Test
    public void getGlobalStat() {
//        CompletionStage<Result> stage = CompletableFuture.completedFuture(ok());
//        when(homeController.getProjectIDStat(anyString())).thenReturn(stage);
//        assertEquals(stage,homeController.getProjectIDStat(""));
//        verify(homeController).getProjectIDStat("");
    }

    @Test
    public void getProjectIDStat() {
    }

}