<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	
	function fetch_Book_type() {
		$.get("BookTypeReader", function(data){
            // Display the returned data in browser
           console.log(data);
            var booktype = JSON.parse(data);
            $.each(booktype, function(propName, propVal) {
            	  console.log(propName, propVal);
            	  $("#bookType").append($('<option></option>').val(propName).html(propVal));
            	});
            
        });
	}
	$(document).ready(function() {
		fetch_Book_type();
		$("form").submit(function(event) {
			// Stop form from submitting normally
			event.preventDefault();

			var formValues = new FormData();
			formValues.append("txtBookName", $("#txtBookName").val());
			formValues.append("txtBookAuthor", $("#txtBookAuthor").val());
			formValues.append("bookFile", $("#bookFile").get(0).files[0]);
			formValues.append("bookType", $("#bookType").val());
			formValues.append("txtBookPublishedDate", $("#txtBookPublishedDate").val());

			$.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : "bookwriter",
				data : formValues,
				processData : false,
				contentType : false,
				success : function(data) {
					window.location.href = './viewAllBooks.jsp';
				},
				error : function(e) {
					console.log("ERROR : ", e);

				}
			});
		});
	});
</script>
<body>
	<form>
		<table>
			<tr>
				<td>Book Name</td>
				<td><input id="txtBookName" type="text" name="txtBookName" /></td>
			</tr>
			<tr>
				<td>Book Author</td>
				<td><input type="text" id="txtBookAuthor" name="txtBookAuthor" /></td>
			</tr>
			<tr>
				<td><label>Book Image</label></td>
				<td><input type="file" name="bookFile" id="bookFile"></td>
			</tr>
			<tr>
				<td>Book Type</td>
				<td>
					<select id="bookType" name="bookType"></select>
				</td>
			</tr>
			<tr>
				<td>Book Published Date</td>
				<td><input type="date" id="txtBookPublishedDate" name="txtBookPublishedDate" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="SAVE" /></td>
			</tr>
		</table>
	</form>
</body>
</html>