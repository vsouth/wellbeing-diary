package ru.vsouth.wellbeingdiary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryRequest;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.diary.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.Grade;
import ru.vsouth.wellbeingdiary.model.user.User;
import ru.vsouth.wellbeingdiary.security.CustomUserDetailsService;
import ru.vsouth.wellbeingdiary.service.diary.DiaryManagementService;
import ru.vsouth.wellbeingdiary.service.diary.export.ExportService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("diary")
public class DiaryController {
    private final DiaryManagementService diaryManagementService;
    private final CustomUserDetailsService userDetailsService;
    private final ExportService exportService;

    public DiaryController(DiaryManagementService diaryManagementService, CustomUserDetailsService userDetailsService, ExportService exportService) {
        this.diaryManagementService = diaryManagementService;
        this.userDetailsService = userDetailsService;
        this.exportService = exportService;
    }

    @GetMapping("/open_list")
    public String getAllOpenEntries(Model model) {
        model.addAttribute("diaryEntries", diaryManagementService.getOpenDiaryEntries());
        return "open_diary_entry_list";
    }

    @GetMapping("/open_list/export")
    public void exportOpenToXls(HttpServletResponse response) throws IOException {
        List<OpenDiaryEntryResponse> openDiaryEntries = diaryManagementService.getOpenDiaryEntries();
        exportService.exportOpenEntriesToXls(openDiaryEntries, response);
    }

    @GetMapping("/list")
    public String getAllEntries(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userDetailsService.loadUserDetailsByUsername(username);
        int userId = user.getId();
        List<DiaryEntryResponse> diaryEntries = diaryManagementService.getDiaryEntries(userId);
        model.addAttribute("diaryEntries", diaryEntries);
        return "diary_entry_list";
    }

    @GetMapping("/list/export")
    public void exportToXls(HttpServletResponse response) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userDetailsService.loadUserDetailsByUsername(username);
        int userId = user.getId();
        List<DiaryEntryResponse> diaryEntries = diaryManagementService.getDiaryEntries(userId);
        exportService.exportEntriesToXls(diaryEntries, response);
    }

    @GetMapping("/{id}")
    public String showDiaryEntry(@PathVariable("id") int id, Model model) {
        DiaryEntryResponse diaryEntryResponse = diaryManagementService.getDiaryEntry(id);

        if (diaryEntryResponse == null) {
            return "error";
        }

        model.addAttribute("diaryEntryResponse", diaryEntryResponse);
        return "diary_entry";
    }

    @GetMapping("/update/{id}")
    public String showUpdateDiaryEntryForm(@PathVariable("id") int id, Model model) {
        DiaryEntryResponse diaryEntryResponse = diaryManagementService.getDiaryEntry(id);

        if (diaryEntryResponse == null) {
            return "redirect:/error";
        }

        model.addAttribute("diaryEntryResponse", diaryEntryResponse);
        model.addAttribute("grades", Arrays.asList(Grade.values()));

        return "update_diary_entry";
    }

    @PostMapping("/update")
    public String updateEntry(@ModelAttribute("diaryEntryRequest") DiaryEntryRequest diaryEntryRequest, Model model) {
        DiaryEntryResponse updatedEntry = diaryManagementService.updateDiaryEntry(diaryEntryRequest);

        if (updatedEntry == null) {
            return "redirect:/error";
        }

        return "redirect:/diary/"+ updatedEntry.getId();
    }


    @PostMapping("/delete")
    public ResponseEntity<String> deleteEntry(@ModelAttribute("diaryEntryRequest") DiaryEntryRequest diaryEntryRequest) {
        DiaryEntryResponse deletedEntry = diaryManagementService.deleteDiaryEntry(diaryEntryRequest.getId());

        if (deletedEntry != null) {
            return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, "/diary/list").body("Запись успешно удалена");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Запись не найдена");
        }
    }

    @GetMapping("/new")
    public String showAddEntryForm(Model model) {
        model.addAttribute("diaryEntryRequest", new DiaryEntryRequest());
        List<String> grades = Arrays.stream(Grade.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        grades.add(null);
        model.addAttribute("grades", grades);
        return "add_entry";
    }
    @PostMapping("/")
    public String addEntry(@ModelAttribute("diaryEntryRequest") DiaryEntryRequest diaryEntryRequest) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userDetailsService.loadUserDetailsByUsername(username);
        int userId = user.getId();

        diaryEntryRequest.setUserId(userId);

        DiaryEntryResponse diaryEntryResponse = diaryManagementService.addDiaryEntry(diaryEntryRequest);
        return "redirect:/diary/"+ diaryEntryResponse.getId();
    }
}
