package com.example.todo.view

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.MainFragmentView
import com.example.todo.R
import com.example.todo.databinding.CreateTaskActivityBinding
import com.example.todo.model.Task
import com.example.todo.viewModel.TaskViewModel
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class CreateTask : Fragment(){

    private lateinit var mTaskViewModel: TaskViewModel
    private lateinit var binding: CreateTaskActivityBinding
    lateinit var alarmManager: AlarmManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreateTaskActivityBinding.inflate(inflater, container, false)
        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        binding.addTask.setOnClickListener {
            insertDataToDatabase()
        }

        binding.calendar.setOnClickListener{
            openTimePicker()
        }

        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    private fun openTimePicker() {

        var cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener{timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            cal.set(Calendar.MILLISECOND, 0)
            cal.set(Calendar.SECOND, 0)
            binding.addTaskTime.text = SimpleDateFormat("HH:mm").format(cal.time)

            alarmManager = context?.getSystemService(AlarmManager::class.java)!!
            val alarmClockInfo = AlarmManager.AlarmClockInfo(cal.timeInMillis, getAlarmInfoToPendingIntent())
            alarmManager.setAlarmClock(alarmClockInfo, getAlarmActionPendingIntent())
            Toast.makeText(context, "Оповещение установлено на ${binding.addTaskTime.text}", Toast.LENGTH_SHORT ).show()
        }
        TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()

    }

    private fun insertDataToDatabase() {
        val name = binding.addTaskName.text.toString()
        val time = binding.addTaskTime.text.toString()
        if (inputCheck(name)) {
            val task = Task(0, name, time)
            mTaskViewModel.addTask(task)
            Toast.makeText(requireContext(), "Добавлено!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_createTask_to_mainFragment)
        } else {
            Toast.makeText(requireContext(), "Заполните поля!", Toast.LENGTH_LONG).show()
        }
    }


    private fun inputCheck(name: String): Boolean {
        return !(TextUtils.isEmpty(name))
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun getAlarmInfoToPendingIntent(): PendingIntent{
        val alarmInfoIntent = Intent(context, MainFragmentView::class.java)
        alarmInfoIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getActivity(context, 0, alarmInfoIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun getAlarmActionPendingIntent():PendingIntent{
        val intent = Intent(context, AlarmFragment::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}