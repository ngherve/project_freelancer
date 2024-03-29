package models;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  Class for .................................
 * @author  Herve Ngomseu Fotsing
 */
public class SearchQueryStats {

    public String searchWord;
    public LinkedHashMap<String, List<Project>> projects;

    /**
     * Parameterized constructor
     * @param searchWord in String
     * @param projects in LinkedHashMap<String,List<Project>>
     */
    public SearchQueryStats(String searchWord, LinkedHashMap<String, List<Project>> projects){
        this.searchWord = searchWord;
        this.projects = projects;
    }

    /**
     *
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
     *
     * @return getStringlongMap(words)
     */
    public Map<String, Long> computeWordLevelStat(){
        String words = combineStrings();
        return getStringLongMap(words);
    }

    /**
     *
     * @param words in String
     * @return getStringLongMap(words)
     */
    public static Map<String, Long> computeWordLevelStatByProject(String words){
        return getStringLongMap(words);
    }

    /**
     *
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
     *
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
