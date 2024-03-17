package ru.vsouth.wellbeingdiary.service.diary.diaryentry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryRequest;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.diary.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.repository.DiaryEntryRepository;
import ru.vsouth.wellbeingdiary.repository.UserRepository;
import ru.vsouth.wellbeingdiary.utils.diary.DiaryEntryMapper;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiaryEntryServiceImplTest {

    @Autowired
    private DiaryEntryRepository diaryEntryRepository;
    @Autowired
    private UserRepository userRepository;

    private final DiaryEntryMapper diaryEntryMapper = new DiaryEntryMapper();

    private DiaryEntryServiceImpl diaryEntryService;

    @BeforeEach
    public void setUp() {
        diaryEntryService = new DiaryEntryServiceImpl(diaryEntryRepository, diaryEntryMapper);
    }

    @Test
    void testGetAllEntries() {
        List<DiaryEntryResponse> result = diaryEntryService.getAllEntries();
        assertEquals(3, result.size());
    }

    @Test
    void testGetAllOpenEntries() {
        List<OpenDiaryEntryResponse> result = diaryEntryService.getAllOpenEntries();
        assertEquals(2, result.size());
    }

    @Test
    void testGetEntryById() {
        DiaryEntryResponse result = diaryEntryService.getEntryById(1);

        assertEquals("Dear Diary, I had a wonderful day.", result.getEntryText());
        assertEquals("EXCELLENT", result.getMood().name());
        assertEquals("GOOD", result.getStateOfHealth().name());
    }

    @Test
    void testSaveEntryAndDeleteEntry() {
        DiaryEntryRequest testEntry = new DiaryEntryRequest();
        testEntry.setUserId(1);
        testEntry.setCreatedAt(new Date(1000));
        testEntry.setEntryText("Test entry");
        DiaryEntryResponse savedEntry = diaryEntryService.saveEntry(testEntry);

        DiaryEntryResponse entryById = diaryEntryService.getEntryById(savedEntry.getId());
        assertEquals(entryById.getEntryText(), savedEntry.getEntryText());
        assertEquals(entryById.getMood(), savedEntry.getMood());
        assertEquals(entryById.getStateOfHealth(), savedEntry.getStateOfHealth());

        diaryEntryService.deleteEntry(entryById.getId());
        assertNull(diaryEntryService.getEntryById(entryById.getId()));
    }

    @Test
    void testGetEntriesByUserId() {
        List<DiaryEntryResponse> entries = diaryEntryService.getEntriesByUserId(1);

        assertNotNull(entries);
        assertEquals(2, entries.size());
    }

    @Test
    void testGetEntryByUserIdAndDiaryEntryId() {
        DiaryEntryResponse entry = diaryEntryService.getEntryByUserIdAndDiaryEntryId(1, 1);

        assertNotNull(entry);
        assertEquals("Dear Diary, I had a wonderful day.", entry.getEntryText());
        assertEquals("EXCELLENT", entry.getMood().name());
        assertEquals("GOOD", entry.getStateOfHealth().name());
    }
}