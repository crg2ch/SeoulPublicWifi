<%@ page import="com.mission1.mission1.db.HistoryService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  request.setCharacterEncoding("UTF-8");
  String id = request.getParameter("id");
  HistoryService historyService = new HistoryService();
  historyService.dbDelete(Integer.parseInt(id));
%>
<script>
  alert("히스토리 정보를 삭제하였습니다.")
  location.href = "history.jsp"
</script>