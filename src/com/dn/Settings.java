package com.dn;

import java.util.ArrayList;

/**
 * Created by 212391398 on 11/17/14.
 */
public class Settings {
    public int maximumCount = 0;
    public ArrayList<String> fileExtensions = new ArrayList<String>();

    public void Print()
    {
        System.out.println(String.format("MaximumCount: %d", maximumCount));
        System.out.println("File Extentions:");
        for (int i=0; i<fileExtensions.size(); i++)
        {
            System.out.println(String.format("  %s", fileExtensions.get(i)));
        }
    }
}
