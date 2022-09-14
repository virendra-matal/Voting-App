<%@page import="com.veer.voat.model.MyResponse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		var radio = $('#alreadyvoted').val();
		console.log("alreadyvottedvalu:" + radio);

		$(".candidatebtn").each(function(index) {
			console.log(index + ": " + $(this).val());
			var canlistid = $(this).val();
			if (radio == canlistid) {
				console.log("match ids");
				$(this).attr("checked", true);
				$('.candidatebtn').attr("disabled",true);
				var btn=$('.votebtn');
				btn.attr("disabled",true);
			}
		});
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

	<div class="container d-flex align-items-center  justify-content-center" style="height: 600px;width: 1000px;">
		<div class="card mt-5">
			<div class="card-body " style="height: 400px;width: 900px;">
			<div class="text-right">
				<a href="/logout" class="btn btn-primary">Logout</a>
			</div>
				<form action="/dovote" method="post" class=" text-center">
					<h2 class="text-center mb-5">Voting</h2>
					<%
						HttpSession session2 = request.getSession();
					MyResponse attribute = (MyResponse) session2.getAttribute("message");

					if (attribute != null) {
					%>
					<div class="alert <%=attribute.getType()%>" role="alert "
						onload="hide()" id="alert">
						<%=attribute.getContent()%>
					</div>
					<%
						}
					session2.removeAttribute("message");
					%>

					
					<input type="hidden" id="alreadyvoted" value=${isvoted}>
					
					<p:forEach items="${candidatelist}" var="ob">
						<div class="form-check">
							<input class="form-check-input candidatebtn" type="radio"
								name="exampleRadios" id="${ob.candidateId}"
								value="${ob.candidateId}" required="required"> <label
								class="form-check-label" for="exampleRadios1">
								${ob.candidateName} </label>
						</div>
					</p:forEach>

					<button type="submit" class="btn btn-primary votebtn">Vote</button>

				</form>
			</div>
		</div>
	</div>


</body>
</html>