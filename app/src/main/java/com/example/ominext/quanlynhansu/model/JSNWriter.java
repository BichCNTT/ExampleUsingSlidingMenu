package com.example.ominext.quanlynhansu.model;


import android.util.JsonWriter;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ominext on 8/4/2017.
 */

public class JSNWriter {
    public static void writeJsonStream(Writer output, EmployeesData employess) throws IOException{
        JsonWriter jsonWriter= new JsonWriter(output);
        jsonWriter.beginObject();//begin root
        jsonWriter.name("id").value(employess.getmId());
        jsonWriter.name("name").value(employess.getmName());
        jsonWriter.name("sex").value(employess.getmSex());
        jsonWriter.name("birth").value(employess.getmDateOfBirth());
        jsonWriter.name("phone").value(employess.getmPhone());
        jsonWriter.endObject();
    }
}
