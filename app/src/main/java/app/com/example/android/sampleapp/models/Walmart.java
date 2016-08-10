
package app.com.example.android.sampleapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Walmart {

    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("sort")
    @Expose
    private String sort;
    @SerializedName("responseGroup")
    @Expose
    private String responseGroup;
    @SerializedName("totalResults")
    @Expose
    private int totalResults;
    @SerializedName("start")
    @Expose
    private int start;
    @SerializedName("numItems")
    @Expose
    private int numItems;
    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();
    @SerializedName("facets")
    @Expose
    private List<Object> facets = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The query
     */
    public String getQuery() {
        return query;
    }

    /**
     * 
     * @param query
     *     The query
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * 
     * @return
     *     The sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * 
     * @param sort
     *     The sort
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * 
     * @return
     *     The responseGroup
     */
    public String getResponseGroup() {
        return responseGroup;
    }

    /**
     * 
     * @param responseGroup
     *     The responseGroup
     */
    public void setResponseGroup(String responseGroup) {
        this.responseGroup = responseGroup;
    }

    /**
     * 
     * @return
     *     The totalResults
     */
    public int getTotalResults() {
        return totalResults;
    }

    /**
     * 
     * @param totalResults
     *     The totalResults
     */
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * 
     * @return
     *     The start
     */
    public int getStart() {
        return start;
    }

    /**
     * 
     * @param start
     *     The start
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * 
     * @return
     *     The numItems
     */
    public int getNumItems() {
        return numItems;
    }

    /**
     * 
     * @param numItems
     *     The numItems
     */
    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    /**
     * 
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * 
     * @return
     *     The facets
     */
    public List<Object> getFacets() {
        return facets;
    }

    /**
     * 
     * @param facets
     *     The facets
     */
    public void setFacets(List<Object> facets) {
        this.facets = facets;
    }

}
