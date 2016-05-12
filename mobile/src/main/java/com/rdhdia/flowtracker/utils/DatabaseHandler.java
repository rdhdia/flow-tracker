package com.rdhdia.flowtracker.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rdhdia.flowtracker.models.Bridge;
import com.rdhdia.flowtracker.models.Person;
import com.rdhdia.flowtracker.models.Project;
import com.rdhdia.flowtracker.models.Reading;
import com.rdhdia.flowtracker.models.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruffyheredia on 14/04/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

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
    private static final String READING_SESSION_ID = "sessionId";

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
                + READING_SESSION_ORDER + " INTEGER,"
                + READING_SESSION_ID + " INTEGER"
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

    // Add a new Session
    public void addSession(Session session) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SESSION_ID, session.getId());
        values.put(SESSION_BRIDGE_ID, session.getBridgeId());
        values.put(SESSION_DATE, session.getDate());
        values.put(SESSION_LOCATION, session.getLocation());
        values.put(SESSION_NOTES, session.getNotes());

        // Insert the row
        db.insert(TABLE_SESSION, null, values);
        db.close();
    }

    // Add a new Person
    public void addPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PERSON_ID, person.getId());
        values.put(PERSON_FULLNAME, person.getFullName());
        values.put(PERSON_PROJECT_ID, person.getProjectId());

        // Insert the row
        db.insert(TABLE_PERSON, null, values);
        db.close();
    }

    // Add a new Bridge
    public void addBridge(Bridge bridge) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BRIDGE_ID, bridge.getId());
        values.put(BRIDGE_NAME, bridge.getName());
        values.put(BRIDGE_LOCATION, bridge.getLocation());
        values.put(BRIDGE_LATITUDE, bridge.getLatitude());
        values.put(BRIDGE_LONGITUDE, bridge.getLongitude());

        // Insert the row
        db.insert(TABLE_BRIDGE, null, values);
        db.close();
    }

    // Add a new Project
    public void addProject(Project project) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PROJECT_ID, project.getId());
        values.put(PROJECT_NAME, project.getName());

        // Insert the row
        db.insert(TABLE_PROJECT, null, values);
        db.close();
    }

    // Add a new Reading
    public void addReading(Reading reading) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(READING_ID, reading.getId());
        values.put(READING_TIME, reading.getTime());
        values.put(READING_FLOW_VALUE, reading.getFlowValue());
        values.put(READING_SESSION_ORDER, reading.getSessionOrder());
        values.put(READING_SESSION_ID, reading.getSessionId());

        // Insert the row
        db.insert(TABLE_READING, null, values);
        db.close();
    }

    // Retrieve a single Session
    public Session getSession(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SESSION,
                new String[] { SESSION_ID, SESSION_BRIDGE_ID, SESSION_DATE,
                                SESSION_LOCATION, SESSION_NOTES},
                SESSION_ID + "=?", new String[] {String.valueOf(id)},
                null, null, null, null);

        if ( cursor != null ) cursor.moveToFirst();

        Session session = new Session(cursor.getString(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4));

        cursor.close();
        return session;
    }

    // Retrieve a single Person
    public Person getPerson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PERSON,
                new String[] { PERSON_ID, PERSON_FULLNAME, PERSON_PROJECT_ID},
                PERSON_ID + "=?", new String[] {String.valueOf(id)},
                null, null, null, null);

        if ( cursor != null ) cursor.moveToFirst();

        Person person = new Person(cursor.getString(0), cursor.getString(1),
                cursor.getString(2));

        cursor.close();
        return person;
    }

    // Retrieve a single Bridge
    public Bridge getBridge(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BRIDGE,
                new String[] { BRIDGE_ID, BRIDGE_NAME, BRIDGE_LOCATION,
                        BRIDGE_LATITUDE, BRIDGE_LONGITUDE},
                BRIDGE_ID + "=?", new String[] {String.valueOf(id)},
                null, null, null, null);

        if ( cursor != null ) cursor.moveToFirst();

        Bridge bridge = new Bridge(cursor.getString(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4));

        cursor.close();
        return bridge;
    }

    // Retrieve a single Project
    public Project getProject(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PROJECT,
                new String[] { PROJECT_ID, PROJECT_NAME },
                PROJECT_ID + "=?", new String[] {String.valueOf(id)},
                null, null, null, null);

        if ( cursor != null ) cursor.moveToFirst();

        Project project = new Project(cursor.getString(0), cursor.getString(1));

        cursor.close();
        return project;
    }

    // Retrieve a single Reading
    public Reading getReading(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_READING,
                new String[] { READING_ID, READING_TIME, READING_FLOW_VALUE,
                        READING_SESSION_ORDER },
                READING_ID + "=?", new String[] {String.valueOf(id)},
                null, null, null, null);

        if ( cursor != null ) cursor.moveToFirst();

        Reading reading = new Reading(Integer.valueOf(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), Integer.valueOf(cursor.getString(3)),
                Integer.valueOf(cursor.getString(4)));

        cursor.close();
        return reading;
    }

    // Retrieve all Session objects
    public List<Session> getAllSession() {
        List<Session> sessionList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_SESSION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if ( cursor.moveToFirst() ) {
            do {
                Session session = new Session();
                session.setId(cursor.getString(0));
                session.setBridgeId(cursor.getString(1));
                session.setDate(cursor.getString(2));
                session.setLocation(cursor.getString(3));
                session.setNotes(cursor.getString(4));

                sessionList.add(session);
            } while ( cursor.moveToNext() );
        }

        db.close();
        cursor.close();
        return sessionList;
    }

    // Retrieve all Person objects
    public List<Person> getAllPerson() {
        List<Person> personList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_PERSON;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if ( cursor.moveToFirst() ) {
            do {
                Person person = new Person();
                person.setId(cursor.getString(0));
                person.setFullName(cursor.getString(1));
                person.setProjectId(cursor.getString(2));

                personList.add(person);
            } while ( cursor.moveToNext() );
        }

        db.close();
        cursor.close();
        return personList;
    }

    // Retrieve all Bridge objects
    public List<Bridge> getAllBridge() {
        List<Bridge> bridgeList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_BRIDGE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if ( cursor.moveToFirst() ) {
            do {
                Bridge bridge = new Bridge();
                bridge.setId(cursor.getString(0));
                bridge.setName(cursor.getString(1));
                bridge.setLocation(cursor.getString(2));
                bridge.setLatitude(cursor.getString(3));
                bridge.setLongitude(cursor.getString(4));

                bridgeList.add(bridge);
            } while ( cursor.moveToNext() );
        }

        db.close();
        cursor.close();
        return bridgeList;
    }

    // Retrieve all Project objects
    public List<Project> getAllProject() {
        List<Project> projectList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_PROJECT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if ( cursor.moveToFirst() ) {
            do {
                Project project = new Project();
                project.setId(cursor.getString(0));
                project.setName(cursor.getString(1));

                projectList.add(project);
            } while ( cursor.moveToNext() );
        }

        db.close();
        cursor.close();
        return projectList;
    }

    // Retrieve all Reading objects
    public List<Reading> getAllReading() {
        List<Reading> readingList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_READING;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if ( cursor.moveToFirst() ) {
            do {
                Reading reading = new Reading();
                reading.setId(Integer.valueOf(cursor.getString(0)));
                reading.setTime(cursor.getString(1));
                reading.setFlowValue(cursor.getString(2));
                reading.setSessionOrder(Integer.valueOf(cursor.getString(3)));
                reading.setSessionId(Integer.valueOf(cursor.getString(4)));

                readingList.add(reading);
            } while ( cursor.moveToNext() );
        }

        db.close();
        cursor.close();
        return readingList;
    }

    // Get Session count
    public int getSessionCount() {
        String countQuery = "SELECT * FROM " + TABLE_SESSION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    // Get Person count
    public int getPersonCount() {
        String countQuery = "SELECT * FROM " + TABLE_PERSON;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    // Get Bridge count
    public int getBridgeCount() {
        String countQuery = "SELECT * FROM " + TABLE_BRIDGE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    // Get Project count
    public int getProjectCount() {
        String countQuery = "SELECT * FROM " + TABLE_PROJECT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    // Get Reading count
    public int getReadingCount() {
        String countQuery = "SELECT * FROM " + TABLE_READING;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    // Update a Session
    public int updateSession(Session session) {
        SQLiteDatabase db  = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SESSION_ID, session.getId());
        values.put(SESSION_BRIDGE_ID, session.getBridgeId());
        values.put(SESSION_DATE, session.getDate());
        values.put(SESSION_LOCATION, session.getLocation());
        values.put(SESSION_NOTES, session.getNotes());

        int result = db.update(TABLE_SESSION, values, SESSION_ID + "=?",
                new String[] { String.valueOf(session.getId()) });
        db.close();
        return result;
    }

    // Update a Person
    public int updatePerson(Person person) {
        SQLiteDatabase db  = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PERSON_ID, person.getId());
        values.put(PERSON_FULLNAME, person.getFullName());
        values.put(PERSON_PROJECT_ID, person.getProjectId());

        int result = db.update(TABLE_PERSON, values, PERSON_ID + "=?",
                new String[] { String.valueOf(person.getId()) });
        db.close();
        return result;
    }

    // Update a Bridge
    public int updateBridge(Bridge bridge) {
        SQLiteDatabase db  = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BRIDGE_ID, bridge.getId());
        values.put(BRIDGE_NAME, bridge.getName());
        values.put(BRIDGE_LOCATION, bridge.getLocation());
        values.put(BRIDGE_LATITUDE, bridge.getLatitude());
        values.put(BRIDGE_LONGITUDE, bridge.getLongitude());

        int result = db.update(TABLE_BRIDGE, values, BRIDGE_ID + "=?",
                new String[] { String.valueOf(bridge.getId()) });
        db.close();
        return result;
    }

    // Update a Project
    public int updateProject(Project project) {
        SQLiteDatabase db  = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PROJECT_ID, project.getId());
        values.put(PROJECT_NAME, project.getName());

        int result = db.update(TABLE_PROJECT, values, PROJECT_ID + "=?",
                new String[] { String.valueOf(project.getId()) });
        db.close();
        return result;
    }

    // Update a Reading
    public int updateReading(Reading reading) {
        SQLiteDatabase db  = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(READING_ID, reading.getId());
        values.put(READING_TIME, reading.getTime());
        values.put(READING_FLOW_VALUE, reading.getFlowValue());
        values.put(READING_SESSION_ORDER, reading.getSessionOrder());
        values.put(READING_SESSION_ID, reading.getSessionId());

        int result = db.update(TABLE_READING, values, READING_ID + "=?",
                new String[] { String.valueOf(reading.getId()) });
        db.close();
        return result;
    }

    // Delete a Session
    public void deleteSession(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SESSION, SESSION_ID + "=?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    // Delete a Person
    public void deletePerson(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PERSON, PERSON_ID + "=?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    // Delete a Bridge
    public void deleteBridge(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BRIDGE, BRIDGE_ID + "=?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    // Delete a Project
    public void deleteProject(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROJECT, PROJECT_ID + "=?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    // Delete a Reading
    public void deleteReading(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_READING, READING_ID + "=?",
                new String[] { String.valueOf(id) });
        db.close();
    }

}
