package com.dn;

import wordcloud.WordFrequency;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Main
{
    private static Settings settings;
    public static void main(String[] args)
    {
	    String pathString = ".";

        if (args.length > 0)
        {
            pathString = args[0];
        }

        settings = SettingsManager.getInstance().getSettingsFromFile("/Users/212391398/source/sandbox/codecloud/settings.json");
        //settings.Print();

        AddFilesFromPath(pathString);

        Hashtable<String, Integer> ht = CountManager.getInstance().getTotalWordCount();
        ArrayList<WordFrequency> wordFrequencies = new ArrayList<WordFrequency>();

        Enumeration keys = ht.keys();

        int count = 0;
        while (keys.hasMoreElements() && count < settings.maximumCount)
        {
            String key = (String) keys.nextElement();
            Integer value = ht.get(key);

            wordFrequencies.add(new WordFrequency(key, value));
            count++;
        }

        String outputFile = String.format("codecloud.png");
        WordCloudGenerator.getInstance().GenerateWordCloud(outputFile, wordFrequencies);

        DictionaryExtensions.Print(CountManager.getInstance().getTotalWordCount());

        System.out.println(String.format("Total Lines: %d", CountManager.getInstance().getTotalLineCount()));
    }

    private static boolean isCodeFile(File f)
    {
        String ext = "";
        String fileName = f.getName();

        int index = fileName.lastIndexOf('.');

        if (index > -1) {
            ext = fileName.substring(index+1);
        }

        for (String e : settings.fileExtensions)
        {

            if (e.compareTo(ext) == 0)
            {
                return true;
            }
        }

        return false;
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

                    if (!isCodeFile(f))
                    {
                        continue;
                    }

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
