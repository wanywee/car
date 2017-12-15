package com.carTravelsky.globalSettings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConvert implements Converter<String, Date> {

	private final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final int leng_yyyyMMdd = 10;
    private final int leng_yyyyMMddHHmmss = 19;

    @Override
    public Date convert(String source) {
        if(source.length() == leng_yyyyMMdd) {
            try {
                return yyyyMMdd.parse(source);
            } catch (ParseException e) {
                e.printStackTrace();  

            }
        } else if(source.length() == leng_yyyyMMddHHmmss) {
            try {
                return yyyyMMddHHmmss.parse(source);
            } catch (ParseException e) {
                e.printStackTrace();  
            }
        }
        return null;
    }

}
