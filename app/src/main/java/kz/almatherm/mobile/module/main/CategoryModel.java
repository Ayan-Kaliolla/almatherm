package kz.almatherm.mobile.module.main;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kz.almatherm.mobile.AlmathermMobile;
import kz.almatherm.mobile.model.Category;

public class CategoryModel {

    private Context context;
    private CategoryPresenter presenter;

    public CategoryModel(Context context, CategoryPresenter presenter) {
        this.context = context.getApplicationContext();
        this.presenter = presenter;
    }

    public void loadCategories() {
        List<Category> categories = new ArrayList<>();
        Observable.just("j")
                .observeOn(Schedulers.io())
                .doOnNext(e -> categories.addAll(((AlmathermMobile) context).getDatabase().getCategoryDao().getCategories()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> presenter.onCategoryLoaded(categories))
                .subscribe();
    }

    public void loadCategories(int parentId) {

    }
}
