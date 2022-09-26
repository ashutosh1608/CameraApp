package com.example.trial

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.trial.databinding.FragmentSecondBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_second.*
import java.io.File


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgPath = requireArguments().getString("CurrImage")
        val bitmap = BitmapFactory.decodeFile(File(imgPath).toString())
        imageView.setImageBitmap(bitmap)

        val categories = resources.getStringArray(R.array.Categories)
        val adapter = ArrayAdapter<String>(requireActivity(),
            android.R.layout.simple_spinner_item, categories)
        category_spinner.adapter = adapter

        binding.buttonSecond.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_SecondFragment_to_FirstFragment)
            File(imgPath).delete()
        }

        binding.buttonUpload.setOnClickListener {
            debug_text.text = category_spinner.selectedItem.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}