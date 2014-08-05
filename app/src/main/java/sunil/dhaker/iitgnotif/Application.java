package sunil.dhaker.iitgnotif;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.PushService;

public class Application extends android.app.Application {

    public Application() {
    }

    @Override
    public void onCreate() {
        ParseObject.registerSubclass(Notification.class);
        Parse.initialize(this, "8NbWpX64iuV1JSEwqNOjk3X97nWedI6TrhWCfAYq", "Qrgug04egp4fu3cBVJuqrDPZBMHptsigVwESfYWi");
        PushService.setDefaultPushCallback(this, Home.class);
    }
}