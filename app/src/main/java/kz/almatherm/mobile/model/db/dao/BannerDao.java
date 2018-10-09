package kz.almatherm.mobile.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import kz.almatherm.mobile.model.Banner;

@Dao
public interface BannerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Banner banner);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Banner> banners);

    @Query("SELECT * FROM Banner where id=:id")
    Banner getBannerById(int id);

    @Query("SELECT * FROM Banner")
    List<Banner> getCategories();
}
