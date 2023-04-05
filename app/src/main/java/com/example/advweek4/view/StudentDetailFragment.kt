package com.example.advweek4.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4.R
import com.example.advweek4.model.Student
import com.example.advweek4.util.loadImage
import com.example.advweek4.view.StudentListAdapter.StudentViewHolder
import com.example.advweek4.viewmodel.ListViewModel

class StudentDetailFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val imageView = view.findViewById<ImageView>(R.id.imgStudentDetail)
            val progressBar = view.findViewById<ProgressBar>(R.id.progressBarStudentDetail)
            val Url =
                StudentListFragmentArgs.fromBundle(requireArguments()).image
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