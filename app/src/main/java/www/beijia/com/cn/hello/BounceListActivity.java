package www.beijia.com.cn.hello;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import view.BounceListView;

/**
 * Created by Wangyingbao on 2016/7/13.
 */
public class BounceListActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

        setContentView(linearLayout);


        BounceListView bounceListView = new BounceListView(this);



        String[] data = new String[30];
        for (int i = 0; i < data.length; i++) {
            data[i] = "回弹效果 " + i;
        }

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        bounceListView.setAdapter(arrayAdapter);

        linearLayout.addView(bounceListView);
    }

}