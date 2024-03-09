package ru.vsouth.wellbeingdiary.controller;

import org.springframework.web.bind.annotation.*;
import ru.vsouth.wellbeingdiary.dto.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.DiaryEntry;
import ru.vsouth.wellbeingdiary.service.diary.DiaryManagementService;

import java.util.List;

@RestController
@RequestMapping("api/diary")
public class DiaryController {
    private final DiaryManagementService diaryManagementService;

    public DiaryController(DiaryManagementService diaryManagementService) {
        this.diaryManagementService = diaryManagementService;
    }

    @GetMapping("/open_list")
    public List<OpenDiaryEntryResponse> getAllOpenEntries() {
        return diaryManagementService.getOpenDiaryEntries();
    }

    @GetMapping("/list")
    public List<DiaryEntryResponse> getAllEntries() {
        return diaryManagementService.getDiaryEntries();
    }

    @GetMapping("/{id}")
    public DiaryEntryResponse findEntryById(@PathVariable int id) {
        return diaryManagementService.getDiaryEntry(id);
    }

    @GetMapping("/{user_id}/list")
    public List<DiaryEntryResponse> findAllEntriesByUserId(@PathVariable("user_id") int userId) {
        return diaryManagementService.getDiaryEntries(userId);
    }


    @GetMapping("/{user_id}/{diary_entry_id}")
    public DiaryEntryResponse findEntryByUserId(@PathVariable("user_id") int userId, @PathVariable("diary_entry_id") int diaryEntryId) {
        return diaryManagementService.getDiaryEntry(userId, diaryEntryId);
    }

    @PutMapping("/update")
    public DiaryEntryResponse updateEntry(@RequestBody DiaryEntry diaryEntry) {
        return diaryManagementService.updateDiaryEntry(diaryEntry);
    }


    @DeleteMapping("/delete/{id}")
    public DiaryEntryResponse deleteEntry(@PathVariable int id) {
        return diaryManagementService.deleteDiaryEntry(id);
    }

    @PostMapping("/")
    public DiaryEntryResponse addEntry(@RequestBody DiaryEntry diaryEntry) {
        return diaryManagementService.addDiaryEntry(diaryEntry);
    }
}
