package com.kino.controller;

import com.kino.exceptions.EntityNotFoundException;
import com.kino.model.Film;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {

    private final Map<Long, Film> films = new HashMap<>();
    private long nextId = 1;

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {

        film.setId(nextId++);
        films.put(film.getId(), film);

        log.info("Добавлен фильм с id={}", film.getId());

        return film;
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {

        if (film.getId() == null || !films.containsKey(film.getId())) {
            throw new EntityNotFoundException(
                    "Фильм с id=" + film.getId() + " не найден");
        }

        films.put(film.getId(), film);

        log.info("Обновлен фильм с id={}", film.getId());

        return film;
    }

    @GetMapping
    public List<Film> getAll() {
        return new ArrayList<>(films.values());
    }
}