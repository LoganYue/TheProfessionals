package professional.team17.com.professional.Navigation;

import android.content.Context;
import android.content.Intent;

import professional.team17.com.professional.Activity.RequesterViewListActivity;


/**
 * Button to Move to Requester Activity to see Assigned tasks
 */
class AcceptedTasksReqNav extends NavButton {
        public AcceptedTasksReqNav(Context con) {
            super(con);
        }

        public Intent getIntent() {
            intent = new Intent(context, RequesterViewListActivity.class);
            intent.putExtra("Status", "Assigned");
            return intent;


        }
}
