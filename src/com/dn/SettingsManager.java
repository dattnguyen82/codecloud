package com.dn;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by 212391398 on 11/17/14.
 */
public class SettingsManager {
    private static SettingsManager ourInstance = new SettingsManager();

    public static SettingsManager getInstance() {
        return ourInstance;
    }

    private SettingsManager() {
    }

    private String getFileContents(String file)
    {
        String output = "";

        BufferedReader reader = null;

        try
        {
            FileReader fr = new FileReader(file);
            reader = new BufferedReader(fr);

            String line;
                while ((line = reader.readLine()) !=null)
                {
                    output += line;
                }

                if (reader != null)
                {
                    reader.close();
                }
        }
        catch (Exception e)
        {
            System.err.println(e.toString());
        }

        return output;
    }


    public Settings getSettingsFromFile(String file)
    {
        Settings settings = new Settings();

        String jsonContent = getFileContents(file);

        try
        {
            JSONObject rootObj = new JSONObject(jsonContent);

            settings.maximumCount = rootObj.getInt("Maximum");
            JSONArray extensions = rootObj.getJSONArray("extensions");

            for (int i=0; i<extensions.length(); i++)
            {
                JSONObject o = extensions.getJSONObject(i);
                String ext = o.getString("extension");
                settings.fileExtensions.add(ext);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return settings;
    }

}
