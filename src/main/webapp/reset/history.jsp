<%@ page import="com.mission1.mission1.db.HistoryService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  HistoryService historyService = new HistoryService();
  historyService.dbDrop();
  historyService.dbCreate();
%>
<script>
  alert("History DB 삭제 완료")
  location.href = "/"
</script>