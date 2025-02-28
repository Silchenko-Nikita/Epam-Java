package com.epam.rd.autotasks.springstatefulcalc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/calc")
public class ExpressionCalculatorController {

    private final Calculator calculator = new Calculator();

    @PutMapping("/expression")
    public ResponseEntity<String> setExpression(
            @RequestBody String expression, HttpSession session) {
        if (!calculator.isValidExpression(expression)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid expression format");
        }

        @SuppressWarnings("unchecked")
        Map<String, String> varsCache = (Map<String, String>) session.getAttribute("varsCache");
        if (varsCache == null) {
            varsCache = new HashMap<>();
            session.setAttribute("varsCache", varsCache);
        }
        session.setAttribute("expression", expression);

        HttpStatus status = session.getAttribute("expression") == null
                ? HttpStatus.CREATED
                : HttpStatus.OK;
        return ResponseEntity.status(status)
                .header("Location", "/calc/expression")
                .body("Expression set");
    }

    @PutMapping("/{variable}")
    public ResponseEntity<String> setVariable(
            @PathVariable("variable") String variable,
            @RequestBody String value,
            HttpSession session) {
        if (!variable.matches("[a-z]")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid variable name");
        }

        if (!value.matches("-?\\d+") && !value.matches("[a-z]")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid variable value");
        }

        if (value.matches("-?\\d+")) {
            int intValue = Integer.parseInt(value);
            if (intValue < -10000 || intValue > 10000) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Variable value out of range [-10000, 10000]");
            }
        }

        @SuppressWarnings("unchecked")
        Map<String, String> varsCache = (Map<String, String>) session.getAttribute("varsCache");
        if (varsCache == null) {
            varsCache = new HashMap<>();
            session.setAttribute("varsCache", varsCache);
        }
        varsCache.put(variable, value);

        HttpStatus status = varsCache.containsKey(variable)
                ? HttpStatus.OK
                : HttpStatus.CREATED;
        return ResponseEntity.status(status)
                .header("Location", "/calc/" + variable)
                .body("Variable set");
    }

    @DeleteMapping("/expression")
    public ResponseEntity<Void> deleteExpression(HttpSession session) {
        session.removeAttribute("expression");
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{variable}")
    public ResponseEntity<Void> deleteVariable(@PathVariable("variable") String variable, HttpSession session) {
        @SuppressWarnings("unchecked")
        Map<String, String> varsCache = (Map<String, String>) session.getAttribute("varsCache");
        if (varsCache != null) {
            varsCache.remove(variable);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/result")
    public ResponseEntity<?> getResult(HttpSession session) {
        String expression = (String) session.getAttribute("expression");
        @SuppressWarnings("unchecked")
        Map<String, String> varsCache = (Map<String, String>) session.getAttribute("varsCache");

        if (expression == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Expression is not set");
        }

        try {
            Numeric result = calculator.calculate(expression, varsCache);
            return ResponseEntity.ok(result.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Cannot evaluate expression: " + e.getMessage());
        }
    }
}

