package org.literacyapp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import org.literacyapp.model.content.multimedia.JoinAudiosWithLetters;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "JOIN_AUDIOS_WITH_LETTERS".
*/
public class JoinAudiosWithLettersDao extends AbstractDao<JoinAudiosWithLetters, Long> {

    public static final String TABLENAME = "JOIN_AUDIOS_WITH_LETTERS";

    /**
     * Properties of entity JoinAudiosWithLetters.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property AudioId = new Property(1, long.class, "audioId", false, "AUDIO_ID");
        public final static Property LetterId = new Property(2, long.class, "letterId", false, "LETTER_ID");
    }


    public JoinAudiosWithLettersDao(DaoConfig config) {
        super(config);
    }
    
    public JoinAudiosWithLettersDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"JOIN_AUDIOS_WITH_LETTERS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"AUDIO_ID\" INTEGER NOT NULL ," + // 1: audioId
                "\"LETTER_ID\" INTEGER NOT NULL );"); // 2: letterId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"JOIN_AUDIOS_WITH_LETTERS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, JoinAudiosWithLetters entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getAudioId());
        stmt.bindLong(3, entity.getLetterId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, JoinAudiosWithLetters entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getAudioId());
        stmt.bindLong(3, entity.getLetterId());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public JoinAudiosWithLetters readEntity(Cursor cursor, int offset) {
        JoinAudiosWithLetters entity = new JoinAudiosWithLetters( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // audioId
            cursor.getLong(offset + 2) // letterId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, JoinAudiosWithLetters entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAudioId(cursor.getLong(offset + 1));
        entity.setLetterId(cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(JoinAudiosWithLetters entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(JoinAudiosWithLetters entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(JoinAudiosWithLetters entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
