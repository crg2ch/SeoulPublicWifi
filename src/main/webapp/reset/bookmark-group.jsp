<%@ page import="com.mission1.mission1.db.BookmarkGroupService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
  bookmarkGroupService.dbDrop();
  bookmarkGroupService.dbCreate();
%>
<script>
  alert("BookmarkGroup DB 삭제 완료")
  location.href = "/"
</script>