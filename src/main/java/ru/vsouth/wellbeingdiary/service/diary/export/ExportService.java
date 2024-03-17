package ru.vsouth.wellbeingdiary.service.diary.export;

import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.diary.OpenDiaryEntryResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ExportService {
    void exportEntriesToXls(List<DiaryEntryResponse> entries, HttpServletResponse response) throws IOException;
    void exportOpenEntriesToXls(List<OpenDiaryEntryResponse> entries, HttpServletResponse response) throws IOException;
}