package fylder.data.demo.realm.dao;

import android.os.ParcelUuid;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fylder.data.demo.realm.model.Bike;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by fylder on 2017/3/30.
 */

public class BikeDao implements BikeDaoImp {

    static BikeDao dao;

    public static BikeDao getInstance() {
        if (dao == null) {
            dao = new BikeDao();
        }
        return dao;
    }

    @Override
    public void add(Realm realm, Bike bike) {
        // Obtain a Realm instance

        realm.beginTransaction();
        // User user = realm.createObject(User.class); // Create a new object
        bike.setId(UUID.randomUUID().toString());
        realm.copyToRealmOrUpdate(bike);
        // ... add or update objects here ...
        realm.commitTransaction();
    }

    @Override
    public List<Bike> query(Realm realm, String name) {
        realm.beginTransaction();
        // Build the query looking at all users:
        RealmQuery<Bike> query = realm.where(Bike.class);
        // Execute the query:
        RealmResults<Bike> result = query.findAll();
        List<Bike> bs = new ArrayList<>();
        bs.addAll(result);
        realm.commitTransaction();
        return bs;
    }

    @Override
    public int delete(Realm realm) {
        realm.beginTransaction();
        realm.delete(Bike.class);
        realm.commitTransaction();
        return 0;
    }
}
