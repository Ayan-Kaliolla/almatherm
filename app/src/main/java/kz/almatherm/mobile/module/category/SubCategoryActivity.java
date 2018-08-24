package kz.almatherm.mobile.module.category;

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
import kz.almatherm.mobile.model.SubCategory;
import kz.almatherm.mobile.module.category.adapter.SubCategoryAdapter;

public class SubCategoryActivity extends MvpAppCompatActivity implements SubCategoryView{

    @InjectPresenter
    SubCategoryPresenter presenter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter.loadCategories(getIntent().getIntExtra("category_id", 0));
    }

    @ProvidePresenter
    SubCategoryPresenter getPresenter() {
        return new SubCategoryPresenter(this);
    }

    @Override
    public void onCategoryLoaded(List<SubCategory> subCategories) {
        recyclerView.setAdapter(new SubCategoryAdapter(subCategories, this));
    }
}
