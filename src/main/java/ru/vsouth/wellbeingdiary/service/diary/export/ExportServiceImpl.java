package ru.vsouth.wellbeingdiary.service.diary.export;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.diary.OpenDiaryEntryResponse;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Сервис экспорта записей в формате .xls
 */
@Service
public class ExportServiceImpl implements ExportService {
    /**
     * Метод для экспорта записей в формат .xls
     *
     * @param entries  Список записей для экспорта
     * @param response Объект HttpServletResponse для отправки файла
     * @throws IOException Исключение в случае ошибки ввода-вывода
     */
    @Override
    public void exportEntriesToXls(List<DiaryEntryResponse> entries, HttpServletResponse response) throws IOException {
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            HSSFSheet sheet = workbook.createSheet("Diary Entries");

            String[] headers = {"№", "Создано", "Заметка", "Настроение", "Самочувствие", "Активность", "Пульс",
                    "Верхнее давление", "Нижнее давление", "Температура на улице", "Тип погоды"};
            createHeaders(sheet, headers);

            int rowNum = 1;
            for (DiaryEntryResponse entry : entries) {
                Row row = sheet.createRow(rowNum++);
                fillRowWithData(row, entry);
            }

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=diary_entries.xls");

            try (ServletOutputStream outputStream = response.getOutputStream()) {
                workbook.write(outputStream);
            }
        }
    }

    /**
     * Метод для экспорта открытых записей в формат .xls
     *
     * @param entries  Список открытых записей для экспорта
     * @param response Объект HttpServletResponse для отправки файла
     * @throws IOException Исключение в случае ошибки ввода-вывода
     */
    @Override
    public void exportOpenEntriesToXls(List<OpenDiaryEntryResponse> entries, HttpServletResponse response) throws IOException {
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            HSSFSheet sheet = workbook.createSheet("Open Diary Entries");

            String[] headers = {"№", "Создано", "Настроение", "Самочувствие", "Активность", "Пульс",
                    "Верхнее давление", "Нижнее давление", "Температура на улице", "Тип погоды"};
            createHeaders(sheet, headers);

            int rowNum = 1;
            for (OpenDiaryEntryResponse entry : entries) {
                Row row = sheet.createRow(rowNum++);
                fillRowWithData(row, entry);
            }

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=open_diary_entries.xls");

            try (ServletOutputStream outputStream = response.getOutputStream()) {
                workbook.write(outputStream);
            }
        }
    }

    /**
     * Вспомогательный метод для создания заголовков таблицы
     */
    private void createHeaders(Sheet sheet, String[] headers) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
    }

    /**
     * Вспомогательный метод для заполнения строки таблицы информацией из обычной записи
     */
    private void fillRowWithData(Row row, DiaryEntryResponse entry) {
        row.createCell(0).setCellValue(row.getRowNum());
        row.createCell(1).setCellValue(entry.getCreatedAt().toString());
        row.createCell(2).setCellValue(entry.getEntryText() != null ? entry.getEntryText() : "");
        row.createCell(3).setCellValue(entry.getMood() != null ? entry.getMood().toString() : "");
        row.createCell(4).setCellValue(entry.getStateOfHealth() != null ? entry.getStateOfHealth().toString() : "");
        row.createCell(5).setCellValue(entry.getActivityAmount() != null ? entry.getActivityAmount().toString() : "");

        if (entry.getHealthEntry() != null) {
            row.createCell(6);
            setCellValueOrBlank(row.getCell(6), entry.getHealthEntry().getHeartRate());

            row.createCell(7);
            setCellValueOrBlank(row.getCell(7), entry.getHealthEntry().getSystolicBloodPressure());

            row.createCell(8);
            setCellValueOrBlank(row.getCell(8), entry.getHealthEntry().getDiastolicBloodPressure());
        } else {
            for (int i = 6; i <= 8; i++) {
                row.createCell(i).setCellValue("");
            }
        }

        if (entry.getWeatherEntry() != null) {
            row.createCell(9);
            setCellValueOrBlank(row.getCell(9), entry.getWeatherEntry().getTemperature());
            row.createCell(10).setCellValue(entry.getWeatherEntry().getWeatherType() != null ? entry.getWeatherEntry().getWeatherType() : "");
        } else {
            for (int i = 9; i <= 10; i++) {
                row.createCell(i).setCellValue("");
            }
        }
    }

    /**
     * Вспомогательный метод для создания заголовков таблицы информацией из открытой записи
     */
    private void fillRowWithData(Row row, OpenDiaryEntryResponse entry) {
        row.createCell(0).setCellValue(row.getRowNum());
        row.createCell(1).setCellValue(entry.getCreatedAt().toString());
        row.createCell(2).setCellValue(entry.getMood() != null ? entry.getMood().toString() : "");
        row.createCell(3).setCellValue(entry.getStateOfHealth() != null ? entry.getStateOfHealth().toString() : "");
        row.createCell(4).setCellValue(entry.getActivityAmount() != null ? entry.getActivityAmount().toString() : "");

        if (entry.getHealthEntry() != null) {
            row.createCell(5);
            setCellValueOrBlank(row.getCell(5), entry.getHealthEntry().getHeartRate());

            row.createCell(6);
            setCellValueOrBlank(row.getCell(6), entry.getHealthEntry().getSystolicBloodPressure());

            row.createCell(7);
            setCellValueOrBlank(row.getCell(7), entry.getHealthEntry().getDiastolicBloodPressure());
        } else {
            for (int i = 5; i <= 7; i++) {
                row.createCell(i).setCellValue("");
            }
        }

        if (entry.getWeatherEntry() != null) {
            row.createCell(8);
            setCellValueOrBlank(row.getCell(8), entry.getWeatherEntry().getTemperature());
            row.createCell(9).setCellValue(entry.getWeatherEntry().getWeatherType() != null ? entry.getWeatherEntry().getWeatherType() : "");
        } else {
            for (int i = 8; i <= 9; i++) {
                row.createCell(i).setCellValue("");
            }
        }
    }

    /**
     * Вспомогательный метод для установления пустого значения числовым (integer) ячейкам
     */
    private void setCellValueOrBlank(Cell cell, Integer value) {
        if (value != null) {
            cell.setCellValue(value);
        } else {
            cell.setCellType(CellType.BLANK);
        }
    }

    /**
     * Вспомогательный метод для установления пустого значения числовым (double) ячейкам
     */
    private void setCellValueOrBlank(Cell cell, Double value) {
        if (value != null) {
            cell.setCellValue(value);
        } else {
            cell.setCellType(CellType.BLANK);
        }
    }
}
