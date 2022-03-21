package models;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class for mapping search keywords to statistics about the unique keywords in the project descriptions
 * @author  Herve Ngomseu Fotsing
 */
public class SearchQueryStats {

    public String searchWord;
    public LinkedHashMap<String, List<Project>> projects;

    /**
     * Parameterized constructor
     * Initialising the specified keuword and its corresponding lists of projects for conducting Stat
     * @param searchWord in String
     * @param projects in LinkedHashMap<String,List<Project>>
     */
    public SearchQueryStats(String searchWord, LinkedHashMap<String, List<Project>> projects){
        this.searchWord = searchWord;
        this.projects = projects;
    }

    /**
     * Combines all project descriptions in a given search to provide global stats
     * @return result in String
     */
    public String combineStrings(){
        String result = "";
        for(Map.Entry<String, List<Project>> entry : projects.entrySet()){
            for (Project proj: entry.getValue()) {
                result += proj.description + "\n";
            }
        }
        return result;
    }

    /**
     * Computes word level stat in a given search of the 250 latest projects
     * @return getStringlongMap(words)
     */
    public Map<String, Long> computeWordLevelStat(){
        String words = combineStrings();
        return getStringLongMap(words);
    }

    /**
     * Given a string, it generates the frequency of each unique keywords
     * @param words in String
     * @return Map<String, Long> getStringLongMap(words)
     */
    public static Map<String, Long> computeWordLevelStatByProject(String words){
        return getStringLongMap(words);
    }

    /**
     * Helper method
     * Given a string, it generates the frequency of each unique keywords
     * @param words in String
     * @return wordFreq
     */
    private static Map<String, Long> getStringLongMap(String words) {
        Stream<String> stream = Stream.of(words.toLowerCase().split("\\W+")).parallel();
        BinaryOperator<Long> expectNoMerging = (a, b) -> { throw new AssertionError(); };
        Map<String, Long> wordFreq = stream.
                collect(Collectors.groupingBy(String::toString, Collectors.counting())).entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, expectNoMerging, LinkedHashMap::new));
        return wordFreq;
    }

    /**
     * Given a word-frequency map, it returns a formated output of each field
     * @param wordFreq
     * @return result.toString()
     */
    public static String combineOutput(Map<String, Long> wordFreq){
        StringBuilder result = new StringBuilder();
        for(var entry : wordFreq.entrySet()){
            result.append("Unique Word: ").append(entry.getKey()).append(" - Frequency: ").append(entry.getValue()).append("<br />");
        }
        return result.toString();
    }

}
