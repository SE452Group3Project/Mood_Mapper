<%-- 
    Document   : signup
    Created on : Oct 10, 2015, 5:49:23 PM
    Author     : faithfulokoye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css" media="screen" charset="utf-8" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="js/app.js"></script>
        <title>Sign-Up Form</title>
    </head>
    <body>
        <div class="form-wrap">
            <div class="tabs">
                <h3 class="signup-tab"><a class="active" href="#signup-tab-content">Sign Up</a></h3>
                <h3 class="login-tab"><a href="#login-tab-content">Login</a></h3>
            </div>

            <div class="tabs-content">
                <div id="signup-tab-content" class="active">
                    <form class="signup-form" action="./signup" method="post">
                        <input type="email" name="email" class="input" id="user_email" autocomplete="off" placeholder="Email">
                        <input type="text" name="username" class="input" id="user_name" autocomplete="off" placeholder="Username">
                        <input type="password" name="password" class="input" id="user_pass" autocomplete="off" placeholder="Password">
                        <input type="submit" class="button" value="Sign Up">
                    </form>
                    <!--.signup form-->
                    <div class="help-text">
                        <p>By signing up, you agree to our</p>
                        <p><a href="#">Terms of service</a></p>
                    </div>
                </div>
                <!--.signup-tab-content-->

                <div id="login-tab-content">
                    <form class="login-form" action="./login" method="post">
                        <input type="text" name="username" class="input" id="user_login" autocomplete="off" placeholder="Email or Username">
                        <input type="password" name="password" class="input" id="user_pass" autocomplete="off" placeholder="Password">
                        <input type="checkbox" name="remember-me" class="checkbox" id="remember_me">
                        <label for="remember_me">Remember me</label>
                        <input type="submit" class="button" value="Login">
                    </form>
                    <!--.login-form-->
                    <div class="help-text">
                        <p><a href="#">Forget your password?</a></p>
                    </div><!--.help-text-->
                </div>
                <!--.login-tab-content-->
            </div>
            <!--.tabs-content-->
        </div>
        <!--.form-wrap-->
    </body>
</html>
