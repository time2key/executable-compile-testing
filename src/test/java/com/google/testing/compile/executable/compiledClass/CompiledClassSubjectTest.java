package com.google.testing.compile.executable.compiledClass;

import com.google.common.truth.ExpectFailure;
import com.google.testing.compile.executable.instantiatedClass.InstantiatedClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.testing.compile.executable.compiledClass.CompiledClassSubject.assertThat;
import static com.google.common.truth.ExpectFailure.assertThat;
import static com.google.testing.compile.executable.compiledClass.CompiledClassSubject.compiledClasses;

@RunWith(JUnit4.class)
public class CompiledClassSubjectTest {

    @Rule
    public final ExpectFailure expectFailure = new ExpectFailure();

    private static class ExampleClassForTesting {

    }

    private CompiledClass exampleClassForTestingCompiledClass = new CompiledClass(
            ExampleClassForTesting.class.getName(),
            null,
            ExampleClassForTesting.class);

    @Test
    public void instantiate_works() {
        assertThat(exampleClassForTestingCompiledClass)
                .instantiate();
    }

    @Test
    public void instantiate_wrongArguments_fails() {
        expectFailure
                .whenTesting()
                .about(compiledClasses())
                .that(exampleClassForTestingCompiledClass)
                .instantiate("arg0");

        AssertionError expected = expectFailure.getFailure();
        assertThat(expected)
                .factValue("class could not be instantiated with arguments of type")
                .isEqualTo("String");
        assertThat(expected)
                .factValue("available constructors declared in class")
                .isEqualTo("()");
        assertThat(expected)
                .factValue("available public constructors")
                .isEqualTo("");
    }
}
