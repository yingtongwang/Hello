package www.beijia.com.cn.hello;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 服务端就一个Service，可以看到代码相当的简单，只需要去声明一个Messenger对象，然后onBind方法返回mMessenger.getBinder()；然后坐等客户端将消息发送到handleMessage想法，根据message.what去判断进行什么操作，然后做对应的操作，最终将结果通过 msgfromClient.replyTo.send(msgToClient);返回。
 */
public class MessengerService extends Service {

    private static final String TAG = "MessengerService";

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Const.CLIENT:
                    Log.i(TAG, "receive Client msg:" + msg.getData().getString("msg"));
                    Messenger messenger = msg.replyTo;
                    Message message = Message.obtain(null, Const.SERVICE);
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", "稍后回复");
                    message.setData(bundle);
                    try {
                        messenger.send(message);
                    } catch (Exception e) {

                    }
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    }
    //类似ADIL，进程中数据共享，传递Messenger，Bundle
    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
