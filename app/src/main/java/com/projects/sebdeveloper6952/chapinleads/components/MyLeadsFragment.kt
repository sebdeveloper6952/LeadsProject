package com.projects.sebdeveloper6952.chapinleads.components


import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import com.projects.sebdeveloper6952.chapinleads.adapters.LeadItemRecyclerVAdapter
import com.projects.sebdeveloper6952.chapinleads.R
import com.projects.sebdeveloper6952.chapinleads.models.*
import com.projects.sebdeveloper6952.chapinleads.repos.DataModel
import com.projects.sebdeveloper6952.chapinleads.viewmodels.MyLeadsViewModel
import com.projects.sebdeveloper6952.chapinleads.viewmodels.MyLeadsViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_my_leads.*
import kotlinx.android.synthetic.main.fragment_my_leads.view.*
import org.jetbrains.anko.Android
import org.jetbrains.anko.design.snackbar

class MyLeadsFragment : Fragment(), ListChooserDialogFragment.OnCompleteListener {

    val RC_NEW_LEAD = 55
    private val mDisposable = CompositeDisposable()
    private lateinit var mAdapter: LeadItemRecyclerVAdapter
    private lateinit var mViewModel: MyLeadsViewModel
    private lateinit var mLayoutRoot: View

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        // set host activity action bar title
        if(context is HomeActivity) {
            with(context) {
                setActionBarTitle(getString(R.string.title_my_leads))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // this fragment contributes to the host activity action bar
        setHasOptionsMenu(true)

        // TODO("fix this")
        val mDataModel = DataModel.getInstance(activity!!)

        val mViewModelFactory = MyLeadsViewModelFactory(mDataModel)

        mViewModel = ViewModelProviders
                .of(this, mViewModelFactory)
                .get(MyLeadsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_my_leads, container, false)
        // for snackbars to work
        mLayoutRoot = layout.fragment_my_leads_layout_root
        // initialize adapter
        mAdapter = LeadItemRecyclerVAdapter(emptyList())
        with(layout) {
            // set recycler view
            with(fragment_my_leads_recycler_view) {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(activity)
            }
            // floating action button onClick listener
            fragment_my_leads_fab_btn_add_lead.setOnClickListener { btnAddLead(layout) }
        }

        // fetch data from ViewModel
        updateLeads()

        return layout
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable.dispose()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            RC_NEW_LEAD -> {
                if(resultCode == Activity.RESULT_OK) {
                    // get created lead
                    val lead = data?.getSerializableExtra(NewLeadActivity.EXTRA_NEW_LEAD) as
                            LeadModel
                    val categories = data.getStringArrayExtra(NewLeadActivity.EXTRA_CATEGORIES)
                    // TODO("check the validity of the newly created Lead")
                    addNewLead(lead, categories.asList())
                    snackbar(fragment_my_leads_layout_root, getString(R.string.new_lead_success))
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        // add actions to host activity action bar
        inflater?.inflate(R.menu.actionbar_my_leads, menu)
        // get reference to search view and configure funtionality
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = true
            override fun onQueryTextChange(newText: String?): Boolean {
                mDisposable.add(mViewModel.getLeadByTitlePattern(newText!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onError = { onError(it.message!!) },
                                onSuccess = { leadsUpdated(it) }
                        ))
                return true
            }
        })
        // TODO("define how to share items")
        //val menuItem = menu?.findItem(R.id.action_share)
        //val shareActionProvider = MenuItemCompat.getActionProvider(menuItem) as ShareActionProvider
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when(item?.itemId) {
        R.id.action_share -> {
            true
        }
        R.id.action_filter -> {
            mDisposable.add(mViewModel.getAllCategoryTitles()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = { showCategoriesDialog(it) },
                            onError = { onError(it.message!!) }
                    ))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onFilterSubmit(list: List<String>) {
        mViewModel.getLeadsForCategories(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = { onError(it.message!!) },
                        onSuccess = { leadsUpdated(it) }
                )
    }

    override fun onFilterCancel() { }

    private fun addNewLead(newLead: LeadModel, categories: List<String>) {
        mDisposable.add(mViewModel.addNewLead(newLead, categories)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = { updateLeads() },
                        onError = { onError(it.message!!) }
                )
        )
    }

    private fun updateLeads() {
        mDisposable.add(
                mViewModel.getAllLeads()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onSuccess = { leadsUpdated(it) },
                                onError = { onError(it.message!!) }
                        )
        )
    }

    private fun onError(msg: String) {
        snackbar(mLayoutRoot,"Error: $msg")
    }

    private fun leadsUpdated(leads: List<LeadModel>) {
        mAdapter.updateDataset(ArrayList(leads))
    }

    private fun showCategoriesDialog(categories: List<String>) {
        ListChooserDialogFragment.newInstance(this, categories.toTypedArray())
                .show(activity?.supportFragmentManager, "categoriesDialog")
    }

    private fun btnAddLead(v: View) {
        startActivityForResult(Intent(activity, NewLeadActivity::class.java), RC_NEW_LEAD)
    }

    companion object {
        fun newInstance() = MyLeadsFragment()
    }
}
