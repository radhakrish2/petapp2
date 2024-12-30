package com.pet.entity.dto;

import lombok.Data;

@Data
public class CommentDTO {

    private Long id;
    private String content;
    private String createdDate;
    private Long petId;
    private Long userId;

    // Getters and Setters
}