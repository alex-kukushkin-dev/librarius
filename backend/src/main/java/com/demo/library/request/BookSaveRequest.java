package com.demo.library.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BookSaveRequest {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private boolean available;
}
