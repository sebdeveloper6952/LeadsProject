package com.projects.sebdeveloper6952.chapinleads.components


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.projects.sebdeveloper6952.chapinleads.R
import com.projects.sebdeveloper6952.chapinleads.interfaces.ItemFilterListener
import com.projects.sebdeveloper6952.chapinleads.interfaces.RecommendationsFilterListener

class TestDialogFragment : DialogFragment() {

    private lateinit var mContext: Context
    private lateinit var mListener: ItemFilterListener
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
                mListener.onSubmit(selectedItems)
            }
            setNegativeButton(getString(R.string.dialog_categories_btn_cancel)) { dialog, which ->
                // TODO("decide if this callback is necessary")
                mListener.onCancel()
            }
            setNeutralButton(getString(R.string.dialog_categories_btn_neutral)) {dialog, which ->
                // neutral button delivers the listener all the categories
                mListener.onSubmit(mCategories.asList())
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

    companion object {
        fun newInstance(listener: ItemFilterListener, items: Array<String>):
                TestDialogFragment {
            return TestDialogFragment().apply {
                mListener = listener
                mCategories = items
            }
        }
    }
}
