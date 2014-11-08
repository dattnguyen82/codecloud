package com.dn;

import wordcloud.WordFrequency;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Main
{

    public static void main(String[] args)
    {
	    String pathString = ".";

        if (args.length > 0)
        {
            pathString = args[0];
        }

        AddFilesFromPath(pathString);

        Hashtable<String, Integer> ht = CountManager.getInstance().getTotalWordCount();
        ArrayList<WordFrequency> wordFrequencies = new ArrayList<WordFrequency>();

        Enumeration keys = ht.keys();

        while (keys.hasMoreElements())
        {
            String key = (String) keys.nextElement();
            Integer value = ht.get(key);

            wordFrequencies.add(new WordFrequency(key, value));
        }

        String outputFile = String.format("codecloud.png");
        WordCloudGenerator.getInstance().GenerateWordCloud(outputFile, wordFrequencies);
    }

    private static void AddFilesFromPath(String path)
    {
        File file = new File(path);

        File[] list = file.listFiles();
        if(list!=null)
        {
            for (File f : list)
            {
                if (f.isDirectory())
                {
                    AddFilesFromPath(f.getPath());
                }
                else
                {
                    try
                    {
                        FileCountInfo fi = new FileCountInfo(f.getPath());
                        CountManager.getInstance().insert(fi);
                    }
                    catch (Exception e)
                    {

                    }
                }
            }
        }
    }
}
