package ru.vsouth.wellbeingdiary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryRequest;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.diary.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.Grade;
import ru.vsouth.wellbeingdiary.model.diary.HealthEntry;
import ru.vsouth.wellbeingdiary.model.diary.WeatherEntry;
import ru.vsouth.wellbeingdiary.model.user.Role;
import ru.vsouth.wellbeingdiary.model.user.User;
import ru.vsouth.wellbeingdiary.security.CustomUserDetailsService;
import ru.vsouth.wellbeingdiary.service.diary.DiaryManagementService;
import ru.vsouth.wellbeingdiary.service.diary.export.ExportService;
import ru.vsouth.wellbeingdiary.utils.diary.DiaryEntryMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiaryControllerTest {
    @InjectMocks
    private DiaryController diaryController;
    @Mock
    private DiaryManagementService diaryManagementService;
    @Mock
    private CustomUserDetailsService userDetailsService;
    @Mock
    private ExportService exportService;

    @BeforeEach
    public void setUp() {
        diaryManagementService = mock(DiaryManagementService.class);
        userDetailsService = mock(CustomUserDetailsService.class);
        exportService = mock(ExportService.class);

        diaryController = new DiaryController(diaryManagementService, userDetailsService, exportService);
    }

    @Test
    public void testGetAllOpenEntries() {
        User user = new User(1, "testuser", "password", Role.ANALYST, true);
        List<OpenDiaryEntryResponse> openDiaryEntries = new ArrayList<>();

        when(diaryManagementService.getOpenDiaryEntries()).thenReturn(openDiaryEntries);
        when(userDetailsService.loadUserDetailsByUsername("testuser")).thenReturn(user);

        Model model = new ConcurrentModel();
        Authentication authentication = new UsernamePasswordAuthenticationToken("testuser", "password");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String viewName = diaryController.getAllOpenEntries(model);

        assertEquals("open_diary_entry_list", viewName);
        assertEquals(user.getRole(), model.getAttribute("role"));
        assertEquals(openDiaryEntries, model.getAttribute("diaryEntries"));
    }


    @Test
    public void testExportOpenToXls() throws IOException {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        diaryController.exportOpenToXls(response);
    }

    @Test
    public void testGetOpenEntriesStats() {
        User user = new User(1, "testuser", "password", Role.ANALYST, true);
        Map<String, Map<String, Map<Grade, Long>>> openDiaryEntryStatistics = new HashMap<String, Map<String, Map<Grade, Long>>>();

        when(diaryManagementService.getOpenDiaryEntriesStatistics()).thenReturn(openDiaryEntryStatistics);
        when(userDetailsService.loadUserDetailsByUsername("testuser")).thenReturn(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken("testuser", "password");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Model model = new ConcurrentModel();

        String viewName = diaryController.getOpenEntriesStats(model);

        assertEquals("statistics", viewName);
        assertEquals(user.getRole(), model.getAttribute("role"));
        assertEquals(openDiaryEntryStatistics, model.getAttribute("stats"));
    }

    @Test
    public void testGetAllEntries() {
        User user = new User(1, "testuser", "password", Role.ANALYST, true);
        List<OpenDiaryEntryResponse> openDiaryEntries = new ArrayList<>();
        when(userDetailsService.loadUserDetailsByUsername("testuser")).thenReturn(user);

        Model model = new ConcurrentModel();
        Authentication authentication = new UsernamePasswordAuthenticationToken("testuser", "password");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String viewName = diaryController.getUserEntries(model);

        assertEquals("diary_entry_list", viewName);
        assertEquals(user.getRole(), model.getAttribute("role"));
        assertEquals(openDiaryEntries, model.getAttribute("diaryEntries"));
    }


    @Test
    public void testExportToXls() throws IOException {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        User user = new User(1, "testuser", "password", Role.ANALYST, true);
        Authentication authentication = new UsernamePasswordAuthenticationToken("testuser", "password");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(userDetailsService.loadUserDetailsByUsername("testuser")).thenReturn(user);
        diaryController.exportToXls(response);
    }

    @Test
    public void testShowDiaryEntry() {
        int id = 1;
        DiaryEntryResponse diaryEntryResponse = new DiaryEntryResponse();
        Mockito.when(diaryManagementService.getDiaryEntry(id)).thenReturn(diaryEntryResponse);

        User user = new User(1, "testuser", "password", Role.ANALYST, true);
        Authentication authentication = new UsernamePasswordAuthenticationToken("testuser", "password");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(userDetailsService.loadUserDetailsByUsername("testuser")).thenReturn(user);

        Model model = new ConcurrentModel();
        String viewName = diaryController.showDiaryEntry(id, model);

        assertEquals("diary_entry", viewName);
        assertEquals(diaryEntryResponse, model.getAttribute("diaryEntryResponse"));
    }

    @Test
    public void testShowUpdateDiaryEntryForm() {
        int id = 1;
        DiaryEntryResponse diaryEntryResponse = new DiaryEntryResponse();
        Mockito.when(diaryManagementService.getDiaryEntry(id)).thenReturn(diaryEntryResponse);

        User user = new User(1, "testuser", "password", Role.ANALYST, true);
        Authentication authentication = new UsernamePasswordAuthenticationToken("testuser", "password");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(userDetailsService.loadUserDetailsByUsername("testuser")).thenReturn(user);

        Model model = new ConcurrentModel();
        String viewName = diaryController.showUpdateDiaryEntryForm(id, model);

        assertEquals("update_diary_entry", viewName);
        assertEquals(diaryEntryResponse, model.getAttribute("diaryEntryResponse"));
        assertTrue(model.containsAttribute("grades"));
    }


    @Test
    public void testUpdateEntry() {
        DiaryEntryRequest diaryEntryRequest = new DiaryEntryRequest();
        DiaryEntryResponse updatedEntry = new DiaryEntryResponse();
        Mockito.when(diaryManagementService.updateDiaryEntry(diaryEntryRequest)).thenReturn(updatedEntry);

        String viewName = diaryController.updateEntry(diaryEntryRequest, new ConcurrentModel());

        assertEquals("redirect:/diary/" + updatedEntry.getId(), viewName);
    }

    @Test
    public void testDeleteEntry() {
        DiaryEntryRequest diaryEntryRequest = new DiaryEntryRequest();
        diaryEntryRequest.setId(1);
        diaryEntryRequest.setUserId(10);
        diaryEntryRequest.setHealthEntry(new HealthEntry());
        diaryEntryRequest.setWeatherEntry(new WeatherEntry());
        diaryEntryRequest.setCity("Test");
        diaryEntryRequest.setCreatedAt(new Date());
        diaryEntryRequest.setEntryText("Test");
        diaryEntryRequest.setMood(Grade.GOOD);
        diaryEntryRequest.setStateOfHealth(Grade.EXCELLENT);
        diaryEntryRequest.setActivityAmount(Grade.GOOD);

        DiaryEntryMapper diaryEntryMapper = new DiaryEntryMapper();
        DiaryEntryResponse deletedEntry = diaryEntryMapper.toDiaryEntryResponse(diaryEntryMapper.toDiaryEntry(diaryEntryRequest));
        Mockito.when(diaryManagementService.deleteDiaryEntry(diaryEntryRequest.getId())).thenReturn(deletedEntry);


        ResponseEntity<String> response = diaryController.deleteEntry(diaryEntryRequest);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals("/diary/list", response.getHeaders().getLocation().toString());
        assertEquals("Запись успешно удалена", response.getBody());
    }

    @Test
    public void testShowAddEntryForm() {
        Model model = new ConcurrentModel();

        User user = new User(1, "testuser", "password", Role.ANALYST, true);
        Authentication authentication = new UsernamePasswordAuthenticationToken("testuser", "password");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(userDetailsService.loadUserDetailsByUsername("testuser")).thenReturn(user);

        String viewName = diaryController.showAddEntryForm(model);

        assertEquals("add_entry", viewName);
        assertTrue(model.containsAttribute("role"));
        assertTrue(model.containsAttribute("diaryEntryRequest"));
        assertTrue(model.containsAttribute("grades"));
    }

    @Test
    public void testAddEntry() throws JsonProcessingException {
        DiaryEntryRequest diaryEntryRequest = new DiaryEntryRequest();
        diaryEntryRequest.setId(1);
        diaryEntryRequest.setUserId(10);
        diaryEntryRequest.setHealthEntry(new HealthEntry());
        diaryEntryRequest.setWeatherEntry(new WeatherEntry());
        diaryEntryRequest.setCity("Test");
        diaryEntryRequest.setCreatedAt(new Date());
        diaryEntryRequest.setEntryText("Test");
        diaryEntryRequest.setMood(Grade.GOOD);
        diaryEntryRequest.setStateOfHealth(Grade.EXCELLENT);
        diaryEntryRequest.setActivityAmount(Grade.GOOD);

        User user = new User(1, "testuser", "password", Role.ANALYST, true);
        Authentication authentication = new UsernamePasswordAuthenticationToken("testuser", "password");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(userDetailsService.loadUserDetailsByUsername("testuser")).thenReturn(user);

        DiaryEntryResponse diaryEntryResponse = new DiaryEntryResponse();
        Mockito.when(diaryManagementService.addDiaryEntry(diaryEntryRequest)).thenReturn(diaryEntryResponse);

        String viewName = diaryController.addEntry(diaryEntryRequest);

        assertEquals("redirect:/diary/" + diaryEntryResponse.getId(), viewName);
    }
}