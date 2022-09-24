package me.lazytechwork.algods.transformers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShuntingYardTransformerTest {
    private void assertPrefixTransformation(String expected, String input) {
        assertEquals(expected, ShuntingYardTransformer.infixToPostfixNotation(input));
    }

    @Test
    void digitPlusDigit() {
        assertPrefixTransformation("12+", "1 + 2");
    }

    @Test
    void digitMinusDigit() {
        assertPrefixTransformation("12-", "1 - 2");
    }

    @Test
    void digitTimesExpression() {
        assertPrefixTransformation("123*-", "1 - 2 * 3");
    }

    @Test
    void expressionDividedByExpression() {
        assertPrefixTransformation("123*64/+-", "1 - 2 * 3 + 6 / 4");
    }

    @Test
    void parenthesesComplexExpressions() {
        assertPrefixTransformation("58*29+*75-8+955**5+-+",
                "5 * 8 * ( 2 + 9 ) + ( 7 - 5 + 8 - 9 * ( 5 * 5 ) + 5 )");
    }

    @Test
    void powExpressions() {
        assertPrefixTransformation("728^9/+", "7 + ( 2 ^ 8 ) / 9");
    }
}
