<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
img.iconimage {
		border-radius: 5px;		
		width: 60px;		
}
th{
text-align: left;
background-color: #cecaca;
}
</style>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<body>
	<table align="center" id="tabbook" border="0" style="width: 60%">
		<thead>
			<th colspan="2">Book Name</th>
			<th>Book Author</th>
			<th>View</th>
			<th>Delete</th>
		</thead>
		<tbody>

		</tbody>
	</table>
	<script type="text/javascript">
		function editBook(bookId){
			window.location.href = './editBook.jsp?bookId=' + bookId;
		}
		function deleteBook(bId){
			$.get("bookdeleter", {bookId: bId} , function(data){
				window.location.href = './viewAllBooks.jsp';
			});
		}
		function fetch_data() {
			books = [];
			$.ajax({
				type : "GET",
				url : "bookreader",				
				dataType : "json"
			}).done(function(data) {
						books = data;
						// Printing Each Array element in table						

						for (var i = 0; i < books.length; i++) {
							var tr = "<tr><td>" + "<img class=\"iconimage\" src=\"data:image/jpg;base64,"+books[i].bookImageString+"\"/>"
									+ "</td><td>" + books[i].bookName
									+ "</td><td>" + books[i].bookAuthor
									+ "</td><td>" + "<a href=\"#\" onclick=\"editBook("+books[i].bookId+")\">[view]</a>"
									+ "</td><td>" + "<a href=\"#\" onclick=\"deleteBook("+books[i].bookId+")\">[delete]</a>"
									+ "</td></tr><tr><td colspan=5><hr/></td>";
							$("#tabbook").append(tr);
						}

					});
		}
		$(document).ready(function() {			
				fetch_data();			
		});
	</script>
</body>
</html>