package org.example.flyhigh.constants;

import org.example.flyhigh.entity.Airport;
import org.example.flyhigh.entity.PlaneType;

import java.util.ArrayList;
import java.util.HashMap;

public class Constants {
    public static final String[] CITIES = {
            "Paris", "Rome", "Barcelona", "New York City", "Tokyo",
            "London", "Sydney", "Prague", "Kyoto", "Dubai",
            "Venice", "Amsterdam", "Bali", "Istanbul", "Florence",
            "Vienna", "Cairo", "Cape Town", "Rio de Janeiro", "San Francisco",
            "Berlin", "Seoul", "Singapore", "Bangkok", "Athens",
            "Edinburgh", "Dublin", "Jerusalem", "Havana", "Toronto",
            "Sydney", "Las Vegas", "New Orleans", "Marrakech", "Munich",
            "Reykjavik", "Helsinki", "Oslo", "Stockholm", "Copenhagen",
            "Auckland", "Vancouver", "Montreal", "Dubrovnik", "Krakow",
            "Budapest", "Prague", "Warsaw", "Zurich", "Beijing"
    };

    public static final String[] PLANE_TYPES = {
            "AIRBUS_A320", "AIRBUS_A380",
            "BOEING_737", "BOEING_777", "Embraer_E190"
    };
    public static final HashMap<String,Integer> PLANE_TYPE_ECONOMIC_CAPACITY = new HashMap<String, Integer>(){{put("AIRBUS_A320", 150); put("AIRBUS_A380", 300); put("BOEING_737", 150); put("BOEING_777", 300); put("Embraer_E190", 100);}};
    public static final HashMap<String,Integer> PLANE_TYPE_BUSINESS_CAPACITY = new HashMap<String, Integer>(){{put("AIRBUS_A320", 25); put("AIRBUS_A380", 50); put("BOEING_737", 20); put("BOEING_777", 50); put("Embraer_E190", 20);}};
    public static final HashMap<String,Integer> PLANE_TYPE_FIRST_CAPACITY = new HashMap<String, Integer>(){{put("AIRBUS_A320", 10); put("AIRBUS_A380", 20); put("BOEING_737", 10); put("BOEING_777", 20); put("Embraer_E190", 10);

    }};
}
