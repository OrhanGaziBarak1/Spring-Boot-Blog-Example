package com.blog.blog_project.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClapDTO {

    @Positive(message = "Clap number must be positive.")
    @NotNull(message = "Clap number can not being null.")
    @Max(value = 50,
            message = "Clap number can being maximum 50.")
    @Min(value = 1,
            message = "Clap number can being minimum 1.")
    private Integer newClap;
}
