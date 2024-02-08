package com.horvat.bookstore.book;

public enum ReviewGrade {
    POOR("Poor"),
    FAIR("Fair"),
    GOOD("Good"),
    VERY_GOOD("Very Good"),
    EXCELLENT("Excellent");

    private final String label;

    ReviewGrade(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

}
