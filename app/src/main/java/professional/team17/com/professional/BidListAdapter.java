package professional.team17.com.professional;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 *
 * To inflate the row selection for bids
 *
 * @author Allison
 * @see Bid BidList
 */
public class BidListAdapter extends ArrayAdapter<Bid> {



        public BidListAdapter(Activity context, BidList bids) {

            super(context, 0,  bids);

        }


        // aided by https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
        public View getView(int position, View v, ViewGroup parent) {
            Bid bid= getItem(position);
            if (v == null) {
                v = LayoutInflater.from(getContext()).inflate(R.layout.bid_row, parent, false);
            }
            //get view
            TextView userNameTextField = (TextView) v.findViewById(R.id.bid_row_userName);
            TextView taskBidTextField = (TextView) v.findViewById(R.id.bid_row_amount);
            ImageButton deleteButton = (ImageButton) v.findViewById(R.id.bid_row_delete);
            ImageButton acceptButton = (ImageButton) v.findViewById(R.id.bid_row_accept);

            //plug in item to row
            userNameTextField.setText(bid.getName());
            taskBidTextField.setText(bid.getName());

            deleteButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //do your thang!
                    notifyDataSetChanged();
                }
            });
            acceptButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //do your thang!!
                    notifyDataSetChanged();
                }
            });

            return v;
        }



}