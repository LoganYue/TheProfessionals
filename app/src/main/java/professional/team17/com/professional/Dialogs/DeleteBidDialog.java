package professional.team17.com.professional.Dialogs;

import android.view.View;

/**
 * Dialog to delete bid on task
 */
public class DeleteBidDialog extends DialogContent {
    public DeleteBidDialog(View view) {
        super(view);
        dmessage.setText("Are you sure you want to delete?");
        dtitle.setText("Cancel Bid");
    }
}
