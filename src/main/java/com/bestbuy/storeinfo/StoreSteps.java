package com.bestbuy.storeinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoreSteps {
    @Step("Creating Store with name : name  :{0}, type : {1}, address : {2}, address2 : {3}, city : {4}, state : {5}, zip : {6}, lat : {7}, lng : {8}, hours : {9}")
    public ValidatableResponse createStore(String name, String type, String address, String address2, String city, String state, String zip, int lat, int lng, String hours) {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .post()
                .then();


    }

    @Step("Getting the Store information with Name : {0}")
    public HashMap<String, Object> getStoreInfoByName(String name) {
        String s1 = "data.findAll{it.Name =='";
        String s2 = "'.get(0)";
        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STORE)
                .then().log().all().statusCode(200)
                .extract()
                .path((s1 + name + s2));


    }

    @Step("Update store information with storeID : {0}, name: {1},type :{2},address :{3},address2  :{4},city  :{5},state  :{6}, zip :{7} ,lat :{8}, lng  :{9}, hours :{10} ")
    public ValidatableResponse updateStore(int storeId, String name, String type, String address, String address2, String city, String state, String zip, int lat, int lng, String hours) {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);


        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("storesID", storeId)
                .body(storePojo)
                .when()
                .put(EndPoints.UPDATE_STORE_BY_ID)
                .then();

    }

    @Step("Deleting store information with storeId : {0}")
    public ValidatableResponse deleteStore(int storeId) {
        return SerenityRest.given()
                .pathParam("storeId", storeId)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then();
    }

    @Step("Getting Store information with ProductID: {0}")
    public ValidatableResponse getStoreById(int storeId) {
        return SerenityRest
                .given()
                .pathParam("storeId", storeId)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then();

    }
}




