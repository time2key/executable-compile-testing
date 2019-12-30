package com.google.testing.compile.executable.instantiatedClass;

import com.google.testing.compile.executable.compiledClass.CompiledClassClause;

/**
 * Clause in the fluent API that allows checks on an instance of a {@linkplain CompiledClassClause compiled class}
 * */
public interface InstantiatedClassClause {

    /**
     * Checks the results of calling a given method on this instance
     * */
    //ExecutedMethodClause callMethod(String methodName, Object... arguments);
}
