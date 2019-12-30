package com.google.testing.compile.executable.compiledClass;

import com.google.testing.compile.CompileTester;
import com.google.testing.compile.executable.instantiatedClass.InstantiatedClassClause;

/**
 * Clause in the fluent API that allows checks on a compiled class.
 * @param <T> the specific clause type implementing this interface
 *
 * @author Thaddeus Reason
 * */
public interface CompiledClassClause<T> extends CompileTester.ChainingClause<CompileTester.GeneratedPredicateClause<T>> {

    /**
     * Checks the results of instantiating with given arguments
     * */
    InstantiatedClassClause instantiate(Object... constructorArguments);
}
