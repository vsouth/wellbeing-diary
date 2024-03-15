package ru.vsouth.wellbeingdiary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryRequest;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.diary.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.Grade;
import ru.vsouth.wellbeingdiary.model.diary.HealthEntry;
import ru.vsouth.wellbeingdiary.service.diary.DiaryManagementService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DiaryControllerTest {
    @InjectMocks
    private DiaryController diaryController;

    @Mock
    private DiaryManagementService diaryManagementService;

    private MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(diaryController).build();
    }

    @Test
    void testGetAllOpenEntries() throws Exception {
        List<OpenDiaryEntryResponse> mockOpenEntries = new ArrayList<>();
        mockOpenEntries.add(new OpenDiaryEntryResponse(1, null, null, new Date(1212121212121L), Grade.AWFUL, Grade.AWFUL, Grade.AWFUL));
        mockOpenEntries.add(new OpenDiaryEntryResponse(2, new HealthEntry(70,80,90), null, new Date(1212121212121L), Grade.AWFUL, Grade.AWFUL, Grade.AWFUL));
        when(diaryManagementService.getOpenDiaryEntries()).thenReturn(mockOpenEntries);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/diary/open_list"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllEntries() throws Exception {
        List<DiaryEntryResponse> mockEntries = new ArrayList<>();
        mockEntries.add(new DiaryEntryResponse(1, 1,null, null, new Date(1212121212121L), "AAA", Grade.AWFUL, Grade.AWFUL, Grade.AWFUL));
        mockEntries.add(new DiaryEntryResponse(2, 1,new HealthEntry(70,80,90), null, new Date(1212121212121L), null, Grade.AWFUL, Grade.AWFUL, Grade.AWFUL));
        when(diaryManagementService.getDiaryEntries()).thenReturn(mockEntries);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/diary/list"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindEntryById() throws Exception {
        int entryId = 1;
        DiaryEntryResponse mockEntry = new DiaryEntryResponse(1, 1, null, null, new Date(1212121212121L), "AAA", Grade.AWFUL, Grade.AWFUL, Grade.AWFUL);
        when(diaryManagementService.getDiaryEntry(entryId)).thenReturn(mockEntry);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/diary/" + entryId))
                .andExpect(status().isOk());
    }

    @Test
    void testFindAllEntriesByUserId() throws Exception {
        int userId = 1;
        List<DiaryEntryResponse> mockEntries = new ArrayList<>();
        mockEntries.add(new DiaryEntryResponse(1, 1, null, null, new Date(1212121212121L), "AAA", Grade.AWFUL, Grade.AWFUL, Grade.AWFUL));
        mockEntries.add(new DiaryEntryResponse(2, 1, new HealthEntry(70, 80, 90), null, new Date(1212121212121L), null, Grade.AWFUL, Grade.AWFUL, Grade.AWFUL));
        when(diaryManagementService.getDiaryEntries(userId)).thenReturn(mockEntries);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/diary/" + userId + "/list"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindEntryByUserId() throws Exception {
        int userId = 1;
        int diaryEntryId = 1;
        DiaryEntryResponse mockEntry = new DiaryEntryResponse(1, 1, null, null, new Date(1212121212121L), "AAA", Grade.AWFUL, Grade.AWFUL, Grade.AWFUL);
        when(diaryManagementService.getDiaryEntry(userId, diaryEntryId)).thenReturn(mockEntry);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/diary/" + userId + "/" + diaryEntryId))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateEntry() throws Exception {
        DiaryEntryRequest diaryEntryRequest = new DiaryEntryRequest(1, 1, new HealthEntry(70, 80, 90), null, new Date(1212121212121L), null, Grade.AWFUL, Grade.AWFUL, Grade.AWFUL);
        DiaryEntryResponse updatedEntry = new DiaryEntryResponse(1, 1, new HealthEntry(70, 80, 90), null, new Date(1212121212121L), null, Grade.AWFUL, Grade.AWFUL, Grade.AWFUL);
        when(diaryManagementService.updateDiaryEntry(diaryEntryRequest)).thenReturn(updatedEntry);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/diary/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(diaryEntryRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteEntry() throws Exception {
        int entryId = 1;
        DiaryEntryResponse deletedEntry = new DiaryEntryResponse(1, 1, null, null, new Date(1212121212121L), "AAA", Grade.AWFUL, Grade.AWFUL, Grade.AWFUL);
        when(diaryManagementService.deleteDiaryEntry(entryId)).thenReturn(deletedEntry);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/diary/delete/" + entryId))
                .andExpect(status().isOk());
    }

    @Test
    void testAddEntry() throws Exception {
        DiaryEntryRequest diaryEntryRequest = new DiaryEntryRequest(1, 1, new HealthEntry(70, 80, 90), null, new Date(1212121212121L), "AAA", Grade.AWFUL, Grade.AWFUL, Grade.AWFUL);
        DiaryEntryResponse addedEntry = new DiaryEntryResponse(1, 1, new HealthEntry(70, 80, 90), null, new Date(1212121212121L), "AAA", Grade.AWFUL, Grade.AWFUL, Grade.AWFUL);
        when(diaryManagementService.addDiaryEntry(diaryEntryRequest)).thenReturn(addedEntry);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/diary/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(diaryEntryRequest)))
                .andExpect(status().isOk());
    }
}