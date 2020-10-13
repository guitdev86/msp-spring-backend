package com.mspproject.backendmspapp.helpers;

public class TotalLessonsWrapper {
    private TotalLesson totalLessons;

    public TotalLessonsWrapper() {
    }

    public TotalLessonsWrapper(TotalLesson totalLessons) {
        this.totalLessons = totalLessons;
    }

    public TotalLesson getTotalLessons() {
        return totalLessons;
    }

    public void setTotalLessons(TotalLesson totalLessons) {
        this.totalLessons = totalLessons;
    }
}
