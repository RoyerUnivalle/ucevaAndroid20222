package com.example.loginuceva222;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends AppCompatActivity implements View.OnClickListener {
    EditText edt3;
    Button btnI, btnD, btnPintar;
    FragmentManager fragmentManager;
    Pintar objPintar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        edt3 = findViewById(R.id.ed3);
        btnD = findViewById(R.id.btnDelegado);
        btnI = findViewById(R.id.btnInterfaz);
        btnPintar = findViewById(R.id.btnPintar);
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

    public void pintar(View g) throws InterruptedException {
        /*for (int i = 0; i < 9; i++) {
            btnPintar.setText("Hola i: "+ i);
            btnPintar.setBackgroundColor(Color.rgb(aleatorio(),aleatorio(),aleatorio()));
            Thread.sleep(1000);
        }*/
        objPintar = new Pintar();
        // objPintar.getStatus(); // esto serai util si la instancia estuviera en el oncreate
        // objPintar.cancel();   // esto serai util si la instancia estuviera en el oncreate
        objPintar.execute(10);//<-- el revise un parametro
    }
    public int aleatorio(){
        return (int)(Math.random() * 255 + 1);
    }
    public  class  Pintar extends AsyncTask<Integer,Integer,Void>{
        @Override
        protected Void doInBackground(Integer... integers) {
            for (int i = 0; i < integers[0]; i++) {
                if(!isCancelled()){
                    publishProgress(i); // << este llamado es para tener contacto con la UI (actualmente opcional)
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            btnPintar.setText("Hola i: "+ values[0]);
            btnPintar.setBackgroundColor(Color.rgb(aleatorio(),aleatorio(),aleatorio()));
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    public void iniciarServicio(View g){
        Intent intentService = new Intent(this, MostrarDatosSegundoPlano.class);
        startService(intentService);
        //stopService(intentService);
    }
}