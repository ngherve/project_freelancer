package models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;

import java.util.*;

import models.Project;

import static org.junit.Assert.*;

public class SearchQueryStatsTest {

    ArrayList<Job> skills = new ArrayList<Job>(Arrays.asList(new Job("1", "java")));
    Project proj = new Project("1", "123", 123456, "title", "type", "descriptions", skills);
    List<Project> projects = new ArrayList<Project>(Arrays.asList(proj));
    LinkedHashMap<String, List<Project>> projectMap = new LinkedHashMap<>();
    SearchQueryStats searchQS = new SearchQueryStats("java", projectMap);

    @Test
    public void SearchQueryStats() {
        SearchQueryStats sqs = new SearchQueryStats("java", projectMap);
         assertTrue(EqualsBuilder.reflectionEquals(searchQS,sqs));
    }


    @Test
    public void combineStrings() {
        projectMap.put("name", projects);
        assertEquals("descriptions"+ "\n",searchQS.combineStrings());

    }

    @Test
    public void computeWordLevelStat() {
        projectMap.put("name", projects);
        Map<String, Long> wordFreq = new LinkedHashMap<>();
        wordFreq.put("descriptions", Long.parseLong("1"));
        assertEquals(wordFreq,searchQS.computeWordLevelStat());
    }

    @Test
    public void computeWordLevelStatByProject() {
        projectMap.put("name", projects);
        Map<String, Long> wordFreq = new LinkedHashMap<>();
        wordFreq.put("", Long.parseLong("1"));
        assertEquals(wordFreq,searchQS.computeWordLevelStatByProject(""));

    }

    @Test
    public void combineOutput() {
        Map<String, Long> wordFreq = new HashMap<String, Long>() ;
        wordFreq.put("name", Long.parseLong("123456"));
        assertEquals("Unique Word: name - Frequency: 123456<br />",searchQS.combineOutput(wordFreq));

    }
}
