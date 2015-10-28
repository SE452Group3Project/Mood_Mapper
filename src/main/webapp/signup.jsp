<%-- 
    Document   : signup
    Created on : Oct 10, 2015, 5:49:23 PM
    Author     : faithfulokoye
--%>

<%@page import="com.moodmapper.entity.UserEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    String pageTitle = "Sign Up";

    String notice = ""; 
    String error = ""; 
    if (request.getSession().getAttribute("user") != null) {
        out.print("You are already logged in"); 
        response.sendRedirect("home.jsp");
    } else {
       if (session.getAttribute("notice") != null){
           notice = (String) session.getAttribute("notice");
       }
       if (session.getAttribute("error") != null){
           error = (String) session.getAttribute("error"); 
           notice = "";
       }
    }
    
%>
<!DOCTYPE html>

<jsp:include page="/WEB-INF/inc/opening_head.jsp" flush="true">
    <jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>
    <link rel="stylesheet" href="css/styles.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.14/angular.min.js"></script>
     <script src="js/app.js"></script>

    <jsp:include page="/WEB-INF/inc/closing_head.jsp" flush="true"/>
<body>
    <jsp:include page="/WEB-INF/inc/background.jsp"/>
    <jsp:include page="/WEB-INF/inc/navbarhome.jsp"/>
    
    <jsp:include page="/WEB-INF/inc/body_head.jsp" flush="true">
        <jsp:param name="error" value="<%=error%>" />
        <jsp:param name="notice" value="<%=notice%>" />
    </jsp:include>
    
   

    <div class="form-wrap" ng-app="loginOrSignUpForm">
        

        <div class="tabs">

            <h3 class="login-tab">
                <a class="active"href="#login-tab-content">Login</a>
            </h3>
            <h3 class="signup-tab">
                <a href="#signup-tab-content">Sign Up</a>
            </h3>
        </div><!--.tabs-->

        <div class="tabs-content">

            <div 
                id="login-tab-content" 
                class="active" 
                ng-controller="LoginController">
                <form 
                    name="form" 
                    ng-submit="form.$valid && submit()" class="login-form"  
                    method="post" novalidate>
                    <input 
                        type="text" 
                        ng-model="user.emailOrUsername" name="emailOrUsername" 
                        class="input" 
                        id="user_login" 
                        autocomplete="off" 
                        placeholder="Email or Username" required="">
                    <div 
                        class="error" 
                        ng-show="form.$submitted || form.emailOrUsername.$touched">
                        <span 
                            ng-show="form.emailOrUsername.$error.required">Username/Email is required
                        </span>
                    </div>
                    <input 
                        type="password" 
                        name="password" 
                        ng-model="user.password" 
                        class="input" 
                        id="user_password" 
                        autocomplete="off" 
                        placeholder="Password" required="">
                    <div 
                        class="error" 
                        ng-show="form.$submitted || form.password.$touched">
                        <span 
                            ng-show="form.password.$error.required">Password is required
                        </span>
                    </div>

                    <input 
                        type="checkbox" 
                        name="remember_me" 
                        class="checkbox" 
                        id="remember_me">
                    <label for="remember_me">Remember me</label>

                    <input 
                        type="submit" 
                        class="button" 
                        value="Login">
                </form><!--.login-form-->

                <div class="help-text">
                    <p>
                        <a href="#">Forgot your password?</a>
                    </p>
                </div>
                <!--.help-text-->
            </div>
            <!--.login-tab-content-->
            <div 
                id="signup-tab-content" 
                ng-controller="SignUpController">
                <form 
                    name="form" 
                    class="signup-form" 
                    ng-submit="form.$valid && submit()" method="post" 
                    novalidate>
                    <input 
                        type="email" 
                        ng-model="user.email" 
                        email-available
                        name="email" 
                        class="input" 
                        id="user_email" 
                        ng-model-options="{ updateOn: 'blur' }" 
                        autocomplete="off" 
                        placeholder="Email" 
                        required="">
                    <div 
                        class="error" 
                        ng-show="form.$submitted || form.email.$touched">
                        <span 
                            ng-show="form.email.$error.required">Email is required
                        </span>
                        <span 
                            ng-show="form.email.$error.email">This is not a valid email.
                        </span>
                        
                    </div>
                    <div class="error" 
                         ng-show="form.email.$touched">

                           <span ng-show="form.email.$pending">Checking if this email is available...</span>
                        <span
                            ng-show="form.email.$error.emailExists">This email is already being used!</span>
                    </div>

                    <input 
                        type="text" 
                        ng-model="user.username" 
                        username-available 
                        ng-model-options="{ updateOn: 'blur' }"
                        ng-pattern="/[a-z0-9_]+/i"
                        name="username" 
                        class="input" 
                        id="user_name" 
                        autocomplete="off" 
                        placeholder="Username" 
                        required="">
                    <div 
                        class="error">
                        <div 
                            ng-show="form.$submitted || form.username.$touched">
                            <span 
                                ng-show="form.username.$error.required">Username is required</span>
                            <span 
                                ng-show="form.username.$error.pattern">Username must start with alphanumeric character and between 3 - 16 characters</span>
                        </div>
                        <div ng-show="form.username.$touched">

                           <span ng-show="form.username.$pending">Checking if this name is available...</span>
                        <span
                            ng-show="form.username.$error.usernameExists">This username is already taken!</span>
                    </div>
                </div>


                    <input 
                        type="password" 
                        ng-model="user.password" 
                        ng-pattern="/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{6,13}$/" 
                        name="password" 
                        class="input" 
                        id="user_pass" 
                        autocomplete="off" 
                        placeholder="Password" 
                        required="">
                    <div 
                        class="error" 
                        ng-show="form.$submitted || form.password.$touched">
                        <span 
                            ng-show="form.password.$error.required">Password is required
                        </span>
                        <span 
                            ng-show="form.password.$error.pattern"> Password must have one lower case, one upper case letter, one digit, 6-13 length, and no spaces
                        </span>
                    </div>
                    
                    <input 
                        type="password" 
                        ng-model="user.confirmPassword" 
                        name="confirm_password" 
                        class="input" 
                        id="user_confirm_password" 
                        autocomplete="off" 
                        placeholder="Confirm Password" 
                        pw-check="user_pass"
                        required="">
                    
                    <div 
                        class="error" 
                        ng-show="form.$submitted || form.confirm_password.$touched">
                        <span 
                            ng-show="form.confirm_password.$error.required">Password Confirmation is required
                        </span>
                        <span 
                            ng-show="form.confirm_password.$error.pwmatch">Passwords do not match.
                        </span>
                    </div>

                    <input 
                        type="submit" 
                        class="button" 
                        value="Sign Up"
                        >
                </form><!--.login-form-->
                <div 
                    class="help-text">
                    <p>By signing up, you agree to our</p>
                    <p>
                        <a href="#">Terms of service</a>
                    </p>
                </div>
                <!--.help-text-->
            </div>
            <!--.signup-tab-content-->
        </div>
        <!--.tabs-content-->
    </div>
    <!--.form-wrap-->
<jsp:include page="/WEB-INF/inc/footer.jsp"/>
