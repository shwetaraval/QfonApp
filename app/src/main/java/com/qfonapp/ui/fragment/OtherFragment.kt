package com.qfonapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.qfonapp.R
import com.qfonapp.databinding.FragmentOtherBinding

private const val DISPLAY_TEXT = "display_text"

/**
 * A simple [Fragment] subclass.
 * Use the [OtherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OtherFragment : Fragment() {
    private var displayText: String? = null
    private lateinit var binding: FragmentOtherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            displayText = it.getString(DISPLAY_TEXT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_other, container, false)
        displayText?.let {
            binding.tvOtherFragment.text = it
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param displayText a text to be displayed on fragments.
         * @return A new instance of fragment OtherFragment.
         */
        @JvmStatic
        fun newInstance(displayText: String) =
            OtherFragment().apply {
                arguments = Bundle().apply {
                    putString(DISPLAY_TEXT, displayText)
                }
            }
    }
}