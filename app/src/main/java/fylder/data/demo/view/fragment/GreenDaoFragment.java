package fylder.data.demo.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fylder.data.demo.MyApplication;
import fylder.data.demo.R;
import fylder.data.demo.greendao.gen.BookDao;
import fylder.data.demo.greendao.gen.DaoSession;
import fylder.data.demo.greendao.table.Book;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by fylder on 2017/3/30.
 */

public class GreenDaoFragment extends Fragment {

    @BindView(R.id.fragment_green_text)
    TextView showText;

    Context mContext;

    BookDao bookDao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_greendao, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mContext = getContext();
        DaoSession daoSession = ((MyApplication) getActivity().getApplication()).getDaoSession();
        bookDao = daoSession.getBookDao();
    }

    @OnClick(R.id.fragment_green_add)
    void add() {
        insertUser("fylder");
        Toast.makeText(mContext, "save", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.fragment_green_query)
    void query() {
        queryBook("fylder").subscribe(books -> {
            String str = "";
            for (Book b : books) {
                str += ("\n" + b.toString());
            }
            showText.setText(str);
        });
    }

    @OnClick(R.id.fragment_green_delete)
    void delete() {
        deleteUser("fylder");
    }

    //插入
    private void insertUser(String name) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(2);
        book.setCount(12);
        bookDao.rx()
                .insertOrReplace(book)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(book1 -> Log.d("DaoExample", "Inserted new note, ID: " + book1.getId()));
    }

    //查询
    private Observable<List<Book>> queryBook(String name) {
        return bookDao.queryBuilder().where(BookDao.Properties.Name.eq(name)).rx()
                .list()
                .observeOn(AndroidSchedulers.mainThread());
    }

    //删除
    private void deleteUser(String name) {
        bookDao.queryBuilder().where(BookDao.Properties.Name.eq(name)).rx()
                .list()
                .map(m -> {
                    int i = 0;
                    for (Book b : m) {
                        if (b != null) {
                            bookDao.deleteByKey(b.getId());
                            i++;
                        } else {
                            Log.w("greendao", "用户不存在");
                        }
                    }
                    return i;
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s > 0) {
                        Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                    }
                }, r -> Toast.makeText(mContext, "删除异常:" + r.getMessage(), Toast.LENGTH_SHORT).show());
    }

}
