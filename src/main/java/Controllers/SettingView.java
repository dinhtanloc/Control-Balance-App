package Controllers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class SettingView extends FrameLayout implements View.OnTouchListener {
    public int balance;
    private TextView btnBack;
    private TextView btnOk;
    public int kd;
    public int ki_thr;
    public int kp;
    public int kp_thr;
    /* access modifiers changed from: private */
    public OnClickListener mListener;
    private float maxTime = 0.0f;
    /* access modifiers changed from: private */
    public View seekbar1;
    /* access modifiers changed from: private */
    public View seekbar2;
    /* access modifiers changed from: private */
    public View seekbar3;
    /* access modifiers changed from: private */
    public View seekbar4;
    /* access modifiers changed from: private */
    public View seekbar5;
    private TextView tv = null;
    /* access modifiers changed from: private */
    public TextView tvTime1;
    /* access modifiers changed from: private */
    public TextView tvTime2;
    /* access modifiers changed from: private */
    public TextView tvTime3;
    /* access modifiers changed from: private */
    public TextView tvTime4;
    /* access modifiers changed from: private */
    public TextView tvTime5;
    private View v1;
    private View v2;
    private View v3;
    private View v4;
    private View v5;
    private View vBall = null;

    public SettingView(Context context) {
        super(context);
        init();
    }

    public SettingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SettingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /* access modifiers changed from: package-private */
    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.setting_view, this, true);
        this.v1 = findViewById(R.id.v1);
        this.v2 = findViewById(R.id.v2);
        this.v3 = findViewById(R.id.v3);
        this.v4 = findViewById(R.id.v4);
        this.v5 = findViewById(R.id.v5);
        this.btnOk = (TextView) findViewById(R.id.btn_ok);
        this.btnBack = (TextView) findViewById(R.id.btn_back);
        this.seekbar1 = findViewById(R.id.seekBar1);
        this.seekbar2 = findViewById(R.id.seekBar2);
        this.seekbar3 = findViewById(R.id.seekBar3);
        this.seekbar4 = findViewById(R.id.seekBar4);
        this.seekbar5 = findViewById(R.id.seekBar5);
        this.tvTime1 = (TextView) findViewById(R.id.tv_time_1);
        this.tvTime2 = (TextView) findViewById(R.id.tv_time_2);
        this.tvTime3 = (TextView) findViewById(R.id.tv_time_3);
        this.tvTime4 = (TextView) findViewById(R.id.tv_time_4);
        this.tvTime5 = (TextView) findViewById(R.id.tv_time_5);
        this.btnOk.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                SettingView settingView = SettingView.this;
                settingView.balance = Integer.parseInt(settingView.tvTime1.getText().toString());
                SettingView settingView2 = SettingView.this;
                settingView2.kp = Integer.parseInt(settingView2.tvTime2.getText().toString());
                SettingView settingView3 = SettingView.this;
                settingView3.kd = Integer.parseInt(settingView3.tvTime3.getText().toString());
                SettingView settingView4 = SettingView.this;
                settingView4.kp_thr = Integer.parseInt(settingView4.tvTime4.getText().toString());
                SettingView settingView5 = SettingView.this;
                settingView5.ki_thr = Integer.parseInt(settingView5.tvTime5.getText().toString());
                NetworkUtils.sendData(String.format("2 %d %d %d %d %d", new Object[]{Integer.valueOf(SettingView.this.balance), Integer.valueOf(SettingView.this.kp), Integer.valueOf(SettingView.this.kd), Integer.valueOf(SettingView.this.kp_thr), Integer.valueOf(SettingView.this.ki_thr)}));
                if (SettingView.this.mListener != null) {
                    SettingView.this.mListener.onClick(v);
                }
            }
        });
        this.btnBack.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (SettingView.this.mListener != null) {
                    SettingView.this.mListener.onClick(v);
                }
            }
        });
        this.seekbar1.setOnTouchListener(this);
        this.seekbar2.setOnTouchListener(this);
        this.seekbar3.setOnTouchListener(this);
        this.seekbar4.setOnTouchListener(this);
        this.seekbar5.setOnTouchListener(this);
    }

    public void setListener(OnClickListener listener) {
        this.mListener = listener;
    }

    public void showLayout() {
        post(new Runnable() {
            public void run() {
                SettingView settingView = SettingView.this;
                settingView.setSeeBar(settingView.seekbar1, SettingView.this.balance);
                SettingView settingView2 = SettingView.this;
                settingView2.setSeeBar(settingView2.seekbar2, SettingView.this.kp);
                SettingView settingView3 = SettingView.this;
                settingView3.setSeeBar(settingView3.seekbar3, SettingView.this.kd);
                SettingView settingView4 = SettingView.this;
                settingView4.setSeeBar(settingView4.seekbar4, SettingView.this.kp_thr);
                SettingView settingView5 = SettingView.this;
                settingView5.setSeeBar(settingView5.seekbar5, SettingView.this.ki_thr);
            }
        });
    }

    /* access modifiers changed from: private */
    public void setSeeBar(View seeBar, int value) {
        switch (seeBar.getId()) {
            case R.id.seekBar1:
                this.tv = this.tvTime1;
                this.vBall = this.v1;
                break;
            case R.id.seekBar2:
                this.tv = this.tvTime2;
                this.vBall = this.v2;
                break;
            case R.id.seekBar3:
                this.tv = this.tvTime3;
                this.vBall = this.v3;
                break;
            case R.id.seekBar4:
                this.tv = this.tvTime4;
                this.vBall = this.v4;
                break;
            case R.id.seekBar5:
                this.tv = this.tvTime5;
                this.vBall = this.v5;
                break;
        }
        TextView textView = this.tv;
        if (textView != null && this.vBall != null) {
            textView.setText(String.valueOf(value));
            this.vBall.setTranslationX((((float) (value + 100)) / 200.0f) * ((float) (seeBar.getWidth() - this.vBall.getWidth())));
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        try {
            switch (v.getId()) {
                case R.id.seekBar1:
                    this.vBall = this.v1;
                    this.tv = this.tvTime1;
                    break;
                case R.id.seekBar2:
                    this.vBall = this.v2;
                    this.tv = this.tvTime2;
                    break;
                case R.id.seekBar3:
                    this.vBall = this.v3;
                    this.tv = this.tvTime3;
                    break;
                case R.id.seekBar4:
                    this.vBall = this.v4;
                    this.tv = this.tvTime4;
                    break;
                case R.id.seekBar5:
                    this.vBall = this.v5;
                    this.tv = this.tvTime5;
                    break;
                default:
                    return false;
            }
            float x = event.getX();
            if (x < ((float) (this.vBall.getWidth() / 2))) {
                x = (float) (this.vBall.getWidth() / 2);
            } else if (x > ((float) (v.getWidth() - (this.vBall.getWidth() / 2)))) {
                x = (float) (v.getWidth() - (this.vBall.getWidth() / 2));
            }
            float x2 = x - ((float) (this.vBall.getWidth() / 2));
            this.vBall.setTranslationX(x2);
            this.tv.setText(String.valueOf(((int) ((x2 / ((float) (v.getWidth() - this.vBall.getWidth()))) * 200.0f)) - 100));
            this.balance = Integer.parseInt(this.tvTime1.getText().toString());
            this.kp = Integer.parseInt(this.tvTime2.getText().toString());
            this.kd = Integer.parseInt(this.tvTime3.getText().toString());
            this.kp_thr = Integer.parseInt(this.tvTime4.getText().toString());
            this.ki_thr = Integer.parseInt(this.tvTime5.getText().toString());
            NetworkUtils.sendData(String.format("2 %d %d %d %d %d", new Object[]{Integer.valueOf(this.balance), Integer.valueOf(this.kp), Integer.valueOf(this.kd), Integer.valueOf(this.kp_thr), Integer.valueOf(this.ki_thr)}));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
