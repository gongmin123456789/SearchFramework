package com.example.a80066158.searchframework;

import com.gm.searchframework.ISearchFrameworkModel;
import com.gm.searchframework.SearchFrameworkPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 80066158 on 2017-03-09.
 */

public class MySearchPresenter extends SearchFrameworkPresenter {
    @Override
    public List<ISearchFrameworkModel> onGetSearchFrameworkModels() {
        List<ISearchFrameworkModel> searchModelList = new ArrayList<ISearchFrameworkModel>();

        searchModelList.add(new MySearchModel1());
        searchModelList.add(new MySearchModel2());
        searchModelList.add(new MySearchModel3());

        return searchModelList;
    }
}
