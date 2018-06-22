package com.projects.sebdeveloper6952.chapinleads.components


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import com.projects.sebdeveloper6952.chapinleads.adapters.LeadItemRecyclerVAdapter
import com.projects.sebdeveloper6952.chapinleads.R
import com.projects.sebdeveloper6952.chapinleads.dummy.DummyData
import com.projects.sebdeveloper6952.chapinleads.interfaces.ItemFilterListener
import kotlinx.android.synthetic.main.fragment_recommendations.view.*
import org.jetbrains.anko.toast

class RecommendationsFragment : Fragment(), ItemFilterListener {

    private lateinit var mAdapter: LeadItemRecyclerVAdapter
    private var mData = DummyData.RECS

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
        mAdapter = LeadItemRecyclerVAdapter(mData)
        with(layout) {
            // initialize recycler view
            with(fragment_my_leads_recycler_view) {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(activity)
            }
        }
        return layout
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.actionbar_recommendations, menu)
        // TODO("decide to separate or not search view behaviour")
        // get reference to search view and configure functionality
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = true
            override fun onQueryTextChange(newText: String?): Boolean {
                mAdapter.updateDataset(filterDatasetByTitle(newText))
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when(item?.itemId) {
        R.id.action_filter -> {
            TestDialogFragment.newInstance(this, DummyData.CATEGORIES)
                    .show(activity?.supportFragmentManager, "testDialog")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onFilterSubmit(list: List<String>) {
        mAdapter.updateDataset(filterDatasetByCategory(list.toTypedArray()))
    }

    override fun onFilterCancel() {
        // TODO("remove for release")
        activity?.toast("onFilterCancel")
    }

    /**
     * Test function to filter dummy items by title.
     */
    private fun filterDatasetByTitle(query: String?): ArrayList<DummyData.ItemLead> {
        if(query == null || query == "") return mData
        val list = ArrayList<DummyData.ItemLead>(mData.size)
        for(item in mData)
            if(item.title.startsWith(query, true))
                list.add(item)
        return list
    }

    /**
     * Test function to filter dummy items by category.
     */
    private fun filterDatasetByCategory(categories: Array<String>): ArrayList<DummyData.ItemLead> {
        val list = ArrayList<DummyData.ItemLead>(mData.size)
        for(item in mData)
            for(category in categories)
                if(item.category.contentEquals(category))
                    list.add(item)
        return list
    }

    companion object {
        fun newInstance() = RecommendationsFragment()
    }
}
