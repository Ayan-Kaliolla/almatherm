package kz.almatherm.mobile.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import kz.almatherm.mobile.model.Category;

@Dao
public interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Category category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Category> categories);

    @Query("SELECT * FROM category where id=:id")
    Category getCategoryById(int id);

    @Query("SELECT * FROM Category")
    List<Category> getCategories();
}
