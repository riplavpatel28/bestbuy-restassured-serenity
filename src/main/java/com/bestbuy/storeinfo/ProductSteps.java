package com.bestbuy.storeinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ProductSteps {
    @Step("Creating product with name : {0},type:  {1}, price:  {2},upc:  {3},shipping: {4}, description:  {5},manufacture: {6},model:  {7},url: {8},image:  {9}" )
    public ValidatableResponse createProductWithName(String name,String type,int price,int upc,int  shipping,String description,String manufacturer,String model,String url,String image) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .post()
                .then();
    }

        @Step("Gettiing the Product information with name:  {0}")
           public HashMap<String,Object> getProductInfoByName(String name) {
            String p1 ="data.findAll{it.name ='";
            String p2 ="'}.get(0)";
             return SerenityRest.given()
                     .when()
                     .get()
                     .then().statusCode(200)
                     .extract()
                     .path(p1 + name +p2);
        }

    @Step("Updating Product information with ProductID: {0}, name : {1}, type: {2}, price: {3}, shipping: {4}, upc: {5},description: {6}, manufacturer: {7},model: {8},url: {9},image: {10}")
    public ValidatableResponse updateProduct(int productId, String name, String type, int price, int shipping, int  upc, String description, String manufacturer, String model, String url, String image) {
        ProductPojo productPojo = ProductPojo.getProductPojo(name, type, price, shipping, upc, description, manufacturer, model, url, image);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("productID", productId)
                .body(productPojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();
    }
    @Step("Updating Product partially with ProductID: {0}, name : {1}, type: {2}, price: {3}")
    public ValidatableResponse updateProductPartially(int productId, String name, String type, int price, Integer shipping, String upc, String description, String manufacturer, String model, String url, String image, Object o) {
        ProductPojo productsPojo = new ProductPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("productID", productId)
                .body(productsPojo)
                .when()
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();
    }
    @Step("Deleting Product information with ProductID: {0}")
    public ValidatableResponse deleteProduct(int productId) {
        return SerenityRest
                .given()
                .pathParam("productID", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Getting Product information with ProductID: {0}")
    public ValidatableResponse getProductById(int productId) {
        return SerenityRest
                .given()
                .pathParam("productID", productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }






}




























