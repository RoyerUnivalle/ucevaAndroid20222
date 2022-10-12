package com.example.loginuceva222;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class MostrarDatosSegundoPlano extends Service {

    ServicioRUN objServicioRun;
    Pintar objPintar;

    public MostrarDatosSegundoPlano() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            iniciarServicio();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public void iniciarServicio() throws InterruptedException {
        // segundo plano en el mismo hilo de la UI
        /*for (int i = 0; i < 9; i++) {
            Toast.makeText(this, "Hola i;"+i, Toast.LENGTH_SHORT).show();
            Thread.sleep(1000);
        }*/
        // 0.5 +
        // segundo plano, hilo independiente con *runnable*
        // objServicioRun = new ServicioRUN();
        // new Thread(objServicioRun).start();

        // segundo plano, hilo independiente con *Asyntask*
        objPintar = new Pintar();
        objPintar.execute(10);
    }

    public  class  Pintar extends AsyncTask<Integer,Integer,Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            for (int i = 0; i < integers[0]; i++) {
                if(!isCancelled()){
                    //Toast.makeText(getApplicationContext(), "Hola i: "+i, Toast.LENGTH_SHORT).show();
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
            // en lugar de mostrar el iterador
            // me muestre la red a la que estoy conectado -> ConectivtyManager
            // Toast.makeText(getApplicationContext(), "Hola i;"+values[0], Toast.LENGTH_SHORT).show();
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            activeNetwork.getType();
            if(activeNetwork.getType() == cm.TYPE_WIFI){
                Toast.makeText(getApplicationContext(), "Hola wifi;"+values[0], Toast.LENGTH_SHORT).show();
            }else if(activeNetwork.getType() == cm.TYPE_MOBILE){
                Toast.makeText(getApplicationContext(), "Hola datos;"+values[0], Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class ServicioRUN implements Runnable{
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    // falla porque el hilo no es el hilo de la UI, entonces cuando Toas -> issue
                    Toast.makeText(getApplicationContext(), "Hola i;"+i, Toast.LENGTH_SHORT).show();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}