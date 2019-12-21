package com.google.testing.compile.executable.compiledClass;

import com.google.testing.compile.Compilation;

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

}
