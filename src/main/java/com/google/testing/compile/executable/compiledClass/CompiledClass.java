package com.google.testing.compile.executable.compiledClass;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.executable.ExecutionHelper;
import com.google.testing.compile.executable.instantiatedClass.InstantiatedClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Represents a class compiled within a {@link Compilation}.
 *
 * @author Thaddeus Reason
 * */
public class CompiledClass {


    private String fullyQualifiedName;
    private Compilation compilation;
    private Class<?> clazz;

    public CompiledClass(String fullyQualifiedName, Compilation compilation, Class<?> clazz) {
        this.fullyQualifiedName = fullyQualifiedName;
        this.compilation = compilation;
        this.clazz = clazz;
    }

    /**
     * The fully qualified name of the class that was attempted to be loaded
     * */
    public String getFullyQualifiedName() {
        return fullyQualifiedName;
    }

    /**
     * The compilation the class was loaded from
     * */
    public Compilation getCompilation() {
        return compilation;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public InstantiatedClass instantiate(Object... constructorArguments) {
        try {
            Constructor<?> constructor = clazz.getConstructor(ExecutionHelper.getClassesForObjects(constructorArguments));
            Object instance = constructor.newInstance(constructorArguments);
            InstantiatedClass instantiatedClass = new InstantiatedClass(instance, this, constructorArguments);
            return instantiatedClass;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }
}
