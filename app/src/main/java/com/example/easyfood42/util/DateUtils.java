package com.example.easyfood42.util;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String format(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy à HH:mm");
        return dateFormat.format(date);
    }

    @Nullable
    public static Date getDateFromTimeMillis(long time) {
        return time != 0 ? new Date(time) : null;
    }

}
