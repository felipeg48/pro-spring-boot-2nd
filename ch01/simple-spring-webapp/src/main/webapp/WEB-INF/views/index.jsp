<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Simple Directory Web App</title>
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap-theme.min.css">
</head>
<body>
	<div class="container theme-showcase" role="main">
		<div class="jumbotron">
			<h1>Directory Application</h1>
			<p>A simple Spring MVC application</p>
		</div>

		<div class="page-header">
			<h1>API</h1>
		</div>
		<div class="row">
			<div class="col-md-8">
				<table class="table">
					<thead>
						<tr>
							<th>Method</th>
							<th>Path</th>
							<th>Example</th>
						</tr>
					</thead>
					<tbody>
						 <c:forEach items="${methods}" var="info">
						 	<tr>
						 		<th>${info.method}</th>
						 		<th>${info.path}</th>
						 		<th><a href = "<c:url value='${info.link}'/>">${info.link}</a> </th>
						 	</tr>
						 </c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</div>
</body>
</html>



