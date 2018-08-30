package kz.almatherm.mobile.module.category;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kz.almatherm.mobile.R;
import kz.almatherm.mobile.model.Category;
import kz.almatherm.mobile.module.category.adapter.CategoryAdapter;
import kz.almatherm.mobile.module.main.CategoryPresenter;
import kz.almatherm.mobile.module.main.CategoryView;

public class CategoryFragment extends MvpAppCompatFragment implements CategoryView {

    @InjectPresenter
    CategoryPresenter presenter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter.loadCategories();
    }

    @Override
    public void onCategoryLoaded(List<Category> categories) {
        CategoryAdapter adapter = new CategoryAdapter(categories, getContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyItemRangeInserted(0, categories.size());
    }

    @ProvidePresenter
    CategoryPresenter getPresenter() {
        return new CategoryPresenter(getContext());
    }
}
