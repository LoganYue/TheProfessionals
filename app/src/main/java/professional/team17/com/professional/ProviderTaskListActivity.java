
/*
 * ProviderTaskListActivity
 *
 * March 14, 2018
 *
 * Copyright @ 2018 Team 17, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */
package professional.team17.com.professional;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 *
 * An activity where the provider can see the tasks - with different ui depending
 * On the task status, and input
 * @author Allison
 * @see ElasticSearchController, ProviderCustomArrayAdapter TaskLIst, Profile
 */
public class ProviderTaskListActivity extends ProviderLayout {
    private ProviderCustomArrayAdapter adapterHelper;
    private ListView listView;
    private String username;
    private SharedPreferences sharedpreferences;
    //TODO both items below can be put in controller (project part 5)
    private TaskList taskList;
    private final ElasticSearchController elasticSearchController = new ElasticSearchController();




    /**
     * On creation of the activity, set all view objects
     * @param savedInstanceState The activity's previously saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO Delete after project part 5 with persistenct
        setContentView(R.layout.provider_tasklist_view);
        taskList = new TaskList();
        adapterHelper = new ProviderCustomArrayAdapter(this, taskList);
        listView = findViewById(R.id.provider_taskList_view_list);
        listView.setAdapter(adapterHelper);
        listView.setOnItemClickListener(clickListener);
        sharedpreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        username = sharedpreferences.getString("username", "error");
        String type = setProviderViewType();
        //createList(type);
        taskList.addAll(createList(type));
        adapterHelper.notifyDataSetChanged();




    }

    /**
     * This is an anonymous method to create a click listener for the listview rows. If the row
     * is selected, it packages up the task selected and the position to ProviderViewTask
     */
    private AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener(){
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            Task task = taskList.get(position);
            Intent intention = new Intent(ProviderTaskListActivity.this, ProviderViewTask.class);
            intention.putExtra("Task", task.getUniqueID());
            startActivity(intention);

        }

    };


    /**
     *
     * @param type - a string representing the status of the task being displayed
     * @return tasklist - a list of tasks of either the tasks assigned to the user,
     * of, tasks the user has bidded on.
     */
    private TaskList createList(String type) {
        TaskList taskList =null;
        setActivityTitle(type + " Tasks");
        if (type.equals("Bidded")) {
            taskList = elasticSearchController.getTasksBidded(username, "Bidded");
        }
        if (type.equals("Assigned")) {
            taskList = elasticSearchController.getTasksBidded(username, "Assigned");
        }
        return taskList;
    }

    //activity will pass flag into this 1 = my bids, 0 = assigned

    /**
     *
     * @return type - a string representing the type of lists the user wishes to see
     * the types of lists are either: 1) Assigned -  the tasks assigned to the user,
     * or 2) Bidded - tasks the user has bidded on.
     */
    private String setProviderViewType() {
        Bundle intent = getIntent().getExtras();
        String type = intent.getString("Status");
        return type;
    }

}
