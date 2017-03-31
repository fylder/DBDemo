package fylder.data.demo.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fylder.data.demo.R;
import fylder.data.demo.realm.dao.BikeDao;
import fylder.data.demo.realm.model.Bike;
import io.realm.Realm;

/**
 * Created by fylder on 2017/3/30.
 */

public class RealmFragment extends Fragment {

    @BindView(R.id.fragment_realm_text)
    TextView showText;

    Context mContext;

    Realm realm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_realm, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mContext = getContext();
        realm = Realm.getDefaultInstance();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }

    @OnClick(R.id.fragment_realm_add)
    void add() {
        Bike bike = new Bike();
        bike.setName("bike");
        bike.setType("XT");
        bike.setPrice(4700L);
        BikeDao.getInstance().add(realm, bike);
        Toast.makeText(mContext, "save", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.fragment_realm_query)
    void query() {
        List<Bike> bikes = BikeDao.getInstance().query(realm, "bike");
        if (bikes != null) {
            String str = "";
            for (Bike b : bikes) {
                str += b.toString() + "\n";
            }
            showText.setText(str);
        }
    }

    @OnClick(R.id.fragment_realm_delete)
    void delete() {
        BikeDao.getInstance().delete(realm);
        query();
    }
}
