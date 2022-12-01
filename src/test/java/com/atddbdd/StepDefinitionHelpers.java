package com.atddbdd;

import java.lang.reflect.Field;
import java.util.Map;

public class StepDefinitionHelpers {
    static public void setFieldFromKeyValue(Object obj, String key, String value) {
        Class<?> c = obj.getClass();
        Field field;
        String camelCaseKey = makeCamel(key);
        if (value == null) {
            value = "";
            System.err.println("Key value is null " + key + "=" + value);
        }
        try {
            field = c.getField(camelCaseKey);
        } catch (NoSuchFieldException e) {
            System.err.println( " Cannot match field name to column name " +
                    camelCaseKey + " " + key);
            throw new RuntimeException(e);
        }
        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    static public void setBooleanFromValue(Object obj, String key, String value) {
        Class<?> c = obj.getClass();
        Field field;
        String camelCaseKey = makeCamel(key);
        boolean result;
        //noinspection RedundantIfStatement
        if (value == null) {
            result = false;
        } else
            result = true;
        try {
            field = c.getField(camelCaseKey);
        } catch (NoSuchFieldException e) {
            System.err.println(" Cannot match field name to column name " +
                    camelCaseKey + " " + key);
            throw new RuntimeException(e);
        }
        field.setAccessible(true);
        try {
            field.set(obj, result);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    static public String makeCamel(String value) {
        StringBuilder result = new StringBuilder();
        boolean convertToUpper = false;
        for (int i = 0; i < value.length(); i++) {
            Character c = value.charAt(i);
            if (i == 0) {
                result.append(Character.toLowerCase(c));
                continue;
            }
            if (c == ' ') {
                convertToUpper = true;
                continue;
            }
            if (convertToUpper) {
                result.append(Character.toUpperCase(c));
                convertToUpper = false;
                continue;
            }
            result.append(c);
        }
        return result.toString();


    }

    static public ExampleTableUsedFields setUseFieldFromEntryMap(Map<String, String> entryMap) {
        ExampleTableUsedFields exampleTableUsedFields = new ExampleTableUsedFields();
        for (Map.Entry<String, String> entry : entryMap.entrySet()) {
            StepDefinitionHelpers.setBooleanFromValue(exampleTableUsedFields, entry.getKey(), entry.getValue());
        }
        System.out.println(" Test Use " +exampleTableUsedFields.toString());
        return exampleTableUsedFields;
    }


    public static void setTypeFieldFromKeyValue(Object obj, String key, String value) {
        Class<?> c = obj.getClass();
        Field field;
        String camelCaseKey = makeCamel(key);
        if (value == null) {
            value = "";
            System.err.println("Key value is null " + key + "=" + value);
        }
        try {
            field = c.getField(camelCaseKey);
        } catch (NoSuchFieldException e) {
            System.err.println( " Cannot match field name to column name " +
                    camelCaseKey + " " + key);
            throw new RuntimeException(e);
        }
        field.setAccessible(true);
        Class whichClass = field.getClass();
        String typeName = whichClass.getTypeName();
        String name = field.getName();
        switch(typeName)
        {
            default:
                System.out.println("Name " + name + " type " + typeName);
        }
/*
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
*/
    }

}
