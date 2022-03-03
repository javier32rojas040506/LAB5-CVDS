package edu.eci.cvds.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.google.gson.Gson;

import edu.eci.cvds.servlet.model.Todo;

public class Service {

    public static Todo getTodo(int id) throws MalformedURLException, IOException {
        URL urldemo = new URL("https://jsonplaceholder.typicode.com/todos/" + id);
        URLConnection yc = urldemo.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        Gson gson = new Gson();
        Todo todo = gson.fromJson(in, Todo.class);
        in.close();
        return todo;
    }

    private static String todoToHTMLRow(Todo todo) {
        return new StringBuilder("<tr>")
                .append("<td>")
                .append(todo.getUserId())
                .append("</td><td>")
                .append(todo.getId())
                .append("</td><td>")
                .append(todo.getTitle())
                .append("</td><td>")
                .append(todo.getCompleted())
                .append("</td>")
                .append("</tr>")
                .toString();
    }

    public static String todosToHTMLTable(List<Todo> todoList) {
        StringBuilder stringBuilder = new StringBuilder("<table>")
                .append("<tr>")
                .append("<th>User Id</th>")
                .append("<th>Id</th>")
                .append("<th>Title</th>")
                .append("<th>Completed</th>")
                .append("</tr>");

        for (Todo todo : todoList) {
            stringBuilder.append(todoToHTMLRow(todo));
        }

        return stringBuilder.append("</table>").toString();
    }
}