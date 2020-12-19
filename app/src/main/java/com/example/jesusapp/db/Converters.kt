package com.example.jesusapp.db

import androidx.room.TypeConverter
import com.example.jesusapp.data.model.PrayerDetailModelsItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


public class Converters {

    @TypeConverter
    fun listToJson(value: ArrayList<PrayerDetailModelsItem>): String? {

        val gson = Gson()
        return gson.toJson(value)


    }

    @TypeConverter
    fun jsontoList(value: String): ArrayList<PrayerDetailModelsItem> {
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<PrayerDetailModelsItem?>?>() {}.type

        val l: ArrayList<PrayerDetailModelsItem> = gson.fromJson(value, type)
        return l
    }

    /* @TypeConverter
     fun ObjecttoString(value:DairyMessageItem):String?{

         val gson= Gson()
         return gson.toJson(value?.message)
     }*/


}