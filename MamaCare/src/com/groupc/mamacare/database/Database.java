package com.groupc.mamacare.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.groupc.mamacare.model.User;
import com.groupc.mamacare.model.Visit;
import com.groupc.mamacare.model.Woman;
import com.groupc.mamacare.util.DateHelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This class is the database configuration file for MamaCare application
 * 
 * @author jmpango
 *
 */
public class Database extends SQLiteOpenHelper {
	/* Database name for the application */
	private static final String dbName = "mamacare";
	private static final int dbVersion = 1;

	/* Woman table structure */
	private static final String tableWoman = "woman";
	private static final String tableWomanColumnId = "woman_id";
	private static final String tableWomanColumnFirstName = "first_name";
	private static final String tableWomanColumnLastName = "last_name";
	private static final String tableWomanColumnAge = "age";
	private static final String tableWomanColumnAddress = "address";

	/* Visit table structure */
	private static final String tableVisit = "visit";
	private static final String tableVisitColumnId = "visit_id";
	private static final String tableVisitColumnType = "visit_type";
	private static final String tableVisitColumnDate = "visit_date";
	private static final String tableVisitColumnVacinated = "vacinated";
	private static final String tableVisitColumnWomanId = "woman_id";

	/* user table structure */
	private static final String tableUser = "user";
	private static final String tableUserColumnUsername = "username";
	private static final String tableUserColumnId = "user_id";
	private static final String tableUserColumnPassword = "password";

	/* Database SQL statements to create the Woman table */
	private static final String createTableWoman = "CREATE TABLE IF NOT EXISTS " + tableWoman + "(" + tableWomanColumnId
			+ " TEXT," + tableWomanColumnFirstName + " TEXT, " + tableWomanColumnLastName + " TEXT, "
			+ tableWomanColumnAge + " INTEGER," + tableWomanColumnAddress + " TEXT)";

	/* Database SQL statements to create the Visit table */
	private static final String createTableVisit = "CREATE TABLE IF NOT EXISTS " + tableVisit + "(" + tableVisitColumnId
			+ " TEXT," + tableVisitColumnType + " TEXT, " + tableVisitColumnDate + " DATETIME, "
			+ tableVisitColumnVacinated + " INTEGER," + tableVisitColumnWomanId + " TEXT)";

	/* Database SQL statements to create the User table */
	private static final String createTableUser = "CREATE TABLE IF NOT EXISTS " + tableUser + "(" + tableUserColumnId
			+ " TEXT," + tableUserColumnUsername + " TEXT, " + tableUserColumnPassword + tableVisitColumnWomanId
			+ " TEXT)";

	public Database(Context context) {
		super(context, dbName, null, dbVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createTableWoman);
		db.execSQL(createTableUser);
		db.execSQL(createTableVisit);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public List<Woman> getWomen() {
		// Array to hold the women objects
		List<Woman> womenList = new ArrayList<Woman>();
		
		womenList.add(new Woman(1, "Catherine", "Kabahuma", 22, "Nakawa"));
		womenList.add(new Woman(1, "Glorious", "Orishaba", 28, "Gayaza"));
		womenList.add(new Woman(1, "Irene", "Yankee", 25, "Bukoto"));
		womenList.add(new Woman(1, "Patience", "Akunda", 18, "Bwaise"));
		womenList.add(new Woman(1, "Mpiima", "Moreen", 35, "Ntinda"));
		
		
		
		/*// SQL query to select all the women from the DB
		String selectQuery = "SELECT * FROM " + tableWoman;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// Read from the DB all the women rows
		if (cursor.moveToFirst()) {
			do {

				// Get the id for a specific woman to be used to retrieve the
				// visit objects
				int womanId = cursor.getInt(cursor.getColumnIndex(tableWomanColumnId));

				// for each woman we retrieve all the visits attached
				String selectVisitQuery = "SELECT * FROM " + tableVisit + " WHERE " + tableVisitColumnWomanId + " = "
						+ womanId;
				Cursor visitCursor = db.rawQuery(selectVisitQuery, null);
				List<Visit> womanVisitList = new ArrayList<Visit>();
				if (visitCursor.moveToFirst()) {
					do {
						Visit visit = new Visit(visitCursor.getInt(visitCursor.getColumnIndex(tableVisitColumnId)),
								visitCursor.getString(visitCursor.getColumnIndex(tableVisitColumnType)),
								DateHelper.getConvertedDate(
										visitCursor.getString(visitCursor.getColumnIndex(tableVisitColumnDate))),
								visitCursor.getInt(visitCursor.getColumnIndex(tableVisitColumnVacinated)), womanId);

						womanVisitList.add(visit);

					} while (visitCursor.moveToNext());
				}

				// populate the woman object
				Woman woman = new Woman(womanId, cursor.getString(cursor.getColumnIndex(tableWomanColumnFirstName)),
						cursor.getString(cursor.getColumnIndex(tableWomanColumnLastName)),
						cursor.getInt(cursor.getColumnIndex(tableWomanColumnAge)),
						cursor.getString(cursor.getColumnIndex(tableWomanColumnAddress)), womanVisitList);

				// Add each woman retrieved from the DB into our arrayList
				womenList.add(woman);
			} while (cursor.moveToNext());// Keeps looping until last item in
											// the table
		}*/
		// Sort the women using the comparable method we defined in the women
		// POJO
		Collections.sort(womenList);
		// Return the womenList
		return womenList;
	}

	public void deleteWoman(int womanId) {
		// Checks if the id exists
		Woman woman = getWoman(womanId);
		if (woman != null) {
			SQLiteDatabase db = this.getWritableDatabase();

			// we start by cascading the deleting the visits attached to that
			// specific woman
			deleteVisitByWomanID(womanId);

			// we can safely delete the woman after deleting her visits
			db.delete(tableWoman, tableWomanColumnId + " = ?", new String[] { String.valueOf(womanId) });
		}
	}

	private void deleteVisitByWomanID(int womanId) {
		String selectVisitQuery = "SELECT * FROM " + tableVisit + " WHERE " + tableVisitColumnWomanId + " = " + womanId;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor visitCursor = db.rawQuery(selectVisitQuery, null);

		// Loop through and get all the visits attached to a specific woman
		if (visitCursor.moveToFirst()) {
			do {
				int visitId = visitCursor.getInt(visitCursor.getColumnIndex(tableVisitColumnId));
				db.delete(tableVisit, tableVisitColumnId + " = ?", new String[] { String.valueOf(visitId) });

			} while (visitCursor.moveToNext());
		}

	}

	public Woman getWoman(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT * FROM " + tableWoman + " WHERE " + tableWomanColumnId + " = '" + id + "'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor != null && cursor.moveToFirst()) {
			int womanId = cursor.getInt(cursor.getColumnIndex(tableWomanColumnId));

			String selectVisitQuery = "SELECT * FROM " + tableVisit + " WHERE " + tableVisitColumnWomanId + " = "
					+ womanId;
			Cursor visitCursor = db.rawQuery(selectVisitQuery, null);
			List<Visit> womanVisitList = new ArrayList<Visit>();

			if (visitCursor.moveToFirst()) {
				do {
					Visit visit = new Visit(visitCursor.getInt(visitCursor.getColumnIndex(tableVisitColumnId)),
							visitCursor.getString(visitCursor.getColumnIndex(tableVisitColumnType)),
							DateHelper.getConvertedDate(
									visitCursor.getString(visitCursor.getColumnIndex(tableVisitColumnDate))),
							visitCursor.getInt(visitCursor.getColumnIndex(tableVisitColumnVacinated)), womanId);

					womanVisitList.add(visit);

				} while (visitCursor.moveToNext());
			}

			Woman woman = new Woman(womanId, cursor.getString(cursor.getColumnIndex(tableWomanColumnFirstName)),
					cursor.getString(cursor.getColumnIndex(tableWomanColumnLastName)),
					cursor.getInt(cursor.getColumnIndex(tableWomanColumnAge)),
					cursor.getString(cursor.getColumnIndex(tableWomanColumnAddress)), womanVisitList);

			return woman;
		}

		return null;
	}

	public Woman editWoman(Woman woman) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues contentValues = new ContentValues();
		contentValues.put(tableWomanColumnId, woman.getId());
		contentValues.put(tableWomanColumnFirstName, woman.getFirstName());
		contentValues.put(tableWomanColumnLastName, woman.getLastName());
		contentValues.put(tableWomanColumnAge, woman.getAge());
		contentValues.put(tableWomanColumnAddress, woman.getAddress());

		db.update(tableWoman, contentValues, null, null);

		return getWoman(woman.getId());
	}

	@SuppressLint("SimpleDateFormat")
	public Visit editVisit(Visit visit) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues contentValues = new ContentValues();
		contentValues.put(tableVisitColumnId, visit.getId());
		contentValues.put(tableVisitColumnType, visit.getVisitType());
		contentValues.put(tableVisitColumnVacinated, visit.getIsVacinated());
		contentValues.put(tableVisitColumnDate,
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(visit.getVisitDate()));
		contentValues.put(tableVisitColumnWomanId, visit.getWomanId());

		db.update(tableVisit, contentValues, null, null);

		return getVisitById(visit.getId(), visit.getWomanId());

	}

	private Visit getVisitById(int id, int womanId) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT * FROM " + tableVisit + " WHERE " + tableVisitColumnId + " = '" + id + "'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor != null && cursor.moveToFirst()) {
			Visit visit = new Visit(cursor.getInt(cursor.getColumnIndex(tableVisitColumnId)),
					cursor.getString(cursor.getColumnIndex(tableVisitColumnType)),
					DateHelper.getConvertedDate(cursor.getString(cursor.getColumnIndex(tableVisitColumnDate))),
					cursor.getInt(cursor.getColumnIndex(tableVisitColumnVacinated)), womanId);
			return visit;
		}
		return null;
	}

	public List<Woman> saveWoman(Woman woman) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues contentValues = new ContentValues();
		contentValues.put(tableWomanColumnId, woman.getId());
		contentValues.put(tableWomanColumnFirstName, woman.getFirstName());
		contentValues.put(tableWomanColumnLastName, woman.getLastName());
		contentValues.put(tableWomanColumnAge, woman.getAge());
		contentValues.put(tableWomanColumnAddress, woman.getAddress());

		db.update(tableWoman, contentValues, null, null);
		
		List<Woman> women = getWomen();
		return women;
	}

	public Visit getVisitByType(String visitType, int womanId) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT * FROM " + tableVisit + " WHERE " + tableVisitColumnType + " = '" + visitType
				+ "'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor != null && cursor.moveToFirst()) {
			Visit visit = new Visit(cursor.getInt(cursor.getColumnIndex(tableVisitColumnId)),
					cursor.getString(cursor.getColumnIndex(tableVisitColumnType)),
					DateHelper.getConvertedDate(cursor.getString(cursor.getColumnIndex(tableVisitColumnDate))),
					cursor.getInt(cursor.getColumnIndex(tableVisitColumnVacinated)), womanId);
			return visit;
		}
		return null;
	}

	@SuppressLint("SimpleDateFormat")
	public void saveVisit(Visit visit) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues contentValues = new ContentValues();
		contentValues.put(tableVisitColumnId, visit.getId());
		contentValues.put(tableVisitColumnType, visit.getVisitType());
		contentValues.put(tableVisitColumnVacinated, visit.getIsVacinated());
		contentValues.put(tableVisitColumnDate,
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(visit.getVisitDate()));
		contentValues.put(tableVisitColumnWomanId, visit.getWomanId());

		db.update(tableWoman, contentValues, null, null);
	}

	public User getUser(String username, String password) {
		
		if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("123")){
			return new User(1, username, password);
			
			//TODO  -  Add DB code to retrieve user
			/*SQLiteDatabase db = this.getReadableDatabase();
			
					String selectQuery = "SELECT * FROM " + tableUser + " WHERE " + tableUserColumnUsername + " = '" + username
				+ "' AND " + tableUserColumnPassword + " = '" + password + "'";
								Cursor cursor = db.rawQuery(selectQuery, null);
				if (cursor != null && cursor.moveToFirst()) {
						User user = new User(cursor.getInt(cursor.getColumnIndex(tableUserColumnId)),
								cursor.getString(cursor.getColumnIndex(tableUserColumnUsername)),
								cursor.getString(cursor.getColumnIndex(tableUserColumnPassword)));
						return user;
				}
			return null;*/
		}else{
			return null;
		}

	}

	public void saveUser(User user) {
	}

	public void deleteUser(int id) {
	}

	public User editUser(User user) {
		return null;
	}
}
