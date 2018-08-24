package kz.almatherm.mobile.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import kz.almatherm.mobile.model.SubCategory;

@Dao
public interface SubCategoryDao {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    void insert(SubCategory subCategory);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<SubCategory> subCategories);

    @Query("SELECT * FROM subcategory where id=:id")
    SubCategory getSubCategory(int id);

    @Query("SELECT * FROM subcategory")
    List<SubCategory> getSubCategory();

    @Query("SELECT * FROM subcategory WHERE parent_id = :parentId")
    List<SubCategory> getSubCategoryByParentId(int parentId);
}
