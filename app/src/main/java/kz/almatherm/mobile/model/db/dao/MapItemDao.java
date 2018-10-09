package kz.almatherm.mobile.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import kz.almatherm.mobile.model.MapItem;

@Dao
public interface MapItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MapItem mapItem);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<MapItem> mapItem);

    @Query("SELECT * FROM MapItem")
    List<MapItem> getMapItems();
}
