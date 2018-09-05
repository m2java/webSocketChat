<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>WebSocket Chat</title>
	<link href="<c:url value="./resources/theme1/css/chat-style.css"/>" rel="stylesheet">
    <script type="text/javascript">
        var ws = new WebSocket("ws://localhost:8080/chat");
        ws.onmessage = function processMessage(message) {
            document.getElementById("messagesTextArea").value += message.data + "\n";
        }
        function sendMessage() {
            ws.send("${cookie.username.value}"+":"+document.getElementById("messageText").value);
            document.getElementById("messageText").value = "";
        }
    </script>
</head>
<body>
<div class="form">
<c:out value="${cookie.username.value}"/>
<br>
<textArea id="messagesTextArea" readonly="readonly"></textArea>
<br>
<input class="input"type="text" id="messageText" onkeydown="if (event.keyCode == 13) sendMessage()" />
<input class="button"type="button" value="Send" onclick="sendMessage()"/>
</div>
</body>
</html>