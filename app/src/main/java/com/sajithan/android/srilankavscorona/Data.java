package com.sajithan.android.srilankavscorona;

public class Data {

    private String update_date_time;
    private int local_new_cases;
    private  int local_total_cases;
    private int local_total_number_of_individuals_in_hospitals;
    private int local_deaths;
    private  int local_new_deaths;
    private int local_recovered;
    private int global_new_cases;
    private int global_total_cases;
    private int global_deaths;
    private int global_new_deaths;
    private int global_recovered;

    public Data(String update_date_time, int local_new_cases, int local_total_cases, int local_total_number_of_individuals_in_hospitals, int local_deaths, int local_new_deaths, int local_recovered, int global_new_cases, int global_total_cases, int global_deaths, int global_new_deaths, int global_recovered) {
        this.update_date_time = update_date_time;
        this.local_new_cases = local_new_cases;
        this.local_total_cases = local_total_cases;
        this.local_total_number_of_individuals_in_hospitals = local_total_number_of_individuals_in_hospitals;
        this.local_deaths = local_deaths;
        this.local_new_deaths = local_new_deaths;
        this.local_recovered = local_recovered;
        this.global_new_cases = global_new_cases;
        this.global_total_cases = global_total_cases;
        this.global_deaths = global_deaths;
        this.global_new_deaths = global_new_deaths;
        this.global_recovered = global_recovered;
    }

    public String getUpdate_date_time() {
        return update_date_time;
    }

    public void setUpdate_date_time(String update_date_time) {
        this.update_date_time = update_date_time;
    }

    public int getLocal_new_cases() {
        return local_new_cases;
    }

    public void setLocal_new_cases(int local_new_cases) {
        this.local_new_cases = local_new_cases;
    }

    public int getLocal_total_cases() {
        return local_total_cases;
    }

    public void setLocal_total_cases(int local_total_cases) {
        this.local_total_cases = local_total_cases;
    }

    public int getLocal_total_number_of_individuals_in_hospitals() {
        return local_total_number_of_individuals_in_hospitals;
    }

    public void setLocal_total_number_of_individuals_in_hospitals(int local_total_number_of_individuals_in_hospitals) {
        this.local_total_number_of_individuals_in_hospitals = local_total_number_of_individuals_in_hospitals;
    }

    public int getLocal_deaths() {
        return local_deaths;
    }

    public void setLocal_deaths(int local_deaths) {
        this.local_deaths = local_deaths;
    }

    public int getLocal_new_deaths() {
        return local_new_deaths;
    }

    public void setLocal_new_deaths(int local_new_deaths) {
        this.local_new_deaths = local_new_deaths;
    }

    public int getLocal_recovered() {
        return local_recovered;
    }

    public void setLocal_recovered(int local_recovered) {
        this.local_recovered = local_recovered;
    }

    public int getGlobal_new_cases() {
        return global_new_cases;
    }

    public void setGlobal_new_cases(int global_new_cases) {
        this.global_new_cases = global_new_cases;
    }

    public int getGlobal_total_cases() {
        return global_total_cases;
    }

    public void setGlobal_total_cases(int global_total_cases) {
        this.global_total_cases = global_total_cases;
    }

    public int getGlobal_deaths() {
        return global_deaths;
    }

    public void setGlobal_deaths(int global_deaths) {
        this.global_deaths = global_deaths;
    }

    public int getGlobal_new_deaths() {
        return global_new_deaths;
    }

    public void setGlobal_new_deaths(int global_new_deaths) {
        this.global_new_deaths = global_new_deaths;
    }

    public int getGlobal_recovered() {
        return global_recovered;
    }

    public void setGlobal_recovered(int global_recovered) {
        this.global_recovered = global_recovered;
    }
}
