package me.lazytechwork.algods.transformers;

import me.lazytechwork.algods.utils.Stack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.regex.Pattern;

public class ShuntingYardTransformer {
    public static final Map<String, Integer> OPERATOR_PRIORITIES = Map.of(
            "^", -1,
            "*", 2,
            "/", 2,
            "+", 0,
            "-", 0
    );

    public static final Pattern ONLY_DIGITS = Pattern.compile("\\d+");

    public static @NotNull String infixToPostfixNotation(@NotNull String input) {
        String[] tokens = input.split(" ");
        StringBuilder outputBuffer = new StringBuilder(input.length());
        Stack<String> waitingStack = new Stack<>();

        for (String token : tokens) {
            if (ONLY_DIGITS.matcher(token).matches()) outputBuffer.append(token);

            else if (OPERATOR_PRIORITIES.containsKey(token)) {
                String top = waitingStack.peek();
                if (top != null) {
                    Integer leftOperand = OPERATOR_PRIORITIES.get(top);
                    Integer rightOperand = OPERATOR_PRIORITIES.get(token);

                    if (!top.equals("(") && (leftOperand == null ||
                            Math.abs(leftOperand) > Math.abs(rightOperand) ||
                            (Math.abs(leftOperand) == Math.abs(rightOperand) && leftOperand >= 0)
                    )) {
                        outputBuffer.append(waitingStack.pop());
                    }
                }
                waitingStack.push(token);
            } else if (token.equals(")"))
                for (String top = waitingStack.pop(); !top.equals("("); top = waitingStack.pop())
                    outputBuffer.append(top);

            else waitingStack.push(token);
        }

        while (!waitingStack.isEmpty()) outputBuffer.append(waitingStack.pop());
        return outputBuffer.toString();
    }
}
