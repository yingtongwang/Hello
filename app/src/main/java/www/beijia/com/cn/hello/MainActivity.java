package www.beijia.com.cn.hello;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import mode.Singleton;
import view.BounceListView;

public class MainActivity extends AppCompatActivity implements BounceListView.OnTouchListener {

    private static final String TAG = "MainActivity";
    private float mFirstY = 0;
    private float mCurrentY = 0;
    private float mTouchSlop = 0;
    private int direction = 0;
    private boolean mShow = true;
    private Toolbar mToolbar;
    private BounceListView bounceListView;
    private View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        bounceListView = (BounceListView) findViewById(R.id.mBounceListView);
        bounceListView.setOnTouchListener(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        String[] data = new String[30];
        for (int i = 0; i < data.length; i++) {
            data[i] = "回弹效果 " + i;
        }

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        bounceListView.setAdapter(arrayAdapter);

        header = new View(this);
        header.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) getResources().getDimension(android.support.v7.appcompat.R.dimen.abc_action_bar_default_height_material)));
        bounceListView.addHeaderView(header);
    }

    @Override
    public boolean onTouch(View v, MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFirstY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurrentY = ev.getY();
                if (mCurrentY - mFirstY > mTouchSlop) {
                    direction = 0;
                } else if (mFirstY - mCurrentY > mTouchSlop) {
                    direction = 1;
                }
                if (direction == 1) {
                    if (mShow) {
                        mShow = !mShow;
                        toolbarAnim(0);
                    }
                } else if (direction == 0) {
                    if (!mShow) {
                        mShow = !mShow;
                        toolbarAnim(1);
                        header.setVisibility(View.VISIBLE);
                    }
                }
                break;
        }
        return false;
    }


    private void toolbarAnim(int flag) {
        ObjectAnimator animation = new ObjectAnimator();
        if (animation != null && animation.isRunning()) {
            animation.cancel();
        }
        if (flag == 0) {
            animation = ObjectAnimator.ofFloat(mToolbar, "translationY", mToolbar.getTranslationY(), 0);
        } else {
            animation = ObjectAnimator.ofFloat(mToolbar, "translationY", mToolbar.getTranslationY(), -mToolbar.getHeight());
        }
        animation.start();
    }
}