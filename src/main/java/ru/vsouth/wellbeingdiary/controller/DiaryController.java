package ru.vsouth.wellbeingdiary.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.user.User;
import ru.vsouth.wellbeingdiary.security.CustomUserDetailsService;
import ru.vsouth.wellbeingdiary.service.diary.DiaryManagementService;

import java.util.List;

@Controller
@RequestMapping("diary")
public class DiaryController {
    private final DiaryManagementService diaryManagementService;
    private final CustomUserDetailsService userDetailsService;

    public DiaryController(DiaryManagementService diaryManagementService, CustomUserDetailsService userDetailsService) {
        this.diaryManagementService = diaryManagementService;
        this.userDetailsService = userDetailsService;
    }

//    @GetMapping("/open_list")
//    public List<OpenDiaryEntryResponse> getAllOpenEntries() {
//        return diaryManagementService.getOpenDiaryEntries();
//    }
//
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
//    @PostMapping("/")
//    public DiaryEntryResponse addEntry(@RequestBody DiaryEntryRequest diaryEntryRequest) throws JsonProcessingException {
//        return diaryManagementService.addDiaryEntry(diaryEntryRequest);
//    }
}
