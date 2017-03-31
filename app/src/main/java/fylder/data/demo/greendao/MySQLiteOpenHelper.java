package fylder.data.demo.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fylder.data.demo.greendao.gen.BookDao;
import fylder.data.demo.greendao.gen.DaoMaster;

/**
 * Created by fylder on 2017/3/30.
 */

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, BookDao.class);
    }
}