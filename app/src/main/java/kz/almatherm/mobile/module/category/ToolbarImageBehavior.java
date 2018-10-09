package kz.almatherm.mobile.module.category;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class ToolbarImageBehavior extends CoordinatorLayout.Behavior<ImageView> {

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        modifyDependencyState(child, dependency);
        return true;
    }

    private void modifyDependencyState(ImageView imageView, View dependency) {
        imageView.setY(dependency.getY());
        //  avatar.setBlahBlah(dependency.blah / blah);
    }


}
