package debug;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.magicalrice.adolph.lib_common.base.BaseApplication;

/**
 * Created by Adolph on 2018/2/23.
 */

public class NewsApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
