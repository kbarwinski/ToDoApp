package pl.barwinski.todoapp.shared.helpers;

import android.content.ContentValues;
import android.database.Cursor;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ContentValuesMapper {

    private static final Map<Class<?>, BiConsumer<ContentValues, FieldValuePair>> typePutters = new HashMap<>();

    static {
        typePutters.put(String.class, (contentValues, pair) -> contentValues.put(pair.fieldName, (String) pair.fieldValue));
        typePutters.put(Integer.class, (contentValues, pair) -> contentValues.put(pair.fieldName, (Integer) pair.fieldValue));
        typePutters.put(Long.class, (contentValues, pair) -> contentValues.put(pair.fieldName, (Long) pair.fieldValue));
        typePutters.put(Float.class, (contentValues, pair) -> contentValues.put(pair.fieldName, (Float) pair.fieldValue));
        typePutters.put(Double.class, (contentValues, pair) -> contentValues.put(pair.fieldName, (Double) pair.fieldValue));
        typePutters.put(Boolean.class, (contentValues, pair) -> contentValues.put(pair.fieldName, (Boolean) pair.fieldValue));
        typePutters.put(Date.class, (contentValues, pair) -> contentValues.put(pair.fieldName, ((Date) pair.fieldValue).getTime()));
    }

    public static ContentValues mapObjectToContentValues(Object object) {
        ContentValues contentValues = new ContentValues();
        Class<?> objectClass = object.getClass();

        for (Field field : objectClass.getDeclaredFields()) {
            field.setAccessible(true);
            String columnName = field.getName();
            try {
                Object fieldValue = field.get(object);
                if (fieldValue != null) {
                    BiConsumer<ContentValues, FieldValuePair> typePutter = typePutters.get(fieldValue.getClass());
                    if (typePutter != null) {
                        typePutter.accept(contentValues, new FieldValuePair(columnName, fieldValue));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return contentValues;
    }

    public static <T> T mapCursorToObject(Cursor cursor, Class<T> objectClass) {
        T object = null;

        try {
            object = objectClass.newInstance();

            Field[] fields = objectClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String columnName = field.getName();
                int columnIndex = cursor.getColumnIndex(columnName);

                if (columnIndex >= 0) {
                    Class<?> fieldType = field.getType();
                    if (fieldType == String.class) {
                        String value = cursor.getString(columnIndex);
                        field.set(object, value);
                    } else if (fieldType == int.class || fieldType == Integer.class) {
                        int value = cursor.getInt(columnIndex);
                        field.set(object, value);
                    } else if (fieldType == long.class || fieldType == Long.class) {
                        long value = cursor.getLong(columnIndex);
                        field.set(object, value);
                    } else if (fieldType == double.class || fieldType == Double.class) {
                        double value = cursor.getDouble(columnIndex);
                        field.set(object, value);
                    } else if (fieldType == float.class || fieldType == Float.class) {
                        float value = cursor.getFloat(columnIndex);
                        field.set(object, value);
                    } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                        boolean value = cursor.getInt(columnIndex) == 1;
                        field.set(object, value);
                    } else if (fieldType == Date.class) {
                        long value = cursor.getLong(columnIndex);
                        field.set(object, new Date(value));
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return object;
    }


    public static String[] getObjectFieldNames(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    private static class FieldValuePair {
        String fieldName;
        Object fieldValue;

        FieldValuePair(String fieldName, Object fieldValue) {
            this.fieldName = fieldName;
            this.fieldValue = fieldValue;
        }
    }
}
