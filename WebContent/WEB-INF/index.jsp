<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>TP library</title>
<link rel="stylesheet" href='<c:url value="/css/style.css" />'>
</head>
<body>
	<header>
		<h1>Librairie</h1>
	</header>
		<c:choose>
			<c:when test="${!empty user }">
			<aside>
		<p>${'Bienvenue ' += user.firstname += ' ' += user.lastname} <a href="/bdd/logout"><input type="button" value="Se déconnecter"/></a></p>
				<c:if test="${user.role == 'admin' }">

					<form action="<c:url value='/addauthor'/>" method="POST">
						<input type="text" name="firstname" id="firstname"
							placeholder="Prénom auteur" /> <input type="text"
							name="lastname" id="lastname" placeholder="Nom auteur" /> <input
							type="text" name="native_country" id="native_country"
							placeholder="Pays d'origine" /> <input type="submit"
							value="ajouter auteur" />
					</form>

					<form action="<c:url value='/add'/>" method="POST">
						<input type="text" name="title" placeholder="Titre" /> <input
							type="text" name="overview" placeholder="Résumé" /> <input
							type="text" name="price" placeholder="Prix" />
						<div>
							<label><input type="radio" name="availability"
								value="true" /><span>en stock</span></label><br /> <label><input
								type="radio" name="availability" value="false" /><span>en
									rupture</span></label>
						</div>
						<select multiple="multiple" name="authors" id="">
							<c:forEach items="${authors}" var="author">
								<option
									value="${author.id} ${author.firstname} ${author.lastname} ${author.native_country}">${author.firstname}
									${author.lastname}</option>
							</c:forEach>
						</select> <input type="submit" value="ajouter livre" />
					</form>
				</c:if>
			</aside>
			<section>
		<form method="POST" action="<c:url value='/edit?id=${bookid}'/>"
			id="form-editbook"></form>

		<c:forEach items="${books}" var="book">
			<c:choose>
				<c:when test="${bookid == book.id}">
					<article class="edit">
						<input type="submit" value="Editer" form="form-editbook" />
						<input type="text" value="${book.title}" name="book-title" form="form-editbook" />
						<c:forEach items="${book.authors}" var="author">
							<input class="author" type="text" value="${author.firstname}"
								name="author-firstname-${author.id}" form="form-editbook" />
							<input class="author" type="text" value="${author.lastname}"
								name="author-lastname-${author.id}" form="form-editbook" />
							<input class="author" type="text" value="${author.native_country}"
								name="author-native_country-${author.id}" form="form-editbook" />
						</c:forEach>
							<input class="overview" type="text" value="${book.overview}" name="book-overview" form="form-editbook" />
							<input class= "tinyInput" type="text" value="${book.availability}" name="book-availability" form="form-editbook" />
							<input class= "tinyInput" type="text" value="${book.price}" name="book-price" form="form-editbook" />
					</article>
				</c:when>
				<c:otherwise>
					<article>
						<h2>${book.title}</h2>
						<c:if test="${user.role == 'admin' }">
						<a href="<c:url value='/delete?id=${book.id}'/>">&#10006;</a><a href="<c:url value='/edit?id=${book.id}'/>">&#128393;</a>
						</c:if>
						<h4>
							<c:forEach items="${book.authors}" var="author">
  								${author.firstname} ${author.lastname} (${author.native_country}) <br />
							</c:forEach>
						</h4>
						<p>"${book.overview}"</p>
						<p>${book.price} &#8364; <br />
							<c:out
								value="${book.availability eq 'true' ? 'en stock': 'indisponible'}" />
						</p>
					</article>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</section>
			</c:when>
		
			<c:when test="${actionname == 'signin' || actionname == 'login'}">
			<aside>
				<form action="${actionname}" method="POST">
					<c:if test="${actionname == 'signin'}">
						<input type="text" name="firstname" placeholder="Prénom" />
						<input type="text" name="lastname" placeholder="Nom" />
						<input type="email" name="email" placeholder="Email" />
					</c:if>
					<input type="text" name="pseudo" placeholder="Pseudo" />
					<input type="password" name="password" placeholder="Mot de passe" />
					<input type="submit" value="Enregistrer" />
				</form>
			</aside>
			</c:when>
		
			<c:otherwise>
			<aside>
				<a href="/bdd/signin"><input type="button" value="S'inscrire" /></a>
				<a href="/bdd/login"><input type="button" value="Se connecter" /></a>
				</aside>
			</c:otherwise>
		</c:choose>

		

	
	<!--   <footer> -->
	<!--     <h1>Librairie &copy; 2017</h1> -->
	<!--   </footer> -->
</body>
</html>