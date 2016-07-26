package org.literacyapp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import java.util.Calendar;
import java.util.Set;
import org.literacyapp.dao.converter.AudioFormatConverter;
import org.literacyapp.dao.converter.CalendarConverter;
import org.literacyapp.dao.converter.LocaleConverter;
import org.literacyapp.dao.converter.StringSetConverter;
import org.literacyapp.model.enums.Locale;
import org.literacyapp.model.enums.content.AudioFormat;

import org.literacyapp.dao.Audio;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "AUDIO".
*/
public class AudioDao extends AbstractDao<Audio, Long> {

    public static final String TABLENAME = "AUDIO";

    /**
     * Properties of entity Audio.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Locale = new Property(1, String.class, "locale", false, "LOCALE");
        public final static Property TimeLastUpdate = new Property(2, Long.class, "timeLastUpdate", false, "TIME_LAST_UPDATE");
        public final static Property RevisionNumber = new Property(3, Integer.class, "revisionNumber", false, "REVISION_NUMBER");
        public final static Property FileUrl = new Property(4, String.class, "fileUrl", false, "FILE_URL");
        public final static Property FileSize = new Property(5, Integer.class, "fileSize", false, "FILE_SIZE");
        public final static Property ContentType = new Property(6, String.class, "contentType", false, "CONTENT_TYPE");
        public final static Property AttributionUrl = new Property(7, String.class, "attributionUrl", false, "ATTRIBUTION_URL");
        public final static Property LiteracySkills = new Property(8, String.class, "literacySkills", false, "LITERACY_SKILLS");
        public final static Property NumeracySkills = new Property(9, String.class, "numeracySkills", false, "NUMERACY_SKILLS");
        public final static Property Transcription = new Property(10, String.class, "transcription", false, "TRANSCRIPTION");
        public final static Property AudioFormat = new Property(11, String.class, "audioFormat", false, "AUDIO_FORMAT");
    };

    private final LocaleConverter localeConverter = new LocaleConverter();
    private final CalendarConverter timeLastUpdateConverter = new CalendarConverter();
    private final StringSetConverter literacySkillsConverter = new StringSetConverter();
    private final StringSetConverter numeracySkillsConverter = new StringSetConverter();
    private final AudioFormatConverter audioFormatConverter = new AudioFormatConverter();

    public AudioDao(DaoConfig config) {
        super(config);
    }
    
    public AudioDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"AUDIO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"LOCALE\" TEXT," + // 1: locale
                "\"TIME_LAST_UPDATE\" INTEGER," + // 2: timeLastUpdate
                "\"REVISION_NUMBER\" INTEGER," + // 3: revisionNumber
                "\"FILE_URL\" TEXT," + // 4: fileUrl
                "\"FILE_SIZE\" INTEGER," + // 5: fileSize
                "\"CONTENT_TYPE\" TEXT," + // 6: contentType
                "\"ATTRIBUTION_URL\" TEXT," + // 7: attributionUrl
                "\"LITERACY_SKILLS\" TEXT," + // 8: literacySkills
                "\"NUMERACY_SKILLS\" TEXT," + // 9: numeracySkills
                "\"TRANSCRIPTION\" TEXT," + // 10: transcription
                "\"AUDIO_FORMAT\" TEXT);"); // 11: audioFormat
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"AUDIO\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Audio entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Locale locale = entity.getLocale();
        if (locale != null) {
            stmt.bindString(2, localeConverter.convertToDatabaseValue(locale));
        }
 
        Calendar timeLastUpdate = entity.getTimeLastUpdate();
        if (timeLastUpdate != null) {
            stmt.bindLong(3, timeLastUpdateConverter.convertToDatabaseValue(timeLastUpdate));
        }
 
        Integer revisionNumber = entity.getRevisionNumber();
        if (revisionNumber != null) {
            stmt.bindLong(4, revisionNumber);
        }
 
        String fileUrl = entity.getFileUrl();
        if (fileUrl != null) {
            stmt.bindString(5, fileUrl);
        }
 
        Integer fileSize = entity.getFileSize();
        if (fileSize != null) {
            stmt.bindLong(6, fileSize);
        }
 
        String contentType = entity.getContentType();
        if (contentType != null) {
            stmt.bindString(7, contentType);
        }
 
        String attributionUrl = entity.getAttributionUrl();
        if (attributionUrl != null) {
            stmt.bindString(8, attributionUrl);
        }
 
        Set literacySkills = entity.getLiteracySkills();
        if (literacySkills != null) {
            stmt.bindString(9, literacySkillsConverter.convertToDatabaseValue(literacySkills));
        }
 
        Set numeracySkills = entity.getNumeracySkills();
        if (numeracySkills != null) {
            stmt.bindString(10, numeracySkillsConverter.convertToDatabaseValue(numeracySkills));
        }
 
        String transcription = entity.getTranscription();
        if (transcription != null) {
            stmt.bindString(11, transcription);
        }
 
        AudioFormat audioFormat = entity.getAudioFormat();
        if (audioFormat != null) {
            stmt.bindString(12, audioFormatConverter.convertToDatabaseValue(audioFormat));
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Audio readEntity(Cursor cursor, int offset) {
        Audio entity = new Audio( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : localeConverter.convertToEntityProperty(cursor.getString(offset + 1)), // locale
            cursor.isNull(offset + 2) ? null : timeLastUpdateConverter.convertToEntityProperty(cursor.getLong(offset + 2)), // timeLastUpdate
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // revisionNumber
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // fileUrl
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // fileSize
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // contentType
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // attributionUrl
            cursor.isNull(offset + 8) ? null : literacySkillsConverter.convertToEntityProperty(cursor.getString(offset + 8)), // literacySkills
            cursor.isNull(offset + 9) ? null : numeracySkillsConverter.convertToEntityProperty(cursor.getString(offset + 9)), // numeracySkills
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // transcription
            cursor.isNull(offset + 11) ? null : audioFormatConverter.convertToEntityProperty(cursor.getString(offset + 11)) // audioFormat
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Audio entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLocale(cursor.isNull(offset + 1) ? null : localeConverter.convertToEntityProperty(cursor.getString(offset + 1)));
        entity.setTimeLastUpdate(cursor.isNull(offset + 2) ? null : timeLastUpdateConverter.convertToEntityProperty(cursor.getLong(offset + 2)));
        entity.setRevisionNumber(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setFileUrl(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setFileSize(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setContentType(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setAttributionUrl(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setLiteracySkills(cursor.isNull(offset + 8) ? null : literacySkillsConverter.convertToEntityProperty(cursor.getString(offset + 8)));
        entity.setNumeracySkills(cursor.isNull(offset + 9) ? null : numeracySkillsConverter.convertToEntityProperty(cursor.getString(offset + 9)));
        entity.setTranscription(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setAudioFormat(cursor.isNull(offset + 11) ? null : audioFormatConverter.convertToEntityProperty(cursor.getString(offset + 11)));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Audio entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Audio entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
