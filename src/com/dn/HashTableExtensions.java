package com.dn;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by 212391398 on 11/7/14.
 */
public class HashTableExtensions
{
    public static void AddOrIncrementKeyValue(Hashtable<String,Integer> hashTable, String key, Integer value)
    {
        Integer existingValue = hashTable.get(key);
        Integer newValue =  (existingValue == null) ? value : existingValue + value;
        hashTable.put(key,newValue);
    }

    public static void Merge(Hashtable<String, Integer> source, Hashtable<String, Integer> destination)
    {
        Enumeration keys = source.keys();

        while (keys.hasMoreElements())
        {
            String key = (String) keys.nextElement();
            Integer value = (Integer) source.get(key);
            HashTableExtensions.AddOrIncrementKeyValue(destination, key, value);
        }
    }
}
