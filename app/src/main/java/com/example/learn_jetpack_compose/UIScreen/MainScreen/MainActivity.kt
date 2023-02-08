package com.example.learn_jetpack_compose.UIScreen.MainScreen

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learn_jetpack_compose.UIScreen.MainScreen.adapter.ProductAdapter
import com.example.learn_jetpack_compose.databinding.ActivitySecondBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivitySecondBinding
    private lateinit var productAdapter: ProductAdapter

    private var isLoadingNext = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initView()
        startObserveData()

    }

    private fun initView(){
        binding.apply {
            layoutLoading.layoutLoading.startShimmerAnimation()
            productAdapter = ProductAdapter(viewModel.getProductList.value,object :ProductAdapter.OnProductClick{
                override fun itemClick(position: Int) {
                    Toast.makeText(this@MainActivity,"You click $position",Toast.LENGTH_SHORT).show()
                }

            },
                this@MainActivity,isLoadingNext
            )
            rvProduct.adapter = productAdapter
            rvProduct.layoutManager = LinearLayoutManager(this@MainActivity)

            rvProduct.addOnScrollListener(object :RecyclerView.OnScrollListener(){
                @SuppressLint("NotifyDataSetChanged")
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy>0){
                        val lastItem = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                        if (lastItem== viewModel.getProductList.value.size-1){
                            productAdapter.notifyDataSetChanged()
                            viewModel.callNextPage()
                        }
                    }
                }
            })
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun startObserveData(){
        lifecycleScope.launch {
            viewModel.getProductModel.collectLatest {

               // start app
                if (it.isLoading){
                    binding.layoutLoading.layoutLoading.visibility = View.VISIBLE
                }
                else
                {
                    binding.layoutLoading.layoutLoading.visibility = View.GONE
                }

                //change list
                if (it.products.isNotEmpty()){
                    productAdapter.notifyDataSetChanged()
                }

                isLoadingNext = it.isLoadingNext
            }
        }

    }


}