package Controllers;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    /* access modifiers changed from: private */
    public Button btnNumber;
    private Button btnPID;
    /* access modifiers changed from: private */
    public Button btnPro;
    private Button btnPush;
    /* access modifiers changed from: private */
    public Handler handler = new Handler();
    private boolean isAuto = false;
    /* access modifiers changed from: private */
    public View layoutMain;
    /* access modifiers changed from: private */
    public FrameLayout layoutServo;
    /* access modifiers changed from: private */
    public SettingView layoutSetting;
    /* access modifiers changed from: private */
    public View leftRight;
    /* access modifiers changed from: private */
    public FrameLayout leftRightLayout;
    /* access modifiers changed from: private */
    public int mServo;
    /* access modifiers changed from: private */
    public int mX;
    /* access modifiers changed from: private */
    public int mY;
    /* access modifiers changed from: private */
    public int number = 4;
    private Timer timer;
    /* access modifiers changed from: private */
    public TextView tvVon;
    /* access modifiers changed from: private */
    public View upDown;
    /* access modifiers changed from: private */
    public FrameLayout upDownLayout;
    /* access modifiers changed from: private */
    public View viewServo;
    private WebView webView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.layoutMain = findViewById(R.id.layout_main);
        this.layoutSetting = (SettingView) findViewById(R.id.setting_view);
        this.upDownLayout = (FrameLayout) findViewById(R.id.upDownLayout);
        this.leftRightLayout = (FrameLayout) findViewById(R.id.leftRightLayout);
        this.layoutServo = (FrameLayout) findViewById(R.id.layout_servo);
        this.upDown = findViewById(R.id.upDown);
        this.leftRight = findViewById(R.id.leftRight);
        this.viewServo = findViewById(R.id.view_servo);
        this.btnPID = (Button) findViewById(R.id.btnPID);
        this.btnPush = (Button) findViewById(R.id.btnPush);
        this.btnPro = (Button) findViewById(R.id.btnPro);
        this.btnNumber = (Button) findViewById(R.id.btnNumber);
        this.tvVon = (TextView) findViewById(R.id.tvVon);
        this.tvVon.setVisibility(8);
        this.btnPID.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("config", 0);
                MainActivity.this.layoutSetting.balance = sharedPreferences.getInt("balance", 0);
                MainActivity.this.layoutSetting.kp = sharedPreferences.getInt("kp", 0);
                MainActivity.this.layoutSetting.kd = sharedPreferences.getInt("kd", 0);
                MainActivity.this.layoutSetting.kp_thr = sharedPreferences.getInt("kp_thr", 0);
                MainActivity.this.layoutSetting.ki_thr = sharedPreferences.getInt("ki_thr", 0);
                MainActivity.this.layoutSetting.setVisibility(0);
                MainActivity.this.layoutMain.setVisibility(4);
                MainActivity.this.layoutSetting.showLayout();
            }
        });
        this.btnPush.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NetworkUtils.sendData("3 1");
            }
        });
        this.btnPro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (MainActivity.this.btnPro.isSelected()) {
                    MainActivity.this.btnPro.setSelected(false);
                    NetworkUtils.sendData("4 0");
                    MainActivity.this.handler.postDelayed(new Runnable() {
                        public void run() {
                            NetworkUtils.sendData("4 0");
                        }
                    }, 30);
                    return;
                }
                MainActivity.this.btnPro.setSelected(true);
                NetworkUtils.sendData("4 1");
                MainActivity.this.handler.postDelayed(new Runnable() {
                    public void run() {
                        NetworkUtils.sendData("4 1");
                    }
                }, 30);
            }
        });
        this.layoutSetting.setListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.layoutSetting.setVisibility(4);
                MainActivity.this.layoutMain.setVisibility(0);
                if (v.getId() == R.id.btn_ok) {
                    SharedPreferences.Editor editor = MainActivity.this.getSharedPreferences("config", 0).edit();
                    editor.putInt("balance", MainActivity.this.layoutSetting.balance);
                    editor.putInt("kp", MainActivity.this.layoutSetting.kp);
                    editor.putInt("kd", MainActivity.this.layoutSetting.kd);
                    editor.putInt("kp_thr", MainActivity.this.layoutSetting.kp_thr);
                    editor.putInt("ki_thr", MainActivity.this.layoutSetting.ki_thr);
                    editor.commit();
                }
            }
        });
        this.upDownLayout.setOnTouchListener(new View.OnTouchListener() {
            /* JADX WARNING: Code restructure failed: missing block: B:13:0x0065, code lost:
                if (r1 != 3) goto L_0x00b1;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public boolean onTouch(View r8, MotionEvent r9) {
                /*
                    r7 = this;
                    float r0 = r9.getY()
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    android.view.View r1 = r1.upDown
                    int r1 = r1.getHeight()
                    r2 = 2
                    int r1 = r1 / r2
                    float r1 = (float) r1
                    int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                    if (r1 >= 0) goto L_0x0022
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    android.view.View r1 = r1.upDown
                    int r1 = r1.getHeight()
                    int r1 = r1 / r2
                    float r1 = (float) r1
                    goto L_0x0023
                L_0x0022:
                    r1 = r0
                L_0x0023:
                    r0 = r1
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    android.widget.FrameLayout r1 = r1.upDownLayout
                    int r1 = r1.getHeight()
                    com.icdayroi.smartcar.MainActivity r3 = com.icdayroi.smartcar.MainActivity.this
                    android.view.View r3 = r3.upDown
                    int r3 = r3.getHeight()
                    int r3 = r3 / r2
                    int r1 = r1 - r3
                    float r1 = (float) r1
                    int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                    if (r1 <= 0) goto L_0x0057
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    android.widget.FrameLayout r1 = r1.upDownLayout
                    int r1 = r1.getHeight()
                    com.icdayroi.smartcar.MainActivity r3 = com.icdayroi.smartcar.MainActivity.this
                    android.view.View r3 = r3.upDown
                    int r3 = r3.getHeight()
                    int r3 = r3 / r2
                    int r1 = r1 - r3
                    float r1 = (float) r1
                    goto L_0x0058
                L_0x0057:
                    r1 = r0
                L_0x0058:
                    r0 = r1
                    int r1 = r9.getAction()
                    r3 = 1
                    if (r1 == 0) goto L_0x0079
                    if (r1 == r3) goto L_0x0068
                    if (r1 == r2) goto L_0x0079
                    r2 = 3
                    if (r1 == r2) goto L_0x0068
                    goto L_0x00b1
                L_0x0068:
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    android.view.View r1 = r1.upDown
                    r2 = 0
                    r1.setTranslationY(r2)
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    r2 = 0
                    int unused = r1.mY = r2
                    goto L_0x00b1
                L_0x0079:
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    android.widget.FrameLayout r1 = r1.upDownLayout
                    int r1 = r1.getHeight()
                    int r1 = r1 / r2
                    float r1 = (float) r1
                    float r0 = r0 - r1
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    android.view.View r1 = r1.upDown
                    r1.setTranslationY(r0)
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    float r4 = -r0
                    r5 = 1120403456(0x42c80000, float:100.0)
                    float r4 = r4 * r5
                    android.widget.FrameLayout r5 = r1.upDownLayout
                    int r5 = r5.getHeight()
                    com.icdayroi.smartcar.MainActivity r6 = com.icdayroi.smartcar.MainActivity.this
                    android.view.View r6 = r6.upDown
                    int r6 = r6.getHeight()
                    int r5 = r5 - r6
                    int r5 = r5 / r2
                    float r2 = (float) r5
                    float r4 = r4 / r2
                    int r2 = (int) r4
                    int unused = r1.mY = r2
                L_0x00b1:
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.icdayroi.smartcar.MainActivity.AnonymousClass5.onTouch(android.view.View, android.view.MotionEvent):boolean");
            }
        });
        this.leftRightLayout.setOnTouchListener(new View.OnTouchListener() {
            /* JADX WARNING: Code restructure failed: missing block: B:13:0x0065, code lost:
                if (r1 != 3) goto L_0x00b0;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public boolean onTouch(View r8, MotionEvent r9) {
                /*
                    r7 = this;
                    float r0 = r9.getX()
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    android.view.View r1 = r1.leftRight
                    int r1 = r1.getWidth()
                    r2 = 2
                    int r1 = r1 / r2
                    float r1 = (float) r1
                    int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                    if (r1 >= 0) goto L_0x0022
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    android.view.View r1 = r1.leftRight
                    int r1 = r1.getWidth()
                    int r1 = r1 / r2
                    float r1 = (float) r1
                    goto L_0x0023
                L_0x0022:
                    r1 = r0
                L_0x0023:
                    r0 = r1
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    android.widget.FrameLayout r1 = r1.leftRightLayout
                    int r1 = r1.getWidth()
                    com.icdayroi.smartcar.MainActivity r3 = com.icdayroi.smartcar.MainActivity.this
                    android.view.View r3 = r3.leftRight
                    int r3 = r3.getWidth()
                    int r3 = r3 / r2
                    int r1 = r1 - r3
                    float r1 = (float) r1
                    int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                    if (r1 <= 0) goto L_0x0057
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    android.widget.FrameLayout r1 = r1.leftRightLayout
                    int r1 = r1.getWidth()
                    com.icdayroi.smartcar.MainActivity r3 = com.icdayroi.smartcar.MainActivity.this
                    android.view.View r3 = r3.leftRight
                    int r3 = r3.getWidth()
                    int r3 = r3 / r2
                    int r1 = r1 - r3
                    float r1 = (float) r1
                    goto L_0x0058
                L_0x0057:
                    r1 = r0
                L_0x0058:
                    r0 = r1
                    int r1 = r9.getAction()
                    r3 = 1
                    if (r1 == 0) goto L_0x0079
                    if (r1 == r3) goto L_0x0068
                    if (r1 == r2) goto L_0x0079
                    r2 = 3
                    if (r1 == r2) goto L_0x0068
                    goto L_0x00b0
                L_0x0068:
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    android.view.View r1 = r1.leftRight
                    r2 = 0
                    r1.setTranslationX(r2)
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    r2 = 0
                    int unused = r1.mX = r2
                    goto L_0x00b0
                L_0x0079:
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    android.widget.FrameLayout r1 = r1.leftRightLayout
                    int r1 = r1.getWidth()
                    int r1 = r1 / r2
                    float r1 = (float) r1
                    float r0 = r0 - r1
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    android.view.View r1 = r1.leftRight
                    r1.setTranslationX(r0)
                    com.icdayroi.smartcar.MainActivity r1 = com.icdayroi.smartcar.MainActivity.this
                    r4 = 1120403456(0x42c80000, float:100.0)
                    float r4 = r4 * r0
                    android.widget.FrameLayout r5 = r1.leftRightLayout
                    int r5 = r5.getWidth()
                    com.icdayroi.smartcar.MainActivity r6 = com.icdayroi.smartcar.MainActivity.this
                    android.view.View r6 = r6.leftRight
                    int r6 = r6.getWidth()
                    int r5 = r5 - r6
                    int r5 = r5 / r2
                    float r2 = (float) r5
                    float r4 = r4 / r2
                    int r2 = (int) r4
                    int unused = r1.mX = r2
                L_0x00b0:
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.icdayroi.smartcar.MainActivity.AnonymousClass6.onTouch(android.view.View, android.view.MotionEvent):boolean");
            }
        });
        this.layoutServo.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float x2 = x < ((float) (MainActivity.this.viewServo.getWidth() / 2)) ? (float) (MainActivity.this.viewServo.getWidth() / 2) : x;
                float x3 = x2 > ((float) (MainActivity.this.layoutServo.getWidth() - (MainActivity.this.viewServo.getWidth() / 2))) ? (float) (MainActivity.this.layoutServo.getWidth() - (MainActivity.this.viewServo.getWidth() / 2)) : x2;
                int action = event.getAction();
                if (action == 0 || action == 1 || action == 2 || action == 3) {
                    float x4 = x3 - ((float) (MainActivity.this.layoutServo.getWidth() / 2));
                    MainActivity.this.viewServo.setTranslationX(x4);
                    MainActivity mainActivity = MainActivity.this;
                    int unused = mainActivity.mServo = (int) ((100.0f * x4) / ((float) ((mainActivity.layoutServo.getWidth() - MainActivity.this.viewServo.getWidth()) / 2)));
                    NetworkUtils.sendData(String.format("5 %d", new Object[]{Integer.valueOf(MainActivity.this.mServo)}));
                }
                return true;
            }
        });
        this.btnNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity mainActivity = MainActivity.this;
                int unused = mainActivity.number = (mainActivity.number % 4) + 1;
                MainActivity.this.btnNumber.setText(String.valueOf(MainActivity.this.number));
            }
        });
        this.webView = (WebView) findViewById(R.id.web_view);
        this.webView.setVisibility(8);
        this.layoutServo.setVisibility(8);
        this.btnNumber.setVisibility(8);
        NetworkUtils.setListener(new NetworkUtils.Listener() {
            public void onData(String data) {
                if (data.startsWith("V:")) {
                    final float v = Float.parseFloat(data.substring(2).trim());
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            MainActivity.this.tvVon.setText(String.format("%.02f", new Object[]{Float.valueOf(v)}));
                            MainActivity.this.tvVon.setVisibility(0);
                        }
                    });
                }
            }
        });
    }

    private void loadCamera(int height) {
        try {
            this.webView.loadData(Base64.encodeToString(("<html style=\"height: 100%;\"><head><meta name=\"viewport\" content=\"width=device-width, minimum-scale=0.1\"></head><body style=\"margin: 0px; background: #999999; height: 100%\"><img style=\"margin: auto;height: " + height + "px\" src=\"http://192.168.4.1:81/stream\"></body></html>").getBytes("UTF-8"), 0), "text/html; charset=utf-8", "base64");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            if (this.timer != null) {
                this.timer.cancel();
            }
        } catch (Exception e) {
        }
        NetworkUtils.reset();
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            public void run() {
                int div = 1;
                int access$1300 = MainActivity.this.number;
                if (access$1300 == 1) {
                    div = 10;
                } else if (access$1300 == 2) {
                    div = 5;
                } else if (access$1300 == 3) {
                    div = 2;
                } else if (access$1300 == 4) {
                    div = 1;
                }
                NetworkUtils.sendData(String.format("1 %d %d", new Object[]{Integer.valueOf(MainActivity.this.mY / div), Integer.valueOf(MainActivity.this.mX)}));
            }
        }, 100, 100);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        NetworkUtils.sendData(String.format("1 %d %d", new Object[]{0, 0}));
        try {
            if (this.timer != null) {
                this.timer.cancel();
            }
        } catch (Exception e) {
        }
    }
}
