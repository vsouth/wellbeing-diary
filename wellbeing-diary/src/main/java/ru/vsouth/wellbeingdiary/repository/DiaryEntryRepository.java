package ru.vsouth.wellbeingdiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vsouth.wellbeingdiary.model.DiaryEntry;

import java.util.Optional;

@Repository
public interface DiaryEntryRepository  extends JpaRepository<DiaryEntry, Integer> {
    @Query("select u from DiaryEntry u where u.userId=?1 and u.id=?2")
    Optional<DiaryEntry> findByUserIdAndDiaryEntryId(int userId, int chatId);
}
