<%@ page import="com.mission1.mission1.db.BookmarkGroupService" %>
<%@ page import="com.mission1.mission1.db.BookmarkGroup" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>와이파이 정보 구하기</title>
  <style>
    * {
      font-family: "Gothic", sans-serif;
    }

    table {
      border-collapse: collapse;
      border-spacing: 1px 0;
      width: 100%;
      border: 1px solid #ddd;
    }

    th {
      background-color: #3CB371;
      color: #FFFFFF;
      text-align: center;
      padding: 16px;
      border: 1px solid #ddd;
    }
    td {
      text-align: left;
      padding: 16px;
      border: 1px solid #ddd;
    }

    tr:nth-child(odd) {
      background-color: #f2f2f2;
    }
  </style>
</head>
<body>
<%
  BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
  List<BookmarkGroup> bookmarkGroups = bookmarkGroupService.dbSelect();
%>
<h1>북마크 그룹 목록</h1>
<div>
  <a href="/">홈</a>
  | <a href="history.jsp">위치 히스토리 정보</a>
  | <a href="load-wifi">Open API 와이파이 정보 가져오기</a>
  | <a href="bookmark-list.jsp">북마크 보기</a>
  | <a href="bookmark-group.jsp">북마크 그룹 관리</a>
</div>
<br>
<button onclick="location.href='bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>
<table>
  <tr>
    <th>ID</th><th>북마크 이름</th><th>순서</th><th>등록일자</th><th>수정일자</th><th>비고</th>
  </tr>
  <% if (bookmarkGroups.isEmpty()) { %>
  <tr>
    <td colspan="6" style="text-align: center">등록된 정보가 없습니다.</td>
  </tr>
  <% } else {
    for (int i = 0; i < bookmarkGroups.size(); i++) {
      BookmarkGroup bookmarkGroup = bookmarkGroups.get(i); %>
  <tr>
    <td><%=bookmarkGroup.getId()%></td>
    <td><%=bookmarkGroup.getBookmarkName()%></td>
    <td><%=bookmarkGroup.getOrderId()%></td>
    <td><%=bookmarkGroup.getRegistrationDate()%></td>
    <td><%=bookmarkGroup.getEditDate()%></td>
    <td style="text-align: center">
      <a href="bookmark-group-edit.jsp?id=<%=bookmarkGroup.getId()%>">수정</a>
      <a href="bookmark-group-delete.jsp?id=<%=bookmarkGroup.getId()%>">삭제</a>
    </td>
  </tr>
  <% }
  } %>
</table>
</body>
</html>
