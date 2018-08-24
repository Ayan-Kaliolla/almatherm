package kz.almatherm.mobile.module.main;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import kz.almatherm.mobile.model.Category;

@InjectViewState
public class CategoryPresenter extends MvpPresenter<CategoryView> {
    private CategoryModel model;

    public CategoryPresenter(Context context) {
        this.model = new CategoryModel(context, this);
    }

    public void onCategoryLoaded(List<Category> categories) {
        getViewState().onCategoryLoaded(categories);
    }

    public void loadCategories() {
        model.loadCategories();
    }
}
