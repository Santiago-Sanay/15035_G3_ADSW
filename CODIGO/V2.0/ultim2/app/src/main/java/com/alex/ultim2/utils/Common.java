package com.alex.ultim2.utils;

import com.alex.ultim2.models.GoogleSheetsResponse;
import com.alex.ultim2.models.IGoogleSheets;
import com.alex.ultim2.models.People;

public class Common {


    public static String BASE_URL = "https://script.google.com/macros/s/AKfycbyX2ImoQdEk0w3NTBPI--LXl4jZa-nVuPcswU3BrdJwZ7WMNRGXvRUSmgSfRIWlcwNy4A/";

    public static String BASE_URL_PASS="https://script.google.com/macros/s/AKfycbzD8-DcK2yeUTHiGJynDH637g80CagcREM6vlcKLM7vm3h8w1XALWBh_a20TVs9yRe_/";

    public static String BASE_URL1="https://script.google.com/macros/s/AKfycbzfgWULqpxxatY7LFL2FEYZNZqEL_Lvg3QHpok-DL5qKdjDOd5AFs3BGsWFgZSIh8wONg/";

    public static String BASE_URL_NOTIFICATION="https://script.google.com/macros/s/AKfycbzCmGxnkNfiDkKTNvajmeGw5ozKCBppzGflVpLI-VxxOYSlkrf6xr1eUR1qWRm8Sug6_A/";

    public static String GOOGLE_SHEET_ID = "1mn4L3tBzuph22MazrgICs7EpTz8TkkObR2gT-vR0yvs";
    public static String SHEET_NAME = "personas";

    private static String username="";
    private static String password="";
    private static double lat=0;
    private static double log=0;
    private static People person;
    public static double getLat(){
        return lat;
    }
    public static double getLog(){
        return log;
    }
    public  static void setLat(double value) {
        lat = value;
    }
    public  static void setLog(double value) {
        log = value;
    }
    public static void setPerson(People newPerson){
        person=newPerson;
    }
    public static People getPerson(){
        return person;
    };
    public static String getUsername() {
        return username;
    }
    public static String getPassword() {
        return password;
    }


    public static IGoogleSheets iGSGetMethodClient(String baseUrl) {
        return GoogleSheetsResponse.getClientGetMethod(baseUrl)
                .create(IGoogleSheets.class);
    }
    public static IGoogleSheets iGSGetMethodClient1(String baseUrl) {
        return GoogleSheetsResponse.getClientGetMethod1(baseUrl)
                .create(IGoogleSheets.class);
    }
    public static IGoogleSheets iGSGetMethodClient3(String baseUrl) {
        return GoogleSheetsResponse.getClientGetMethod3(baseUrl)
                .create(IGoogleSheets.class);
    }
    public static IGoogleSheets iGSGetMethodClient2(String baseUrl) {
        return GoogleSheetsResponse.getClientGetMethod2(baseUrl)
                .create(IGoogleSheets.class);
    }

    public  static void setUsername(String user) {
        username = user;
    }
    public  static void setPassword(String pass) {
        password = pass;
    }
}
