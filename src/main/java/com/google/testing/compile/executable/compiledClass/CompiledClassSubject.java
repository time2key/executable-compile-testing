package com.google.testing.compile.executable.compiledClass;

import com.google.common.truth.FailureMetadata;
import com.google.common.truth.Subject;
import com.google.common.truth.Truth;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import javax.tools.JavaFileObject;

/**
 * A {@link Truth} subject representing a {@link CompiledClass}.
 *
 * @author Thaddeus Reason
 * */
public class CompiledClassSubject extends Subject<CompiledClassSubject, CompiledClass> {

    private static final Subject.Factory<CompiledClassSubject, CompiledClass> FACTORY =
            new CompiledClassSubjectFactory();

    /**
     * Returns a {@link Subject.Factory} for {@link CompiledClassSubject}s.
     * */
    public static Subject.Factory<CompiledClassSubject, CompiledClass> compiledClasses() {
        return FACTORY;
    }

    private final CompiledClass actual;

    protected CompiledClassSubject(FailureMetadata metadata, @NullableDecl CompiledClass actual) {
        super(metadata, actual);
        this.actual = actual;
    }

    @Override
    protected String actualCustomStringRepresentation() {
        return actual.getFullyQualifiedName();
    }
}
