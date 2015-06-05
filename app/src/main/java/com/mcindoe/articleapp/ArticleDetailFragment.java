package com.mcindoe.articleapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mcindoe on 6/5/15.
 */
public class ArticleDetailFragment extends Fragment {

    private static final String TAG = "ArticleDetailFragment";

    private static final String ARG_TITLE = "title";
    private static final String ARG_TEXT = "text";

    /** Instances of this fragment should always be created via this method. */
    public static ArticleDetailFragment newInstance(Article article) {
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, article.getTitle());
        args.putString(ARG_TEXT, article.getText());

        ArticleDetailFragment fragment = new ArticleDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @InjectView(R.id.text_view_title) TextView mTitleTextView;
    @InjectView(R.id.text_view_text) TextView mTextTextView;
    @InjectView(R.id.card_view_text) CardView mTextCardView;

    private Article mArticle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Grab our Article out of our arguments.
        mArticle = new Article(
                getArguments().getString(ARG_TITLE),
                getArguments().getString(ARG_TEXT));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_detail, container, false);
        ButterKnife.inject(this, view);

        // Fill out our view's fields.
        mTitleTextView.setText(mArticle.getTitle());
        mTextTextView.setText(mArticle.getText());

        /**************************************************************************/
        /*** THIS IS WHERE MY ISSUE STARTS ****************************************/
        /**************************************************************************/

        // The following code does not add transitions to this Fragment's entrance.

        // Create a transition set for our entrance.
        TransitionSet enterTransitions = new TransitionSet();

        // Add a fade in and slide from bottom transition to our Article text's card view.
        enterTransitions.addTransition(new TransitionSet()
                .addTransition(new Fade(Fade.IN))
                .addTransition(new Slide(Gravity.BOTTOM))
                .addTarget(mTextCardView));

        // Set the enter transition.
        setEnterTransition(enterTransitions);

        /**************************************************************************/
        /*** THIS IS WHERE MY ISSUE ENDS ******************************************/
        /**************************************************************************/

        return view;
    }
}
