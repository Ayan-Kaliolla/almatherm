package kz.almatherm.infinityloadinglibrary;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class InfinityAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private List<T> items = new ArrayList<>();
    private int loadingLayout = R.layout.loading_layout;
    private boolean useLoadingItem = true;

    public InfinityAdapter() {
    }

    public InfinityAdapter(@LayoutRes int loadingLayout) {
        this.loadingLayout = loadingLayout;
    }

    public InfinityAdapter(int loadingLayout, boolean useLoadingItem) {
        this.loadingLayout = loadingLayout;
        this.useLoadingItem = useLoadingItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (getItemViewType(i) == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(loadingLayout, viewGroup, false);
            Log.d(this.getClass().getSimpleName(), "Create Loading View Holder");
            return new LoadingViewHolder(view);
        }
        Log.d(this.getClass().getSimpleName(), "Create User View Holder");
        return createCustomViewHolder(viewGroup);
    }

    protected abstract VH createCustomViewHolder(ViewGroup viewGroup);

    protected abstract void bindCustomViewHolder(VH viewHolder, T item);

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof InfinityAdapter.LoadingViewHolder) {
            viewHolder.getAdapterPosition();
            Log.d(this.getClass().getSimpleName(), "Bind Loading View Holder");
        } else {
            bindCustomViewHolder((VH) viewHolder, getItem(i));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!useLoadingItem) return VIEW_TYPE_ITEM;
        return getItem(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public T getItem(int position) {
        return items.get(position);
    }

    public void addItem(T item, boolean showLoading) {
        items.remove(null);
        items.add(item);
        addLoadingObject(showLoading);
    }

    public void addAll(List<T> items, boolean showLoading) {
        this.items.addAll(items);
        this.items.remove(null);
        addLoadingObject(showLoading);
    }

    private void addLoadingObject(boolean show) {
        if (useLoadingItem && show) {
            items.add(null);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clearItems() {
        items.clear();
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
