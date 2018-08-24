package kz.almatherm.mobile.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import kz.almatherm.mobile.model.SubCategory;
import kz.almatherm.mobile.model.SubService;

@Dao
public interface SubServiceDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    void insert(SubService subService);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<SubService> subServices);

    @Query("SELECT * FROM SubService where id=:id")
    SubService getSubService(int id);

    @Query("SELECT * FROM SubService")
    List<SubService> getSubServices();

    @Query("SELECT * FROM SubService WHERE parent_id = :parentId")
    List<SubService> getSubServiceByParentId(int parentId);
}
