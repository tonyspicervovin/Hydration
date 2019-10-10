package com.tony.hydration;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WaterRepository {

    private WaterDAO waterDAO;

    public WaterRepository (Application application) {
        WaterDatabase db = WaterDatabase.getDatabase(application);
        waterDAO = db.waterDAO();

    }

    public void insert(WaterRecord record) {
        //insert record asyncrhonously
        new InsertWaterAsync(waterDAO).execute(record);

    }

    static class InsertWaterAsync extends AsyncTask<WaterRecord, Void, Void> {

        private WaterDAO waterDAO;

        InsertWaterAsync(WaterDAO waterDAO) {
            this.waterDAO = waterDAO;
        }

        @Override
        protected Void doInBackground(WaterRecord... waterRecords) {
            waterDAO.insert(waterRecords);
            return null;
        }
    }

    public void update(WaterRecord record) {
        new UpdateWaterAsync(waterDAO).execute(record);

    }
    static class UpdateWaterAsync extends AsyncTask<WaterRecord, Void, Void> {

        private WaterDAO waterDAO;

        UpdateWaterAsync(WaterDAO waterDao) {
            this.waterDAO = waterDao;
        }

        @Override
        protected Void doInBackground(WaterRecord... waterRecords) {
            waterDAO.update(waterRecords);
            return null;
        }
    }

    public LiveData<List<WaterRecord>> getAllRecords() {
        return waterDAO.getAllRecords();

    }

    public LiveData<WaterRecord> getRecordForDay(String day) {
        return waterDAO.getRecordForDay(day);
    }
}