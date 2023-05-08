package com.mission1.mission1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loadWifi", value = "/load-wifi")
public class LoadWifi extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SeoulAPI seoulAPI = new SeoulAPI();
        int rowCnt = seoulAPI.getData();

        response.setContentType("text/html; charset=utf-8");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><style>");
        out.println("h1, p {font-family: \"Gothic\", sans-serif;");
        out.println("text-align: center;}");
        out.println("</style></head>");
        out.println("<body>");
        out.println("<h1>" + rowCnt + "개의 WIFI 정보를 정상적으로 저장하였습니다." + "</h1>");
        out.println("<p><a href=\"/\">" + "홈으로 가기" + "</a></p>");
        out.println("</body></html>");

    }

}
