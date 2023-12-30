package com.makesoftware.siga.model;

import lombok.Getter;

@Getter
public enum GraduationLevel {
    GRADUATE("Graduação"),
    SPECIALIST("Especialização"),
    MASTER("Mestrado"),
    DOCTORATE("Doutorado");

    private final String getFriendlyName;

    GraduationLevel(String getFriendlyName) {
        this.getFriendlyName = getFriendlyName;
    }

    public static GraduationLevel fromFriendlyName(String graduationLevelFriendlyName) {
        for (GraduationLevel graduationLevel : GraduationLevel.values()) {
            if (graduationLevel.getGetFriendlyName().equals(graduationLevelFriendlyName)) {
                return graduationLevel;
            }
        }

        return null;
    }
}
