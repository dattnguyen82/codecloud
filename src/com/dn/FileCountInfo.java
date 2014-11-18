package com.dn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by 212391398 on 11/6/14.
 */
public class FileCountInfo {

    private Hashtable<String, Integer> countHashTable = new Hashtable<String, Integer>();
    private int lines = 0;
    private String path;

    public FileCountInfo(String filePath) throws IOException
    {
        if (FileUtils.isBinaryFile(filePath))
        {
            throw new IOException("Cannot count binary files");
        }

        countHashTable = new Hashtable<String, Integer>();
        lines = 0;
        path = filePath;

        BufferedReader inputStream = null;
        try
        {
            inputStream = new BufferedReader(new FileReader(filePath));

            String l;
            while ((l = inputStream.readLine()) != null)
            {

                //l = l.replaceAll("[^A-Za-z0-9 ]", " ");
                l = l.replaceAll("[^A-Za-z]", " ");

                String[] tokens = l.split("\\s+");

               for (String t : tokens)
               {
                   if (t.isEmpty() )
                       continue;

                   HashTableExtensions.AddOrIncrementKeyValue(countHashTable, t, new Integer(1));
               }

               lines++;
            }
        }
        finally
        {
            if (inputStream != null)
            {
                inputStream.close();
            }
        }
    }

    public int getLineCount()
    {
        return lines;
    }

    public Hashtable<String,Integer> GetWordCount()
    {
        return countHashTable;
    }

    public String getPath() { return path; }
}
