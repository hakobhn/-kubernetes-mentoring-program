package com.epam.training.kubernetes.dockerdemo.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PostDto {
    private String id;
    private Long authorId;
    private String topic;
    private String text;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate postedAt;
}
