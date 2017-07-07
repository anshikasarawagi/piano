package com.example.dell.piano;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;


public class SoundPool2 extends Activity implements View.OnClickListener{
    HashMap<String,Integer> hm;
    boolean flag=true;
    SoundPool soundPool;
   Execute ob;
    boolean loaded;

    Button playBtn;
    Button stopBtn;
    EditText text;
    String input;

    AudioAttributes attributes;
    String log="LOG12345";
    String[] values;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playBtn=(Button)findViewById(R.id.play);
        playBtn.setOnClickListener(this);
        stopBtn=(Button)findViewById(R.id.stop);
        stopBtn.setOnClickListener(this);
        text=(EditText)findViewById(R.id.notes);
hm=new HashMap<String, Integer>();
        attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                 .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
Log.d(log,"on create");
        getSoundPool();
    }

    public void getSoundPool()
    {

        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .setMaxStreams(1)
                .build();
        loadSounds();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.play) {
flag=true;
            input = text.getText().toString();
            values = input.split("\\s");
            actionChoose();

        }
        else{
            if(values!=null) {
                for (int i = 0; i < values.length; i++) {
                    values[i] = "";

                }
            }

            flag=false;
            text.setText("");
            playBtn.setEnabled(true);
            text.setText("");


        }
    }

    public void actionChoose()
    {
Log.d(log,"in choose");
        ob=new Execute();
        Thread t = new Thread(ob);
        playBtn.setEnabled(false);
        t.start();

    }




    public void loadSounds() {
       hm.put("a1Id",soundPool.load(this, R.raw.a1, 1));
        hm.put("a1sId",soundPool.load(this, R.raw.a1s, 1));
        hm.put("b1Id", soundPool.load(this, R.raw.b1, 1));
        hm.put("c1Id", soundPool.load(this, R.raw.c1, 1));
        hm.put("c1sId" , soundPool.load(this, R.raw.c1s, 1));
        hm.put("c2Id", soundPool.load(this, R.raw.c2, 1));
        hm.put("d1Id", soundPool.load(this, R.raw.d1, 1));
        hm.put("d1sId", soundPool.load(this, R.raw.d1s, 1));
        hm.put("e1Id",soundPool.load(this, R.raw.e1, 1));
        hm.put("f1Id",soundPool.load(this, R.raw.f1, 1));
        hm.put("f1sId", soundPool.load(this, R.raw.f1s, 1));
        hm.put("g1Id", soundPool.load(this, R.raw.g1, 1));
        hm.put("g1sId", soundPool.load(this, R.raw.g1s, 1));
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                loaded = true;
            }
        });
    }

   @Override
    protected void onStop(){
        super.onStop();
       if(values!=null) {
           for (int i = 0; i < values.length; i++) {
               values[i] = "";

           }
       }
        flag=false;
        playBtn.setEnabled(true);
        Log.d(log,"in stop "+ flag);
    }
   @Override
    protected void onPause(){
        super.onPause();
      flag=false;
        Log.d(log,"in pause " +flag);
    }
    @Override
    protected void onResume(){

        super.onResume();
        flag=true;
        Log.d(log,"in resume " +flag);
    }





    public class Execute implements Runnable {
        int a1StreamId,a1sStreamId,b1StreamId,c1StreamId,c1sStreamId,c2StreamId,d1StreamId,d1sStreamId,e1StreamId,f1StreamId,f1sStreamId,
              g1StreamId,g1sStreamId;






        @Override
        public void run() {
            Log.d(log,"in run");
            while(flag && loaded)
            {
                for(int i=0;i<values.length;i++)
                {
                    if(values[i].equals(".")) {
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {

                        }
                    }
                    else{
                        playIt(values[i]);

                    }

                }
              //  playBtn.setEnabled(true);
                runOnUiThread(new Runnable() {
                    public void run() {
                      playBtn.setEnabled(true);
                        text.setText("");
                    }
                });
                flag=false;
            }


        }// end of run

        public  void playIt(String s)
        {
            if(flag)
            {
                switch (s)
                {
                    case "a1":a1StreamId=soundPool.play(hm.get("a1Id"),1,1,1,0,1);
                        Log.d(log,a1StreamId+"a1");
                        break;
                    case "a1s":a1sStreamId=soundPool.play(hm.get("a1sId"),1,1,1,0,1);
                        Log.d(log,a1sStreamId+"a1s");
                        break;
                    case "b1":b1StreamId=soundPool.play(hm.get("b1Id"),1,1,1,0,1);
                        Log.d(log,b1StreamId+"b1");
                        break;
                    case "c1":c1StreamId=soundPool.play(hm.get("c1Id"),1,1,1,0,1);
                        Log.d(log,c1StreamId+"c1");
                        break;
                    case "c1s":c1sStreamId=soundPool.play(hm.get("c1sId"),1,1,1,0,1);
                        Log.d(log,c1sStreamId+"c1s");
                        break;
                    case "c2":c2StreamId=soundPool.play(hm.get("c2Id"),1,1,1,0,1);
                        Log.d(log,c2StreamId+"c2");
                        break;
                    case "d1":d1StreamId=soundPool.play(hm.get("d1Id"),1,1,1,0,1);
                        Log.d(log,d1StreamId+"d1");
                        break;
                    case "d1s":d1sStreamId=soundPool.play(hm.get("d1sId"),1,1,1,0,1);
                        Log.d(log,d1sStreamId+"d1s");
                        break;
                    case "e1":e1StreamId=soundPool.play(hm.get("e1Id"),1,1,1,0,1);
                        Log.d(log,e1StreamId+"e1");
                        break;
                    case "f1":f1StreamId=soundPool.play(hm.get("f1Id"),1,1,1,0,1);
                        Log.d(log,f1StreamId+"f1");
                        break;
                    case "f1s":f1sStreamId=soundPool.play(hm.get("f1sId"),1,1,1,0,1);
                        Log.d(log,f1sStreamId+"f1s");
                        break;
                    case "g1":g1StreamId=soundPool.play(hm.get("g1Id"),1,1,1,0,1);
                        Log.d(log,g1StreamId+"g1");
                        break;
                    case "g1s":g1sStreamId=soundPool.play(hm.get("g1sId"),1,1,1,0,1);
                        Log.d(log,g1sStreamId+"g1s");
                        break;
                    default:
                }

            }

        }
    }


}
