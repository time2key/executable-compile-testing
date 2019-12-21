package com.google.testing.compile.executable.compiledClass;

import com.google.common.truth.FailureMetadata;
import com.google.common.truth.Subject;

public class CompiledClassSubjectFactory implements Subject.Factory<CompiledClassSubject, CompiledClass>  {

    @Override
    public CompiledClassSubject createSubject(FailureMetadata failureMetadata, CompiledClass that) {
        return new CompiledClassSubject(failureMetadata, that);
    }
}
