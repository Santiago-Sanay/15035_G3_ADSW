package com.alex.ultim2.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alex.ultim2.R;
import com.alex.ultim2.utils.BaseActivity;

public class PasswordScreen extends BaseActivity {

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.password_validate);

    }
    public void presion(View v){

        Toast.makeText(this,"hola mundo",Toast.LENGTH_LONG).show();
    }
}
