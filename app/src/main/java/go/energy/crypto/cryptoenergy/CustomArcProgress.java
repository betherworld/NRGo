package go.energy.crypto.cryptoenergy;

import android.content.Context;
import android.util.AttributeSet;

import com.github.lzyzsd.circleprogress.ArcProgress;

public class CustomArcProgress extends ArcProgress {
    public CustomArcProgress(Context context) {
        super(context);
        super.setBottomText(super.getProgress()+"/100");
    }

    public CustomArcProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setBottomText(super.getProgress()+"/100");
    }

    public CustomArcProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setBottomText(super.getProgress()+"/100");
    }

    @Override
    public void setProgress(int progress) {
        super.setProgress(progress);
        super.setBottomText(progress+"/100");
    }
}
