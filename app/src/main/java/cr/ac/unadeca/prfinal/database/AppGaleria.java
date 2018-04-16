package cr.ac.unadeca.prfinal.database;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Freddy on 4/15/2018.
 */

public class AppGaleria extends Application {
    @Override
    public void onCreate (){
        super.onCreate();
        FlowManager.init(this);
    }
}
