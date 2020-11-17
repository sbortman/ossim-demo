<%--
  Created by IntelliJ IDEA.
  User: sbortman
  Date: 11/9/20
  Time: 12:34 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>psmView</title>
    <asset:stylesheet src="2cmvView.css"/>
</head>

<body>
<div id="map"></div>
<asset:javascript src="2cmvView.js"/>
<asset:script>
    $(document).ready(function() {
        var twoCmvParams = ${raw( twoCmvParams?.encodeAsJSON()?.toString() )};
    TwoCMV.init(twoCmvParams);
});
</asset:script>
<asset:deferredScripts/>
</body>
</html>