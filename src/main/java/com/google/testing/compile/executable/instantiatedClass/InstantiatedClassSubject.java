package com.google.testing.compile.executable.instantiatedClass;

import com.google.common.truth.FailureMetadata;
import com.google.common.truth.Subject;
import com.google.common.truth.Truth;
import com.google.testing.compile.executable.compiledClass.CompiledClass;
import com.google.testing.compile.executable.compiledClass.CompiledClassSubject;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/**
 * A {@link Truth} subject representing an instantiated compiled class.
 *
 * @author Thaddeus Reason
 * */
public final class InstantiatedClassSubject extends Subject<InstantiatedClassSubject, InstantiatedClass> {

    private static final Subject.Factory<InstantiatedClassSubject, InstantiatedClass> FACTORY =
            new InstantiatedClassSubjectFactory();

    private final InstantiatedClass actual;

    /**
     * Returns a {@link Subject.Factory} for {@link InstantiatedClassSubject}s.
     * */
    public static Subject.Factory<InstantiatedClassSubject, InstantiatedClass> instantiatedClassFactory() {
        return FACTORY;
    }

    protected InstantiatedClassSubject(FailureMetadata metadata, @NullableDecl InstantiatedClass actual) {
        super(metadata, actual);
        this.actual = actual;
    }

    @Override
    protected String actualCustomStringRepresentation() {
        return actual.getActualInstance().getClass().getName();
    }


}
