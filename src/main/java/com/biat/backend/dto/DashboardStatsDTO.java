package com.biat.backend.dto;

import java.util.List;

public class DashboardStatsDTO {

    private long totalConvertedDay;
    private long totalNSConvertDay;
    private long totalFailedDay;
    private long totalConvertedWeek;
    private long totalNSConvertWeek;
    private long totalFailedWeek;
    private List<WeeklyData> weeklyData;

    // Getters and Setters
    public long getTotalConvertedDay() {
        return totalConvertedDay;
    }

    public void setTotalConvertedDay(long totalConvertedDay) {
        this.totalConvertedDay = totalConvertedDay;
    }

    public long getTotalNSConvertDay() {
        return totalNSConvertDay;
    }

    public void setTotalNSConvertDay(long totalNSConvertDay) {
        this.totalNSConvertDay = totalNSConvertDay;
    }

    public long getTotalFailedDay() {
        return totalFailedDay;
    }

    public void setTotalFailedDay(long totalFailedDay) {
        this.totalFailedDay = totalFailedDay;
    }

    public long getTotalConvertedWeek() {
        return totalConvertedWeek;
    }

    public void setTotalConvertedWeek(long totalConvertedWeek) {
        this.totalConvertedWeek = totalConvertedWeek;
    }

    public long getTotalNSConvertWeek() {
        return totalNSConvertWeek;
    }

    public void setTotalNSConvertWeek(long totalNSConvertWeek) {
        this.totalNSConvertWeek = totalNSConvertWeek;
    }

    public long getTotalFailedWeek() {
        return totalFailedWeek;
    }

    public void setTotalFailedWeek(long totalFailedWeek) {
        this.totalFailedWeek = totalFailedWeek;
    }

    public List<WeeklyData> getWeeklyData() {
        return weeklyData;
    }

    public void setWeeklyData(List<WeeklyData> weeklyData) {
        this.weeklyData = weeklyData;
    }

    // ✅ Nested class for weekly stats
    public static class WeeklyData {

        private String date;
        private long converted;
        private long ns;
        private long failed;

        // Getters and Setters
        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public long getConverted() {
            return converted;
        }

        public void setConverted(long converted) {
            this.converted = converted;
        }

        public long getNs() {
            return ns;
        }

        public void setNs(long ns) {
            this.ns = ns;
        }

        public long getFailed() {
            return failed;
        }

        public void setFailed(long failed) {
            this.failed = failed;
        }
    }
}
