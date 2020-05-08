package com.example.upxiaoni;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Score> scores=null;
    private List<Score> scores1=null;
    private List<Score> speed_only=null;
    private List<Score> speed_only1=null;
    private List<Score> slalom_only=null;
    private List<Score> slalom_only1=null;
    private List<Score> speed_only_round=null;
    private List<Score> speed_only_num=null;
    private List<Score> slalom_only_num=null;
    private List<Score> scores_round=null;
    private List<Score> scores_num=null;
    private TextView textView;
    private Button button1;
    private Button button2;
    private Button button5_refresh;
    private Button button_score_up;
    private Button button3_score_down;
    private Button button4_speed_only;
    private Button button6_slalom_only;
    private Button button7_all;
    private int select_state=0;//全选为0.选择速桩模式为1，选择速滑模式为2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.textView);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        new Thread(networkTask).start();
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        button5_refresh=(Button) findViewById(R.id.button5);
        button_score_up=(Button) findViewById(R.id.button);
        button3_score_down=(Button) findViewById(R.id.button3);
        button4_speed_only=(Button) findViewById(R.id.button4);
        button6_slalom_only=(Button) findViewById(R.id.button6);
        button7_all=(Button) findViewById(R.id.button7);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scores!=null) {
                    if (select_state == 0) {
                        String texts = "";
                        for(int i=0;i<scores_round.size();i++){
                            texts=texts+scores_round.get(i).getStringValue();
                            for (int j=0;j<8-scores_round.get(i).getValLength();j++){
                                texts+="  ";
                            }
                            texts+=scores_round.get(i).getTime()+"  ";
                            texts=texts+scores_round.get(i).getRound()+"圈  ";
                            texts=texts+scores_round.get(i).getMode()+"  ";
                            texts=texts+scores_round.get(i).getNum()+"号\n";
                        }
                        textView.setText(texts);
                    }
                    else if (select_state == 2){
                        if (speed_only!=null) {
                            String texts = "";
                            for (int i = 0; i < speed_only_round.size(); i++) {
                                texts = texts + speed_only_round.get(i).getStringValue();
                                for (int j = 0; j < 8 - speed_only_round.get(i).getValLength(); j++) {
                                    texts += "  ";
                                }
                                texts += speed_only_round.get(i).getTime() + "  ";
                                texts = texts + speed_only_round.get(i).getRound() + "圈  ";
                                texts = texts + speed_only_round.get(i).getMode() + "  ";
                                texts = texts + speed_only_round.get(i).getNum() + "号\n";
                            }
                            textView.setText(texts);
                        }
                    }
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scores!=null){
                    if (select_state == 0) {
                        String texts = "";
                        for(int i=scores_num.size()-1;i>=0;i--){
                            texts=texts+scores_num.get(i).getStringValue();
                            for (int j=0;j<8-scores_num.get(i).getValLength();j++){
                                texts+="  ";
                            }
                            texts+=scores_num.get(i).getTime()+"  ";
                            texts=texts+scores_num.get(i).getRound()+"圈  ";
                            texts=texts+scores_num.get(i).getMode()+"  ";
                            texts=texts+scores_num.get(i).getNum()+"号\n";
                        }
                        textView.setText(texts);
                    }
                    else if (select_state==1){
                        if (slalom_only_num!=null) {
                            String texts = "";
                            for (int i = slalom_only_num.size() - 1; i >= 0; i--) {
                                texts = texts + slalom_only_num.get(i).getStringValue();
                                for (int j = 0; j < 8 - slalom_only_num.get(i).getValLength(); j++) {
                                    texts += "  ";
                                }
                                texts += slalom_only_num.get(i).getTime() + "  ";
                                texts = texts + slalom_only_num.get(i).getRound() + "圈  ";
                                texts = texts + slalom_only_num.get(i).getMode() + "  ";
                                texts = texts + slalom_only_num.get(i).getNum() + "号\n";
                            }
                            textView.setText(texts);
                        }
                    }
                    else{
                        if (speed_only!=null) {
                            String texts = "";
                            for (int i = speed_only_num.size() - 1; i >= 0; i--) {
                                texts = texts + speed_only_num.get(i).getStringValue();
                                for (int j = 0; j < 8 - speed_only_num.get(i).getValLength(); j++) {
                                    texts += "  ";
                                }
                                texts += speed_only_num.get(i).getTime() + "  ";
                                texts = texts + speed_only_num.get(i).getRound() + "圈  ";
                                texts = texts + speed_only_num.get(i).getMode() + "  ";
                                texts = texts + speed_only_num.get(i).getNum() + "号\n";
                            }
                            textView.setText(texts);
                        }
                    }
                }
            }
        });
        button_score_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scores1!=null){
                    if (select_state == 0) {
                        String texts = "";
                        for(int i=0;i<scores1.size();i++){
                            texts=texts+scores1.get(i).getStringValue();
                            for (int j=0;j<8-scores1.get(i).getValLength();j++){
                                texts+="  ";
                            }
                            texts+=scores1.get(i).getTime()+"  ";
                            texts=texts+scores1.get(i).getRound()+"圈  ";
                            texts=texts+scores1.get(i).getMode()+"  ";
                            texts=texts+scores1.get(i).getNum()+"号\n";
                        }
                        textView.setText(texts);
                    }
                    else if (select_state==1){
                        if (slalom_only1!=null) {
                            String texts = "";
                            for (int i = 0; i < slalom_only1.size(); i++) {
                                texts = texts + slalom_only1.get(i).getStringValue();
                                for (int j = 0; j < 8 - slalom_only1.get(i).getValLength(); j++) {
                                    texts += "  ";
                                }
                                texts += slalom_only1.get(i).getTime() + "  ";
                                texts = texts + slalom_only1.get(i).getRound() + "圈  ";
                                texts = texts + slalom_only1.get(i).getMode() + "  ";
                                texts = texts + slalom_only1.get(i).getNum() + "号\n";
                            }
                            textView.setText(texts);
                        }
                    }
                    else{
                        if (speed_only1!=null) {
                            String texts = "";
                            for (int i = 0; i < speed_only1.size(); i++) {
                                texts = texts + speed_only1.get(i).getStringValue();
                                for (int j = 0; j < 8 - speed_only1.get(i).getValLength(); j++) {
                                    texts += "  ";
                                }
                                texts += speed_only1.get(i).getTime() + "  ";
                                texts = texts + speed_only1.get(i).getRound() + "圈  ";
                                texts = texts + speed_only1.get(i).getMode() + "  ";
                                texts = texts + speed_only1.get(i).getNum() + "号\n";
                            }
                            textView.setText(texts);
                        }
                    }
                }
            }
        });
        button3_score_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scores1!=null){
                    if (select_state == 0) {
                        String texts = "";
                        for(int i=scores1.size()-1;i>=0;i--){
                            texts=texts+scores1.get(i).getStringValue();
                            for (int j=0;j<8-scores1.get(i).getValLength();j++){
                                texts+="  ";
                            }
                            texts+=scores1.get(i).getTime()+"  ";
                            texts=texts+scores1.get(i).getRound()+"圈  ";
                            texts=texts+scores1.get(i).getMode()+"  ";
                            texts=texts+scores1.get(i).getNum()+"号\n";
                        }
                        textView.setText(texts);
                    }
                    else if (select_state==1){
                        if (slalom_only1!=null) {
                            String texts = "";
                            for (int i = slalom_only1.size() - 1; i >= 0; i--) {
                                texts = texts + slalom_only1.get(i).getStringValue();
                                for (int j = 0; j < 8 - slalom_only1.get(i).getValLength(); j++) {
                                    texts += "  ";
                                }
                                texts += slalom_only1.get(i).getTime() + "  ";
                                texts = texts + slalom_only1.get(i).getRound() + "圈  ";
                                texts = texts + slalom_only1.get(i).getMode() + "  ";
                                texts = texts + slalom_only1.get(i).getNum() + "号\n";
                            }
                            textView.setText(texts);
                        }
                    }
                    else{
                        if (speed_only1!=null) {
                            String texts = "";
                            for (int i = speed_only1.size() - 1; i >= 0; i--) {
                                texts = texts + speed_only1.get(i).getStringValue();
                                for (int j = 0; j < 8 - speed_only1.get(i).getValLength(); j++) {
                                    texts += "  ";
                                }
                                texts += speed_only1.get(i).getTime() + "  ";
                                texts = texts + speed_only1.get(i).getRound() + "圈  ";
                                texts = texts + speed_only1.get(i).getMode() + "  ";
                                texts = texts + speed_only1.get(i).getNum() + "号\n";
                            }
                            textView.setText(texts);
                        }
                    }
                }
            }
        });
        button4_speed_only.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                select_state=2;
                if (speed_only1!=null){
                    String texts = "";
                    for (int i = 0; i < speed_only.size(); i++) {
                        texts = texts + speed_only.get(i).getStringValue();
                        for (int j = 0; j < 8 - speed_only.get(i).getValLength(); j++) {
                            texts += "  ";
                        }
                        texts += speed_only.get(i).getTime() + "  ";
                        texts = texts + speed_only.get(i).getRound() + "圈  ";
                        texts = texts + speed_only.get(i).getMode() + "  ";
                        texts = texts + speed_only.get(i).getNum() + "号\n";
                    }
                    textView.setText(texts);
                }
                else
                    textView.setText("无速滑数据");
            }
        });
        button6_slalom_only.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                select_state=1;
                if (slalom_only!=null){
                    String texts = "";
                    for (int i = 0; i < slalom_only.size(); i++) {
                        texts = texts + slalom_only.get(i).getStringValue();
                        for (int j = 0; j < 8 - slalom_only.get(i).getValLength(); j++) {
                            texts += "  ";
                        }
                        texts += slalom_only.get(i).getTime() + "  ";
                        texts = texts + slalom_only.get(i).getRound() + "圈  ";
                        texts = texts + slalom_only.get(i).getMode() + "  ";
                        texts = texts + slalom_only.get(i).getNum() + "号\n";
                    }
                    textView.setText(texts);
                }
                else
                    textView.setText("无速桩数据");
            }
        });
        button7_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_state=0;
                if (scores!=null){
                    String texts="";
                    for(int i=0;i<scores.size();i++){
                        texts=texts+scores.get(i).getStringValue();
                        for (int j=0;j<8-scores.get(i).getValLength();j++){
                            texts+="  ";
                        }
                        texts+=scores.get(i).getTime()+"  ";
                        texts=texts+scores.get(i).getRound()+"圈  ";
                        texts=texts+scores.get(i).getMode()+"  ";
                        texts=texts+scores.get(i).getNum()+"号\n";
                    }
                    textView.setText(texts);
                }
                else
                    textView.setText("无历史数据");
            }
        });
        button5_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(networkTask).start();
                textView.setText("加载中");
                scores=null;
                scores1=null;
                speed_only=null;
                speed_only1=null;
                slalom_only=null;
                slalom_only1=null;
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
                    texts=texts+scores.get(i).getStringValue();
                    for (int j=0;j<8-scores.get(i).getValLength();j++){
                        texts+="  ";
                    }
                    texts+=scores.get(i).getTime()+"  ";
                    texts=texts+scores.get(i).getRound()+"圈  ";
                    texts=texts+scores.get(i).getMode()+"  ";
                    texts=texts+scores.get(i).getNum()+"号\n";
                }
                textView.setText(texts);
                scores1=new ArrayList<Score>();//按成绩排序的数组
                for (int i=0;i<scores.size();i++){
                    scores1.add(scores.get(i));
                }
                Collections.sort(scores1,new SortByValue());
                speed_only=new ArrayList<>();
                slalom_only=new ArrayList<>();
                for (int i=0;i<scores.size();i++){//给速滑和速桩分别建一个数组
                    if (scores.get(i).getMode().equals("速滑")){
                        speed_only.add(scores.get(i));
                    }
                    else{
                        slalom_only.add(scores.get(i));
                    }
                }
                speed_only1=new ArrayList<>();//给速滑和速桩建按成绩排序的数组
                for (int i=0;i<speed_only.size();i++){
                    speed_only1.add(speed_only.get(i));
                }
                Collections.sort(speed_only1,new SortByValue());
                slalom_only1=new ArrayList<>();
                for (int i=0;i<slalom_only.size();i++){
                    slalom_only1.add(slalom_only.get(i));
                }
                Collections.sort(slalom_only1,new SortByValue());
                scores_num=new ArrayList<>();//按号码排序
                for (int i=0;i<scores.size();i++){
                    scores_num.add(scores1.get(i));
                }
                Collections.sort(scores_num,new SortByNum());
                scores_round=new ArrayList<>();//按圈数排序
                for (int i=0;i<scores.size();i++){
                    scores_round.add(scores1.get(i));
                }
                Collections.sort(scores_round,new SortByRound());
                speed_only_num=new ArrayList<>();
                for (int i=0;i<speed_only.size();i++){
                    speed_only_num.add(speed_only.get(i));
                }
                Collections.sort(speed_only_num,new SortByNum());
                speed_only_round=new ArrayList<>();
                for (int i=0;i<speed_only.size();i++){
                    speed_only_round.add(speed_only.get(i));
                }
                Collections.sort(speed_only_round,new SortByRound());
                slalom_only_num=new ArrayList<>();
                for (int i=0;i<slalom_only.size();i++){
                    slalom_only_num.add(slalom_only.get(i));
                }
                Collections.sort(slalom_only_num,new SortByNum());
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
