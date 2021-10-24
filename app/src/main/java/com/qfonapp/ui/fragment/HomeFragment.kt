package com.qfonapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.qfonapp.R
import com.qfonapp.base.BaseNavigator
import com.qfonapp.data.model.PostModel
import com.qfonapp.databinding.FragmentHomeBinding
import com.qfonapp.ui.adapter.PostAdapter
import com.qfonapp.utils.isNetworkAvailable
import com.qfonapp.viewModels.HomeViewModel
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerFragment
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : DaggerFragment(), HasAndroidInjector, BaseNavigator {
    @Inject
    public lateinit var mViewModel: HomeViewModel
    private lateinit var adapter: PostAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        mViewModel?.setNavigator(this)
        binding.viewModel = mViewModel
        setRecyclerView()
        setObserver()

        if (isNetworkAvailable(context)) {
            binding?.noNetwork?.root?.visibility = View.GONE
            mViewModel?.getPostList()
        } else {
            binding?.noNetwork?.root?.visibility = View.VISIBLE
        }
    }

    private fun setRecyclerView() {
        adapter = PostAdapter(context)
        layoutManager = LinearLayoutManager(activity)
        binding?.rvPost?.let {
            it.adapter = adapter
            it.layoutManager = layoutManager
        }
    }

    private fun setObserver() {
        mViewModel.isViewLoading.observe(this, loadingObserver)
        mViewModel.postList.observe(this, audioListObserver)
        mViewModel.onMessageError.observe(this, errorObserver)
        mViewModel.isEmptyList.observe(this, emptyObserver)
    }

    private var emptyObserver = Observer<Boolean> {
        if (it) {
            binding?.noData?.root?.visibility = View.VISIBLE
            binding?.rvPost?.visibility = View.GONE
        } else {
            binding?.noData?.root?.visibility = View.GONE
            binding?.rvPost?.visibility = View.VISIBLE
        }
    }

    private var errorObserver = Observer<Any> {
        if (it is String) {
            binding?.noData?.tvNoData?.text = it
        }
    }

    private var loadingObserver = Observer<Boolean> {
        if (it) {
            binding?.progress.visibility = View.VISIBLE
        } else {
            binding?.progress.visibility = View.GONE
        }
    }

    private var audioListObserver = Observer<ArrayList<PostModel>> {
        binding.progress.visibility = GONE
        adapter?.submitList(it)
        if (it.size > 0) {
            binding?.rvPost?.visibility = View.VISIBLE
        } else {
            binding?.rvPost?.visibility = GONE
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }

    override fun retryNetworkOperations() {
        if (isNetworkAvailable(context)) {
            binding?.noNetwork?.root?.visibility = View.GONE
            mViewModel?.getPostList()
        } else {
            binding?.noNetwork?.root?.visibility = View.VISIBLE
        }
    }
}