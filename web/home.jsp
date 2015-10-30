<%-- 
    Document   : home.jsp
    Created on : Oct 12, 2015, 2:20:18 PM
    Author     : saumil
--%>
<%response.setHeader("Access-Control-Allow-Origin", "*" );%>
<%response.addHeader("Access-Control-Allow-Origin", "*" );%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

    <head>
        <meta name="viewport" content="width=device-width" />
        <title>Eatatouille</title>
	<!--Custom CSS-->
	<link rel="stylesheet" href="css/custom.css">
	<!--Bootstrap CSS-->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<!-- Custom Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
    </head>
	
    <body>
        <script>
            window.fbAsyncInit = function() {
              FB.init({
                appId      : '949837685087310',
                xfbml      : true,
                version    : 'v2.4'
              });
            };

            (function(d, s, id){
               var js, fjs = d.getElementsByTagName(s)[0];
               if (d.getElementById(id)) {return;}
               js = d.createElement(s); js.id = id;
               js.src = "//connect.facebook.net/en_US/sdk.js";
               fjs.parentNode.insertBefore(js, fjs);
             }(document, 'script', 'facebook-jssdk'));
             function logout() {
              FB.getLoginStatus(function(response) {
                  if (response && response.status === 'connected') {
                      FB.logout(function(response) {
                          document.location.href="http://localhost:8080/Eatatouille/UserDetails?task=logout";
                      });
                  }else{
                      document.location.href="http://localhost:8080/Eatatouille/UserDetails?task=logout";
                  }
              });
            }
        </script>
	
        <div class="col-md-12">
            <jsp:include page="header.jsp" />
        </div>        
        <br /><br />
        <div class="col-md-12">
            <fieldset>
                <legend><h2>All Recipes</h2></legend>
            </fieldset>
            <div class="col-md-12">
                <div class="col-md-9">
                    <input autocomplete='off' onchange="byName();" onkeyup="this.onchange();" onpaste="this.onchange();" oninput="this.onchange();"  type="text" class="form-control" name="queryTags" id="queryTags" placeholder="Enter name of the dish.." />
                </div>
                <div class="col-md-3">
                    <button onclick="toggleAdvance();" class="btn btn-default">Advance Search</button>
                </div>
            </div>
            <div class="col-md-12" id="advanceZone">
                <p>&nbsp;</p>
                <form action="Recipe" method="get" width="70%" id="advanceSearchForm">
                    <div class="col-md-12">
                        <div class="col-md-12">
                            <input type="text" class="form-control" placeholder="Name of the dish/recipe" id="advName" />
                        </div>
                    </div>
                    <p>&nbsp;</p>
                    <div class="col-md-12">
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="advCusine" placeholder="Cusine(Eg. Indian, Iranian, Mexican etc)" />
                        </div>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="advDiet" placeholder="Type(Eg. Vegan, Paleo etc)" />
                        </div>
                    </div>
                    <div class="col-md-12">
                        <p>&nbsp;</p>
                    </div>
                    <div class="col-md-12">
                        <div class="col-md-5">
                            <div class="col-md-4">
                                <label>Prepared within: </label>
                            </div>
                            <div class="col-md-6">
                                <input type="number" id="advTime" class="form-control" placeholder="Minutes" />
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="col-md-4">
                                <label>Calories within: </label>
                            </div>
                            <div class="col-md-6">
                                <input type="number" id="advCalories" class="form-control" placeholder="Calories" />
                            </div>
                        </div>
                    </div>
                    <p>&nbsp;</p>
                    <center>
                        <input type="button" class="btn btn-lg btn-success" value="Search" onclick="advanceSearch()" />
                        <input type="button" class="btn btn-lg btn-warning" value="Cancel" onclick="toggleAdvance()" />
                    </center>
                </form>
            </div>
            <p>&nbsp;</p>
            <div id="recipeList">
                <c:forEach var="i" begin="0" end="${recipeList.size()-1}">
                    <a href="Recipe?task=single&recipeId=${recipeList.get(i).getRecipeId()}">
                        <div class="col-md-4">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <img src="${recipeList.get(i).getImage()}" class="img-responsive" />
                                    <br />
                                    <h3><b>${recipeList.get(i).getName()}</b></h3>
                                    <h3><b>Time: </b>${recipeList.get(i).getTime()}</h3>
                                    <h3><b>Calories: </b>${recipeList.get(i).getCalories()}</h3>
                                    <h3><b>Cuisine: </b>${recipeList.get(i).getCusine()}</h3>
                                    <h3><b>Type: </b>${recipeList.get(i).getDiet()}</h3>
                                </div>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
        
	<!--jQuery-->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<!--Bootstrap CSS-->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <!--Custom script-->
        <script>
            
            $(document).ready(function(){
                $("#advanceZone").hide();
                $.getJSON('https://api.edamam.com/search?q=chicken&app_id=24572922&app_key=c82a86336f7a415a59569fff1b6b3ee7',
                    function (data) {
                        console.log(JSON.stringify(data));
                    }
                );
            });
            
            $('#addRecipeForm').submit(function(e){
                e.preventDefault();
                //do some verification
                $.ajax({
                  url: 'Recipe',
                  data: $(this).serialize(),
                  success: function(data){
                      $("#addRecipe").modal("hide");
                      $("#addRecipeImage").modal("show");
                  }
                });
            });
            
            $("#ingredientsType").on('keypress',function(e) {
                if(e.keyCode == 44) {
                    e.preventDefault();
                    var fun = "removeIngredient('"+$("#ingredientsType").val()+"',this)";
                    var tag = '<button type="button" onclick="'+fun+';" class="btn btn-sm btn-default">'+$("#ingredientsType").val()+'&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-remove" style="color:red"></span></button>&nbsp;&nbsp;&nbsp;';
                    $("#ingredients").val($("#ingredients").val()+","+$("#ingredientsType").val());
                    $("#tags").html($("#tags").html()+tag);
                    $("#ingredientsType").val("");
                }
            });
            
            function advanceSearch(){
                console.log($("#advName").val()+$("#advCusine").val()+$("#advDiet").val()+$("#advTime").val()+$("#advCalories").val());
                $.get('Recipe?task=advanceSearch&name='+$("#advName").val()+'&cusine='+$("#advCusine").val()+'&diet='+$("#advDiet").val()+'&time='+$("#advTime").val()+'&calories='+$("#advCalories").val(),function(responseJson) {
                    if(responseJson!=null){
                        console.log("some");
                        var parent = $("#recipeList");
                        var child = "";
                        $.each(responseJson, function(key,value) {
                            child = child + '<a href="Recipe?task=single&recipeId='+value['recipeId']+'">\n\
                                            <div class="col-md-4"><div class="panel panel-default">\n\
                                            <div class="panel-body">\n\
                                            <img src="'+value['image']+'" class="img-responsive" />\n\
                                            <br /><h3><b>'+value['name']+'</b></h3>\n\
                                            <h3><b>Time: </b>'+value['time']+'</h3>\n\
                                            <h3><b>Calories: </b>'+value['calories']+'</h3>\n\
                                            <h3><b>Cuisine: </b>'+value['cusine']+'</h3>\n\
                                            <h3><b>Type: </b>'+value['diet']+'</h3>\n\
                                            </div></div></div></a>';
                            parent.html(child);
                        });
                    }else{
                        console.log("null");
                    }
                });
            }
            
            function toggleAdvance(){
                $("#advanceZone").slideToggle();
                displayAll();
                $("#advanceSearchForm").trigger("reset");
            }
            
            function removeIngredient(ing,e){
                var arr = $("#ingredients").val().split(",");
                var final = "";
                for(var i=0;i<arr.length;i++){
                    if(arr[i]!==ing){
                        final += arr[i]+",";
                    }else{
                        ing = "";
                    }
                }
                $("#ingredients").val(final);
                e.remove();
            }
            
            function byName(){
                var recipeName = $("#queryTags").val();
                
                if(recipeName.length > 0){
                    $.get('Recipe?task=byName&recipeName='+recipeName,function(responseJson) {
                        if(responseJson!=null){
                            var parent = $("#recipeList");
                            var child = "";
                            $.each(responseJson, function(key,value) {
                                child = child + '<a href="Recipe?task=single&recipeId='+value['recipeId']+'">\n\
                                            <div class="col-md-4"><div class="panel panel-default">\n\
                                            <div class="panel-body">\n\
                                            <img src="'+value['image']+'" class="img-responsive" />\n\
                                            <br /><h3><b>'+value['name']+'</b></h3>\n\
                                            <h3><b>Time: </b>'+value['time']+'</h3>\n\
                                            <h3><b>Calories: </b>'+value['calories']+'</h3>\n\
                                            <h3><b>Cuisine: </b>'+value['cusine']+'</h3>\n\
                                            <h3><b>Type: </b>'+value['diet']+'</h3>\n\
                                            </div></div></div></a>';
                                parent.html(child);
                            });
                        }
                    });
                }else{
                    displayAll();
                }
            }
            
            function displayAll(){
                $.get('Recipe?task=allRecipes',function(responseJson) {
                        if(responseJson!=null){
                            var parent = $("#recipeList");
                            var child = "";
                            $.each(responseJson, function(key,value) {
                                child = child + '<a href="Recipe?task=single&recipeId='+value['recipeId']+'">\n\
                                            <div class="col-md-4"><div class="panel panel-default">\n\
                                            <div class="panel-body">\n\
                                            <img src="'+value['image']+'" class="img-responsive" />\n\
                                            <br /><h3><b>'+value['name']+'</b></h3>\n\
                                            <h3><b>Time: </b>'+value['time']+'</h3>\n\
                                            <h3><b>Calories: </b>'+value['calories']+'</h3>\n\
                                            <h3><b>Cuisine: </b>'+value['cusine']+'</h3>\n\
                                            <h3><b>Type: </b>'+value['diet']+'</h3>\n\
                                            </div></div></div></a>';
                                parent.html(child);
                            });
                        }
                    });
            }
            
        </script>
    </body>

</html>