<%@ page import="com.mission1.mission1.db.Wifi" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mission1.mission1.db.WifiService" %>
<%@ page import="com.mission1.mission1.Haversine" %>
<%@ page import="com.mission1.mission1.db.HistoryService" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
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
  String lat = request.getParameter("latitude");
  String lnt = request.getParameter("longitude");
%>
<h1>와이파이 정보 구하기</h1>
<div>
  <a href="/">홈</a>
  | <a href="history.jsp">위치 히스토리 정보</a>
  | <a href="load-wifi">Open API 와이파이 정보 가져오기</a>
  | <a href="bookmark-list.jsp">북마크 보기</a>
  | <a href="bookmark-group.jsp">북마크 그룹 관리</a>
</div>
<br>
<form method="get" action="">
  <label for="latitude">LAT:</label>
  <input type="text" id="latitude" name="latitude" value=<%= lat==null || lat.isEmpty() ? 0.0 : lat%>>,
  <label for="longitude">LNT:</label>
  <input type="text" id="longitude" name="longitude" value=<%= lnt==null || lnt.isEmpty() ? 0.0 : lnt%>>
  <button type="button" onclick="getLocation()">내 위치 가져오기</button>
  <button type="submit">근처 WIFI 정보 보기</button>
</form>
<script>
  function getLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(showPosition);
    } else {
      alert("Geolocation is not supported by this browser.")
    }
  }
  function showPosition(position) {
    var latitude = document.getElementById("latitude");
    latitude.value = position.coords.latitude;
    var longitude = document.getElementById("longitude")
    longitude.value = position.coords.longitude;
  }
</script>
<br>
<table>
  <tr>
    <th>거리(Km)</th>
    <th>관리번호</th>
    <th>자치구</th>
    <th>와이파이명</th>
    <th>도로명주소</th>
    <th>상세주소</th>
    <th>설치위치(층)</th>
    <th>설치유형</th>
    <th>설치기관</th>
    <th>서비스구분</th>
    <th>망종류</th>
    <th>설치년도</th>
    <th>실내외구분</th>
    <th>WIFI접속환경</th>
    <th>X좌표</th>
    <th>Y좌표</th>
    <th>작업일자</th>
  </tr>
  <% if (lat==null || lat.isEmpty() || lnt==null || lnt.isEmpty()) { %>
  <tr>
    <td colspan="17" style="text-align: center">위치 정보를 입력한 후에 조회해 주세요.</td>
  </tr>
  <% } else {
    Double latitude = Double.parseDouble(lat);
    Double longitude = Double.parseDouble(lnt);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    Date date = new Date();
    String dt = sdf.format(date);

    History history = new History();
    history.setLat(latitude);
    history.setLnt(longitude);
    history.setDt(dt);

    HistoryService historyService = new HistoryService();
    historyService.dbCreate();
    historyService.dbInsert(history);

    WifiService wifiService = new WifiService();
    List<Wifi> wifis = wifiService.get20Wifi(latitude, longitude);
    for (Wifi wifi : wifis) {
      double x = wifi.getLAT();
      double y = wifi.getLNT();
      double km = Haversine.distance(latitude, longitude, x, y); %>
      <tr>
        <td><%= String.format("%.4f", km)%></td>
        <td><%= wifi.getX_SWIFI_MGR_NO()%></td>
        <td><%= wifi.getX_SWIFI_WRDOFC()%></td>
        <td>
          <a href="detail.jsp?mgrNo=<%= wifi.getX_SWIFI_MGR_NO()%>&km=<%= String.format("%.4f", km)%>">
            <%= wifi.getX_SWIFI_MAIN_NM()%>
          </a>
        </td>
        <td><%= wifi.getX_SWIFI_ADRES1()%></td>
        <td><%= wifi.getX_SWIFI_ADRES2()%></td>
        <td><%= wifi.getX_SWIFI_INSTL_FLOOR()%></td>
        <td><%= wifi.getX_SWIFI_INSTL_TY()%></td>
        <td><%= wifi.getX_SWIFI_INSTL_MBY()%></td>
        <td><%= wifi.getX_SWIFI_SVC_SE()%></td>
        <td><%= wifi.getX_SWIFI_CMCWR()%></td>
        <td><%= wifi.getX_SWIFI_CNSTC_YEAR()%></td>
        <td><%= wifi.getX_SWIFI_INOUT_DOOR()%></td>
        <td><%=wifi.getX_SWIFI_REMARS3() %></td>
        <td><%= String.format("%.6f", wifi.getLAT())%></td>
        <td><%= String.format("%.6f", wifi.getLNT())%></td>
        <td><%= wifi.getWORK_DTTM()%></td>
      </tr>
    <% }
  } %>
</table>

</body>
</html>
