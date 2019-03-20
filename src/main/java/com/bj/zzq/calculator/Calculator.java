package com.bj.zzq.calculator;


import java.util.Stack;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/3/18
 * @Description: 计算器，只支持1-9得+ - * /，包含括号
 */
public class Calculator {
    private String input;
    private String suffixOutput;
    private String output;
    private Stack<Character> stack;

    public Calculator(String input) {
        this.input = input;
        stack = new Stack<Character>();
        suffixOutput = "";
        output = "";
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
        for (int i = 0; i < input.length(); i++) {
            char item = input.charAt(i);
            System.out.println("For " + item + ' ' + stack.toString());
            switch (item) {
                case ' ':
                    break;
                case '+':
                case '-':
                    if (!stack.isEmpty()) {
                        char itemInStack;
                        do {
                            itemInStack = stack.pop();
                            if (itemInStack == '(') {
                                stack.push(itemInStack);
                                break;
                            } else {
                                suffixOutput += itemInStack;
                            }
                        } while (!stack.isEmpty());
                    }
                    stack.push(item);
                    break;

                case '*':
                case '/':
                    if (!stack.isEmpty()) {
                        char itemInStack2;
                        do {
                            itemInStack2 = stack.pop();
                            if (itemInStack2 == '(' || itemInStack2 == '+' || itemInStack2 == '-') {
                                stack.push(itemInStack2);
                                break;
                            } else {
                                suffixOutput += itemInStack2;
                            }
                        } while (!stack.isEmpty());
                    }
                    stack.push(item);
                    break;
                case '(':
                    stack.push(item);
                    break;
                case ')':
                    char pop = ' ';
                    while (!stack.isEmpty() && !('(' == (pop = stack.pop()))) {
                        suffixOutput += pop;
                    }
                    break;
                default:
                    suffixOutput += item;
                    break;
            }
        }
        while (!stack.isEmpty()) {
            suffixOutput += stack.pop();
        }

    }

    public void calculate() {
        Stack<Integer> tempStackNumber = new Stack<Integer>();
        for (int i = 0; i < suffixOutput.length(); i++) {
            char c = suffixOutput.charAt(i);
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                int result;
                int num2 = tempStackNumber.pop();
                int num1 = tempStackNumber.pop();
                switch (c) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                    default:
                        throw new IllegalStateException("无法识别的符号！");
                }
                tempStackNumber.push(result);
            } else {
                tempStackNumber.push(Character.getNumericValue(c));
            }
        }
        output = String.valueOf(tempStackNumber.pop());
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator("1+2*(4+5*3)");
        calculator.convertToSuffixExp();
        calculator.calculate();
        System.out.println(calculator.output);
    }
}
