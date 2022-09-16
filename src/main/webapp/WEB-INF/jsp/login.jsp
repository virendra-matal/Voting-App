<%@page import="com.veer.voat.model.MyResponse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(()=>{
		setTimeout(()=>{
			var alert=$('#alert');
			console.log("alert.is(':visible')",alert.is(':visible'));
			if(alert.is(':visible')){
				alert.hide();
				}
		},3000);
	});
</script>
</head>
<body style="background: #e2e2e2;">
	<div class="container text-center mt-5 d-flex align-items-center  justify-content-center" style="height: 500px;width: 1000px;">
		<div class="card mt-5">
			<div class="card-body" style="height: 350px;width: 900px;">
				<form method="post" action="/dologin">
				<%
					HttpSession session2 = request.getSession();
					MyResponse attribute = (MyResponse) session2.getAttribute("logoutMessage");

					if (attribute != null) {
					%>
					<div class="alert <%=attribute.getType()%>" role="alert "
						onload="hide()" id="alert">
						<%=attribute.getContent()%>
					</div>
					<%
						}
					session2.removeAttribute("logoutMessage");
					%>
					
				<h2 class="text-center">Login</h2>
				  <div class="form-group">
				    <label for="exampleInputEmail1">Username :</label>
				    <input type="text" class="form-control" name="username" id="username" aria-describedby="emailHelp" placeholder="Enter Username" required="required">
				    
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">Password</label>
				    <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password" required="required">
				  </div>
				  
				  <button type="submit" class="btn btn-primary">Submit</button>
				  <a class="btn btn-success text-light" href="/register">New user?</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>