package com.example.upxiaoni;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Score> scores=null;
    private List<Score> scores1=null;
    private TextView textView;
    private Button button1;
    private Button button2;
    private Button button5_refresh;
    private Button button_score_up;
    private Button button3_score_down;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.textView);
        new Thread(networkTask).start();
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        button5_refresh=(Button) findViewById(R.id.button5);
        button_score_up=(Button) findViewById(R.id.button);
        button3_score_down=(Button) findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scores!=null){
                    String texts="";
                    for(int i=0;i<scores.size();i++){
                        texts=texts+scores.get(i).getValue()+"         "+scores.get(i).getTime()+"\n";
                    }
                    textView.setText(texts);
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scores!=null){
                    String texts="";
                    for(int i=scores.size()-1;i>=0;i--){
                        texts=texts+scores.get(i).getValue() +"         "+scores.get(i).getTime()+"\n";
                    }
                    textView.setText(texts);
                }
            }
        });
        button_score_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scores1!=null){
                    String texts="";
                    for(int i=0;i<scores1.size();i++){
                        texts=texts+scores1.get(i).getValue() +"         "+scores1.get(i).getTime()+"\n";
                    }
                    textView.setText(texts);
                }
            }
        });
        button3_score_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scores1!=null){
                    String texts="";
                    for(int i=scores1.size()-1;i>=0;i--){
                        texts=texts+scores1.get(i).getValue() +"         "+scores1.get(i).getTime()+"\n";
                    }
                    textView.setText(texts);
                }
            }
        });
        button5_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(networkTask).start();
                textView.setText("加载中");
                scores=null;
                scores1=null;
            }
        });
    }
    @SuppressLint("HandlerLeak")
    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if (msg.what==1){
                String texts="";
                for(int i=0;i<scores.size();i++){
                    texts=texts+scores.get(i).getValue() +"         "+scores.get(i).getTime()+"\n";
                }
                textView.setText(texts);
                scores1=new ArrayList<Score>();
                for (int i=0;i<scores.size();i++){
                    scores1.add(scores.get(i));
                }
                Collections.sort(scores1,new SortByValue());
            }
            else if (msg.what==0){
                textView.setText("无历史数据");
            }
        }
    };
    Runnable networkTask= new Runnable() {
        @Override
        public void run() {
            try {
                GetData.access_token();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try{
                scores= GetData.api_value();
                myHandler.sendEmptyMessage(1);
            }catch (Exception e){
                e.printStackTrace();
                myHandler.sendEmptyMessage(0);
            }
        }
    };
}
