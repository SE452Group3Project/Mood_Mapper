<%-- 
    Document   : user_profile
    Created on : Oct 20, 2015, 7:30:42 PM
    Author     : faithfulokoye
--%>

<%@page import="com.moodmapper.entity.UserEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    String pageTitle = "User Profile";
    UserEntity user = null;
    
    String first_name = ""; String last_name = ""; 
    
     user = (UserEntity)session.getAttribute("user");  
     first_name = user.getFirstName(); 
     first_name = (first_name == null) ? "" : first_name; 
       
     last_name = user.getLastName();
     last_name = (last_name == null) ? "" : last_name; 

    String notice = ""; 
    String error = ""; 
     if (session.getAttribute("notice") != null){
           notice = (String) session.getAttribute("notice");   
     }
       
     if (session.getAttribute("error") != null){
           error = (String) session.getAttribute("error"); 
           notice = "";
      }
    

%>


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
    <jsp:include page="/WEB-INF/inc/navbar.jsp"/>
    <jsp:include page="/WEB-INF/inc/navdrawer.jsp"/>

    <jsp:include page="/WEB-INF/inc/body_head.jsp" flush="true">
        <jsp:param name="body_heading" value="Update Your User Profile" />
        <jsp:param name="error" value="<%=error%>" />
        <jsp:param name="notice" value="<%=notice%>" />
    </jsp:include>
            
           <div class="form-wrap" ng-app="loginOrSignUpForm"
                id="signup-tab-content" 
                ng-controller="UpdateUserController">
                <form 
                    name="form" 
                    class="signup-form" 
                    ng-submit="form.$valid && submit()" method="post" 
                    novalidate>
                    
                    <input 
                        type="text" 
                        ng-model="user.fname" 
                        ng-init="user.fname='<jsp:expression>first_name</jsp:expression>'"
                        name="fname" 
                        class="input" 
                        id="fname" 
                        ng-model-options="{ updateOn: 'blur' }" 
                        autocomplete="off" 
                        placeholder="First Name" 
                        required="">
                    <div 
                        class="error" 
                        ng-show="form.$submitted || form.fname.$touched">
                        <span 
                            ng-show="form.fname.$error.required">First Name is required
                        </span>
                    </div>
                    
                    <input 
                        type="text" 
                        ng-model="user.lname"
                        ng-init="user.lname='<jsp:expression>last_name</jsp:expression>'"
                        name="lname" 
                        class="input" 
                        id="lname" 
                        ng-model-options="{ updateOn: 'blur' }" 
                        autocomplete="off" 
                        placeholder="Last Name" 
                        required=""
                        >
                    <div 
                        class="error" 
                        ng-show="form.$submitted || form.lname.$touched">
                        <span 
                            ng-show="form.lname.$error.required">Last Name is required
                        </span>
                    </div>
                    
                    <input 
                        type="password" 
                        ng-model="user.password" 
                        ng-pattern="/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{6,13}$/" 
                        name="password" 
                        class="input" 
                        id="user_pass" 
                        autocomplete="off" 
                        placeholder="New Password (Not Required)" 
                        >
                    <div 
                        class="error" 
                        ng-show="form.$submitted || form.password.$touched">
                        <span 
                            ng-show="form.password.$error.pattern">New Password must have one lower case, one upper case letter, one digit, 6-13 length, and no spaces
                        </span>
                    </div>
                    
                    <input 
                        type="password" 
                        ng-model="user.confirm_password" 
                        name="confirm_password" 
                        class="input" 
                        id="user_confirm_password" 
                        autocomplete="off" 
                        placeholder="Confirm Password" 
                        pw-check="user_pass"
                        >
                    
                    <div 
                        class="error" 
                        ng-show="form.$submitted || form.confirm_password.$touched">
                        <span 
                            ng-show="form.confirm_password.$error.pwmatch">Passwords do not match.
                        </span>
                    </div>

                    
                    <input 
                        type="password" 
                        ng-model="user.current_password" 
                        name="current_password" 
                        class="input" 
                        id="user_current_password" 
                        autocomplete="off" 
                        placeholder="Current Password" 
                        required="">
                    
                    <div 
                        class="error" 
                        ng-show="form.$submitted || form.current_password.$touched">
                        <span 
                            ng-show="form.current_password.$error.required">Password Confirmation is required
                        </span>
                    </div>

                    <input 
                        type="submit" 
                        class="button" 
                        value="Update Profile"
                        >
                </form><!--.login-form-->
                
                <!--.help-text-->
            </div>
     
 <jsp:include page="/WEB-INF/inc/footer.jsp"/>
