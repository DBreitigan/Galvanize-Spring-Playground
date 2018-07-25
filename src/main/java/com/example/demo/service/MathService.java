package com.example.demo.service;

import com.example.demo.model.Shape;
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


    public String calculateVolume(Integer x, Integer y, Integer z) {
        int volume = x * y * z;
        return "The volume of a " + x + "x" + y + "x" + z + " rectangle is " + volume;
    }

    public String calculateArea(Shape shape) {
        if (shape.getType().equalsIgnoreCase("circle")) {
            return calculateCircleArea(shape);
        } else if (shape.getType().equalsIgnoreCase("rectangle")) {
            return calculateRectangleArea(shape);
        }
        return "Invalid type";
    }

    private String calculateRectangleArea(Shape shape) {
        if (shape.getRadius() != null || shape.getHeight() == null || shape.getWidth() == null) {
            return "Invalid";
        }
        Integer area = shape.getHeight() * shape.getWidth();
        return "Area of a " + shape.getWidth() + "x" + shape.getHeight() + " rectangle is " + area;
    }

    private String calculateCircleArea(Shape shape) {
        if (shape.getRadius() == null || shape.getHeight() != null || shape.getWidth() != null) {
            return "Invalid";
        }
        double area = Math.PI * Math.pow(shape.getRadius(), 2);
        return "Area of a circle with a radius of " + shape.getRadius() + " is " + area;
    }
}
