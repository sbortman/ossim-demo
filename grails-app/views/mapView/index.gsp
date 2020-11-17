<%--
  Created by IntelliJ IDEA.
  User: sbortman
  Date: 11/9/20
  Time: 12:34 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>MapView</title>
    <asset:stylesheet src="mapView.css"/>
</head>

<body>
<div id="map"></div>
<asset:javascript src="mapView.js"/>
<asset:script>
    $(document).ready(function() {
        var mapViewParams = ${raw( mapViewParams?.encodeAsJSON()?.toString() )};
    MapView.init(mapViewParams);
});
</asset:script>
<asset:deferredScripts/>
</body>
</html>