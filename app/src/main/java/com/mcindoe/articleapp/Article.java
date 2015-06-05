package com.mcindoe.articleapp;

import java.util.LinkedList;
import java.util.List;

import de.svenjacobs.loremipsum.LoremIpsum;

/**
 * Created by mcindoe on 6/4/15.
 */
public class Article {

    private static final LoremIpsum mLoremIpsum = new LoremIpsum();

    /** Helper method to create a bunch of random articles for dummy data. */
    public static List<Article> generateRandomArticles(int num) {
        List<Article> articles = new LinkedList<>();
        for (int i = 0; i < num; i++) {
            articles.add(new Article(generateRandomArticleTitle(), generateRandomArticleText()));
        }
        return articles;
    }

    private static String generateRandomArticleTitle() {
        switch ((int)Math.floor(Math.random()*7)) {
            case 0:
                return "Murder on Elm Street!";
            case 1:
                return "Lower Your Taxes";
            case 2:
                return "Swim with Sharks!";
            case 3:
                return "Spice Up Your Love Life";
            case 4:
                return "Pandas on the Loose!";
            case 5:
                return "Wanted: Android Developers";
            case 6:
                return "Google I/O: Big Success";
            default:
                return null;
        }
    }

    private static String generateRandomArticleText() {
        return mLoremIpsum.getParagraphs(8);
    }

    private String mTitle, mText;

    public Article(String title, String text) {
        mTitle = title;
        mText = text;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
