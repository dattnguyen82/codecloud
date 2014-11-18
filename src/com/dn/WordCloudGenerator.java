package com.dn;

import wordcloud.CollisionMode;
import wordcloud.WordCloud;
import wordcloud.WordFrequency;
import wordcloud.bg.RectangleBackground;
import wordcloud.font.scale.LinearFontScalar;
import wordcloud.palette.ColorPalette;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 212391398 on 11/7/14.
 */
public class WordCloudGenerator {

    private static final int width = 800;
    private static final int height = 600;


    private static WordCloudGenerator ourInstance = new WordCloudGenerator();

    public static WordCloudGenerator getInstance() {
        return ourInstance;
    }

    private WordCloudGenerator() {
    }

    public void GenerateWordCloud(String fileName,  ArrayList<WordFrequency> wordFrequencies)
    {
        WordCloud wordCloud = new WordCloud(width, height, CollisionMode.RECTANGLE);

        wordCloud.setPadding(0);
        wordCloud.setBackground(new RectangleBackground(width, height));
        wordCloud.setBackgroundColor(Color.white);
        wordCloud.setColorPalette(setColors(25));
        wordCloud.setFontScalar(new LinearFontScalar(8,80));
        wordCloud.build(wordFrequencies);
        wordCloud.writeToFile(fileName);
    }

    private ColorPalette setColors(int n)
    {
        Random random = new Random();
        final Color[] colors = new Color[n];
        for(int i = 0; i < colors.length; i++)
        {
            colors[i] = new Color(random.nextInt(225), random.nextInt(225), random.nextInt(225));
        }
        return new ColorPalette(colors);
    }
}
