package com.dn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by 212391398 on 11/7/14.
 */
public class FileUtils
{
    public static boolean isBinaryFile( String path  ) throws IOException
    {
        FileInputStream in = new FileInputStream(new File(path));

        int size = Math.min(in.available(), 512);

        byte[] data = new byte[size];

        in.read(data);
        in.close();

        int ascii = 0;
        int other = 0;

        for( int i = 0; i < data.length; i++ )
        {
            byte b = data[i];

            if( b < 0x09 )
            {
                return true;
            }

            if( b==0x09 || b==0x0A || b==0x0C || b==0x0D || (b>=0x20) && (b<=0x7E) )
            {
                ascii++;
            }
            else
            {
                other++;
            }
        }

        if( other == 0 )
        {
            return false;
        }

        return  (other / (ascii + other) * 100 )  > 95;
    }
}
