package com.softuni.demo.domain.dtos;

import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameAddDto {

    @Pattern(regexp = "[A-Z].+", message = "Title format is not valid.")
    @Size(min = 3, max = 100, message = "Title length not valid.")
    private String title;

    @DecimalMin(value = "0", message = "Price must be a positive number.")
    private BigDecimal price;

    @Min(value = 0, message = "Size must be a positive number.")
    private double size;

    @Size(min = 11, max = 11, message = "Trailer is not valid")
    private String trailer;

    @Pattern(regexp = "^http:\\/\\/.+|https:\\/\\/.+", message = "Image URL not valid.")
    private String image;

    @Size(min = 20, message = "Description must be at least 20 symbols long.")
    private String description;

    private LocalDate releaseDate;

}
