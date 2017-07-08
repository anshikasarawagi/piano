package com.example.dell.piano;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.AsyncTask;
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
    public void onClick(View v) {
        if(v.getId()==R.id.play) {

new Execute().execute();
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





    public class Execute extends AsyncTask<Void,Void,Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            playBtn.setEnabled(false);
            flag=true;
            input = text.getText().toString();
            values = input.split("\\s");


        }



        public  void playIt(String s)
        {
            if(flag && hm.containsKey(s))
            {



                    hm.put(s+"StreamId",soundPool.play(hm.get(s),1,1,1,0,1));
                Log.d(log,s);


            }

        }

        @Override
        protected Void doInBackground(Void... params) {
           while(loaded && flag) {
               for (int i = 0; i < values.length; i++) {
                   if (values[i].equals(".")) {
                       try {
                           Thread.sleep(50);
                       } catch (Exception e) {

                       }
                   } else {
                       playIt(values[i]);

                   }

               }
               flag=false;


           }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            playBtn.setEnabled(true);
            text.setText("");
            flag=false;
        }
    }


}
