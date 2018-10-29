package com.groovy.todo.api.dto

import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

class TodoDto {

    @NotNull(message = "Field name can't be empty")
    private String name
    @NotNull(message = "Field description can't be empty")
    private String description
    @NotNull(message = "Field priority can't be empty")
    @Min(value = 1L, message = "The value to priority can't be minor then 1")
    @Max(value = 5L, message = "The value to priority can't be major then 5")
    private Integer priority
    @NotNull(message = "Field author can't be empty")
    private String author

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }

    Integer getPriority() {
        return priority
    }

    void setPriority(Integer priority) {
        this.priority = priority
    }

    String getAuthor() {
        return author
    }

    void setAuthor(String author) {
        this.author = author
    }
}
