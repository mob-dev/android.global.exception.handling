import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Thread.UncaughtExceptionHandler {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* Global Exception Handling */
    @Override
    public void uncaughtException(final Thread thread, final Throwable throwable) {
        try {
            /* Optional Step: Log exception */
            Log.e(TAG + " called :" + throwable.getClass(), throwable.getMessage(), throwable);

            /* Exception Handled through another activity. This is not mandatory to handle the exception in this way
             * Free to handle through other ways */
            String exceptionMessage = TAG + ": called :" + throwable.getClass() + " : Error :" + throwable.getMessage();
            Intent exceptionHandlerActivity = new Intent(this, ExceptionHandlerActivity.class);
            exceptionHandlerActivity.putExtra("EXTRA_GLOBAL_EXCEPTION", exceptionMessage);
            exceptionHandlerActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            exceptionHandlerActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
            startActivity(exceptionHandlerActivity);

            /* Required Step: Kill all process otherwise app will freeze */
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
            
        } catch (Exception e) {
            Log.e(TAG, " Open exception dialog failed! :" + e);
        }
    }
}