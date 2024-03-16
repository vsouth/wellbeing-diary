package ru.vsouth.wellbeingdiary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryRequest;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.Grade;
import ru.vsouth.wellbeingdiary.model.user.User;
import ru.vsouth.wellbeingdiary.security.CustomUserDetailsService;
import ru.vsouth.wellbeingdiary.service.diary.DiaryManagementService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("diary")
public class DiaryController {
    private final DiaryManagementService diaryManagementService;
    private final CustomUserDetailsService userDetailsService;

    public DiaryController(DiaryManagementService diaryManagementService, CustomUserDetailsService userDetailsService) {
        this.diaryManagementService = diaryManagementService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/open_list")
    public String getAllOpenEntries(Model model) {
        model.addAttribute("diaryEntries", diaryManagementService.getOpenDiaryEntries());
        return "open_diary_entry_list";
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

    @GetMapping("/{id}")
    public String showDiaryEntry(@PathVariable("id") int id, Model model) {
        DiaryEntryResponse diaryEntryResponse = diaryManagementService.getDiaryEntry(id);

        if (diaryEntryResponse == null) {
            return "error";
        }

        model.addAttribute("diaryEntryResponse", diaryEntryResponse);
        return "diary_entry";
    }

//    @PutMapping("/update")
//    public DiaryEntryResponse updateEntry(@RequestBody DiaryEntryRequest diaryEntryRequest) {
//        return diaryManagementService.updateDiaryEntry(diaryEntryRequest);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public DiaryEntryResponse deleteEntry(@PathVariable int id) {
//        return diaryManagementService.deleteDiaryEntry(id);
//    }
//
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
