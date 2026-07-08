package com.skillforge.app.features.dashboard.model;

public class DashboardCard {

    private String title;
    private String subtitle;

    public DashboardCard(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }
}