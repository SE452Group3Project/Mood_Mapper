<%-- 
    Document   : index
    Created on : Oct 13, 2015, 8:21:26 PM
    Author     : Dave Messer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>

   <meta charset="utf-8">
   <meta name="google-signin-client_id" content="260435330613-uvlunbaam70iv523mmh5457gm4r8cc57.apps.googleusercontent.com">

   <!-- Google OAuth -->
   <script src="https://apis.google.com/js/platform.js" async defer></script>

   <!-- Material Design Lite -->
   <script src="https://storage.googleapis.com/code.getmdl.io/1.0.5/material.min.js"></script>
   <link rel="stylesheet" href="https://storage.googleapis.com/code.getmdl.io/1.0.5/material.blue_grey-blue.min.css" /> 

   <!-- Material Design icon font -->
   <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

    <!-- D3.js Library -->
    <script src="http://d3js.org/d3.v3.min.js"></script>

   <title>Mood Mapper | New Status</title>
</head>
<body>
    <!-- Uses a transparent header that draws on top of the layout's background -->
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header" style="background: url('http://images.adsttc.com/media/images/54da/8b55/e58e/cecb/5300/0066/large_jpg/08__Intrinsic_School_Steve_Hall_Hedrich_Blessing_Photography.jpg?1423608643') center / cover">    <!-- Nav Bar -->
<header class="mdl-layout__header mdl-layout__header--fixed">
  <div class="mdl-layout__header-row">
    <!-- Title -->
    <span class="mdl-layout-title">Mood Mapper</span>
    <!-- Add spacer, to align navigation to the right -->
    <div class="mdl-layout-spacer"></div>
    <!-- Navigation -->
    <nav class="mdl-navigation">
      <a href="/app/new-status/">
      <button class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-button--colored">
        <i class="material-icons">add</i>
      </button>
    </a>
    </nav>
  </div>
</header>    <!-- Nav Drawer -->
<div class="mdl-layout__drawer">
  <span class="mdl-layout-title">Mood Mapper</span>
  <nav class="mdl-navigation">
    <a class="mdl-navigation__link" href="/app/"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">home</i>Home</a>
    <a class="mdl-navigation__link" href="/app/my-groups/"><i class="material-icons">people</i>My Groups</a>
    <a class="mdl-navigation__link" href="/app/mood-maps/"><i class="material-icons">insert_chart</i>Mood Maps</a>
    <a class="mdl-navigation__link" href="/app/new-status"><i class="material-icons">add_circle</i>New Status</a>
    <a class="mdl-navigation__link" href=""><i class="material-icons">exit_to_app</i>Sign out</a>
  </nav>
</div>  
      <main class="mdl-layout__content">
        <div class="page-content">
          <div class="mdl-card mdl-shadow--2dp mdl-cell mdl-cell--6-col mdl-cell--8-col-tablet" style="margin: 0 auto; margin-bottom: 48px; margin-top: 48px;">
            <div class="mdl-card__title mdl-card--expand" style="margin: auto;">
              <h2 class="mdl-card__title-text">New Status</h2>
            </div>
            <div class="mdl-card__supporting-text" style="margin: auto;">
              <form action="#" style="margin: auto;">
                
                <!-- Energy -->
                <div class="mdl-card__supporting-text">
                  On a scale of -5 to 5, what is your energy level?
                </div>
                <input class="mdl-slider mdl-js-slider" type="range" min="-5" max="5" value="0" tabindex="1"/>
                
                <!-- Pleasantness -->
                <div class="mdl-card__supporting-text">
                  On a scale of -5 to 5, what is your pleasantness level?
                </div>
                <input class="mdl-slider mdl-js-slider" type="range" min="-5" max="5" value="0" tabindex="1"/>
                
                <!-- Descriptive Word -->
                <div class="mdl-card__supporting-text" style="margin-bottom: 0;">
                  One word that describes how you feel:
                </div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width: 100%;">
                  <input class="mdl-textfield__input" type="text" id="sample3" />
                  <label class="mdl-textfield__label" for="sample3">#</label>
                </div>

                <!-- Reflection Paragraph -->
                <div class="mdl-card__supporting-text">
                  Why do you feel this way?
                </div>
                <div class="mdl-textfield mdl-js-textfield" style="width: 100%;">
                  <textarea class="mdl-textfield__input" type="text" rows= "3" id="sample5"></textarea>
                  <label class="mdl-textfield__label" for="sample5">I feel this way because...</label>
                </div>

                <!-- Sharing -->
                <div class="mdl-card__supporting-text">
                  Sharing
                </div>
                <label class="mdl-switch mdl-js-switch mdl-js-ripple-effect" for="switch-1">
                  <input type="checkbox" id="switch-1" class="mdl-switch__input" checked />
                  <span class="mdl-switch__label"></span>
                </label>

              </form>
            </div>
            <div class="mdl-card__actions mdl-card--border">
              <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent mdl-js-ripple-effect" style="float: right;">
                Submit
              </button>
            </div>
          </div>
        </div>
      </main>
      


      <script>function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail());
      }</script>

    </div>
  </body>
</body>
</html>
