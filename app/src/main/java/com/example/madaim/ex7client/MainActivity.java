package com.example.madaim.ex7client;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private final int MY_CALL_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private void call_phone(String phone_number) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone_number));
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case (MY_CALL_REQUEST):
                if (grantResults.length>0&&
                    permissions[0].equals(Manifest.permission.CALL_PHONE)&&
                            grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    EditText et= (EditText) findViewById(R.id.plain_text_input_call);

                    call_phone(et.getText().toString());
                }
        }
    }

    public void HelloToast(View view){
        switch (view.getId()){
            case (R.id.callButton):

                if (checkSelfPermission(Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
                     EditText et= (EditText) findViewById(R.id.plain_text_input_call);
                     call_phone(et.getText().toString());
                }
                else {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, MY_CALL_REQUEST);
                }

                break;
            case (R.id.surfButton):
                break;
            case (R.id.eMailButton):
                break;
            case (R.id.registerButton):
                break;
        }
    }
}
