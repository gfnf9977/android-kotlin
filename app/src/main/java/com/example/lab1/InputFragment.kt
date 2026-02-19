package com.example.lab1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class InputFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        val editFrom = view.findViewById<EditText>(R.id.editFrom)
        val editTo = view.findViewById<EditText>(R.id.editTo)
        val radioGroupTime = view.findViewById<RadioGroup>(R.id.radioGroupTime)
        val btnOk = view.findViewById<Button>(R.id.btnOk)

        sharedViewModel.fromCity.observe(viewLifecycleOwner) { if (it.isEmpty()) editFrom.text.clear() }
        sharedViewModel.toCity.observe(viewLifecycleOwner) { if (it.isEmpty()) editTo.text.clear() }
        sharedViewModel.time.observe(viewLifecycleOwner) { if (it.isEmpty()) radioGroupTime.clearCheck() }

        btnOk.setOnClickListener {
            val fromText = editFrom.text.toString().trim()
            val toText = editTo.text.toString().trim()
            val checkedId = radioGroupTime.checkedRadioButtonId

            if (fromText.isEmpty() || toText.isEmpty() || checkedId == -1) {
                Toast.makeText(requireContext(), "Завершіть введення всіх даних!", Toast.LENGTH_SHORT).show()
            } else {
                val selectedRadio = view.findViewById<RadioButton>(checkedId)

                sharedViewModel.fromCity.value = fromText
                sharedViewModel.toCity.value = toText
                sharedViewModel.time.value = selectedRadio.text.toString()

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ResultFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}
