package com.mcindoe.articleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by mcindoe on 6/5/15.
 */
public class ArticleListFragment extends Fragment {

    private static final String TAG = "ArticleListFragment";

    /** Instances of this fragment should always be created via this method. */
    public static ArticleListFragment newInstance() {
        return new ArticleListFragment();
    }

    @InjectView(R.id.recycler_view) RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);
        ButterKnife.inject(this, view);

        // Sets up our RecyclerView with some random filler data.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new ArticleAdapter(Article.generateRandomArticles(30)));

        /**************************************************************************/
        /*** THIS IS WHERE MY ISSUE STARTS ****************************************/
        /**************************************************************************/

        // The following code does not add transitions to this Fragment's exit.

        // Create a transition set for our exit.
        TransitionSet exitTransitions = new TransitionSet();

        // Add a fade out and slide to the left transition to our RecyclerView.
        exitTransitions.addTransition(new TransitionSet()
                .addTransition(new Fade(Fade.OUT))
                .addTransition(new Slide(Gravity.LEFT))
                .addTarget(mRecyclerView));

        // Set the exit transition.
        setExitTransition(exitTransitions);

        /**************************************************************************/
        /*** THIS IS WHERE MY ISSUE ENDS ******************************************/
        /**************************************************************************/

        return view;
    }

    /** RecyclerView Adapter for our Article ViewHolder */
    class ArticleAdapter extends RecyclerView.Adapter<ArticleHolder> {

        private List<Article> mArticles;

        public ArticleAdapter(List<Article> galleryItems) {
            mArticles = galleryItems;
        }

        @Override
        public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_article, parent, false);
            return new ArticleHolder(view);
        }

        @Override
        public void onBindViewHolder(ArticleHolder holder, int position) {
            holder.bindArticle(mArticles.get(position));
        }

        @Override
        public int getItemCount() {
            return mArticles.size();
        }
    }

    /** ViewHolder for our Article object. */
    class ArticleHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.text_view_title) TextView mTitleTextView;

        private Article mArticle;

        public ArticleHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void bindArticle(Article article) {
            mArticle = article;
            mTitleTextView.setText(article.getTitle());
        }

        @OnClick(R.id.card_view_title)
        public void onCardViewClick(View view) {
            Intent intent = ArticleDetailActivity.newIntent(getActivity(), mArticle);
            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(),
                            new Pair<>(itemView, getString(R.string.transition_title_card)));
            ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
        }
    }
}
