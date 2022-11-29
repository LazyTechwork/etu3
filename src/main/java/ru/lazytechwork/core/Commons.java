package ru.lazytechwork.core;

import java.util.regex.Pattern;

public class Commons {
    public static final Pattern ONLY_DIGITS = Pattern.compile("^(\\+|\\-)?\\d+$");
}
