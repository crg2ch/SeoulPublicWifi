<%@ page import="com.mission1.mission1.db.BookmarkService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  BookmarkService bookmarkService = new BookmarkService();
  bookmarkService.dbDrop();
  bookmarkService.dbCreate();
%>
<script>
  alert("Bookmark DB 삭제 완료")
  location.href = "/"
</script>