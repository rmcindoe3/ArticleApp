package com.mcindoe.articleapp;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.Gravity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mcindoe on 6/2/15.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar) Toolbar mToolbar;

    /** Creates the Fragment to be used in this Activity. */
    protected abstract Fragment createFragment();

    /** Returns true if this activity is not a root activity of the application. */
    protected abstract boolean isChildActivity();

    /**
     * Method to return the layout id for this activity.
     * Can be optionally overridden in subclasses if the standard layout is not desired.
     * @return - Layout resource id for this activity's content view.
     */
    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.inject(this);

        // Set up our Toolbar as this activity's ActionBar.
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(isChildActivity());

        /**************************************************************************/
        /*** THE FOLLOWING TRANSITION CODE WORKS PERFECTLY ************************/
        /**************************************************************************/

        // Set up our enter and exit transitions.
        // The Toolbar should slide from the top and everything else should just fade in and out.
        getWindow().setEnterTransition(new TransitionSet()
                .addTransition(new Fade(Fade.IN))
                .addTransition(new Slide(Gravity.TOP).addTarget(mToolbar)));
        getWindow().setExitTransition(new TransitionSet()
                .addTransition(new Fade(Fade.OUT))
                .addTransition(new Slide(Gravity.TOP).addTarget(mToolbar)));

        /**************************************************************************/
        /*** THE PREVIOUS TRANSITION CODE WORKS PERFECTLY *************************/
        /**************************************************************************/

        // Add our fragment to the view.
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
