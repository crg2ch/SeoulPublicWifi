<%@ page import="java.util.List" %>
<%@ page import="com.mission1.mission1.db.HistoryService" %>
<%@ page import="com.mission1.mission1.db.History" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    request.setCharacterEncoding("UTF-8");
    HistoryService historyService = new HistoryService();
    List<History> histories = historyService.dbSelect();
%>
<h1>위치 히스토리 목록</h1>
<div>
    <a href="/">홈</a>
    | <a href="history.jsp">위치 히스토리 정보</a>
    | <a href="load-wifi">Open API 와이파이 정보 가져오기</a>
    | <a href="bookmark-list.jsp">북마크 보기</a>
    | <a href="bookmark-group.jsp">북마크 그룹 관리</a>
</div>
<br>
<table>
    <tr>
        <th>ID</th><th>X좌표</th><th>Y좌표</th><th>조회일자</th><th>비고</th>
    </tr>
    <% if (histories.isEmpty()) { %>
    <tr>
        <td colspan="5" style="text-align: center">히스토리 정보가 없습니다.</td>
    </tr>
    <% } else {
        for (int i = histories.size() - 1; i >= 0; i--) {
            History history = histories.get(i);
    %>
    <tr>
        <td><%= history.getId() %>
        </td>
        <td><%= history.getLat() %>
        </td>
        <td><%= history.getLnt() %>
        </td>
        <td><%= history.getDt() %>
        </td>
        <td style="text-align: center">
            <form action="history-delete.jsp" method="get">
                <input type="hidden" name="id" value="<%=history.getId()%>">
                <input type="submit" value="삭제">
            </form>
        </td>
    </tr>
    <% }
    } %>
</table>
</body>
</html>