package me.whiteship.java8to11._00_java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://codechacha.com/ko/java-sort-map/
 * */
public class HashMapAlignmentTest {

    @Test
    void 리스트를_이용하여_key를_기준으로_정렬하는_방법() {
        Map<String, String> map = new HashMap<>();
        map.put("Nepal", "Kathmandu");
        map.put("United States", "Washington");
        map.put("India", "New Delhi");
        map.put("England", "London");
        map.put("Australia", "Canberra");

        List<String> keyList = new ArrayList<>(map.keySet());
        keyList.sort(String::compareTo);    //key 를 기준으로 오름차운으로 정렬 
        for (String key : keyList) {
            System.out.println("key : " + key + ", value : " + map.get(key));
        }
        /*
         * key : Australia, value : Canberra
         * key : England, value : London
         * key : India, value : New Delhi
         * key : Nepal, value : Kathmandu
         * key : United States, value : Washington
         * */
    }

    @Test
    void 리스트를_이용하여_value로_정렬하는_방법() {
        Map<String, String> map = new HashMap<>();
        map.put("Nepal", "Kathmandu");
        map.put("United States", "Washington");
        map.put("India", "New Delhi");
        map.put("England", "London");
        map.put("Australia", "Canberra");

        List<String> valueList = new ArrayList<>(map.values());
        valueList.sort(String::compareTo);
        for (String value : valueList) {
            System.out.println("Value : " + value);
        }

        /*
        Value : Canberra
        Value : Kathmandu
        Value : London
        Value : New Delhi
        Value : Washington
        * */
    }


}
