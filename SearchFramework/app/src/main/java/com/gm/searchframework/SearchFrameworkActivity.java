package com.gm.searchframework;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 80066158 on 2017-03-08.
 */

public abstract class SearchFrameworkActivity extends Activity implements IOnSearchResultGetListener {
    private static final String TAG = "SearchFrameworkActivity";

    protected EditText searchEditText = null;

    private boolean isInited = false;
    private SearchFrameworkPresenter searchFrameworkPresenter = null;
    private String searchText = null;
    private Timer searchTimer = null;

    public abstract EditText onGetSearchEditText();
    public abstract SearchFrameworkPresenter onGetSearchFrameworkPresenter();


    @Override
    protected void onStart() {
        super.onStart();

        Log.i(TAG, "<onStart> start");

        if (!isInited) {
            initSearchFrameworkPresenter();
            initSearchEditText();
        }
    }

    private void initSearchFrameworkPresenter() {
        Log.i(TAG, "<initSearchFrameworkPresenter> start");

        searchFrameworkPresenter = onGetSearchFrameworkPresenter();
        searchFrameworkPresenter.onCreate(this);
    }

    private void search(final String searchText, boolean searchRightNow) {
        if (searchRightNow) {
            searchFrameworkPresenter.search(searchText);
        } else {
            if (null != searchTimer) {
                searchTimer.cancel();
            }
            searchTimer = new Timer();
            searchTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    search(searchText, true);
                }
            }, 500);
        }
    }

    private void initSearchEditText() {
        Log.i(TAG, "<initSearchEditText> start");

        searchEditText = onGetSearchEditText();
        searchEditText.addTextChangedListener(textWatcher);
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search(searchEditText.getText().toString().trim(), true);
                    return false;
                }
                return false;
            }
        });
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub
            if (null != searchText && searchText.equals(s.toString().trim())) {
                Log.i(TAG, "<onTextChanged> searchKey is " + searchText + ", no need search again");
                return;
            }

            Log.i(TAG, "<onTextChanged> (" + s + ", " + start + ", " + before + ", " + count + ")");
            searchText = s.toString().trim();
            if (searchText.equals("")) {
                search(null, true);
                searchText = null;
            } else {
                search(searchText, false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub

        }
    };
}
