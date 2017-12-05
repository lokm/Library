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
  <section>
  	<c:forEach items="${books}" var="book">
  	<article>
  		<h2>${book.title}</h2>
  		<h4>
  		<c:forEach items="${book.authors}" var="author">
  			${author.firstname} ${author.lastname} (${author.native_country}) <br />
  		</c:forEach>
  		</h4>
  		<p>"${book.overview}"</p>
  		<p>${book.price} €, <c:out value="${book.availability eq 'true' ? 'en stock': 'indisponible'}"/></p>
  	</article>
    </c:forEach>
    <%-- <article>
      <table>
        <tr>
          <th>Titre</th>
          <th>résumé</th>
          <th>Disponible</th>
          <th>Prix</th>
        </tr>
        <c:forEach items="${books}" var="book">
        <tr>
          <td>${book.title}</td>
          <td>${book.overview}</td>
          <td><c:out value="${book.availability eq 'true' ? 'disponile': 'non disponible'}"/></td>

          <td>${book.price}</td>
        </tr>
        	
        </c:forEach>
      </table>
    </article>
 --%>    <br />
   <%--  <article>
    	<table>
        <tr>
          <th>ID</th>
          <th>Prénom</th>
          <th>Nom</th>
        </tr>
        <c:forEach items="${authors}" var="author">
        <tr>
          <td>${author.id}</td>
          <td>${author.lastname}</td>
          <td>${author.firstname}</td>
        </tr>
        	
        </c:forEach>
      </table> 
    </article>--%>
  </section>
  <aside>
    <form action="">
      <input type="text" name="login" placeholder="Identifiant">
      <input type="password" name="password" placeholder="Mot de passe">
      <input type="submit" value="connection">
    </form>
  </aside>
  <footer>
    <h1>Librairie &copy; 2017</h1>
  </footer>
</body>
</html>