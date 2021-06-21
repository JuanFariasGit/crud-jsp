<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="script" fragment="true"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>CRUD USUÁRIOS</title>
	<jsp:invoke fragment="head" />
</head>
<body>
	<header class="bg-light py-2">
		<div>
			<h1 class="text-center mb-0">CRUD USUÁRIOS</h1>
		</div>
	</header>
	<main class="container">
		<jsp:doBody />
	</main>
	<footer class="text-center bg-light py-3">
		<small>Desenvolvido por Juan De Oliveira Farias</small>
	</footer>
	<jsp:invoke fragment="script" />
</body>
</html>