package kz.almatherm.mobile.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kz.almatherm.mobile.model.Category;
import kz.almatherm.mobile.model.Service;
import kz.almatherm.mobile.model.SubCategory;
import kz.almatherm.mobile.model.SubService;
import kz.almatherm.mobile.model.db.dao.CategoryDao;
import kz.almatherm.mobile.model.db.dao.ServiceDao;
import kz.almatherm.mobile.model.db.dao.SubCategoryDao;
import kz.almatherm.mobile.model.db.dao.SubServiceDao;

@Database(entities = {Category.class,
        SubCategory.class,
        Service.class,
        SubService.class},
        version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CategoryDao getCategoryDao();

    public abstract SubCategoryDao getSubCategoryDao();

    public abstract ServiceDao getServiceDao();

    public abstract SubServiceDao getSubServiceDao();
}
