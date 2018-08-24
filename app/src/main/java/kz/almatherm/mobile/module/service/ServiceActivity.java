package kz.almatherm.mobile.module.service;

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
import kz.almatherm.mobile.model.Service;

public class ServiceActivity extends MvpAppCompatActivity implements ServiceView{

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @InjectPresenter
    ServicePresenter presenter;

    ServiceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);

        adapter = new ServiceAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter.loadServices(getIntent().getIntExtra("subcategory_id", 0));
    }

    @ProvidePresenter
    ServicePresenter getPresenter() {
        return new ServicePresenter(this);
    }

    @Override
    public void showData(List<Service> services) {
        adapter.setServices(services);
        adapter.notifyDataSetChanged();
    }
}
