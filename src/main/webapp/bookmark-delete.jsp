<%@ page import="com.mission1.mission1.db.Bookmark" %>
<%@ page import="com.mission1.mission1.db.BookmarkService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
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
    request.setCharacterEncoding("UTF-8");
    int id = Integer.parseInt(request.getParameter("id"));
    BookmarkService bookmarkService = new BookmarkService();
    Bookmark bookmark = bookmarkService.bookmarkById(id);
%>
<h1>북마크 그룹 목록</h1>
<div>
    <a href="/">홈</a>
    | <a href="history.jsp">위치 히스토리 정보</a>
    | <a href="load-wifi">Open API 와이파이 정보 가져오기</a>
    | <a href="bookmark-list.jsp">북마크 보기</a>
    | <a href="bookmark-group.jsp">북마크 그룹 관리</a>
</div>
<p>북마크를 삭제하시겠습니까?</p>
<form method="post" action="bookmark-delete-submit.jsp" accept-charset="UTF-8">
    <input type="hidden" name="id" value="<%=id%>">
    <table>
        <tr>
            <th>북마크 이름</th>
            <td><%=bookmark.getBookmarkName()%></td>
        </tr>
        <tr>
            <th>와이파이명</th>
            <td><%=bookmark.getWifiName()%></td>
        </tr>
        <tr>
            <th>등록일자</th>
            <td><%=bookmark.getRegistrationDate()%></td>
        </tr>
        <tr>
            <td colspan="3" style="background-color: #FFFFFF; text-align: center">
                <a href="bookmark-list.jsp">돌아가기</a>
                | <button type="submit">삭제</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>