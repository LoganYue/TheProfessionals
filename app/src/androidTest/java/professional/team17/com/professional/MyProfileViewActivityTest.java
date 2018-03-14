package professional.team17.com.professional;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;


/**
 * Created by Hailan on 2018-03-14.
 */

public class MyProfileViewActivityTest extends ActivityInstrumentationTestCase2<MyProfileViewActivity>{
    private Solo solo;

    public MyProfileViewActivityTest() {
        super(MyProfileViewActivity.class);
    }

    public void setUp() throws Exception{
        solo = new Solo(getInstrumentation(), getActivity());
        Context context = getInstrumentation().getTargetContext();
        final SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString("username", "hailan333");
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testEditMyProfile(){
        solo.assertCurrentActivity("Wrong Activity", MyProfileViewActivity.class);

        solo.clickOnView(solo.getView(R.id.profilePicButton));
        solo.assertCurrentActivity("Wrong Activity", EditMyProfileActivity.class);

        EditText enterName = (EditText) solo.getView(R.id.editName);
        EditText enterEmail = (EditText) solo.getView(R.id.editEmail);
        EditText enterPhone = (EditText) solo.getView(R.id.editEmail);

        solo.clearEditText(enterName);
        solo.clearEditText(enterEmail);
        solo.clearEditText(enterPhone);

        solo.enterText(enterName, "TEST name");
        solo.enterText(enterEmail, "TEST@email.ca");
        solo.enterText(enterPhone, "1111111111");

        solo.clickOnButton("SAVE");

        solo.assertCurrentActivity("WrongActivity", MyProfileViewActivity.class);
        assertTrue(solo.waitForText("TEST name"));
        assertTrue(solo.waitForText("TEST@email.ca"));
        assertTrue(solo.waitForText("1111111111"));
    }

}