package com.example.h2flywaydemo;

/**
 * Compile time-constants
 */
public class Constants {
    // Compile-time constant for configuring JPA Entity @Table(schema=Constants.SCHEMA) value.
    // This value needs to align with library.properties value, user.settings.schema_name, which is used by the flyway scripts.
    // Note: both this value and the library.properties value could potentially be overriden at compile time using
    // a Maven filtered resource plugin.
    public static final String SCHEMA="application";
}
