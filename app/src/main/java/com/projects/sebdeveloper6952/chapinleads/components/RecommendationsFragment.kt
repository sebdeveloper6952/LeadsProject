package com.projects.sebdeveloper6952.chapinleads.components


import DummyData.DummyData
import adapters.LeadItemRecyclerVAdapter
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.projects.sebdeveloper6952.chapinleads.R
import kotlinx.android.synthetic.main.fragment_recommendations.*
import kotlinx.android.synthetic.main.fragment_recommendations.view.*
import org.jetbrains.anko.design.snackbar

class RecommendationsFragment : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        // set host activity action bar title
        if(context is HomeActivity) {
            with(context) {
                setActionBarTitle(getString(R.string.title_recommendations))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // this fragment contributes to the host activity action bar
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_recommendations, container, false)
        with(layout) {
            // initialize recycler view
            with(recyclerView) {
                adapter = LeadItemRecyclerVAdapter(DummyData.RECS)
                layoutManager = LinearLayoutManager(activity)
            }
        }
        return layout
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.actionbar_recommendations, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when(item?.itemId) {
        R.id.action_filter -> {
            snackbar(layout_root, getString(R.string.action_filter))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    companion object {
        fun newInstance() = RecommendationsFragment()
    }
}
