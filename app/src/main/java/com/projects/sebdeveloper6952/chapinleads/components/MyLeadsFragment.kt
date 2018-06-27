package com.projects.sebdeveloper6952.chapinleads.components


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.projects.sebdeveloper6952.chapinleads.adapters.LeadItemRecyclerVAdapter
import com.projects.sebdeveloper6952.chapinleads.R
import com.projects.sebdeveloper6952.chapinleads.interfaces.ItemFilterListener
import com.projects.sebdeveloper6952.chapinleads.models.CategoryModel
import com.projects.sebdeveloper6952.chapinleads.models.DummyCategories
import com.projects.sebdeveloper6952.chapinleads.models.LeadCategory
import com.projects.sebdeveloper6952.chapinleads.models.LeadModel
import com.projects.sebdeveloper6952.chapinleads.repos.DataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_my_leads.*
import kotlinx.android.synthetic.main.fragment_my_leads.view.*
import org.jetbrains.anko.design.snackbar

class MyLeadsFragment : Fragment(), ItemFilterListener {

    val RC_NEW_LEAD = 55
    private val mDisposable = CompositeDisposable()
    private lateinit var mAdapter: LeadItemRecyclerVAdapter
    private lateinit var mDataModel: DataModel
    private lateinit var mCategories: List<CategoryModel>

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
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_my_leads, container, false)
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

        // fetch leads from database
        mDataModel = DataModel.getInstance(activity!!)
        updateLeads()
        updateCategories()

        var leadCat: LeadCategory
        // test
        mDisposable.add(mDataModel.getAllLeadCategoryObjects()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = { update(it) },
                        onError = {  }
                )
        )

        return layout
    }

    fun update(list: List<LeadCategory>) {
        snackbar(fragment_my_leads_layout_root, "lol")
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
        // TODO("define how to share items")
        //val menuItem = menu?.findItem(R.id.action_share)
        //val shareActionProvider = MenuItemCompat.getActionProvider(menuItem) as ShareActionProvider
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when(item?.itemId) {
        R.id.action_share -> {
            true
        }
        R.id.action_filter -> {
            TestDialogFragment.newInstance(this, DummyCategories.Cats)
                    .show(activity?.supportFragmentManager, "testDialog")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onFilterSubmit(list: List<String>) {
        // TODO("implement filtering")
    }

    override fun onFilterCancel() { }

    private fun addNewLead(newLead: LeadModel, categories: List<String>) {
        mDisposable.add(
                mDataModel.insertLead(newLead, categories)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onSuccess = { updateLeads() },
                                onError = { }
                        )
        )
    }

    private fun updateLeads() {
        mDisposable.add(
                mDataModel.getAllLeads()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onSuccess = { leadsUpdated(it) },
                                onError = { snackbar(fragment_my_leads_layout_root,
                                        getString(R.string.lead_update_error))
                                }
                        )
        )
    }

    private fun updateCategories() {
        mDisposable.add(
                mDataModel.getAllCategories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onSuccess = { categoriesUpdated(it) },
                                onError = {snackbar(fragment_my_leads_layout_root,
                                        "Error al actualizar categorias")}
                        )
        )
    }

    private fun leadsUpdated(leads: List<LeadModel>) {
        mAdapter.updateDataset(ArrayList(leads))
    }

    private fun categoriesUpdated(categories: List<CategoryModel>) {
        mCategories = ArrayList(categories)
    }

    private fun btnAddLead(v: View) {
        startActivityForResult(Intent(activity, NewLeadActivity::class.java), RC_NEW_LEAD)
    }

    companion object {
        fun newInstance() = MyLeadsFragment()
    }
}
