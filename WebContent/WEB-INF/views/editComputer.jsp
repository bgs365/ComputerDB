<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<%@ include file="header.jsp" %> 

<body>
	<%@ include file="navbar.jsp" %> 
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

					<form:form action="${pageContext.request.contextPath}/computer/editComputer" method="POST" modelAttribute="ComputerDTO">
						<form:input type="hidden" value="${computer.id}" id="id"
							name="computerId" path="id"/>
						<fieldset>
							<div class="form-group">
								<form:label for="computerName" path="name">Computer name</form:label> <form:input
									type="text" class="form-control" id="computerName"
									name="computerName" placeholder="Computer name"
									value="${computer.name}" path="name"/>
							</div>
							<div class="form-group">
								<form:label for="introduced" path="introduced">Introduced date</form:label> <form:input
									type="date" class="form-control" id="introduced"
									name="introduced" placeholder="Introduced date"
									value="${computer.introduced}" path="introduced"/>
							</div>
							<div class="form-group">
								<form:label for="discontinued" path="discontinued">Discontinued date</form:label> <form:input
									type="date" class="form-control" id="discontinued"
									name="discontinued" placeholder="Discontinued date"
									value="${computer.discontinued}" path="discontinued"/>
							</div>
							<div class="form-group">
								<form:label for="companyId"  path="companyId">Company</form:label> <form:select
									class="form-control" id="companyId" name="companyId"  path="companyId">
									<option value="${computer.company.id}">${computer.company.name}</option>
									<option value="0">--</option>
									<c:forEach items="${companies}" var="record">
										<option value="${record.id }">${record.name }</option>
									</c:forEach>
								</form:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary"
								id="update"> or <a href="${pageContext.request.contextPath}/computer/"
								class="btn btn-default">Cancel</a>
						</div>
					</form:form>
				</div>
			</div>

		</div>
	</section>
</body>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>

<script type='text/javascript'
	src='http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js'></script>
<script src="../js/updateComputerValidator.js"></script>
</html>