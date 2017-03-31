package fylder.data.demo.realm.dao;

import java.util.List;

import fylder.data.demo.realm.model.Bike;
import io.realm.Realm;

/**
 * Created by fylder on 2017/3/30.
 */

public interface BikeDaoImp {

    void add(Realm realm, Bike bike);

    List<Bike> query(Realm realm, String name);

    int delete(Realm realm);
}
