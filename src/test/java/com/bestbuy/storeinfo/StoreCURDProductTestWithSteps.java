package com.bestbuy.storeinfo;

import com.bestbuy.testbase.StoreTestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoreCURDProductTestWithSteps extends StoreTestBase {
    static String name = "Tesco";
    static String type = "Metro";
    static String address = "145 Wembley High Road";
    static String address2 = "wembley";
    static String city = "wembley";
    static String state = "London";
    static String zip = "380050";
    static int lat = 55;
    static int lng = 44;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeId;

    @Steps
    StoreSteps storeSteps;

    @Title("This will create a new store")
    @Test
    public void test001() {

        storeSteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours);
    }

    @Title("Getting store information with name :{0}")
    @Test
    public void test002() {
        HashMap<String, Object> storeMap = storeSteps.getStoreInfoByName(name);
        Assert.assertThat(storeMap, hasValue(name));
        storeId = (int) storeMap.get("id");
    }

    @Title("Update store information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        storeSteps.updateStore(storeId, name, type, address, address2, city, state, zip, lat,lng, hours).statusCode(200);
        HashMap<String, Object> storeMap = storeSteps.getStoreInfoByName(name);
        Assert.assertThat(storeMap, hasValue(name));

    }

    @Title("Delete the student and verify if the student is deleted!")
    @Test
    public void test004() {
       storeSteps.deleteStore(storeId).statusCode(204);
       storeSteps.getStoreById(storeId).statusCode(404);

    }
}