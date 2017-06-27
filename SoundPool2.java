package com.example.dell.piano;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by DELL on 27-06-2017.
 */

public class SoundPool2 extends Activity implements View.OnClickListener{
    SoundPool soundPool;
     SoundPool soundPool1;
    int a1Id,a1sId,b1Id,c1Id,c1sId,c2Id,d1Id,d1sId,e1Id,f1Id,f1sId,g1Id,g1sId;
     int a1Id1,a1sId1,b1Id1,c1Id1,c1sId1,c2Id1,d1Id1,d1sId1,e1Id1,f1Id1,f1sId1,g1Id1,g1sId1;
    int a1StreamId,a1sStreamId,b1StreamId,c1StreamId,c1sStreamId,c2StreamId,d1StreamId,d1sStreamId,e1StreamId,f1StreamId,f1sStreamId,
            g1StreamId,g1sStreamId;
    int a1StreamId1,a1sStreamId1,b1StreamId1,c1StreamId1,c1sStreamId1,c2StreamId1,d1StreamId1,d1sStreamId1,e1StreamId1,f1StreamId1,f1sStreamId1,
     g1StreamId1,g1sStreamId1;
    boolean loaded;
     boolean loaded1;
    Button playBtn;
    Button stopBtn;
    TextView text;
    String input;
    String[] values;
 //   AudioManager audioManager;
    float vol,maxVol,leftVolume,rightVolume,normal_playback_rate;
    int priority,no_loop;
    ArrayList<Integer> ids;
    AudioAttributes attributes;
    String log="LOG12345";
    int loop;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playBtn=(Button)findViewById(R.id.play);
        playBtn.setOnClickListener(this);
        stopBtn=(Button)findViewById(R.id.stop);
        stopBtn.setOnClickListener(this);
        text=(TextView)findViewById(R.id.notes);
        ids=new ArrayList<>();
        loop=0;
        //attributes
        attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                 .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        //audio manager
      //   audioManager=(AudioManager) getSystemService(AUDIO_SERVICE);
       //  vol = audioManager.getStreamVolume(
          //          AudioManager.STREAM_MUSIC);
         //maxVol = audioManager.getStreamMaxVolume(
                 //   AudioManager.STREAM_MUSIC);
         //leftVolume = vol/maxVol;
         //rightVolume = vol/maxVol;
         priority = 1;
         no_loop = 0;
         normal_playback_rate = 1f;
        //soundpool
        getSoundPool();




    }
    public void loadSounds() {
        a1Id = soundPool.load(this, R.raw.a1, 1);
        a1sId = soundPool.load(this, R.raw.a1s, 1);
        b1Id = soundPool.load(this, R.raw.b1, 1);
        c1Id = soundPool.load(this, R.raw.c1, 1);
        c1sId = soundPool.load(this, R.raw.c1s, 1);
        c2Id = soundPool.load(this, R.raw.c2, 1);
        d1Id = soundPool.load(this, R.raw.d1, 1);
        d1sId = soundPool.load(this, R.raw.d1s, 1);
        e1Id = soundPool.load(this, R.raw.e1, 1);
        f1Id = soundPool.load(this, R.raw.f1, 1);
        f1sId = soundPool.load(this, R.raw.f1s, 1);
        g1Id = soundPool.load(this, R.raw.g1, 1);
        g1sId = soundPool.load(this, R.raw.g1s, 1);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                loaded = true;
            }
        });
    }
    public void loadSounds1(){
        //soundpool1

            a1Id1= soundPool1.load(this,R.raw.a1,1);
             a1sId1=soundPool1.load(this,R.raw.a1s,1);
          b1Id1=soundPool1.load(this,R.raw.b1,1);
          c1Id1=soundPool1.load(this,R.raw.c1,1);
         c1sId1=soundPool1.load(this,R.raw.c1s,1);
         c2Id1=soundPool1.load(this,R.raw.c2,1);
        d1Id1=soundPool1.load(this,R.raw.d1,1);
         d1sId1=soundPool1.load(this,R.raw.d1s,1);
        e1Id1=soundPool1.load(this,R.raw.e1,1);
         f1Id1=soundPool1.load(this,R.raw.f1,1);
        f1sId1=soundPool1.load(this,R.raw.f1s,1);
         g1Id1=soundPool1.load(this,R.raw.g1,1);
           g1sId1=soundPool1.load(this,R.raw.g1s,1);
         soundPool1.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
              @Override
              public void onLoadComplete(SoundPool soundPool, int sampleId,
                                               int status) {
                  loaded1 = true;
                     }
                      });
              }


public void getSoundPool()
{

    soundPool = new SoundPool.Builder()
            .setAudioAttributes(attributes)
            .setMaxStreams(1)
            .build();
    loadSounds();
}

public void getSoundPool1()
{
    soundPool1=new SoundPool.Builder()
            .setAudioAttributes(attributes)
            .setMaxStreams(1)
            .build();
    loadSounds1();
}



    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.play) {

            input = text.getText().toString();
            values = input.split("\\s");
            actionChoose();
        }
        else{
            stopIt();
        }
    }
    public void actionChoose()
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
        }

    public  void playIt(String s)
    {
        if(loaded && soundPool!=null && loop==0)
        {
            switch (s)
            {
                case "a1":a1StreamId=soundPool.play(a1Id,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(a1StreamId);
                    Log.d(log,a1StreamId+"a1");
                    break;
                case "a1s":a1sStreamId=soundPool.play(a1sId,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(a1sStreamId);
                    Log.d(log,a1sStreamId+"a1s");
                    break;
                case "b1":b1StreamId=soundPool.play(b1Id,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(b1StreamId);
                    Log.d(log,b1StreamId+"b1");
                    break;
                case "c1":c1StreamId=soundPool.play(c1Id,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(c1StreamId);
                    Log.d(log,c1StreamId+"c1");
                    break;
                case "c1s":c1sStreamId=soundPool.play(c1sId,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(c1sStreamId);
                    Log.d(log,c1sStreamId+"c1s");
                    break;
                case "c2":c2StreamId=soundPool.play(c2Id,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(c2StreamId);
                    Log.d(log,c2StreamId+"c2");
                    break;
                case "d1":d1StreamId=soundPool.play(d1Id,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(d1StreamId);
                    Log.d(log,d1StreamId+"d1");
                    break;
                case "d1s":d1sStreamId=soundPool.play(d1sId,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(d1sStreamId);
                    Log.d(log,d1sStreamId+"d1s");
                    break;
                case "e1":e1StreamId=soundPool.play(e1Id,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(e1StreamId);
                    Log.d(log,e1StreamId+"e1");
                    break;
                case "f1":f1StreamId=soundPool.play(f1Id,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(f1StreamId);
                    Log.d(log,f1StreamId+"f1");
                    break;
                case "f1s":f1sStreamId=soundPool.play(f1sId,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(f1sStreamId);
                    Log.d(log,f1sStreamId+"f1s");
                    break;
                case "g1":g1StreamId=soundPool.play(g1Id,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(g1StreamId);
                    Log.d(log,g1StreamId+"g1");
                    break;
                case "g1s":g1sStreamId=soundPool.play(g1sId,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(g1sStreamId);
                    Log.d(log,g1sStreamId+"g1s");
                    break;
                default:
            }
           // onComplete();
        }
        else if(loaded1 && soundPool1!=null && (loop==1))
        {
            switch (s)
            {
                case "a1":a1StreamId1=soundPool1.play(a1Id1,1,1,priority,no_loop,normal_playback_rate);
                     ids.add(a1StreamId1);
                    break;
                case "a1s":a1sStreamId1=soundPool1.play(a1sId1,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(a1sStreamId1);
                    break;
                case "b1":b1StreamId1=soundPool1.play(b1Id1,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(b1StreamId1);
                    break;
                case "c1":  c1StreamId1=soundPool1.play(c1Id1,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(c1StreamId1);
                    break;
                case "c1s":c1sStreamId1=soundPool1.play(c1sId1,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(c1sStreamId1);
                    break;
                case "c2": c2StreamId1=soundPool1.play(c2Id1,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(c2StreamId1);
                    break;
                case "d1":  d1StreamId1=soundPool1.play(d1Id1,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(d1StreamId1);
                    break;
                case "d1s":  d1sStreamId1=soundPool1.play(d1sId1,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(d1sStreamId1);
                    break;
                case "e1":e1StreamId1=soundPool1.play(e1Id1,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(e1StreamId1);
                    break;
                case "f1":f1StreamId1=soundPool1.play(f1Id1,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(f1StreamId1);
                    break;
                case "f1s":f1sStreamId1=soundPool1.play(f1sId1,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(f1sStreamId1);
                    break;
                case "g1s": g1sStreamId1=soundPool1.play(g1sId1,1,1,priority,no_loop,normal_playback_rate);
                    ids.add(g1sStreamId1);
                    break;
                default:

            }
        }

    }
   /* public void onComplete()
    {
        soundPool.release();
        soundPool=null;
    }*/

public void stopIt()
{
    if(loop==0) {
        for (Integer id : ids) {
            Log.d("LOG12345", id + "");
            soundPool.stop(id);

        }
        soundPool.release();
        loop=1;
        getSoundPool1();
    }
    else if(loop==1){
         for (Integer id : ids) {
             Log.d("LOG12345", id + "");
             soundPool1.stop(id);
         }
         soundPool1=null;
        getSoundPool();
        loop=0;
    }
    ids.clear();
    values=null;

}


    @Override
    protected void onPostResume() {
        super.onPostResume();
        loop=0;
        getSoundPool();;
    }

   /* @Override
    protected void onDestroy() {
        super.onDestroy();
        loop=0;

    }    */
}
