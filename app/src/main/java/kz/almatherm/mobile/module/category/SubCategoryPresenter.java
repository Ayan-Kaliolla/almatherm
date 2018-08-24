package kz.almatherm.mobile.module.category;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import kz.almatherm.mobile.model.SubCategory;

@InjectViewState
public class SubCategoryPresenter extends MvpPresenter<SubCategoryView>{
    private SubCategoryModel model;

    public SubCategoryPresenter(SubCategoryActivity subCategoryActivity) {
        Context context = subCategoryActivity.getApplicationContext();
        model = new SubCategoryModel(context, this);
    }

    public void onCategoryLoaded(List<SubCategory> categories) {
        getViewState().onCategoryLoaded(categories);
    }

    public void loadCategories(int parentId) {
        model.loadSubCategoryByParentId(parentId);
    }

}
