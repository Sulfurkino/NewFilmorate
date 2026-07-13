package com.kino.model;

import com.kino.annotation.ReleaseDateValidation;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
public class Film {
    private Long id;

    @NotBlank
    private String name;

    @Size(max = 200)
    private String description;

    @NotNull
    @ReleaseDateValidation(startDate = "1895.12.28", errorMessage = "Укажите дату не ранее 1895.12.28")
    private LocalDate releaseDate;

    @NotNull
    @Positive
    private int duration;
}
