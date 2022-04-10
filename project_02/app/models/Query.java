package models;

/**
 * Query class is for passing query parameters when we call the API
 * @author Seung Hyun Hong, Tamanna Jahin, Nastaran Naseri, Herve Ngomseu Fosting
 */
public class Query {
    public String key;
    public String value;

    /**
     * Parametrized Constractor for Query
     * @param key String key for storing the Query key
     * @param value  String value for storing the Query value
     */
    public Query(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
