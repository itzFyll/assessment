package org.aodocs.aodocassessmentapp.model.dto;

import jakarta.validation.constraints.*;

public class EmailDto {
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
