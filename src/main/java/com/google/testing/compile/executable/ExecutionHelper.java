package com.google.testing.compile.executable;

import java.lang.reflect.Executable;
import java.lang.reflect.Parameter;

public class ExecutionHelper {

    public static Class<?>[] getClassesForObjects(Object[] objects) {
        Class<?>[] returnValue = new Class[objects.length];
        for (int i = 0; i < objects.length; i++) {
            returnValue[i] = objects[i].getClass();
        }
        return returnValue;
    }

    /**
     * Get a debug string describing the classes in a set of [Executable]s, for debugging or failure message purposes.
     *
     * @return A debug string describing the classes in a set of executables, e.g:
     * (), (String), (Int, String), (Int, String, String)
     * */
    public static String getStringDescribingExecutables(Executable[] executables) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirstExecutable = true;
        for (Executable executable : executables) {
            stringBuilder.append("(");
            boolean isFirstParameter = true;
            for (Parameter parameter : executable.getParameters()) {
                if (!isFirstParameter) {
                    stringBuilder.append(", ");
                }
                isFirstParameter = false;
                stringBuilder.append(parameter.getType().getSimpleName());
            }
            stringBuilder.append(")");

            if (!isFirstExecutable) {
                stringBuilder.append(", ");
            }
            isFirstExecutable = false;
        }
        return stringBuilder.toString();
    }
}
