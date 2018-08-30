package kz.almatherm.infinityloadinglibrary;

import android.support.annotation.NonNull;

import java.util.List;

public abstract class InfinityPresenter<T> {
    enum State {
        NONE,
        EMPTY,
        DATA,
        LOADING,
        REFRESHING,
        DONE,
    }

    private State state = State.NONE;
    private InfinityAdapter adapter;
    private int page = 0;
    private int perPage = 10;

    public InfinityPresenter(@NonNull InfinityAdapter adapter) {
        this.adapter = adapter;
    }

    public void onDataLoaded(@NonNull List<T> items) {
        if (adapter.getItemCount() == 0 && items.size() == 0) {
            state = State.EMPTY;
            showNoData();
            return;
        }
        if (items.size() < perPage) {
            state = State.DONE;
            adapter.addAll(items, false);
        } else {
            state = State.DATA;
            adapter.addAll(items, true);
        }
        adapter.notifyDataSetChanged();
    }

    public void loadFirstPage() {
        state = State.LOADING;
        showFirstPageLoading();
        page = 1;
        loadData(page, perPage);
    }

    public void loadNextPage() {
        if (state == State.DATA || state == State.NONE || state == State.REFRESHING) {
            state = State.LOADING;
            page++;
            loadData(page, perPage);
        }
    }

    public void refresh() {
        adapter.clearItems();
        adapter.notifyDataSetChanged();
        state = State.REFRESHING;
        page = 1;
        loadData(page, perPage);
    }

    protected abstract void showFirstPageLoading();

    protected abstract void loadData(int page, int perPage);

    protected abstract void showNoData();

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
