<%-- 
    Document   : index
    Created on : Oct 13, 2015, 8:18:50 PM
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

   <title>Mood Mapper | Create Group</title>
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
         <style>
          .demo-card-square.mdl-card {
            width: 320px;
            height: 320px;
          }
          .demo-card-square > .mdl-card__title {
            color: #fff;
            background:
              url('../assets/demos/dog.png') bottom right 15% no-repeat #46B6AC;
          }
          </style>
          <div class="demo-card-square mdl-card mdl-shadow--2dp" style="margin: 0 auto; margin-bottom: 48px; margin-top: 48px;">
            <div class="mdl-card__title mdl-card--expand">
              <h2 class="mdl-card__title-text">Create Group</h2>
            </div>
            <div class="mdl-card__supporting-text">
              <form action="#">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" id="sample3" />
                  <label class="mdl-textfield__label" for="sample3">Name of Group</label>
                </div>
              </form>
            </div>
            <div class="mdl-card__actions mdl-card--border">
              <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                Create
              </a>
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