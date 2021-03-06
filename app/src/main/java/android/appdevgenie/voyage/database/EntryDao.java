package android.appdevgenie.voyage.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface EntryDao {

    @Insert
    void insertEntry(NewEntry newEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateEntry(NewEntry newEntry);

    @Delete
    void deleteEntry(NewEntry newEntry);

    @Query("SELECT * FROM voyage WHERE id = :id")
    LiveData<NewEntry> loadEntryById(int id);

    @Query("SELECT * FROM voyage WHERE username = :username ORDER By updated_on DESC")
    LiveData<List<NewEntry>> loadAllEntriesByUsername(String username);
}
