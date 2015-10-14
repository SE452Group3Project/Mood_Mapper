<%-- 
    Document   : index
    Created on : Oct 13, 2015, 8:17:02 PM
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

   <title>Mood Mapper | App</title>
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
          

          <!-- Wide card with share menu button -->
          <div class="demo-card-wide mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col" style="margin: 0 auto; margin-bottom: 48px; margin-top: 48px;">
            <div class="mdl-card__title">
              <h2 class="mdl-card__title-text">Jimmy</h2>
            </div>
            <div class="mdl-card__supporting-text">
              Woke up and took a run this morning
              <h4>#energized (5,3)</h4>
            </div>
            <div class="mdl-card__actions mdl-card--border">
              <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                <i class="material-icons">comment</i>
                Comment
              </a>
            </div>
            <div class="mdl-card__menu">
            </div>
          </div>

          <!-- Wide card with share menu button -->
          <div class="demo-card-wide mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col" style="margin: 0 auto; margin-bottom: 48px;">
            <div class="mdl-card__title">
              <h2 class="mdl-card__title-text">Erin</h2>
            </div>
            <div class="mdl-card__supporting-text">
              Forgot to do my homework
              <h4>#frustrated (2,-3)</h4>
            </div>
            <div class="mdl-card__actions mdl-card--border">
              <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                <i class="material-icons">comment</i>
                Comment
              </a>
            </div>
            <div class="mdl-card__menu">
            </div>
          </div>

          <!-- Wide card with share menu button -->
          <div class="demo-card-wide mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col" style="margin: 0 auto; margin-bottom: 48px;">
            <div class="mdl-card__title">
              <h2 class="mdl-card__title-text">Dave</h2>
            </div>
            <div class="mdl-card__supporting-text">
              Rode my bike to school!
              <h4>#excited (4, 4)</h4>
            </div>
            <div class="mdl-card__actions mdl-card--border">
              <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                <i class="material-icons">comment</i>
                Comment
              </a>
            </div>
            <div class="mdl-card__menu">
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