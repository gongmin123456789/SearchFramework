package com.gm.searchframework;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 80066158 on 2017-03-08.
 */

public abstract class SearchFrameworkPresenter implements ISearchFrameworkPresenter {

    private static final String TAG = "SearchFrameworkPresenter";

    private List<ISearchFrameworkModel> searchFrameworkModels = null;
    private IOnSearchResultGetListener onSearchResultGetListener = null;
    private int getSearchResultModelCount = 0;

    public abstract List<ISearchFrameworkModel> onGetSearchFrameworkModels();


    public void onCreate(IOnSearchResultGetListener onSearchResultGetListener) {
        Log.i(TAG, "<onCreate> start");

        this.onSearchResultGetListener = onSearchResultGetListener;
        searchFrameworkModels = onGetSearchFrameworkModels();
    }

    @Override
    public void search(final String searchText) {
        Log.i(TAG, "<search> searchText = " + searchText);

        if (searchFrameworkModels == null) {
            Log.w(TAG, "<search> searchFrameworkModels is null");
            onSearchResultGetListener.onGetSearchResult(null);
            return;
        }
        if (searchText == null ||
                searchText.isEmpty()) {
            Log.w(TAG, "<search> searchText is empty");
            onSearchResultGetListener.onGetSearchResult(null);
            return;
        }
        getSearchResultModelCount = 0;

        final int searchModelCount = searchFrameworkModels.size();

        final SearchResult searchResults[] = new SearchResult[searchModelCount];

        for (int i = 0; i < searchModelCount; i++) {
            final int j = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    searchResults[j] = searchFrameworkModels.get(j).search(searchText);

                    getSearchResultModelCount++;
                    if (getSearchResultModelCount == searchModelCount) {
                        List<SearchResult> searchResultList = new ArrayList<SearchResult>();
                        for (SearchResult searchResult : searchResults) {
                            searchResultList.add(searchResult);
                        }
                        onSearchResultGetListener.onGetSearchResult(searchResultList);
                    }
                }
            });
            thread.run();
        }
    }
}
