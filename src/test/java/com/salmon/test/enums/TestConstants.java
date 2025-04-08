package com.salmon.test.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
* Define Constants.
* Create a new enum Class for defining new constants
*/


//@AllArgsConstructor
//public enum TestConstants {
//    SALMON_TEST_FRAMEWORK("salmon test framework"),
//    TEST_COUNT("test count");
//
//    @Getter
//    String cssClass;
//}



public enum TestConstants {
    SALMON_TEST_FRAMEWORK("salmon test framework"),
    TEST_COUNT("test count");

    @Getter
    private final String cssClass;

    // Define a constructor explicitly for the enum
    TestConstants(String cssClass) {
        this.cssClass = cssClass;
    }
}


