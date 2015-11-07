<!-- Nav Drawer -->
<div class="mdl-layout__drawer">
	<span class="mdl-layout-title">Mood Mapper</span>
	<nav class="mdl-navigation">
		<a class="mdl-navigation__link" href="home.jsp"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">home</i>Home</a>
		<a class="mdl-navigation__link" href="my_groups.jsp"><i class="material-icons">people</i>My Groups</a>
		<a class="mdl-navigation__link" href="mood_maps.jsp"><i class="material-icons">insert_chart</i>Mood Maps</a>
		<a class="mdl-navigation__link" href="new_status.jsp"><i class="material-icons">add_circle</i>New Status</a>
                <a class="mdl-navigation__link" href="user_profile.jsp"><i class="material-icons">exit_to_app</i>User Profile</a>
                <a class="mdl-navigation__link" href="search.jsp"><i class="material-icons">search</i>Search</a>
		<a class="mdl-navigation__link" onclick="var auth2 = gapi.auth2.getAuthInstance(); auth2.signOut();" href="logout"><i class="material-icons">account_circle</i>Sign out</a>
	</nav>
</div>