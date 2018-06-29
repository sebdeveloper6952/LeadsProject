package com.projects.sebdeveloper6952.chapinleads.components

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.projects.sebdeveloper6952.chapinleads.R
import com.projects.sebdeveloper6952.chapinleads.interfaces.ItemFilterListener

class ListChooserDialogFragment : DialogFragment() {

    private lateinit var mContext: Context
    private lateinit var mListener: OnCompleteListener
    private lateinit var mCategories: Array<String>

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val selectedItems = ArrayList<String>()
        val builder = AlertDialog.Builder(mContext).apply {
            setTitle(getString(R.string.dialog_categories_title))
            setPositiveButton(getString(R.string.dialog_categories_btn_ok)) { dialog, which ->
                // deliver to the listener a list that contains categories as strings
                mListener.onFilterSubmit(selectedItems)
            }
            setNegativeButton(getString(R.string.dialog_categories_btn_cancel)) { dialog, which ->
                // TODO("decide if this callback is necessary")
                mListener.onFilterCancel()
            }
            setNeutralButton(getString(R.string.dialog_categories_btn_neutral)) {dialog, which ->
                // neutral button delivers the listener all the categories
                mListener.onFilterSubmit(mCategories.asList())
            }
            setMultiChoiceItems(mCategories, null) { dialog, which, isChecked ->
                if(isChecked)
                    selectedItems.add(mCategories[which])
                else if(selectedItems.contains(mCategories[which]))
                    selectedItems.remove(mCategories[which])
            }
        }
        return builder.create()
    }

    interface OnCompleteListener {
        fun onFilterSubmit(list: List<String>)
        // TODO("decide if this callback is necessary")
        fun onFilterCancel()
    }

    companion object {
        fun newInstance(listener: OnCompleteListener, items: Array<String>):
                ListChooserDialogFragment {
            return ListChooserDialogFragment().apply {
                mListener = listener
                mCategories = items
            }
        }
    }

}
