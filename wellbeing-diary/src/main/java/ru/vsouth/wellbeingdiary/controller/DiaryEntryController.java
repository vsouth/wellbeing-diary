package ru.vsouth.wellbeingdiary.controller;

import org.springframework.web.bind.annotation.*;
import ru.vsouth.wellbeingdiary.dto.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.DiaryEntry;
import ru.vsouth.wellbeingdiary.service.DiaryEntryServiceImpl;

import java.util.List;

@RestController
@RequestMapping("api/diary")
public class DiaryEntryController {
    private final DiaryEntryServiceImpl diaryEntryService;

    public DiaryEntryController(DiaryEntryServiceImpl diaryEntryService) {
        this.diaryEntryService = diaryEntryService;
    }

    @GetMapping("/open_list")
    public List<OpenDiaryEntryResponse> getAllOpenEntries() {
        return diaryEntryService.getAllOpenEntries();
    }

    @GetMapping("/list")
    public List<DiaryEntryResponse> getAllEntries() {
        return diaryEntryService.getAllEntries();
    }

    @GetMapping("/{id}")
    public DiaryEntryResponse findEntryById(@PathVariable int id) {
        return diaryEntryService.getEntryById(id);
    }

    @GetMapping("/{user_id}/list")
    public List<DiaryEntryResponse> findAllEntriesByUserId(@PathVariable("user_id") int userId) {
        return diaryEntryService.getEntriesByUserId(userId);
    }


    @GetMapping("/{user_id}/{diary_entry_id}")
    public DiaryEntryResponse findEntryByUserId(@PathVariable("user_id") int userId, @PathVariable("diary_entry_id") int diaryEntryId) {
        return diaryEntryService.getEntryByUserIdAndDiaryEntryId(userId, diaryEntryId);
    }

    @PutMapping("/update")
    public DiaryEntryResponse updateEntry(@RequestBody DiaryEntry diaryEntry) {
        return diaryEntryService.updateEntry(diaryEntry);
    }


    @DeleteMapping("/delete/{id}")
    public DiaryEntryResponse deleteEntry(@PathVariable int id) {
        return diaryEntryService.deleteEntry(id);
    }

    @PostMapping("/")
    public DiaryEntryResponse addEntry(@RequestBody DiaryEntry diaryEntry) {
        return diaryEntryService.saveEntry(diaryEntry);
    }
}
