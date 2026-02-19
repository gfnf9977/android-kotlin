package com.example.lab1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class ResultFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        val txtResult = view.findViewById<TextView>(R.id.txtResult)
        val btnCancel = view.findViewById<Button>(R.id.btnCancel)

        val from = sharedViewModel.fromCity.value
        val to = sharedViewModel.toCity.value
        val time = sharedViewModel.time.value

        txtResult.text = "Маршрут: $from — $to\nЧас: $time"

        btnCancel.setOnClickListener {
            sharedViewModel.clearData()

            parentFragmentManager.popBackStack()
        }
    }
}
