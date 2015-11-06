<%-- 
    Document   : footer.jsp
    Created on : Oct 22, 2015, 10:22:00 PM
    Author     : faithfulokoye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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