<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <h1>Titre</h1>
  </header>
  <nav>
    <a href="">Page 1</a>
    <a href="">Page 2</a>
    <a href="">Page 3</a>
    <a href="">Page 4</a>
    <a href="">Page 5</a>
  </nav>
    <aside>
 
    <form action="">
      <input type="text" name="login" placeholder="Identifiant">
      <input type="password" name="password" placeholder="Mot de passe">
      <input type="submit" value="connection">
    </form>
 
  <form action="${pageContext.request.contextPath}/books" method="POST">
  	<input type="text" name="firstname" id="firstname" placeholder="Prénom auteur"/>
  	<input type="text" name="lastname" id="lastname" placeholder="Nom auteur"/>
  	<input type="text" name="native_country" id="native_country" placeholder="Pays d'origine"/>
 	<input type="submit" value="ajouter"/>
  </form>
  
  	<form action="<c:url value='/books/add'/>" method="POST">
  		<input type="text" name="title" placeholder="Titre"/>
  		<input type="text" name="overview" placeholder="Résumé"/> 
  		<input type="text" name="price" placeholder="Prix"/>
  		<div>
  		<label><input type="radio" name="availability" value="true"/><span>en stock</span></label><br />
  		<label><input type="radio" name="availability" value="false"/><span>en rupture</sapn></label>
   		</div>
   		<select multiple="multiple" name="authors" id="">
   			<c:forEach items="${authors}" var="author">
   				<option value="${author.firstname} ${author.lastname}">${author.firstname} ${author.lastname}</option>
			</c:forEach>
   		</select>
   		<input type="submit" value="ajouter"/>
  	</form>
 
  </aside>
  <section>

  	<c:forEach items="${books}" var="book">
  	
  	<article>
  		<h2>${book.title} </h2>
  		<h4>
  		<c:forEach items="${book.authors}" var="auteur">
  			<p>${auteur.firstname}</p>
  		</c:forEach>
  		<%-- <c:forEach items="${book.authors}" var="author">
  			${author.firstname} ${author.lastname} (${author.native_country}) <br />
  		</c:forEach> --%>
  		</h4>
  		<p>"${book.overview}"</p>
  		<p>${book.price} € <br />
  		<c:out value="${book.availability eq 'true' ? 'en stock': 'indisponible'}"/></p>
  	</article>
    </c:forEach>
  </section>
  <footer>
    <h1>Librairie &copy; 2017</h1>
  </footer>
</body>
</html>