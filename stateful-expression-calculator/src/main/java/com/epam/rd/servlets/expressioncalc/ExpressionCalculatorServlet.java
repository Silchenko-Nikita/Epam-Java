package com.epam.rd.servlets.expressioncalc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/calc/*")
public class ExpressionCalculatorServlet extends HttpServlet {
    Calculator calculator = new Calculator();
    String expression;
    String backupExpression;
    Map<String, String> varsCache = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.getWriter().write("");
            return;
        }

        String getString = pathInfo.split("/")[1];
        if (getString.equals("result")) {
            if (expression == null) {
                resp.setStatus(409);
                expression = backupExpression;
                return;
            }

            try {
                resp.getWriter().write(calculator.calculate(expression, varsCache).toString());
            }finally {
                backupExpression = expression;
                expression = null;
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.getWriter().write("");
            return;
        }

        String putString = pathInfo.split("/")[1];
        if (putString.equals("expression")) {
            StringBuilder body = new StringBuilder();
            try (BufferedReader reader = req.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    body.append(line);
                }
            }

            if (expression == null) {
                varsCache.clear();
                resp.setStatus(201);
            }

            if (!calculator.isValidExpression(body.toString())) {
                resp.setStatus(400);
                return;
            }

            expression = body.toString();

            System.out.println(expression);
        } else if (putString.matches("[a-z]")) {
            StringBuilder body = new StringBuilder();
            try (BufferedReader reader = req.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    body.append(line);
                }
            }

            String varString = body.toString();

            if (!varString.matches("[a-z]")) {
                int varInt = Integer.parseInt(varString);

                if (varInt > 10000 || varInt < -10000) {
                    resp.setStatus(403);
                    return;
                }
            }

            if (!varsCache.containsKey(putString)) {
                resp.setStatus(201);
            }

            varsCache.put(putString, varString);
            System.out.println(putString + " = " + body);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.getWriter().write("");
            return;
        }

        String putString = pathInfo.split("/")[1];
        if (putString.equals("expression")) {
            expression = null;
        } else if (putString.matches("[a-z]")) {
            varsCache.remove(putString);
        }

        resp.setStatus(204);
    }
}