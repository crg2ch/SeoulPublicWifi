<%@ page import="com.mission1.mission1.db.BookmarkGroupService" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.mission1.mission1.db.BookmarkGroup" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  request.setCharacterEncoding("UTF-8");
  String bookmarkName = request.getParameter("bookmarkName");
  int orderId = Integer.parseInt(request.getParameter("orderId"));

  BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
  BookmarkGroup bookmarkGroup = new BookmarkGroup();
  bookmarkGroup.setBookmarkName(bookmarkName);
  bookmarkGroup.setOrderId(orderId);

  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
  Date date = new Date();
  String str = sdf.format(date);
  bookmarkGroup.setRegistrationDate(str);
  bookmarkGroup.setEditDate("");

  int affected = bookmarkGroupService.dbInsert(bookmarkGroup);
  if (affected > 0) {
%>
<script>
  alert("북마크 그룹 정보를 추가하였습니다.")
  location.href = "bookmark-group.jsp"
</script>
<% } else { %>
<script>
  alert("북마크 그룹 정보를 추가하지 못 하였습니다.\n\"<%=bookmarkName%>\"이 이미 존재하는지 확인해주세요.")
  location.href = "bookmark-group-add.jsp"
</script>
<% } %>