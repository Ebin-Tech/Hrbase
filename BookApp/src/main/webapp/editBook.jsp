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
var loadFile = function(event) {
	var output = document.getElementById('bookImage');
	output.src = URL.createObjectURL(event.target.files[0]);
};
	$(document).ready(function() {
		fetch_Book_type();
		//calling the bookid which passsed through URL
		const params = new URLSearchParams(window.location.search)
		let bId = params.get('bookId');
		$.get("bookviewer", {bookId: bId} , function(data){
            // Display the returned data in browser
           
            var book = JSON.parse(data);
            console.log(book);
            $("#txtBookName").val(book.bookName);
            $("#txtBookAuthor").val(book.bookAuthor);
            $("#txtBookId").val(book.bookId);
            $("#bookImage").attr("src", 'data:image/jpg;base64,'+book.bookImageString);
            $("#txtBookPublishedDate").val(book.bookPublishedDate);
            $("#txtBookId").val(book.bookId);
            $("#bookType").val(book.bookType);
            
        });
		
		
		
		$("form").submit(function(event) {
			// Stop form from submitting normally
	        event.preventDefault();
			
			
	        /* Serialize the submitted form control values to be sent to the web server with the request */
	       // var formValues = $(this).serialize();
	        
	     // Send the form data using post
	        
	       /*  $.post("bookupdater", formValues, function(data){
	            // Display the returned data in browser
	            //$("#result").html(data);
	        	window.location.href = './viewAllBooks.jsp';
	        }); */
	        
	        $("form").submit(function(event) {
				// Stop form from submitting normally
		        event.preventDefault();
				        
		        var formValues = new FormData();
		        formValues.append("txtBookId", $("#txtBookId").val());
		        formValues.append("txtBookName", $("#txtBookName").val());
		        formValues.append("txtBookAuthor", $("#txtBookAuthor").val());
		        formValues.append("bookFile", $("#bookFile").get(0).files[0]);
		        formValues.append("bookType", $("#bookType").val());
		        formValues.append("txtBookPublishedDate", $("#txtBookPublishedDate").val());
	        
		        $.ajax({
		            type: "POST",
		            enctype: 'multipart/form-data',
		            url: "bookupdater",
		            data: formValues,
		            processData: false,
		            contentType: false,	          
		            success: function (data) {
		            	window.location.href = './viewAllBooks.jsp';
		            },
		            error: function (e) {	               
		                console.log("ERROR : ", e);                

		            }
		        });
			});

		});
	});
</script>
<body>
	<form>
		<table align="center" border="0">
			<tr style="height: 20px">
				<td rowspan="6">
					<img id="bookImage" style="width: 280px;" alt="" src="">
				</td>
				<td>Book Name</td>
				<td><input id="txtBookName" type="text" id="txtBookName" name="txtBookName" /></td>
			</tr>
			<tr style="height: 30px">
				<td>Book Author</td>
				<td><input type="text" id="txtBookAuthor" name="txtBookAuthor" /></td>
			</tr>
			<tr style="height: 30px">
				<td><label>Book Image</label></td>
				<td><input type="file" name="bookFile" id="bookFile" onchange="loadFile(event)"></td>
			</tr>
			<tr style="height: 30px">
				<td>Book Type</td>
				<td>
					<select id="bookType" name="bookType"></select>
				</td>
			</tr>
			<tr style="height: 30px">
				<td>Book Published Date</td>
				<td><input type="date" id="txtBookPublishedDate" name="txtBookPublishedDate" /></td>
			</tr>
			<tr>
				<td colspan="2" style="vertical-align:top; text-align: right;"><input type="submit" value="UPDATE" /></td>
			</tr>
		</table>
		<input type="hidden" id="txtBookId" name="txtBookId" />
	</form>
</body>
</html>