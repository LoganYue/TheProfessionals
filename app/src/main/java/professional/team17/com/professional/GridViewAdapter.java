package professional.team17.com.professional;

import android.app.Activity;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import professional.team17.com.professional.Bid;
import professional.team17.com.professional.BidList;
import professional.team17.com.professional.Photo;
import professional.team17.com.professional.R;

public class GridViewAdapter extends ArrayAdapter<String> {

    public GridViewAdapter(Activity context, ArrayList<String> photos){
        super(context, 0, photos );
    }

    /**
     * Gets and inflates the ListView associated with this adapter.
     * @param position The position of the cell
     * @param v The view object
     * @param parent The parent viewgroup
     * @return the row's view.
     */
    // aided by https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
    public View getView(int position, View v, ViewGroup parent) {
        final String photo= getItem(position);
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.grid_cell, parent, false);
        }
        //get view
        ImageView photoView = (ImageView) v.findViewById(R.id.gridPhotoView);

        //plug in image
        byte[] decodedString = Base64.decode(photo, Base64.DEFAULT);
        Glide.with(getContext())
                .load(decodedString)
                .asBitmap()
                //.placeholder(R.drawable.ic_broken)
                .into(photoView);


        return v;
    }
}