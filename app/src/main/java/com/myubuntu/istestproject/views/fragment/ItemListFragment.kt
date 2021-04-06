package com.myubuntu.istestproject.views.fragment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.myubuntu.istestproject.R
import com.myubuntu.istestproject.model.Product
import com.myubuntu.istestproject.model.User
import com.myubuntu.istestproject.repository.NetworkUtils
import com.myubuntu.istestproject.utils.Methods
import com.myubuntu.istestproject.views.adapter.ProductListAdapter
import com.myubuntu.istestproject.utils.RecyclerViewItemDecorator
import com.myubuntu.istestproject.viewmodel.LoginViewModel
import com.myubuntu.istestproject.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_item_list.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemListFragment : Fragment() {
    private val TAG = this::class.java.simpleName
    private lateinit var loader: Dialog
    private var adapter = ProductListAdapter()
    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        loader = Methods.getLoader(requireActivity())
        initViewModel()
        rvList.adapter = adapter
        rvList.addItemDecoration(RecyclerViewItemDecorator(24, RecyclerViewItemDecorator.VERTICAL))

        loader.show()
        productViewModel.getProductList(requireContext())
                .observe(viewLifecycleOwner, Observer<Product>{
            loader.dismiss()
            if(it.hasError) Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            else{
                adapter.setData(it.list)
                if(it.list.size > 0) tvNoItems.visibility = View.GONE
            }
        })
    }

    private fun initViewModel(){
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        productViewModel.init()
    }
}