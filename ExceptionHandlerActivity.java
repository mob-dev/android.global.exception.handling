import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExceptionHandlerActivity extends AppCompatActivity {
    public static final String TAG = ExceptionHandlerActivity.class.getSimpleName();

    @BindView(R.id.message)
    TextView message;

    @BindView(R.id.close)
    TextView close;

    @BindView(R.id.open)
    TextView open;

    private String exceptionMessage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the data from bundles after validation
        Intent intent = getIntent();

        if (intent.hasExtra("EXTRA_GLOBAL_EXCEPTION")) {
            exceptionMessage = intent.getStringExtra("EXTRA_GLOBAL_EXCEPTION");
        }

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_exception_handler);
        ButterKnife.bind(this);
        setListeners();
        setExceptionMessage();
    }

    private void setListeners() {
        close.setOnClickListener(view -> forceCloseApplication());
        open.setOnClickListener(view -> reopenApplication());
    }

    private void setExceptionMessage() {
        if (BuildConfig.DEBUG && exceptionMessage != null)
            message.setText(exceptionMessage);
    }

    private void reopenApplication() {
        Intent exceptionDialogActivity = new Intent(this, SplashActivity.class);
        startActivity(exceptionDialogActivity);
        finishAndRemoveTask();
    }

    private void forceCloseApplication() {
        finish();
    }
}
