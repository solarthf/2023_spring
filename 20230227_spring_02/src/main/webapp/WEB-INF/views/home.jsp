<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<script type="text/javascript">
	var msg = "${alertMsg}";
	if(msg){
		alert(msg);
	}
</script>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
