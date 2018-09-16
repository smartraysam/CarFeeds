package com.softdev.smartraysam.carfeeds;

import android.content.Context;
import android.util.Log;

import com.softdev.smartraysam.carfeeds.Core.GetDataContract;
import com.softdev.smartraysam.carfeeds.Core.Intractor;
import com.softdev.smartraysam.carfeeds.Core.Presenter;
import com.softdev.smartraysam.carfeeds.model.carModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;

public class PresenterTest  {
    @Mock
    private GetDataContract.View mGetDataView;

    @Mock
    private GetDataContract.Interactor mInteractor;
    @Mock
    private  GetDataContract.onGetDataListener mListener;

    private GetDataContract.Presenter presenter;
    Context context;
    String url="https://s3-us-west-2.amazonaws.com";
    String msg;
    @Mock
    List<carModel> allData;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mInteractor= new Intractor(mListener);
        presenter = new Presenter(mGetDataView);
    }
    @Test
    public void getDataFromURL( ) {
        mInteractor.initRetrofitCall(context,url);
        mGetDataView.onStart(msg);
    }
    @Test
    public void onSuccess() {
        mGetDataView.onGetDataSuccess(msg,allData);
        Log.v("onSuccess",msg);
    }

    @Test
    public void onFailure() {
        mGetDataView.onGetDataFailure(msg);
        Log.v("onFailure",msg);
    }
}