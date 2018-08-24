package kz.almatherm.mobile.module.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kz.almatherm.mobile.R;
import kz.almatherm.mobile.model.Category;
import kz.almatherm.mobile.module.category.adapter.CategoryAdapter;

public class MainActivity extends MvpAppCompatActivity implements CategoryView{

    @InjectPresenter
    CategoryPresenter presenter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter.loadCategories();
    }

    @ProvidePresenter
    CategoryPresenter getPresenter() {
        return new CategoryPresenter(this);
    }

    @Override
    public void onCategoryLoaded(List<Category> categories) {
        CategoryAdapter adapter = new CategoryAdapter(categories, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyItemRangeInserted(0, categories.size());
    }
}
