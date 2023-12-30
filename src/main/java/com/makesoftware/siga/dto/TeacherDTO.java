package com.makesoftware.siga.dto;

import com.makesoftware.siga.model.GraduationLevel;
import com.makesoftware.siga.model.User;
import lombok.Data;

@Data
public class TeacherDTO {
    private Long id;

    private User user;

    private String graduationLevel;

    private String urlCurriculoLattes;

    public void selfParseGraduationLevel() {
        if (this.graduationLevel != null) {
            this.graduationLevel = GraduationLevel.valueOf(this.graduationLevel).getGetFriendlyName();
        }
    }
}
