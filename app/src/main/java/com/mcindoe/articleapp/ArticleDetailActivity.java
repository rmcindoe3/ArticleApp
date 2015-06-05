package com.mcindoe.articleapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;


public class ArticleDetailActivity extends SingleFragmentActivity {

    private static final String EXTRA_TITLE = "title";
    private static final String EXTRA_TEXT = "text";

    /** All intents for this activity should be created via this method. */
    public static Intent newIntent(Context context, Article article) {
        return new Intent(context, ArticleDetailActivity.class)
                .putExtra(EXTRA_TITLE, article.getTitle())
                .putExtra(EXTRA_TEXT, article.getText());
    }

    @Override
    protected boolean isChildActivity() {
        return true;
    }

    @Override
    protected Fragment createFragment() {
        return ArticleDetailFragment.newInstance(new Article(
                getIntent().getStringExtra(EXTRA_TITLE),
                getIntent().getStringExtra(EXTRA_TEXT)));
    }
}
