package org.literacyapp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.literacyapp.receiver.DownloadContentAlarmReceiver;

public class MainActivity extends AppCompatActivity {

    public static final int PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 0;
    public static final int PERMISSION_REQUEST_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(getClass().getName(), "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        Log.i(getClass().getName(), "onCreate");
        super.onStart();

        // Ask for permissions
        int permissionCheckWriteExternalStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheckWriteExternalStorage != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE);
            return;
        }
        int permissionCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            return;
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        long timeOfLastSynchronizationInMillis = sharedPreferences.getLong(DownloadContentAlarmReceiver.PREF_LAST_CONTENT_SYNC, 0);
        if (timeOfLastSynchronizationInMillis == 0) {
            // Download content
            Intent intent = new Intent("org.literacyapp.receiver.DownloadContentAlarmReceiver");
            intent.setPackage("org.literacyapp");
            sendBroadcast(intent);
        } else {
            // Assume content has already been downloaded
            Intent categoryIntent = new Intent(this, CategoryActivity.class);
            startActivity(categoryIntent);
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if ((requestCode == PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE)
                || (requestCode == PERMISSION_REQUEST_CAMERA)) {
            if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission granted

                // Restart application
                Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                // Permission denied

                // Close application
                finish();
            }
        }
    }
}
