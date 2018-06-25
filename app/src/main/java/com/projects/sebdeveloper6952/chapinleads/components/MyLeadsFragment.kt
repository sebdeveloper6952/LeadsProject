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
import com.projects.sebdeveloper6952.chapinleads.dummy.DummyData
import com.projects.sebdeveloper6952.chapinleads.interfaces.ItemFilterListener
import com.projects.sebdeveloper6952.chapinleads.repos.LeadRepository
import com.projects.sebdeveloper6952.chapinleads.room.AppDatabase
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
    private lateinit var mLeadRepo: LeadRepository

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

        // fetch items from database
        val leadModel = AppDatabase.getInstance(activity?.applicationContext!!)
                ?.leadsModel()!!
        mLeadRepo = LeadRepository(leadModel)
        // fetch leads from database
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
                            DummyData.Lead
                    // TODO("check the validity of the newly created Lead")
                    addNewLead(lead)
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
            TestDialogFragment.newInstance(this, DummyData.CATEGORIES)
                    .show(activity?.supportFragmentManager, "testDialog")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onFilterSubmit(list: List<String>) {
        // TODO("implement filtering")
    }

    override fun onFilterCancel() { }

    private fun addNewLead(newLead: DummyData.Lead) {
        mDisposable.add(
                mLeadRepo.insertItem(newLead)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onComplete = { updateLeads() },
                                onError = {
                                    snackbar(fragment_my_leads_layout_root,
                                            getString(R.string.new_lead_creating_error))
                                }
                        )
        )
    }

    private fun updateLeads() {
        mDisposable.add(
                mLeadRepo.getAllItems()
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

    private fun leadsUpdated(leads: List<DummyData.Lead>) {
        mAdapter.updateDataset(ArrayList(leads))
    }

    private fun btnAddLead(v: View) {
        startActivityForResult(Intent(activity, NewLeadActivity::class.java), RC_NEW_LEAD)
    }

    companion object {
        fun newInstance() = MyLeadsFragment()
    }
}
