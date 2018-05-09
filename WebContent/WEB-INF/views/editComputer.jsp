<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>

<script type='text/javascript'
	src='http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js'></script>
<script src="js/updateComputerValidator.js"></script>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>
	<section id="main">
		<div>
			<c:choose>
				<c:when test="${success=='true'}">
					<div class="alert alert-success">
						<strong>Computer updated!</strong>
					</div>
					<br />
				</c:when>
				<c:when test="${success=='false'}">
					<div class="alert alert-danger">
						<strong>Some field are not well fill! ${errors}</strong>
					</div>
					<br />
				</c:when>
				<c:otherwise>
					<br />
				</c:otherwise>
			</c:choose>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1>Edit Computer</h1>

					<form action="editComputer" method="POST">
						<input type="hidden" value="${computer.id}" id="id"
							name="computerId" />
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName"
									name="computerName" placeholder="Computer name"
									value="${computer.name}">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" id="introduced"
									name="introduced" placeholder="Introduced date"
									value="${computer.introduced}">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" id="discontinued"
									name="discontinued" placeholder="Discontinued date"
									value="${computer.discontinued}">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<option value="${computer.company.id}">${computer.company.name}</option>
									<option value="0">--</option>
									<c:forEach items="${companies}" var="record">
										<option value="${record.id }">${record.name }</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary"
								id="update"> or <a href="dashboard"
								class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>

		</div>
	</section>
</body>
</html>