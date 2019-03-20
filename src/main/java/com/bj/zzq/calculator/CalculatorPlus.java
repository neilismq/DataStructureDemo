package com.bj.zzq.calculator;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/18
 * @Description:  完整的计算器，支持整数、小数的+ - * /，包含括号
 */
public class CalculatorPlus {
    private String input;
    private Queue<String> suffixOutput;
    private String output;
    private Stack<String> stack;
    private Queue<String> inputQueue;

    public CalculatorPlus(String input) {
        this.input = input;
        stack = new Stack<String>();
        suffixOutput = new LinkedList<String>();
        output = "";
        inputQueue = new LinkedList<String>();
    }

    public void pullInQueue() {
        input = input.replace(" ", "");
        String reg = "([0-9\\.]+|\\+|\\-|\\*|/|\\(|\\))";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String group = matcher.group();
            inputQueue.offer(group);
        }
    }

    public void convertToSuffixExp() {
        /**
         * 3+2*(1+5)
         * 3
         * 3 +
         * 32 +
         * 32 +*
         * 32 +*(
         * 321 +*(
         * 321 +*(+
         * 3215 +*(+
         * 3215+  +*
         * 3215+*  +
         * 3215+*+
         */
        while (!inputQueue.isEmpty()) {
            String item = inputQueue.poll();
            System.out.println("For " + item + ' ' + stack.toString());
            if ("+".equals(item) || "-".equals(item)) {
                if (!stack.isEmpty()) {
                    String itemInStack;
                    do {
                        itemInStack = stack.pop();
                        if (itemInStack.equals("(")) {
                            stack.push(itemInStack);
                            break;
                        } else {
                            suffixOutput.offer(itemInStack);
                        }
                    } while (!stack.isEmpty());
                }
                stack.push(item);
            } else if ("*".equals(item) || "/".equals(item)) {

                if (!stack.isEmpty()) {
                    String itemInStack2;
                    do {
                        itemInStack2 = stack.pop();
                        if (itemInStack2.equals("(") || itemInStack2.equals("+") || itemInStack2.equals("-")) {
                            stack.push(itemInStack2);
                            break;
                        } else {
                            suffixOutput.offer(itemInStack2);
                        }
                    } while (!stack.isEmpty());
                }
                stack.push(item);
            } else if ("(".equals(item)) {
                stack.push(item);
            } else if (")".equals(item)) {
                String pop = "";
                while (!stack.isEmpty() && !("(".equals(pop = stack.pop()))) {
                    suffixOutput.offer(pop);
                }
            } else {
                suffixOutput.offer(item);
            }
        }
        while (!stack.isEmpty()) {
            suffixOutput.offer(stack.pop());
        }
    }

    public void calculate() {
        pullInQueue();
        convertToSuffixExp();
        Stack<String> tempStackNumber = new Stack<String>();
        while (!suffixOutput.isEmpty()) {
            String c = suffixOutput.poll();
            String reg = "(\\+|\\-|\\*|/)";
            if (c.matches(reg)) {
                double result;
                double num2 = Double.parseDouble(tempStackNumber.pop());
                double num1 = Double.parseDouble(tempStackNumber.pop());
                if ("+".equals(c)) {
                    result = num1 + num2;
                } else if ("-".equals(c)) {
                    result = num1 - num2;
                } else if ("*".equals(c)) {
                    result = num1 * num2;
                } else if ("\\/".equals("\\/")) {
                    result = num1 / num2;
                } else {
                    throw new IllegalStateException("无法识别的符号！");
                }
                tempStackNumber.push(result + "");
            } else {
                tempStackNumber.push(c);
            }
        }
        output = String.valueOf(tempStackNumber.pop());
        System.out.println(this.output);
    }

    public static void main(String[] args) {
        CalculatorPlus calculator = new CalculatorPlus("0-0.2+0.2/0.2*1*(1+1)");
        calculator.calculate();
    }
}
