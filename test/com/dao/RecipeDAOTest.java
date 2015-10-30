/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.RecipeBean;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Saumil
 */
public class RecipeDAOTest {
    
    public RecipeDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of advanceSearch method, of class RecipeDAO.
     */
    @Test
    public void testAdvanceSearch() {
        System.out.println("advanceSearch");
        RecipeBean recipeBean = null;
        RecipeDAO instance = new RecipeDAO();
        ArrayList expResult = null;
        ArrayList result = instance.advanceSearch(recipeBean);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecipeByName method, of class RecipeDAO.
     */
    @Test
    public void testGetRecipeByName() {
        System.out.println("getRecipeByName");
        String recipeName = "";
        RecipeDAO instance = new RecipeDAO();
        ArrayList expResult = null;
        ArrayList result = instance.getRecipeByName(recipeName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllRecipes method, of class RecipeDAO.
     */
    @Test
    public void testGetAllRecipes() {
        System.out.println("getAllRecipes");
        RecipeDAO instance = new RecipeDAO();
        ArrayList expResult = null;
        ArrayList result = instance.getAllRecipes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSingleRecipe method, of class RecipeDAO.
     */
    @Test
    public void testGetSingleRecipe() {
        System.out.println("getSingleRecipe");
        String rcpId = "";
        RecipeDAO instance = new RecipeDAO();
        ArrayList expResult = null;
        ArrayList result = instance.getSingleRecipe(rcpId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRecipeDetails method, of class RecipeDAO.
     */
    @Test
    public void testAddRecipeDetails() {
        System.out.println("addRecipeDetails");
        RecipeBean recipeBean = null;
        RecipeDAO instance = new RecipeDAO();
        String expResult = "";
        String result = instance.addRecipeDetails(recipeBean);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMealInfo method, of class RecipeDAO.
     */
    @Test
    public void testAddMealInfo() {
        System.out.println("addMealInfo");
        String[] meal = null;
        String recipeId = "";
        RecipeDAO instance = new RecipeDAO();
        instance.addMealInfo(meal, recipeId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addIngredients method, of class RecipeDAO.
     */
    @Test
    public void testAddIngredients() {
        System.out.println("addIngredients");
        String[] ingredients = null;
        String recipeId = "";
        RecipeDAO instance = new RecipeDAO();
        instance.addIngredients(ingredients, recipeId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addImage method, of class RecipeDAO.
     */
    @Test
    public void testAddImage() {
        System.out.println("addImage");
        String recipeId = "";
        String image = "";
        RecipeDAO instance = new RecipeDAO();
        instance.addImage(recipeId, image);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
