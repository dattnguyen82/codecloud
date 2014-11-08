package com.dn;

import java.util.Dictionary;
import java.util.Enumeration;

/**
 * Created by 212391398 on 11/6/14.
 */
public class DictionaryExtensions
{
    public static <K,V> void Print(Dictionary<K,V> dictionary)
    {
       K key;
       Enumeration e = dictionary.keys();
       while ( e.hasMoreElements() )
       {
           key = (K) e.nextElement();
           V val = dictionary.get(key);
           if (val != null)
           {
               System.out.println(String.format("%s:%s", key.toString(), val.toString()));
           }
       }
    }
}
