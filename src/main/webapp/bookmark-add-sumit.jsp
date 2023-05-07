<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.mission1.mission1.db.BookmarkService" %>
<%@ page import="com.mission1.mission1.db.Bookmark" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  request.setCharacterEncoding("UTF-8");
  String bookmarkName = request.getParameter("bookmark-group");
  String wifiName = request.getParameter("wifi-name");

  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
  Date date = new Date();
  String registrationDate = sdf.format(date);

  Bookmark bookmark = new Bookmark();
  bookmark.setBookmarkName(bookmarkName);
  bookmark.setWifiName(wifiName);
  bookmark.setRegistrationDate(registrationDate);

  BookmarkService bookmarkService = new BookmarkService();
  bookmarkService.dbCreate();
  bookmarkService.dbInsert(bookmark);
%>
<script>
  alert("북마크 정보를 추가하였습니다.")
  location.href = "bookmark-list.jsp"
</script>