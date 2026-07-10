package com.kino.controller;

import com.kino.exceptions.ValidationException;
import com.kino.model.Film;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {

    private final Map<Long, Film> films = new HashMap<>();
    private long nextId = 1;

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        validate(film);

        film.setId(nextId++);
        films.put(film.getId(), film);

        log.info("Добавлен фильм: {}", film);

        return film;
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        validate(film);

        if (film.getId() == null || !films.containsKey(film.getId())) {
            throw new ValidationException("Фильм не найден");
        }

        films.put(film.getId(), film);

        log.info("Обновлен фильм: {}", film);

        return film;
    }

    @GetMapping
    public Collection<Film> getAll() {
        return films.values();
    }

    private void validate(Film film) {
        if (film.getName() == null || film.getName().isBlank()) {
            log.error("Название фильма пустое");
            throw new ValidationException("Название фильма не может быть пустым");
        }

        if (film.getDescription() != null && film.getDescription().length() > 200) {
            log.error("Описание длиннее 200 символов");
            throw new ValidationException("Описание не должно превышать 200 символов");
        }

        LocalDate minDate = LocalDate.of(1895, 12, 28);

        if (film.getReleaseDate().isBefore(minDate)) {
            log.error("Некорректная дата релиза");
            throw new ValidationException("Дата релиза раньше 28.12.1895");
        }

        if (film.getDuration() <= 0) {
            log.error("Некорректная продолжительность");
            throw new ValidationException("Продолжительность должна быть положительной");
        }
    }
}