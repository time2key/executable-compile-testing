package com.google.testing.compile.executable.instantiatedClass;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.executable.compiledClass.CompiledClass;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Represents an instantiated {@link CompiledClass} within a {@link Compilation}.
 *
 * If the attempt succeeded, the actual instance will be stored in this class.
 *
 * @author Thaddeus Reason
 * */
public class InstantiatedClass {

    private Object actualInstance;
    private CompiledClass compiledClass;
    private Object[] constructorArguments;

    public InstantiatedClass(Object actualInstance, CompiledClass compiledClass, Object... constructorArguments) {
        this.actualInstance = actualInstance;
        this.compiledClass = compiledClass;
        this.constructorArguments = constructorArguments;
    }

    @NonNull
    public Object getActualInstance() {
        return actualInstance;
    }

    @NonNull
    public CompiledClass getCompiledClass() {
        return compiledClass;
    }

    @NonNull
    public Object[] getConstructorArguments() {
        return constructorArguments;
    }
}
