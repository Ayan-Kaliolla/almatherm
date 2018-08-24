package kz.almatherm.mobile.module.category;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import kz.almatherm.mobile.model.Category;
import kz.almatherm.mobile.model.SubCategory;

interface SubCategoryView extends MvpView {
    void onCategoryLoaded(List<SubCategory> subCategories);
}