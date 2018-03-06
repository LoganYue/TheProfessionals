package professional.team17.com.professional;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class OtherProfileViewActivity extends ProfileViewActivity{
    private ListView listViewOfRatings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentMode.setText("An User's Profile");
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected void setInfo(String aUserName, String aName, String anEmail, String aPhoneNumber) {
        super.setInfo(aUserName, aName, anEmail, aPhoneNumber);
        // Make mock ratings and adapter for reviews; Initiate listViewOfRatings here.
    }
}
