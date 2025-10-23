package com.example.hostmapp;

import com.example.hostmapp.ParcelableDashboardData;

interface IDashboardInterface {
    ParcelableDashboardData getDashboardData();
    void updateSetting(String key, String value);
}