package com.dn;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by 212391398 on 11/7/14.
 */
public class CountManager
{
    private ArrayList<FileCountInfo> fileInfoList = new ArrayList<FileCountInfo>();
    private Hashtable<String, Integer> totalWordCount = new Hashtable<String, Integer>();
    private int totalLineCount = 0;

    private static CountManager ourInstance = new CountManager();

    public static CountManager getInstance() {
        return ourInstance;
    }

    private CountManager() {
    }

    public void insert(FileCountInfo fileCountInfo)
    {
        fileInfoList.add(fileCountInfo);
        totalLineCount += fileCountInfo.getLineCount();
        HashTableExtensions.Merge(fileCountInfo.GetWordCount(), totalWordCount);
    }

    public int getTotalLineCount()
    {
        return totalLineCount;
    }

    public Hashtable<String, Integer> getTotalWordCount()
    {
        return totalWordCount;
    }

    public void print()
    {
        for (FileCountInfo f : fileInfoList)
        {
            System.out.println(f.getPath());
        }
    }



}
