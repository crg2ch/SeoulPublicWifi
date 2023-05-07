<%@ page import="com.mission1.mission1.db.BookmarkGroupService" %>
<%@ page import="com.mission1.mission1.db.BookmarkGroup" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.mission1.mission1.db.BookmarkService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  request.setCharacterEncoding("UTF-8");
  int id = Integer.parseInt(request.getParameter("id"));
  BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
  BookmarkGroup bookmarkGroup = bookmarkGroupService.dbSelectById(id);
  String oldBookmarkName = bookmarkGroup.getBookmarkName();
  String bookmarkName = request.getParameter("bookmarkName");
  int orderId = Integer.parseInt(request.getParameter("orderId"));


  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
  Date date = new Date();
  String str = sdf.format(date);
  int affected = bookmarkGroupService.dbUpdate(id, bookmarkName, orderId, str);

  BookmarkService bookmarkService = new BookmarkService();
  bookmarkService.updateByBookmarkName(oldBookmarkName, bookmarkName);
  if (affected > 0 || oldBookmarkName.equals(bookmarkName)) {
%>
<script>
  alert("북마크 그룹 정보를 수정하였습니다.")
  location.href = "bookmark-group.jsp"
</script>
<% } else { %>
<script>
  alert("북마크 그룹 정보를 수정하지 못 하였습니다.\n\"<%=bookmarkName%>\"이 이미 존재하는지 확인해주세요.")
  location.href = "bookmark-group-edit.jsp?id=<%=id%>"
</script>
<% } %>