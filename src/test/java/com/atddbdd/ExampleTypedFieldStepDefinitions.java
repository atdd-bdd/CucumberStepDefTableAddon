package com.atddbdd;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;

import java.util.List;
import java.util.Map;

class MyClass {
    String value;
    public MyClass(String value)
    {
        this.value = value;
    }
    public static MyClass parse(String value){
        MyClass result = new MyClass(value);
        return result;
    }
}
class TypedTable {
    public String aString;
    public Number aNumber;
    public MyClass aMyClass;

    @Override
    public String toString() {
        return "TypedTable{" +
                "string='" + aString + '\'' +
                ", number=" + aNumber +
                ", myClass=" + aMyClass +
                '}';
    }
}
public class ExampleTypedFieldStepDefinitions {


    @Given("typed table is")
    public void typed_table_is(List<TypedTable> dataTable) {
        System.out.println("TypedTable is " );
        for (TypedTable typedTable: dataTable){
            System.out.println(" Table is " + typedTable.toString());
        }
    }
    @DataTableType
    public TypedTable inputTypeTable(Map<String, String> entry) {
        TypedTable typedTable = setTypeTableFromEntryMap(entry);
        return typedTable;
    }

    private TypedTable setTypeTableFromEntryMap(Map<String, String> entryMap) {
        TypedTable typedTable = new TypedTable();
        for (Map.Entry<String, String> entry : entryMap.entrySet()) {
            StepDefinitionHelpers.setTypeFieldFromKeyValue(typedTable, entry.getKey(), entry.getValue());
        }
        return typedTable;  }

}
