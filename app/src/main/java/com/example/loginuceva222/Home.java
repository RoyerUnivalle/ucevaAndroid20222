package com.example.loginuceva222;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends AppCompatActivity implements View.OnClickListener {
    EditText edt3;
    Button btnI, btnD;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        edt3 = findViewById(R.id.ed3);
        btnD = findViewById(R.id.btnDelegado);
        btnI = findViewById(R.id.btnInterfaz);
        Bundle recieved = getIntent().getExtras();
        edt3.setText("User: "+recieved.getString("user"));
        /*btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Hola delegado", Toast.LENGTH_SHORT).show();
            }
        });*/
        btnI.setOnClickListener(this);
        btnD.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
    }
    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(this, "Hola onstart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(this, "Hola method onStop", Toast.LENGTH_LONG).show();
    }
    public  void goToMain(View h){
        Intent go = new Intent(this, MainActivity.class);
        go.addFlags(go.FLAG_ACTIVITY_CLEAR_TASK | go.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(go);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInterfaz:
                //Toast.makeText(this, "Hola interfaz", Toast.LENGTH_LONG).show();
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.contanierFragments, BlankFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();
                break;
            case R.id.btnDelegado:
                //Toast.makeText(this, "Hola interfaz", Toast.LENGTH_LONG).show();
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.contanierFragments, BlankFragment2.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();
                break;
            default: break;
        }
    }


}