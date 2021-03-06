package fylder.data.demo.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import fylder.data.demo.greendao.table.Book;
import fylder.data.demo.greendao.table.BookShop;

import fylder.data.demo.greendao.gen.BookDao;
import fylder.data.demo.greendao.gen.BookShopDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig bookDaoConfig;
    private final DaoConfig bookShopDaoConfig;

    private final BookDao bookDao;
    private final BookShopDao bookShopDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        bookDaoConfig = daoConfigMap.get(BookDao.class).clone();
        bookDaoConfig.initIdentityScope(type);

        bookShopDaoConfig = daoConfigMap.get(BookShopDao.class).clone();
        bookShopDaoConfig.initIdentityScope(type);

        bookDao = new BookDao(bookDaoConfig, this);
        bookShopDao = new BookShopDao(bookShopDaoConfig, this);

        registerDao(Book.class, bookDao);
        registerDao(BookShop.class, bookShopDao);
    }
    
    public void clear() {
        bookDaoConfig.clearIdentityScope();
        bookShopDaoConfig.clearIdentityScope();
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public BookShopDao getBookShopDao() {
        return bookShopDao;
    }

}
