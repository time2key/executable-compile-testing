package com.google.testing.compile.executable.compiledClass;

import com.google.common.truth.Fact;
import com.google.common.truth.FailureMetadata;
import com.google.common.truth.Subject;
import com.google.common.truth.Truth;
import com.google.testing.compile.Compilation;
import com.google.testing.compile.CompilationSubject;
import com.google.testing.compile.executable.ExecutionHelper;
import com.google.testing.compile.executable.instantiatedClass.InstantiatedClass;
import com.google.testing.compile.executable.instantiatedClass.InstantiatedClassClause;
import com.google.testing.compile.executable.instantiatedClass.InstantiatedClassSubject;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import javax.tools.JavaFileObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.executable.instantiatedClass.InstantiatedClassSubject.instantiatedClassFactory;

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

    /**
     * Starts making assertions about a {@link CompiledClass}.
     * */
    public static CompiledClassSubject assertThat(CompiledClass actual) {
        return assertAbout(compiledClasses()).that(actual);
    }

    public InstantiatedClassClause instantiate(Object... constructorArguments) {
        InstantiatedClass instance = actual.instantiate(constructorArguments);
        if (instance == null) {
            Class<?>[] classesForConstructorArguments = ExecutionHelper.getClassesForObjects(constructorArguments);

            StringBuilder constructorArgumentsStringBuilder = new StringBuilder();
            for (int i = 0; i < classesForConstructorArguments.length; i++) {
                if (i > 0) {
                    constructorArgumentsStringBuilder.append(", ");
                }
                constructorArgumentsStringBuilder.append(classesForConstructorArguments[i].getSimpleName());
            }

            String declaredConstructors = ExecutionHelper
                    .getStringDescribingExecutables(actual.getClazz().getDeclaredConstructors());
            String publicConstructors = ExecutionHelper
                    .getStringDescribingExecutables(actual.getClazz().getConstructors());

            failWithoutActual(
                    Fact.fact("class could not be instantiated with arguments of type",
                            constructorArgumentsStringBuilder.toString()),
                    Fact.fact("available constructors declared in class",
                            declaredConstructors),
                    Fact.fact("available public constructors",
                            publicConstructors));
        }
        final InstantiatedClassSubject subject = assertAbout(instantiatedClassFactory()).that(instance);

        return new InstantiatedClassClause() {
        };
    }

}
