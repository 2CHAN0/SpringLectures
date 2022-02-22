package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseHtmlServelet", urlPatterns = "/response-html")
public class ResponseHtmlServelet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Content-Type: text/html;charset=utf8
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        resp.getWriter().println("<html>");
        resp.getWriter().println("<body>");
        resp.getWriter().println("<div>안녕</div>");
        resp.getWriter().println("</body>");
        resp.getWriter().println("</html>");

    }
}
