<%@ page import="com.mission1.mission1.db.BookmarkGroupService" %>
<%@ page import="com.mission1.mission1.db.BookmarkGroup" %>
<%@ page import="com.mission1.mission1.db.BookmarkService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  request.setCharacterEncoding("UTF-8");
  int id = Integer.parseInt(request.getParameter("id"));

  BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
  BookmarkGroup bookmarkGroup = bookmarkGroupService.dbSelectById(id);
  String bookmarkName = bookmarkGroup.getBookmarkName();
  bookmarkGroupService.dbDelete(id);

  BookmarkService bookmarkService = new BookmarkService();
  bookmarkService.deleteByBookmarkName(bookmarkName);
%>
<script>
  alert("북마크 그룹 정보를 삭제하였습니다.")
  location.href = "bookmark-group.jsp"
</script>