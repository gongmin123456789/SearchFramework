package com.example.a80066158.searchframework;

import android.util.Log;

import com.gm.searchframework.ISearchFrameworkModel;
import com.gm.searchframework.SearchResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 80066158 on 2017-03-09.
 */

public class MySearchModel2 implements ISearchFrameworkModel {
    private static final String TAG = "MySearchModel2";

    @Override
    public SearchResult search(String searchText) {
        Log.i(TAG, "<search> searchText = " + searchText);

        List<String> resultList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            resultList.add(TAG + i);
        }

        SearchResult searchResult = new SearchResult();
        searchResult.setSearchText(searchText);
        searchResult.setSourceId(0);
        searchResult.setResultList(resultList);

        return searchResult;
    }
}
