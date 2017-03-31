package fylder.data.demo.greendao.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fylder on 2017/3/30.
 */
@Entity
public class BookShop {
    @Id(autoincrement = true)
    private Long id;
    private String name;

    @Generated(hash = 1491763364)
    public BookShop(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1221983486)
    public BookShop() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
