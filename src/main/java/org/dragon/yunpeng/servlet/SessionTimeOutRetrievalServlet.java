package org.dragon.yunpeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class SessionTimeOutRetrievalServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    System.out.println("SessionTimeOutRetrievalServlet doGet() is invoked.");

    int timeoutInSeconds = request.getSession().getMaxInactiveInterval();
    System.out.println("timeoutInSeconds: " + timeoutInSeconds);

    setResponeHeaders(response);

    PrintWriter pw = response.getWriter();

    JSONObject jsonOutput = new JSONObject();

    try {
      jsonOutput.put("sessionTimeOut", timeoutInSeconds);

      pw.println(jsonOutput.toString());
    } catch (JSONException e) {

      e.printStackTrace();
      pw.println("Error: " + e.getMessage());
    }

  }

  private void setResponeHeaders(HttpServletResponse response) {
    response.setContentType("text/plain; charset=utf-8");
    response.setCharacterEncoding("utf-8");
    response.setHeader("Cache-Control", "no-store, no-cache");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", System.currentTimeMillis());
  }
}
