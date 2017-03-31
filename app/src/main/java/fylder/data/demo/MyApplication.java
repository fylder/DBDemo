package fylder.data.demo;

import android.app.Application;

import fylder.data.demo.greendao.MySQLiteOpenHelper;
import fylder.data.demo.greendao.gen.DaoMaster;
import fylder.data.demo.greendao.gen.DaoSession;
import fylder.data.demo.realm.migration.Migration;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by fylder on 2017/3/30.
 */

public class MyApplication extends Application {

    /**
     * A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher.
     */
    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        MySQLiteOpenHelper devOpenHelper = new MySQLiteOpenHelper(this, "book.db", null);
        DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = mDaoMaster.newSession();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(6)
                .migration(new Migration())
                //.deleteRealmIfMigrationNeeded() //数据表有变动时需要重新删除创建
                .name("demo.realm")
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
