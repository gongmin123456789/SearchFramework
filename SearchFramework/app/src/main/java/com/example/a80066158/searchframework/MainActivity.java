package com.example.a80066158.searchframework;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.gm.searchframework.SearchFrameworkActivity;
import com.gm.searchframework.SearchFrameworkPresenter;
import com.gm.searchframework.SearchResult;

import java.util.List;

public class MainActivity extends SearchFrameworkActivity {
    private static final String TAG = "MainActivity";

    private EditText searchEditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initContentView();
    }

    @Override
    public EditText onGetSearchEditText() {
        return searchEditText;
    }

    @Override
    public SearchFrameworkPresenter onGetSearchFrameworkPresenter() {
        return new MySearchPresenter();
    }

    @Override
    public void onGetSearchResult(List<SearchResult> resultList) {
        Log.i(TAG, "<onGetSearchResult> start");

        if (null == resultList) {
            Log.w(TAG, "<onGetSearchResult> resultList is null");
            return;
        }

        for (SearchResult searchResult : resultList) {
            Log.i(TAG, "<onGetSearchResult> " + searchResult.getSearchText() + ", " +
                    searchResult.getSourceId());
            if (searchResult.getResultList() == null) {
                Log.w(TAG, "<onGetSearchResult> searchResult.getResultList() is null");
            } else {
                for (int i = 0; i < searchResult.getResultList().size(); i++) {
                    String result = (String) searchResult.getResultList().get(i);
                    Log.i(TAG, "<onGetSearchResult> result = " + result);
                }
            }
        }
    }

    private void initContentView() {
        searchEditText = (EditText) findViewById(R.id.searchEditText);
    }
}
