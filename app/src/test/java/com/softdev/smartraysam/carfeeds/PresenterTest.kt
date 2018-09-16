package com.softdev.smartraysam.carfeeds

import android.content.Context
import android.util.Log

import com.softdev.smartraysam.carfeeds.contract.GetDataContract
import com.softdev.smartraysam.carfeeds.interactor.Intractor
import com.softdev.smartraysam.carfeeds.Presenter.Presenter
import com.softdev.smartraysam.carfeeds.model.carModel

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PresenterTest {
    @Mock
    private val mGetDataView: GetDataContract.View? = null

    @Mock
    private var mInteractor: GetDataContract.Interactor? = null
    @Mock
    private val mListener: GetDataContract.onGetDataListener? = null

    private var presenter: GetDataContract.Presenter? = null
    internal var context: Context? = null
    internal var url = "https://s3-us-west-2.amazonaws.com"
    internal var msg: String? = null
    @Mock
    internal var allData: List<carModel>? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mInteractor = Intractor(mListener!!)
        presenter = Presenter(mGetDataView!!)
    }

    @Test
    fun getDataFromURL() {
        msg?.let { nonNullMessage ->
            mInteractor!!.initRetrofitCall(context!!, url)
            mGetDataView!!.onStart(msg!!)
        }

    }

    @Test
    fun onSuccess() {
        msg?.let { nonNullMessage ->
            mGetDataView!!.onGetDataSuccess(msg!!, allData!!)
            Log.v("onSuccess", msg)
        }

    }

    @Test
    fun onFailure() {
        msg?.let { nonNullMessage ->
            mGetDataView!!.onGetDataFailure(msg!!)
            Log.v("onFailure", msg)
        }
    }
}