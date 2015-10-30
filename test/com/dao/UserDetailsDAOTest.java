/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.UserDetailsBean;
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
public class UserDetailsDAOTest {
    
    public UserDetailsDAOTest() {
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
     * Test of facebookLogin method, of class UserDetailsDAO.
     */
    @Test
    public void testFacebookLogin() {
        System.out.println("facebookLogin");
        UserDetailsBean userDetailsBean = null;
        UserDetailsDAO instance = new UserDetailsDAO();
        UserDetailsBean expResult = null;
        UserDetailsBean result = instance.facebookLogin(userDetailsBean);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of signup method, of class UserDetailsDAO.
     */
    @Test
    public void testSignup() {
        System.out.println("signup");
        UserDetailsBean userDetailsBean = null;
        UserDetailsDAO instance = new UserDetailsDAO();
        UserDetailsBean expResult = null;
        UserDetailsBean result = instance.signup(userDetailsBean);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkEmail method, of class UserDetailsDAO.
     */
    @Test
    public void testCheckEmail() {
        System.out.println("checkEmail");
        String email = "";
        UserDetailsDAO instance = new UserDetailsDAO();
        boolean expResult = false;
        boolean result = instance.checkEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class UserDetailsDAO.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String email = "";
        UserDetailsDAO instance = new UserDetailsDAO();
        String expResult = "";
        String result = instance.getPassword(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserDetails method, of class UserDetailsDAO.
     */
    @Test
    public void testGetUserDetails() {
        System.out.println("getUserDetails");
        String email = "";
        UserDetailsDAO instance = new UserDetailsDAO();
        UserDetailsBean expResult = null;
        UserDetailsBean result = instance.getUserDetails(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
