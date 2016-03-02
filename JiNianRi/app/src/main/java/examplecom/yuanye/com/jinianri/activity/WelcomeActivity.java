package examplecom.yuanye.com.jinianri.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import examplecom.yuanye.com.jinianri.MainActivity;
import examplecom.yuanye.com.jinianri.R;
import examplecom.yuanye.com.jinianri.utils.DataConfigUtil;

/**
 * Created by weizhenhua on 2016/2/19.
 */
public class WelcomeActivity extends AppCompatActivity {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE:
                    updateUI(msg,textView_1);
                    break;

                default:
                    break;
            }
        }
    };
    protected static final int UPDATE = 1;
    private DataConfigUtil util;
    private long days;
    private TextView textView_1;
    private TimerTask timerTask;
    private String target = "";
    private String string = "";
    private int count = 0;
    private char[] ch;

    public WelcomeActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_welcome);

        initView();
        porPost();
        string = "就这样一直幸福下去吧";
        startAni();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                WelcomeActivity.this.startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                WelcomeActivity.this.finish();
            }
        }, 3500);


    }

    private void porPost() {
        util = new DataConfigUtil();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date d1 = df.parse("2016-02-07 00:00:00");
            long d2 = System.currentTimeMillis();

            long diff = d2 - d1.getTime();//这样得到的差值是微秒级别
            days = diff / (1000 * 60 * 60 * 24) + 1;
            util.setDays(days);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        textView_1 = (TextView) findViewById(R.id.welcome_1);
    }

    protected void updateUI(Message msg,TextView tv) {
        String strMsg = String.valueOf(msg.obj);
        if(strMsg.length()<string.length()){
            tv.setText(strMsg.substring(0, strMsg.length())+"_");
        } else {
            tv.setText(strMsg.substring(0, strMsg.length()));
        }
    }

    private void startAni(){
        ch = string.toCharArray();

        Timer timer = new Timer();
        timerTask = new TimerTask() {

            @Override
            public void run() {
                count++;
                if (count <= ch.length) {
                    if (count > 0) {
                        target += ch[count - 1];
                    }
                    Message msg = new Message();
                    msg.what = UPDATE;
                    msg.obj = target;
                    handler.sendMessage(msg);
                } else {
                    endAni();
                }
            }
        };
        timer.schedule(timerTask, 1, 300);
    }

    protected void endAni() {
        if(timerTask != null){
            timerTask.cancel();
        }
    }
}
