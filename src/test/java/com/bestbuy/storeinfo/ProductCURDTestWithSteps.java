package com.bestbuy.storeinfo;

import com.bestbuy.testbase.ProductTestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductCURDTestWithSteps extends ProductTestBase {
    static String name = "Costa";
    static String type = "HotDrink" ;

    static int price = 6;
    static int upc = 0202;
    static int  shipping = 0;
    static String description = "serves with hot drink";
    static String manufactures = "Costa";
    static String model = "cf001111" ;
    static String url = "http://costa.com";
    static String image = "https://costa.com";
    static  int productID;

    @Steps
    ProductSteps productSteps;


    @Title("This will create a new product")
    @Test
    public void test001(){
        productSteps.createProductWithName(name,type,price,upc,shipping,description,manufactures,model,url,image).statusCode(201);
    }
    @Title("Getting the product information with name :{0}")
    @Test
    public void test002(){
        HashMap<String,Object> productMap =productSteps.getProductInfoByName(name);
        Assert.assertThat(productMap,hasValue(name));
        productID =(int) productMap.get("id");
    }
    @Title("Update store information and verify the updated information")
    @Test
    public void test003(){
        name =name + "_updated";
        productSteps.updateProduct(productID,name,type,price,shipping,upc,description,manufactures,model,url,image).statusCode(200);
        HashMap<String,Object> productMap = productSteps.getProductInfoByName(name);
        Assert.assertThat(productMap,hasValue(name));

    }
    @Title("Update product partially with productname ")
    @Test
    public void test004(){
        name =name + "_updated";
        productSteps.updateProduct(productID,name,type,price,shipping,upc,description,manufactures,model,url,image).statusCode(200);
        HashMap<String,Object> productMap = productSteps.getProductInfoByName(name);
        Assert.assertThat(productMap,hasValue(name));

    }
    @Title("Delete the product and verify if the product is deleted!")
    @Test
    public void test005() {
        productSteps.deleteProduct(productID).statusCode(204);
        productSteps.getProductById(productID).statusCode(404);


    }
    }
