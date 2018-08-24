package kz.almatherm.mobile.module.main;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import kz.almatherm.mobile.model.Category;

interface CategoryView extends MvpView {
    void onCategoryLoaded(List<Category> categories);
}
