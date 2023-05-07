<%@ page import="com.mission1.mission1.db.WifiService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    WifiService wifiService = new WifiService();
    wifiService.dbDrop();
    wifiService.dbCreate();
%>
<script>
    alert("WIFI DB 삭제 완료")
    location.href = "/"
</script>