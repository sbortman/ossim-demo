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
    <asset:stylesheet src="psmView.css"/>
</head>

<body>
<div id="map"></div>
<asset:javascript src="psmView.js"/>
<asset:script>
    $(document).ready(function() {
        var psmViewParams = ${raw( psmViewParams?.encodeAsJSON()?.toString() )};
    PsmView.init(psmViewParams);
});
</asset:script>
<asset:deferredScripts/>
</body>
</html>