package com.example.advweek4.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4.R
import com.example.advweek4.model.Student
import com.example.advweek4.util.loadImage



@Suppress("NAME_SHADOWING")
class StudentListAdapter(private val studentList:ArrayList<Student>):
    RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {
    class StudentViewHolder(var view: View) : RecyclerView.ViewHolder(view)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int = studentList.size


    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val photoString: String? = studentList[position].photoUrl
        val imageView = holder.view.findViewById<ImageView>(R.id.studentPhoto)
        val progressBar = holder.view.findViewById<ProgressBar>(R.id.progressbarStudentlist)
        imageView.loadImage(studentList[position].photoUrl,progressBar)
//        Picasso.get().load(studentList[position].photoUrl).into(holder.view.findViewById<ImageView>(R.id.studentPhoto))
        val holder = holder.view
        holder.findViewById<TextView>(R.id.txtID).text = studentList[position].id
        holder.findViewById<TextView>(R.id.txtName).text = studentList[position].name
        holder.findViewById<Button>(R.id.btnDetail).setOnClickListener {
            val action = StudentListFragmentDirections.actionStudentListFragmentToStudentDetailFragment2(photoString)
            Navigation.findNavController(it).navigate(action)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateStudentList(newStudentList: List<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}