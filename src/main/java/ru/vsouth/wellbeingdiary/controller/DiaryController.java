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
import ru.vsouth.wellbeingdiary.model.user.Role;
import ru.vsouth.wellbeingdiary.model.user.User;
import ru.vsouth.wellbeingdiary.security.CustomUserDetailsService;
import ru.vsouth.wellbeingdiary.service.diary.DiaryManagementService;
import ru.vsouth.wellbeingdiary.service.diary.export.ExportService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер дневника. Отвечает за записи и работу с ними
 */
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

    /**
     * Метод для отображения списка открытых записей
     *
     * @param model Модель для передачи данных на страницу
     * @return      Страница "Открытые записи"
     */
    @GetMapping("/open_list")
    public String getAllOpenEntries(Model model) {
        User user = getAuthorizedUser();
        Role role = user.getRole();
        model.addAttribute("role", role);
        model.addAttribute("diaryEntries", diaryManagementService.getOpenDiaryEntries());
        return "open_diary_entry_list";
    }

    /**
     * Метод для экспорта списка открытых записей в формате .xls
     *
     * @param response      Ответ HTTP для передачи файла
     * @throws IOException  Исключение, которое может возникнуть при работе с потоками
     */
    @GetMapping("/open_list/export")
    public void exportOpenToXls(HttpServletResponse response) throws IOException {
        List<OpenDiaryEntryResponse> openDiaryEntries = diaryManagementService.getOpenDiaryEntries();
        exportService.exportOpenEntriesToXls(openDiaryEntries, response);
    }

    /**
     * Метод для получения статистики по открытым записям
     *
     * @param model Модель для передачи данных на страницу
     * @return      Страница статистики по открытым записям
     */
    @GetMapping("/open_list/stats")
    public String getOpenEntriesStats(Model model) {
        User user = getAuthorizedUser();
        Role role = user.getRole();
        model.addAttribute("role", role);
        model.addAttribute("stats", diaryManagementService.getOpenDiaryEntriesStatistics());
        return "statistics";
    }

    /**
     * Метод для отображения списка записей авторизованного пользователя
     *
     * @param model Модель для передачи данных на страницу
     * @return      Страница "Мои записи"
     */
    @GetMapping("/list")
    public String getUserEntries(Model model) {
        User user = getAuthorizedUser();
        int userId = user.getId();
        Role role = user.getRole();
        model.addAttribute("role", role);
        List<DiaryEntryResponse> diaryEntries = diaryManagementService.getDiaryEntries(userId);
        model.addAttribute("diaryEntries", diaryEntries);
        return "diary_entry_list";
    }

    /**
     * Метод для экспорта списка записей авторизованного пользователя в формате .xls
     *
     * @param response Ответ HTTP для передачи файла
     * @throws IOException Исключение, которое может возникнуть при работе с потоками
     */
    @GetMapping("/list/export")
    public void exportToXls(HttpServletResponse response) throws IOException {
        User user = getAuthorizedUser();
        int userId = user.getId();
        List<DiaryEntryResponse> diaryEntries = diaryManagementService.getDiaryEntries(userId);
        exportService.exportEntriesToXls(diaryEntries, response);
    }

    /**
     * Метод для получения статистики по записям авторизованного пользователя
     *
     * @param model Модель для передачи данных на страницу
     * @return      Страница статистики по записям пользователя
     */
    @GetMapping("/list/stats")
    public String getUserEntriesStats(Model model) {
        User user = getAuthorizedUser();
        int userId = user.getId();
        Role role = user.getRole();
        model.addAttribute("role", role);
        model.addAttribute("stats", diaryManagementService.getDiaryEntriesStatisticsById(userId));
        return "statistics";
    }

    /**
     * Метод для отображения конкретной записи
     *
     * @param id    Идентификатор записи
     * @param model Модель для передачи данных на страницу
     * @return      Страница записи
     */
    @GetMapping("/{id}")
    public String showDiaryEntry(@PathVariable("id") int id, Model model) {
        User user = getAuthorizedUser();
        Role role = user.getRole();
        model.addAttribute("role", role);
        DiaryEntryResponse diaryEntryResponse = diaryManagementService.getDiaryEntry(id);
        if (diaryEntryResponse == null) {
            return "error";
        }
        model.addAttribute("diaryEntryResponse", diaryEntryResponse);
        return "diary_entry";
    }

    /**
     * Метод для отображения страницы обновления (изменения) конкретной записи
     *
     * @param id    Идентификатор записи в дневнике
     * @param model Модель для передачи данных на страницу
     * @return      Страница обновления записи
     */
    @GetMapping("/update/{id}")
    public String showUpdateDiaryEntryForm(@PathVariable("id") int id, Model model) {
        User user = getAuthorizedUser();
        Role role = user.getRole();
        model.addAttribute("role", role);
        DiaryEntryResponse diaryEntryResponse = diaryManagementService.getDiaryEntry(id);
        if (diaryEntryResponse == null) {
            return "redirect:/error";
        }
        model.addAttribute("diaryEntryResponse", diaryEntryResponse);
        model.addAttribute("grades", Arrays.asList(Grade.values()));
        return "update_diary_entry";
    }

    /**
     * Метод обновления записи
     *
     * @param diaryEntryRequest Запрос на обновление записи в дневнике (запись с обновленными полями)
     * @param model             Модель для передачи данных на страницу
     * @return                  Страница записи
     */
    @PostMapping("/update")
    public String updateEntry(@ModelAttribute("diaryEntryRequest") DiaryEntryRequest diaryEntryRequest, Model model) {
        DiaryEntryResponse updatedEntry = diaryManagementService.updateDiaryEntry(diaryEntryRequest);
        if (updatedEntry == null) {
            return "redirect:/error";
        }
        return "redirect:/diary/" + updatedEntry.getId();
    }

    /**
     * Метод удаления записи
     *
     * @param diaryEntryRequest Запрос на удаление записи в дневнике (запись, которую необходимо удалить)
     * @return                  Страница "Мои записи"
     */
    @PostMapping("/delete")
    public ResponseEntity<String> deleteEntry(@ModelAttribute("diaryEntryRequest") DiaryEntryRequest diaryEntryRequest) {
        DiaryEntryResponse deletedEntry = diaryManagementService.deleteDiaryEntry(diaryEntryRequest.getId());
        if (deletedEntry != null) {
            return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, "/diary/list").body("Запись успешно удалена");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Запись не найдена");
        }
    }

    /**
     * Метод для отображения страницы добавления новой записи
     *
     * @param model Модель для передачи данных на страницу
     * @return      Страница добавления новой записи
     */
    @GetMapping("/new")
    public String showAddEntryForm(Model model) {
        User user = getAuthorizedUser();
        Role role = user.getRole();
        model.addAttribute("role", role);
        model.addAttribute("diaryEntryRequest", new DiaryEntryRequest());
        List<String> grades = Arrays.stream(Grade.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        grades.add(null);
        model.addAttribute("grades", grades);
        return "add_entry";
    }
    /**
     * Метод добавления новой записи
     *
     * @param diaryEntryRequest Запрос на добавление записи
     * @return                  Страница добавленной записи
     */
    @PostMapping("/")
    public String addEntry(@ModelAttribute("diaryEntryRequest") DiaryEntryRequest diaryEntryRequest) throws JsonProcessingException {
        User user = getAuthorizedUser();
        int userId = user.getId();
        diaryEntryRequest.setUserId(userId);
        DiaryEntryResponse diaryEntryResponse = diaryManagementService.addDiaryEntry(diaryEntryRequest);
        return "redirect:/diary/" + diaryEntryResponse.getId();
    }

    /**
     * Вспомогательный метод для получения авторизованного пользователя
     *
     * @return Авторизованный пользователь
     */
    private User getAuthorizedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userDetailsService.loadUserDetailsByUsername(username);
    }
}
