package kz.almatherm.mobile.module.sub_service;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kz.almatherm.mobile.R;
import kz.almatherm.mobile.model.SubService;

public class SubServiceActivity extends MvpAppCompatActivity implements SubServiceView{

    @InjectPresenter
    SubServicePresenter presenter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    SubServiceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_service);

        ButterKnife.bind(this);
        adapter = new SubServiceAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        presenter.loadSubServices(getIntent().getIntExtra("service_id", 0));
    }

    @ProvidePresenter
    SubServicePresenter presenter() {
        return new SubServicePresenter(this);
    }

    @Override
    public void showData(List<SubService> services) {
        adapter.setSubServices(services);
        adapter.notifyDataSetChanged();
    }
}
