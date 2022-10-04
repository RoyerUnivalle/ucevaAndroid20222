package com.example.loginuceva222;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ed1, ed2; // instancia de la clase EditText

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = findViewById(R.id.edName); // enlazamiento
        ed2 = findViewById(R.id.edPasswd); // enlazamiento
        //Toast.makeText(this, "hi method onCreate", Toast.LENGTH_SHORT).show();
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
    public  void goToHome(View h){
        Intent go = new Intent(this, Home.class);
        go.addFlags(go.FLAG_ACTIVITY_CLEAR_TASK | go.FLAG_ACTIVITY_CLEAR_TOP);
        if(ed1.getText().toString().matches("") || ed2.getText().toString().matches("")){
            //Toast.makeText(this, "Please pick both fields", Toast.LENGTH_LONG).show();
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Advertencia, debe diligenciar ambos campos")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // START THE GAME!
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            builder.create().show();
        }else {
            Bundle data = new Bundle();
            data.putString("user",ed1.getText().toString());
            data.putString("passw",ed2.getText().toString());
            go.putExtras(data);
            startActivity(go);
        }
    }
}