package com.gaohanghang.springbootgephi;

import com.gaohanghang.springbootgephi.common.enums.Group;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static void main(String[] args) throws JSONException {
        try{
            List<String> foo = new ArrayList<String>();
            foo.add("1");
            foo.add("2");
            foo.add("3");
            System.out.println("values :: "+foo);
            Writer writer = new FileWriter("operatorList.json");
            Gson gson = new GsonBuilder().create();
            gson.toJson(foo, writer);
            writer.flush(); //flush data to file   <---
            writer.close(); //close write          <---
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println(Group.BUILDING.getName());
    }
}
