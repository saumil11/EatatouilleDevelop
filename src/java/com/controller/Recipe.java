/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.bean.RecipeBean;
import com.bean.UserDetailsBean;
import com.dao.RecipeDAO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author saumil
 */
public class Recipe extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession();
            
            RecipeBean recipeBean = new RecipeBean();
            RecipeDAO recipeDAO = new RecipeDAO();
            
            ArrayList recipeDetails = new ArrayList();
            Gson gson = new Gson();
            JsonElement element;
            JsonArray jsonArray;
            
            String name = request.getParameter("name");
            String meal[] = request.getParameterValues("meal");
            String diet = request.getParameter("diet");
            String cusine = request.getParameter("cusine");
            String people = request.getParameter("people");
            String calories = request.getParameter("calories");
            String time = request.getParameter("time");
            String steps = request.getParameter("steps");
            UserDetailsBean userDetailsBean = (UserDetailsBean)session.getAttribute("userDetails");
            String userId = userDetailsBean.getUserId();
            
            String task = request.getParameter("task");
            System.out.println(calories);
            System.out.println(name);
            System.out.println(cusine);
            System.out.println(diet);
            System.out.println(time);
            switch(task){
                
                case "add":
                    String ingredients[] = request.getParameter("ingredients").split(",");
                    recipeBean.setCalories(calories);
                    recipeBean.setCusine(cusine);
                    recipeBean.setDiet(diet);
                    recipeBean.setIngredients(ingredients);
                    recipeBean.setMeal(meal);
                    recipeBean.setName(name);
                    recipeBean.setPeople(people);
                    recipeBean.setSteps(steps);
                    recipeBean.setTime(time);
                    recipeBean.setUserId(userId);
                    
                    String recipeId = recipeDAO.addRecipeDetails(recipeBean);
                    session.setAttribute("recipeId", recipeId);
                    
                    //add type of meal information
                    recipeDAO.addMealInfo(meal, recipeId);
                    
                    //add ingredients
                    recipeDAO.addIngredients(ingredients, recipeId);
                    break;
                    
                case "single":
                    
                    recipeId = request.getParameter("recipeId");
                    recipeDetails = recipeDAO.getSingleRecipe(recipeId);
                    request.setAttribute("recipeDetails", recipeDetails);
                    request.getRequestDispatcher("singleRecipe.jsp").forward(request, response);
                    
                    break;
                
                case "allRecipes":
                    recipeDetails = recipeDAO.getAllRecipes();
                    
                    element = gson.toJsonTree(recipeDetails, new TypeToken<List<RecipeBean>>() {}.getType());
                    jsonArray = element.getAsJsonArray();
                    response.setContentType("application/json");
                    response.getWriter().print(jsonArray);
                    break;
                    
                case "byName":
                    name = request.getParameter("recipeName");
                    recipeDetails = recipeDAO.getRecipeByName(name);
                    
                    element = gson.toJsonTree(recipeDetails, new TypeToken<List<RecipeBean>>() {}.getType());

                    jsonArray = element.getAsJsonArray();
                    response.setContentType("application/json");
                    response.getWriter().print(jsonArray);
                    break;
                    
                case "advanceSearch":
                    if(cusine == null){
                        cusine = "";
                    }
                    if(diet == null){
                        diet = "";
                    }
                    if(name == null){
                        name = "";
                    }
                    if(calories == null){
                        calories = "1000000";
                    }
                    if(time == null){
                        time = "1000000";
                    }
                    recipeBean.setCalories(calories);
                    recipeBean.setCusine(cusine);
                    recipeBean.setDiet(diet);
                    recipeBean.setName(name);
                    recipeBean.setTime(time);
                    recipeDetails = recipeDAO.advanceSearch(recipeBean);
                    
                    element = gson.toJsonTree(recipeDetails, new TypeToken<List<RecipeBean>>() {}.getType());
                    jsonArray = element.getAsJsonArray();
                    response.setContentType("application/json");
                    response.getWriter().print(jsonArray);
                    break;
            }
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
