package com.salmon.test.models;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class ExampleModel {
    private final String title;
    private final String colour;
    private final String topic;
    private final String mood;
    private final boolean section;
    private final boolean featuredColourSection;
    private final boolean meetTheExpert;
}
