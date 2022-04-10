package models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;

import java.util.*;

import models.Project;

import static org.junit.Assert.*;

/**
 * Test class for Searchquerystats.
 * @author Herve Ngomseu Fosting
 */
public class SearchQueryStatsTest {

    /**
     * Variable for test that contains skills
     */
    ArrayList<Job> skills = new ArrayList<Job>(Arrays.asList(new Job("1", "java")));
    /**
     * Variable for test that project object
     */
    Project proj = new Project("1", "123", 123456, "title", "type", "descriptions", skills);
    /**
     * Variable for test that contains projects
     */
    List<Project> projects = new ArrayList<Project>(Arrays.asList(proj));
    /**
     * Variable for test that map have String and List of projects
     */
    LinkedHashMap<String, List<Project>> projectMap = new LinkedHashMap<>();
    /**
     * Variable for test that contains search Query
     */
    SearchQueryStats searchQS = new SearchQueryStats("java", projectMap);

    /**
     * test Searchquerystats constructor  method..
     */
    @Test
    public void SearchQueryStats() {
        SearchQueryStats sqs = new SearchQueryStats("java", projectMap);
         assertTrue(EqualsBuilder.reflectionEquals(searchQS,sqs));
    }


    /**
     * test Combinestrings  method..
     */
    @Test
    public void combineStrings() {
        projectMap.put("name", projects);
        assertEquals("descriptions"+ "\n",searchQS.combineStrings());

    }

    /**
     * test  Computewordlevelstat  method..
     */
    @Test
    public void computeWordLevelStat() {
        projectMap.put("name", projects);
        Map<String, Long> wordFreq = new LinkedHashMap<>();
        wordFreq.put("descriptions", Long.parseLong("1"));
        assertEquals(wordFreq,searchQS.computeWordLevelStat());
    }

    /**
     * test Computewordlevelstatbyproject method.
     */
    @Test
    public void computeWordLevelStatByProject() {
        projectMap.put("name", projects);
        Map<String, Long> wordFreq = new LinkedHashMap<>();
        wordFreq.put("", Long.parseLong("1"));
        assertEquals(wordFreq,searchQS.computeWordLevelStatByProject(""));

    }

    /**
     * test Combineoutput method.
     */
    @Test
    public void combineOutput() {
        Map<String, Long> wordFreq = new HashMap<String, Long>() ;
        wordFreq.put("name", Long.parseLong("123456"));
        assertEquals("Unique Word: name - Frequency: 123456<br />",searchQS.combineOutput(wordFreq));

    }
}
