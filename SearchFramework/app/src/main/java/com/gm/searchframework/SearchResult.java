package com.gm.searchframework;

import java.util.List;

/**
 * Created by 80066158 on 2017-03-08.
 */

public class SearchResult {
    private String searchText = null;
    private int sourceId = 0;
    private List resultList = null;

    public List getResultList() {
        return resultList;
    }

    public void setResultList(List resultList) {
        this.resultList = resultList;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }
}
