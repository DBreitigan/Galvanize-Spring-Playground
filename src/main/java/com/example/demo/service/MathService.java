package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MathService {


    public String calculate(String operation, Integer x, Integer y) {
        if (operation.equalsIgnoreCase("add")) {
            return add(x, y);
        } else if (operation.equalsIgnoreCase("multiply")) {
            return multiply(x, y);
        } else if (operation.equalsIgnoreCase("subtract")) {
            return subtract(x, y);
        } else if (operation.equalsIgnoreCase("divide")) {
            return divide(x, y);
        }
        return "Invalid operation";
    }

    public String sum(List<Integer> integerList) {
        int result = integerList.stream().mapToInt(i -> i).sum();
        return createSumString(integerList, result);
    }

    private String add(Integer x, Integer y) {
        return createCalculateString(x, "+", y, x + y);
    }

    private String multiply(Integer x, Integer y) {
        return createCalculateString(x, "*", y, x * y);
    }

    private String subtract(Integer x, Integer y) {
        return createCalculateString(x, "-", y, x - y);
    }

    private String divide(Integer x, Integer y) {
        return createCalculateString(x, "/", y, x / y);
    }

    private String createCalculateString(Integer x, String operation, Integer y, Integer result) {
        return x.toString() + " " + operation + " " + y.toString() + " = " + result.toString();
    }

    private String createSumString(List<Integer> integerList, Integer result) {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < integerList.size(); i++) {
            resultString.append(integerList.get(i));
            if (i != integerList.size() - 1) {
                resultString.append(" + ");
            }
        }
        resultString.append(" = ");
        resultString.append(result);
        return resultString.toString();
    }


}
