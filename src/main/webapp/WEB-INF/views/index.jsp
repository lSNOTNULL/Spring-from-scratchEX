<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>영화추천</title>
    <style>
        @font-face {
            font-family: 'ChosunGu';
            src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/ChosunGu.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }

        body {
            font-family: ChosunGu, serif;
        }
    </style>
</head>
<body>
    <p><%= request.getAttribute("movies")%></p>
    <p><%= request.getAttribute("recommendation")%></p>

</body>
</html>
