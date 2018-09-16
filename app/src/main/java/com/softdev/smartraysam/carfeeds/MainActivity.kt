package com.softdev.smartraysam.carfeeds

import android.app.ProgressDialog
import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast

import com.softdev.smartraysam.carfeeds.contract.GetDataContract
import com.softdev.smartraysam.carfeeds.Presenter.Presenter
import com.softdev.smartraysam.carfeeds.adapter.FeedRecycleViewAdapter
import com.softdev.smartraysam.carfeeds.model.carModel
import com.softdev.smartraysam.carfeeds.util.ItemDivider
import com.softdev.smartraysam.carfeeds.util.OnItemClickListener
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GetDataContract.View{
    private var mPresenter: Presenter? = null

    private var linearLayoutManager: LinearLayoutManager?= null
    private var feedRecycleViewAdapter: FeedRecycleViewAdapter? = null
    private  var progressDialog: ProgressDialog?=null

    internal var baseURL = "https://s3-us-west-2.amazonaws.com"
    private var recyclerView: RecyclerView?=null
    private var mSwipeRefreshLayout: SwipeRefreshLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycleList)
        mSwipeRefreshLayout = findViewById(R.id.refresh_layout)
        progressDialog = ProgressDialog(this)
        mPresenter = Presenter(this)
        mPresenter!!.getDataFromURL(applicationContext, baseURL, "Loading")
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView!!.addItemDecoration(ItemDivider(this))
        mSwipeRefreshLayout!!.setOnRefreshListener {
            mPresenter!!.getDataFromURL(applicationContext, baseURL, "Loading")
            mSwipeRefreshLayout!!.isRefreshing = false
        }

    }

    override fun onGetDataSuccess(message: String, allCars: List<carModel>) {
        progressDialog!!.cancel()
        feedRecycleViewAdapter = FeedRecycleViewAdapter(applicationContext, allCars, object : OnItemClickListener {
            override fun onItemClick(v: View, position: Int) {
                val intent = Intent(baseContext, MapsActivity::class.java)
                intent.putExtra("CAR_POSITION", allCars[position].name)
                intent.putParcelableArrayListExtra("CAR_MODEL", allCars as ArrayList<carModel>)
                startActivity(intent)
            }
        })
        recyclerView!!.adapter = feedRecycleViewAdapter


    }

    override fun onGetDataFailure(message: String) {
        Log.d("Status", message)
        progressDialog!!.cancel()
        Toast.makeText(this, "Error occur while loading", Toast.LENGTH_SHORT).show()
    }

    override fun onStart(message: String) {
        Log.d("Status", message)
        progressDialog!!.setMessage("Loading...")
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }

    public override fun onDestroy() {
        super.onDestroy()
        recyclerView!!.adapter =null
    }

}
