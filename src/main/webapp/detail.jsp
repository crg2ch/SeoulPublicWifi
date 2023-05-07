<%@ page import="java.util.List" %>
<%@ page import="com.mission1.mission1.db.*" %>
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
    String mgrNo = request.getParameter("mgrNo");
    String km = request.getParameter("km");
    WifiService wifiService = new WifiService();
    Wifi wifi = wifiService.detail(mgrNo);
    BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
    List<BookmarkGroup> bookmarkGroups = bookmarkGroupService.dbSelect();
%>
<h1>와이파이 정보 구하기</h1>
<div>
    <a href="/">홈</a>
    | <a href="history.jsp">위치 히스토리 정보</a>
    | <a href="load-wifi">Open API 와이파이 정보 가져오기</a>
    | <a href="bookmark-list.jsp">북마크 보기</a>
    | <a href="bookmark-list.jsp">북마크 그룹 관리</a>
</div>
<br>
<form method="post" action="bookmark-add-sumit.jsp" accept-charset="UTF-8" onsubmit="return checkForm()">
    <input type="hidden" name="wifi-name" value="<%=wifi.getX_SWIFI_MAIN_NM()%>">
    <select name="bookmark-group">
        <option value="" selected>북마크 그룹 이름 선택</option>
        <% for (int i = 0; i < bookmarkGroups.size(); i++) {
            BookmarkGroup bookmarkGroup = bookmarkGroups.get(i); %>
        <option value="<%=bookmarkGroup.getBookmarkName()%>"><%=bookmarkGroup.getBookmarkName()%></option>
        <% } %>
    </select>
    <input type="submit" value="북마크 추가하기">
</form>
<script>
function checkForm() {
    var selectBox = document.getElementsByName("bookmark-group")[0];
    if (selectBox.value=="") {
        alert("북마크 그룹 이름을 선택해주세요.");
        return false;
    }
    return true;
}
</script>
<table>
    <tr>
        <th>거리(Km)</th>
        <td><%= km%></td>
    </tr>
        <th>관리번호</th>
        <td><%= wifi.getX_SWIFI_MGR_NO()%></td>
    </tr>
        <th>자치구</th>
        <td><%= wifi.getX_SWIFI_WRDOFC()%></td>
    </tr>
        <th>와이파이명</th>
        <td>
            <a href="detail.jsp?mgrNo=<%= wifi.getX_SWIFI_MGR_NO()%>&km=<%= km%>">
                <%= wifi.getX_SWIFI_MAIN_NM()%>
            </a>
        </td>
    </tr>
        <th>도로명주소</th>
        <td><%= wifi.getX_SWIFI_ADRES1()%></td>
    </tr>
        <th>상세주소</th>
        <td><%= wifi.getX_SWIFI_ADRES2()%></td>
    </tr>
        <th>설치위치(층)</th>
        <td><%= wifi.getX_SWIFI_INSTL_FLOOR()%></td>
    </tr>
        <th>설치유형</th>
        <td><%= wifi.getX_SWIFI_INSTL_TY()%></td>
    </tr>
        <th>설치기관</th>
        <td><%= wifi.getX_SWIFI_INSTL_MBY()%></td>
    </tr>
        <th>서비스구분</th>
        <td><%= wifi.getX_SWIFI_SVC_SE()%></td>
    </tr>
        <th>망종류</th>
        <td><%= wifi.getX_SWIFI_CMCWR()%></td>
    </tr>
        <th>설치년도</th>
        <td><%= wifi.getX_SWIFI_CNSTC_YEAR()%></td>
    </tr>
        <th>실내외구분</th>
        <td><%= wifi.getX_SWIFI_INOUT_DOOR()%></td>
    </tr>
        <th>WIFI접속환경</th>
        <td><%=wifi.getX_SWIFI_REMARS3() %></td>
    </tr>
        <th>X좌표</th>
        <td><%= String.format("%.6f", wifi.getLAT())%></td>
    </tr>
        <th>Y좌표</th>
        <td><%= String.format("%.6f", wifi.getLNT())%></td>
    </tr>
        <th>작업일자</th>
        <td><%= wifi.getWORK_DTTM()%></td>
    </tr>
</table>
</body>
</html>
