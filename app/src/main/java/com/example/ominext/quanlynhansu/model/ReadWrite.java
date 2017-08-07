package com.example.ominext.quanlynhansu.model;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Ominext on 8/7/2017.
 */

public class ReadWrite {
    private static String mAppDir;

    public static void init(File file,Context context) {
        file = new File(Environment.getExternalStorageDirectory(), context.getPackageName());
        if (!file.exists())
            file.mkdir();
        mAppDir = file.getAbsolutePath();
    }

    public static String readFromFile(File file) throws IOException {
        //tạo 1 file có đường dẫn
        file = new File(mAppDir, "Employee.txt");
        if (!file.exists())
            throw new FileNotFoundException();

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);

        String receiveString;
        StringBuilder data = new StringBuilder();

        while ((receiveString = bufferedReader.readLine()) != null) {
            data.append(receiveString);
        }
        isr.close();
        fis.close();
        return data.toString();
    }
}
