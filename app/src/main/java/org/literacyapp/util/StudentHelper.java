package org.literacyapp.util;

import android.os.Environment;

import java.io.File;

public class StudentHelper {

    public static String extractDeviceIdFromUniqueId(String uniqueId) {
        String[] uniqueIdArray = uniqueId.split("_");
        String deviceId = uniqueIdArray[0];
        return deviceId;
    }

    public static Long extractLongIdFromUniqueId(String uniqueId) {
        String[] uniqueIdArray = uniqueId.split("_");
        Long longId = Long.valueOf(uniqueIdArray[1]);
        return longId;
    }

    public static File getStudentDirectory() {
        File studentDirectory = new File(Environment.getExternalStorageDirectory() + "/.literacyapp/student");
        if (!studentDirectory.exists()) {
            studentDirectory.mkdirs();
        }
        return studentDirectory;
    }

    public static File getTestStudentsDirectory() {
        File testStudentsDirectory = new File(getStudentDirectory(), "test_students");
        if (!testStudentsDirectory.exists()) {
            testStudentsDirectory.mkdir();
        }
        return testStudentsDirectory;
    }

    public static File getStudentImageDirectory() {
        File studentImageDirectory = new File(getStudentDirectory(), "student_image");
        if (!studentImageDirectory.exists()) {
            studentImageDirectory.mkdir();
        }
        return studentImageDirectory;
    }

    public static File getStudentThumbnailDirectory() {
        File studentThumbnailDirectory = new File(getStudentDirectory(), "student_thumbnail");
        if (!studentThumbnailDirectory.exists()) {
            studentThumbnailDirectory.mkdir();
        }
        return studentThumbnailDirectory;
    }
}