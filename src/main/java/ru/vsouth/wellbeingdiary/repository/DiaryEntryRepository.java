package ru.vsouth.wellbeingdiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vsouth.wellbeingdiary.model.diary.DiaryEntry;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryEntryRepository  extends JpaRepository<DiaryEntry, Integer> {
    Optional<DiaryEntry> findByUserIdAndId(int userId, int diaryEntryId);
    @Query("SELECT de FROM DiaryEntry de WHERE de.user.id IN (SELECT u.id FROM User u WHERE u.allowsDataAccess = true)")
    List<DiaryEntry> findOpenDiaryEntries();
    List<DiaryEntry> findByUserId(int userId);
}
