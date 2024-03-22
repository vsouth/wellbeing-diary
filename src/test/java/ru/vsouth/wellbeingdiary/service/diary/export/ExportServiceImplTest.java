package ru.vsouth.wellbeingdiary.service.diary.export;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.diary.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.Grade;
import ru.vsouth.wellbeingdiary.model.diary.PartOfDay;
import ru.vsouth.wellbeingdiary.model.diary.WeatherEntry;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExportServiceImplTest {
    private ExportServiceImpl exportService;

    @BeforeEach
    void setUp() {
        exportService = new ExportServiceImpl();
    }

    @Test
    public void testExportEntriesToXls() throws IOException {
        List<DiaryEntryResponse> entries = Arrays.asList(
                new DiaryEntryResponse(3, 2, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 12.1, "Дождь"),
                        new Date(11111), "", Grade.BAD, Grade.GOOD, Grade.BAD),
                new DiaryEntryResponse(3, 2, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 10.1, "Переменная облачность"),
                        new Date(11111), "", Grade.EXCELLENT, Grade.GOOD, Grade.EXCELLENT)
        );

        MockHttpServletResponse response = new MockHttpServletResponse();

        exportService.exportEntriesToXls(entries, response);

        assertNotNull(response.getContentAsByteArray());
        assertTrue(response.getContentAsByteArray().length > 0);
    }
    @Test
    public void testExportOpenEntriesToXls() throws IOException {
        List<OpenDiaryEntryResponse> entries = Arrays.asList(
                new OpenDiaryEntryResponse(3, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 12.1, "Ясно"),
                        new Date(11111), Grade.BAD, Grade.AWFUL, Grade.BAD),
                new OpenDiaryEntryResponse(3, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 10.1, "Облачно"),
                        new Date(11111), Grade.EXCELLENT, Grade.GOOD, Grade.EXCELLENT)
        );

        MockHttpServletResponse response = new MockHttpServletResponse();

        exportService.exportOpenEntriesToXls(entries, response);

        assertNotNull(response.getContentAsByteArray());
        assertTrue(response.getContentAsByteArray().length > 0);
    }
}