package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

public class TestDataReader {
    private static final String JSON_DATA_PATH = "src/main/resources/testdata/testdata.json";
    private static final String EXCEL_DATA_PATH = "src/main/resources/testdata/users.xlsx";

public static Map<String, Object> getJsonData(String key) {
    try {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> allData = mapper.readValue(new File(JSON_DATA_PATH), Map.class);
        Object value = allData.get(key);
        if (value instanceof Map) {
            return (Map<String, Object>) value;
        } else {
            throw new RuntimeException("Value for key '" + key + "' is not a Map");
        }
    } catch (Exception e) {
        throw new RuntimeException("Failed to read JSON test data", e);
    }
}

    public static Object[][] getExcelData(String sheetName) {
        try (FileInputStream fis = new FileInputStream(EXCEL_DATA_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            int rows = sheet.getPhysicalNumberOfRows() - 1;
            int cols = sheet.getRow(0).getPhysicalNumberOfCells();
            Object[][] data = new Object[rows][cols];

            for (int i = 1; i <= rows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < cols; j++) {
                    Cell cell = row.getCell(j);
                    data[i - 1][j] = (cell == null) ? "" : cell.toString();
                }
            }
            return data;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel test data", e);
        }
    }
}
