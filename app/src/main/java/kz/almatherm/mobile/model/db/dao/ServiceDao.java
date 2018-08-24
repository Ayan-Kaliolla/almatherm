package kz.almatherm.mobile.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import kz.almatherm.mobile.model.Service;
import kz.almatherm.mobile.model.SubCategory;

@Dao
public interface ServiceDao {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    void insert(Service service);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Service> service);

    @Query("SELECT * FROM service where id=:id")
    Service getService(int id);

    @Query("SELECT * FROM service")
    List<Service> getService();

    @Query("SELECT * FROM service WHERE parent_id = :parentId")
    List<Service> getServiceByParentId(int parentId);

}
