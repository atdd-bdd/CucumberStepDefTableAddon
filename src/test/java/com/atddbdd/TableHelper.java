package com.atddbdd;

import com.csvreader.CsvReader;
import io.cucumber.java.DataTableType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.atddbdd.StepDefinitionHelpers.setUseFieldFromEntryMap;

public class TableHelper <T> {

        private Supplier<T> supplier;

        TableHelper(Supplier<T> supplier) {
            this.supplier = supplier;
        }
    public T input(Map<String, String> entry) {
        T exampleTable = this.setTableFromEntryMap(entry);
          return exampleTable;
    }
     public T setExampleTableFromEntryMap(Map<String, String> entryMap) {
        T exampleTable = supplier.get();
        for (Map.Entry<String, String> entry : entryMap.entrySet()) {
            StepDefinitionHelpers.setTypeFieldFromKeyValue(exampleTable, entry.getKey(), entry.getValue());
        }
        return exampleTable;
    }
    public List<T> readCSV(CsvReader csvReader) throws IOException {
        List<List<String>> result = new ArrayList<>();
        int itemCount = 0;
        while (csvReader.readRecord()) {
            List<String> line = new ArrayList<>();
            itemCount = csvReader.getColumnCount();
            for (int i = 0; i < itemCount; i++) {
                String item = csvReader.get(i);
                line.add(item);
            }
            result.add(line);
        }
        int lineCount = result.size();
        Map<String, String> entry = new HashMap<>();
        List<T> results = new ArrayList<>();
        for (int i = 1; i < lineCount; i++) {
            for (int j = 0; j < itemCount; j++) {
                String header = result.get(0).get(j);
                String value = result.get(i).get(j);
                entry.put(header, value);
            }
            T exampleTable= setTableFromEntryMap(entry);
            results.add(exampleTable);
        }
        return results;
    }
    public T setTableFromEntryMap(Map<String, String> entryMap) {
        T exampleTable = supplier.get();
        for (Map.Entry<String, String> entry : entryMap.entrySet()) {
            StepDefinitionHelpers.setTypeFieldFromKeyValue(exampleTable, entry.getKey(), entry.getValue());
        }
        return exampleTable;
    }

}
