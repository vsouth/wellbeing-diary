package ru.vsouth.wellbeingdiary.controller;

import org.springframework.web.bind.annotation.*;
import ru.vsouth.wellbeingdiary.dto.EntryResponse;
import ru.vsouth.wellbeingdiary.model.DiaryEntry;
import ru.vsouth.wellbeingdiary.service.EntryService;

import java.util.List;

@RestController
@RequestMapping("api/diary")
public class DiaryEntryController {
    private final EntryService diaryEntryService;

    public DiaryEntryController(EntryService diaryEntryService) {
        this.diaryEntryService = diaryEntryService;
    }

    @GetMapping("/open_list")
    public List<EntryResponse> getAllOpenEntries() {
        return diaryEntryService.getAllOpenEntries();
    }

    @GetMapping("/list")
    public List<EntryResponse> getAllEntries() {
        return diaryEntryService.getAllEntries();
    }

    @GetMapping("/{id}")
    public EntryResponse findEntryById(@PathVariable int id) {
        return diaryEntryService.getEntryById(id);
    }

    @GetMapping("/{user_id}/list")
    public List<EntryResponse> findAllEntriesByUserId(@PathVariable("user_id") int userId) {
        return diaryEntryService.getEntriesByUserId(userId);
    }


    @GetMapping("/{user_id}/{diary_entry_id}")
    public EntryResponse findEntryByUserId(@PathVariable("user_id") int userId, @PathVariable("diary_entry_id") int diaryEntryId) {
        return diaryEntryService.getEntryByUserIdAndDiaryEntryId(userId, diaryEntryId);
    }

    @PutMapping("/update")
    public EntryResponse updateEntry(@RequestBody DiaryEntry diaryEntry) {
        return diaryEntryService.updateEntry(diaryEntry);
    }


    @DeleteMapping("/delete/{id}")
    public EntryResponse deleteEntry(@PathVariable int id) {
        return diaryEntryService.deleteEntry(id);
    }

    @PostMapping("/")
    public EntryResponse addEntry(@RequestBody DiaryEntry diaryEntry) {
        return diaryEntryService.saveEntry(diaryEntry);
    }
}
