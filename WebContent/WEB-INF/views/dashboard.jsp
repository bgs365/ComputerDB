<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pagination" uri="/WEB-INF/taglibs/pagination.tld"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a href="dashboard?action=doGet">Application - Computer Database</a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${numberOfComputers}Computersfound</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${computers}" var="record">

						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="0"></td>
							<td><a href="editComputer.html" onclick="">${record.name }</a>
							</td>
							<td>${record.introduced }</td>
							<td>${record.discontinued }</td>
							<td>${record.company.name }</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

			
			
	<footer class="navbar-fixed-bottom">

		<div class="container text-center">				
				<c:url var="searchUri" value="dashboard?pageNumber=##" />
				<pagination:display maxLinks="10" currPage="${computerPage.numerosPage}" totalPages="${numberTotalOfPages}" uri="${searchUri}" />
			<div class="btn-group btn-group-sm pull-right" role="group">

				<form action="${pageContext.request.contextPath}/dashboard"
					method="get">
					<button type="submit" name="buttonSetNumberElementDisplayed"
						value="10" class="btn btn-default">10</button>
					<button type="submit" name="buttonSetNumberElementDisplayed"
						value="50" class="btn btn-default">50</button>
					<button type="submit" name="buttonSetNumberElementDisplayed"
						value="100" class="btn btn-default">100</button>
				</form>

			</div>

		</div>

	</footer>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>