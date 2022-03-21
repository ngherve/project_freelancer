package controllers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Project;
import models.Query;
import org.junit.*;
import play.inject.Injector;
import play.inject.guice.GuiceInjectorBuilder;
import services.IApiService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static play.inject.Bindings.bind;

/**
 * Twitter api test by bind TwitterApi to TwitterImplementation, and get the instance of TwitterApi
 * We use an instance of TwitterTestImplementation provided by Guice
 * @author Adrien Poupa
 */

public class HomeControllerImplementationTest {

    private static IApiService homeControllerImplementationTest;

    private static Injector testApp;

    /**
     * Initialise the test application, bind the TwitterAPI interface to its mock implementation
     */
    @BeforeClass
    public static void initTestApp() {
        testApp = new GuiceInjectorBuilder()
                .overrides(bind(IApiService.class).to(HomeControllerTestImplementation.class))
                .build();
        homeControllerImplementationTest = testApp.instanceOf(IApiService.class);
    }


    @Test
    public void testGetProjects() throws ExecutionException, InterruptedException, IOException {
//        List<Query> queries = new ArrayList<>();
//        List<Project> search = homeControllerImplementationTest.getProjects(queries, "").toCompletableFuture().get();
//
//        String searchJsonFile = getJsonFileAsString(File.separator + "test" + File.separator + "Resources" +
//                File.separator + "Project.json");
//        ObjectMapper mapper = new ObjectMapper();
//        JsonFactory factory = mapper.getFactory();
//        JsonParser parser = factory.createParser(searchJsonFile);
//        JsonNode actualObj = mapper.readTree(parser);
//        JsonNode jsonProjects = actualObj.get("result").get("projects");
//        ArrayList<Project> projects = new ArrayList<>();
//        for(var json : jsonProjects){
//            try{
//                var project = mapper.treeToValue(json,Project.class);
//                projects.add(project);
//            }catch(JsonProcessingException ex){
//                ex.printStackTrace();
//            }
//        }
//
//        Assert.assertEquals(projects, search);
    }


  /*  @Test
    public void testProfile() throws ExecutionException, InterruptedException, IOException {
        WSResponse search = twitterTestImplementation.profile("test").toCompletableFuture().get();
        String body = search.getBody();

        String profileJsonFile = getJsonFileAsString(File.separator + "test" + File.separator + "resources" +
                File.separator + "profile.json");

        Assert.assertEquals(profileJsonFile, body);
    }
*/

    private String getJsonFileAsString(String path) throws IOException {
        String filePath = new File("").getAbsolutePath();
        byte[] encoded = Files.readAllBytes(Paths.get(filePath.concat(path)));

        return new String(encoded, "UTF-8");
    }
}
