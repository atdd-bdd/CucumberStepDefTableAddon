package com.atddbdd;

import com.csvreader.CsvReader;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


import static com.atddbdd.StepDefinitionHelpers.setUseFieldFromEntryMap;
import static org.junit.Assert.assertArrayEquals;



class ExampleTable {

    public String name1;
    public String name2;

    @Override
    public String toString() {
        return "ExampleTable{" +
                "name1='" + name1 + '\'' +
                ", name2='" + name2 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExampleTable that = (ExampleTable) o;
        return Objects.equals(name1, that.name1) && Objects.equals(name2, that.name2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name1, name2);
    }
}

class ExampleTableUsedFields {
    public boolean name1;
    public boolean name2;

    @Override
    public String toString() {
        return "ExampleTableUsedFields{" +
                "field1=" + name1 +
                ", fields2=" + name2 +
                '}';
    }
}
public class ExampleStepDefinitions {

    ExampleTableUsedFields exampleTableUsedFields;
    TableHelper<ExampleTable> tableHelperExampleTable =
            new TableHelper<ExampleTable>(ExampleTable::new);
    @DataTableType
    public ExampleTable inputExampleTable(Map<String, String> entry) {
        ExampleTable exampleTable = tableHelperExampleTable.setTableFromEntryMap(entry);
        exampleTableUsedFields = setUseFieldFromEntryMap(entry);
        return exampleTable;
    }


    @Given("table is")
    public void table_is(List<ExampleTable> dataTable) {
        for (ExampleTable exampleTable : dataTable) {
            System.out.println(exampleTable.toString());
        }
    }

    String filenameCSV;
    CsvReader csvReader;

    @Given("CSV file is")
    public void csv_file_is(List<String> datatable) {
        filenameCSV = datatable.get(0);
    }

    List<ExampleTable> actual;
    @When("CSV file is read")
    public void csv_file_is_read() {
        try {
            csvReader = new CsvReader(filenameCSV);
            actual = tableHelperExampleTable.readCSV(csvReader);

        } catch (FileNotFoundException e) {
            System.err.println("Cannot find " + filenameCSV);
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("Error when reading " + filenameCSV);
            throw new RuntimeException(e);
        }
    }

    @Then("data matches")
    public void data_matches(List<ExampleTable> dataTable)
        {
        System.out.println(" Expected ");
        for (ExampleTable exampleTable : dataTable) {
            System.out.println(exampleTable.toString());
        }
        System.out.println(" Actual ");
        for (ExampleTable exampleTable : actual) {
            System.out.println(exampleTable.toString());
        }
        assertArrayEquals(dataTable.toArray(), actual.toArray());
    }

}

