package com.mahesh.hp.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {
    Button bt1,bt2,bt3 ,bt4;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice>pairedDevices;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1=(Button)findViewById(R.id.button);
        bt2=(Button)findViewById(R.id.button2);
        bt3=(Button)findViewById(R.id.button3);
        bt4=(Button)findViewById(R.id.button4);
        BA= BluetoothAdapter.getDefaultAdapter();
        lv=(ListView)findViewById(R.id.list);


    }
    public void on(View v){
        if(!BA.isEnabled()){
            Intent turnOn=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn,0);
            Toast.makeText(getApplicationContext(),"TURNED ON",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"ALREADY ON ",Toast.LENGTH_LONG).show();
        }
    }

    public void off(View view){
        BA.disable();
        Toast.makeText(getApplicationContext(),"Turned off",Toast.LENGTH_LONG).show();
    }
    public void visible(View view){

        Intent getVisible=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible,0);
    }
    public void list(View view){
        try{
        pairedDevices=BA.getBondedDevices();
        ArrayList arrayList=new ArrayList();
        for(BluetoothDevice bt : pairedDevices)
            arrayList.add(bt.getName());
        Toast.makeText(getApplicationContext(), "Showing Paired Devices",Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new  ArrayAdapter(this,android.R.layout.simple_list_item_1, arrayList);

        lv.setAdapter(adapter);}
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error :"+e,Toast.LENGTH_LONG).show();
        }
    }




}
