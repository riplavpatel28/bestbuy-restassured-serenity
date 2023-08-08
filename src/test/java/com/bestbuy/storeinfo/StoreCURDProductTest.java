package com.bestbuy.storeinfo;

import com.bestbuy.testbase.ProductTestBase;
import com.bestbuy.utils.TestUtils;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StoreCURDProductTest extends ProductTestBase {
    static String name = "Tesco" + TestUtils.getRandomValue();
    static String type = "Metro" ;
    static String address = "145 Wembley High Road";
    static String address2 = "wembley";
    static String city = "wembley";
    static String state = "London";
    static String zip = "380050";
    static String lat = "55.88888" ;
    static String ing = "-45.1111";
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static String createdAt ="2016-11-17T17:57:09.213Z";
    static String updatedAt ="2016-11-17T17:57:09.213Z";
    @Title("This will create new store")
     @Test
     public void test001(){
        List<String> serviceList =new ArrayList<>();
        serviceList.add("AppleShop");



    }











}
