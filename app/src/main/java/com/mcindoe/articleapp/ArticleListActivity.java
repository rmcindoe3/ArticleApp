package com.mcindoe.articleapp;

import android.support.v4.app.Fragment;

public class ArticleListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return ArticleListFragment.newInstance();
    }

    @Override
    protected boolean isChildActivity() {
        return false;
    }
}
