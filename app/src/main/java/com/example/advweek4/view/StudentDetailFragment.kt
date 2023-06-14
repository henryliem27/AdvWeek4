package com.example.advweek4.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.advweek4.R
import com.example.advweek4.util.loadImage
import com.example.advweek4.viewmodel.ListViewModel

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val imageView = view.findViewById<ImageView>(R.id.imgStudentDetail)
            val progressBar = view.findViewById<ProgressBar>(R.id.progressBarStudentDetail)

            viewModel = ViewModelProvider(this)[ListViewModel::class.java]
            StudentListFragmentArgs.fromBundle(requireArguments()).id?.let { viewModel.single(it) }
            val Url =
                StudentListFragmentArgs.fromBundle(requireArguments()).id
            imageView.loadImage(Url,progressBar)
        }



    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }
}