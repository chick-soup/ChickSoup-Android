package com.example.kakaotalk_dms.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment


class ChangeIdDialogFragment : DialogFragment() {

    companion object {
        const val TAG_CHANGE_ID_DIALOG = "dialog_change_id"
        fun getInstance(): ChangeIdDialogFragment = ChangeIdDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(
            com.example.kakaotalk_dms.R.layout.dialog_id_fragment,
            container,
            false
        )

        return v
    }

    override fun onResume() {
        super.onResume()
        val params = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as android.view.WindowManager.LayoutParams
    }

}