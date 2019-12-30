package com.google.testing.compile.executable.instantiatedClass;

import com.google.common.truth.FailureMetadata;
import com.google.common.truth.Subject;
import com.google.common.truth.Truth;

/**
 * A {@link Truth} subject factory for a {@link InstantiatedClass}.
 * */
class InstantiatedClassSubjectFactory implements Subject.Factory<InstantiatedClassSubject, InstantiatedClass> {

    @Override
    public InstantiatedClassSubject createSubject(FailureMetadata failureMetadata, InstantiatedClass that) {
        return new InstantiatedClassSubject(failureMetadata, that);
    }
}
