package edu.eci.cvds.servlet;

import edu.eci.cvds.servlet.model.Todo;

import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        urlPatterns = "/todo"
)
public class OurServlet extends HttpServlet {
    static final long serialVersionUID = 35L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Writer responseWriter = resp.getWriter();
            Optional<String> optName = Optional.ofNullable(req.getParameter("id"));
            String id = optName.isPresent() && !optName.get().isEmpty() ? optName.get() : "";
            if (id.equals("")) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
            else {
                resp.setStatus(HttpServletResponse.SC_OK);
                Todo todo = Service.getTodo(Integer.parseInt(id));
                List<Todo> listTodo = new ArrayList();
                listTodo.add(todo);
                String table = Service.todosToHTMLTable(listTodo);
                responseWriter.write(table);
                responseWriter.flush();
            }
        }
        catch (NumberFormatException exceptionNumber) {
            resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
        catch (MalformedURLException exceptionUrl) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        catch (Exception ignored) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected  void doPost(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
        try {
            Writer responseWriter = resp.getWriter();
            Optional<String> optName = Optional.ofNullable(req.getParameter("fid"));
            String id = optName.isPresent() && !optName.get().isEmpty() ? optName.get() : "";
            if (id.equals("")) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
            else {
                resp.sendRedirect("todo?id=" + id);
                resp.setStatus(HttpServletResponse.SC_OK);
                Todo todo = Service.getTodo(Integer.parseInt(id));
                List<Todo> listTodo = new ArrayList();
                listTodo.add(todo);
                String table = Service.todosToHTMLTable(listTodo);
                responseWriter.write(table);
                responseWriter.flush();
            }
        }
        catch (NumberFormatException exceptionNumber) {
            resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
        catch (MalformedURLException exceptionUrl) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        catch (Exception ignored) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
