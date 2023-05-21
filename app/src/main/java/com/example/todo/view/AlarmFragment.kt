@file:Suppress("SENSELESS_COMPARISON")

package com.example.todo.view

import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.R
import com.example.todo.databinding.FragmentAlarmBinding


class AlarmFragment : Fragment() {
    lateinit var ringtone: Ringtone
    lateinit var binding: FragmentAlarmBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var notificationUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(context, notificationUri)
        binding = FragmentAlarmBinding.inflate(inflater, container, false)

        if (ringtone == null){
            notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            ringtone = RingtoneManager.getRingtone(context, notificationUri)
        }

        if (ringtone != null){
            ringtone.play()
        }

        return binding.root
    }

    override fun onDestroy() {
    if(ringtone != null && ringtone.isPlaying){
        ringtone.stop()
    }
    super.onDestroy()
}
}