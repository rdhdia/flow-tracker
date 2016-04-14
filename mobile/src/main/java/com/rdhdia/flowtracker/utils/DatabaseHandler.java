package com.rdhdia.flowtracker.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ruffyheredia on 14/04/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "flow_tracker";

    // Table names
    private static final String TABLE_SESSION = "session";
    private static final String TABLE_PERSON = "person";
    private static final String TABLE_BRIDGE = "bridge";
    private static final String TABLE_PROJECT = "project";
    private static final String TABLE_READING = "reading";

    // Columns - Session
    private static final String SESSION_ID = "_id";
    private static final String SESSION_BRIDGE_ID = "bridgeId";
    private static final String SESSION_DATE = "date";
    private static final String SESSION_LOCATION = "location";
    private static final String SESSION_NOTES = "notes";

    // Columns - Person
    private static final String PERSON_ID = "_id";
    private static final String PERSON_FULLNAME = "fullname";
    private static final String PERSON_PROJECT_ID = "projectId";

    // Columns - Bridge
    private static final String BRIDGE_ID = "_id";
    private static final String BRIDGE_NAME = "name";
    private static final String BRIDGE_LOCATION = "location";
    private static final String BRIDGE_LATITUDE = "latitude";
    private static final String BRIDGE_LONGITUDE = "longitude";

    // Columns - Project
    private static final String PROJECT_ID = "_id";
    private static final String PROJECT_NAME = "name";

    // Columns - Reading
    private static final String READING_ID = "_id";
    private static final String READING_TIME = "time";
    private static final String READING_FLOW_VALUE = "flowValue";
    private static final String READING_SESSION_ORDER = "sessionOrder";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SESSION_TABLE = "CREATE TABLE " + TABLE_SESSION + "("
                + SESSION_ID + " INTEGER PRIMARY KEY,"
                + SESSION_BRIDGE_ID + " TEXT,"
                + SESSION_DATE + " TEXT,"
                + SESSION_LOCATION + " TEXT,"
                + SESSION_NOTES + " TEXT"
                + ")";
        String CREATE_PERSON_TABLE = "CREATE TABLE " + TABLE_PERSON + "("
                + PERSON_ID + " INTEGER PRIMARY KEY,"
                + PERSON_FULLNAME + " TEXT,"
                + PERSON_PROJECT_ID + " TEXT"
                + ")";
        String CREATE_BRIDGE_TABLE = "CREATE TABLE " + TABLE_BRIDGE + "("
                + BRIDGE_ID + " INTEGER PRIMARY KEY,"
                + BRIDGE_NAME + " TEXT,"
                + BRIDGE_LOCATION + " TEXT,"
                + BRIDGE_LATITUDE + " TEXT,"
                + BRIDGE_LONGITUDE + " TEXT"
                + ")";
        String CREATE_PROJECT_TABLE = "CREATE TABLE " + TABLE_PROJECT + "("
                + PROJECT_ID + " INTEGER PRIMARY KEY,"
                + PROJECT_NAME + " TEXT"
                + ")";
        String CREATE_READING_TABLE = "CREATE TABLE " + TABLE_READING + "("
                + READING_ID + " INTEGER PRIMARY KEY,"
                + READING_TIME + " TEXT,"
                + READING_FLOW_VALUE + " TEXT,"
                + READING_SESSION_ORDER + " INTEGER"
                + ")";

        db.execSQL(CREATE_SESSION_TABLE);
        db.execSQL(CREATE_PERSON_TABLE);
        db.execSQL(CREATE_BRIDGE_TABLE);
        db.execSQL(CREATE_PROJECT_TABLE);
        db.execSQL(CREATE_READING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if they exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SESSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BRIDGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_READING);

        // Create tables again
        onCreate(db);
    }
}
