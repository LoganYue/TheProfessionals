package professional.team17.com.professional;

import android.widget.TextView;

/**
 * Created by Zhipeng Zhang on 2018/3/13.
 */

public class RequesterRequestedViewHolder {
    TextView statusTextField;
    TextView taskTitleTextField;

    public RequesterRequestedViewHolder(TextView statusTextField, TextView taskTitleTextField){
        this.statusTextField = statusTextField;
        this.taskTitleTextField = taskTitleTextField;
    }

    public TextView getStatusTextField() {
        return statusTextField;
    }

    public TextView getTaskTitleTextField() {
        return taskTitleTextField;
    }
}
