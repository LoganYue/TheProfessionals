package professional.team17.com.professional;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class SearchActivity extends ProviderLayout {
    private ArrayAdapterSearchResults searchAdapterHelper;
    private ListView listView;
    private SearchView searchView;
    private TaskList taskList;
    private Profile user;
    //TODO DELETE
    private TaskList dummyTaskList;

    //TODO DELETE METHOD
    public void dummyDate(){
        dummyTaskList = new TaskList();
        user = new Profile("John Smith", "john123", "johnSmith@email.ca", "123-4567");
        Task task1 = new Task("ProfileName1", "Name1", "Description1", "Location1","ID1" );
        Task task2 = new Task("ProfileName2", "Name2", "Description2", "Location2","ID2" );
        Task task3 = new Task("ProfileName3", "Name3", "Description3", "Location3","ID3" );
        dummyTaskList.addTask(task1);
        dummyTaskList.addTask(task2);
        dummyTaskList.addTask(task3);
        task1.addBid(new Bid("john123", 234.3));
        task1.addBid(new Bid("ProfileName4", 23.40));
        task2.addBid(new Bid("ProfileName4", 277.40));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        taskList = new TaskList();
        searchAdapterHelper = new ArrayAdapterSearchResults(this, taskList);
        listView =findViewById(R.id.provider_taskList_view_list);
        listView.setAdapter(searchAdapterHelper);
        listView.setOnItemClickListener(clickListener);

        /* Change activity title */
        this.setActivityTitle("Task Search");


        searchView = (SearchView) findViewById(R.id.Search_Activity_Input);
        searchView.setQueryHint("Enter search");



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, "SEARCH ENTERED"+query, duration);
                toast.show();
                displayResults();
                searchView.clearFocus(); //remove focus on submit
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    /**
     * This is an anonymous method to create a click listener for the listview rows. If the row
     * is selected, it packages up the task selected and the position to ViewTaskBidded
     */
    private AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener(){
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            TaskList taskList = dummyTaskList;
            Task task = taskList.get(position);
            Intent intention = new Intent(SearchActivity.this, ProviderViewTask.class);
            intention.putExtra("Task", task);
            intention.putExtra("position", position);
            intention.putExtra("profile", user);
            startActivity(intention);
        }

    };


    //TODO this will fill results from search (may need to take parameters in bundle)
    public TaskList getTasks() {
        dummyDate();
        return dummyTaskList;
    }

    //This updates the adapter with the results
    public void displayResults(){
        taskList.clear();
        taskList.addAll(getTasks());
        searchAdapterHelper.notifyDataSetChanged();
    }


}
