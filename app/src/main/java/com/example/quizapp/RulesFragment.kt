
package com.example.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.quizapp.databinding.FragmentRulesBinding

class RulesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding=DataBindingUtil.inflate<FragmentRulesBinding>(inflater,R.layout.fragment_rules,container,false)
        binding.playButtonInRules.setOnClickListener {view:View->
            view.findNavController().navigate(R.id.action_rulesFragment_to_gameFragment)
        }

        return binding.root
    }
}
