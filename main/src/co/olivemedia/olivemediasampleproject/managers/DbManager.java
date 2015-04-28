package co.olivemedia.olivemediasampleproject.managers;

import java.util.List;

import co.olivemedia.olivemediasampleproject.dao.MeetingsDao;
import co.olivemedia.olivemediasampleproject.holders.Meetings;
import co.olivemedia.olivemediasampleproject.holders.MeetingsBaseHolder;

public class DbManager {


    private static final String TAG = DbManager.class.getSimpleName();

    private static DbManager instance = null;

    public static DbManager getInstance() {

        if (instance == null) {

            instance = new DbManager();

        }
        return instance;
    }

    

    public List<Meetings> getAllMeetingsFromDb() {

        return MeetingsDao.getInstance().getAllMeetingsFromDb();
    }
    public void insertSingleEssentialExercises(Meetings meetings) {

    	MeetingsDao.getInstance().insertSingleMeetings(meetings);
    }





}