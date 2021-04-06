package com.myubuntu.istestproject.views.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.myubuntu.istestproject.R
import com.myubuntu.istestproject.model.User
import com.myubuntu.istestproject.views.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        bViewDetails.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_itemListFragment)
        }
        tvLogout.setOnClickListener {
            User.removeUser(requireContext())
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            activity?.finish()
        }
    }
}