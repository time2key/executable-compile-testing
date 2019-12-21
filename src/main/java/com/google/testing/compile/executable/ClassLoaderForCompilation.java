package com.google.testing.compile.executable;

import com.google.common.collect.ImmutableList;
import com.google.common.io.ByteStreams;
import com.google.testing.compile.Compilation;

import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * {@link ClassLoader} for the compiled classes in a {@link Compilation}.
 *
 * This is required for executing any code in a {@link Compilation}
 * */
public class ClassLoaderForCompilation extends ClassLoader {

    private Compilation compilation;
    private ImmutableList<JavaFileObject> compiledClassFiles;

    public ClassLoaderForCompilation(Compilation compilation) {
        this.compilation = compilation;
        this.compiledClassFiles = filterOutNonClasses(compilation.generatedFiles());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        JavaFileObject source = null;

        for (JavaFileObject javaFileObject : compiledClassFiles) {
            if (getClassNameForJavaFileObject(javaFileObject).equals(name)) {
                source = javaFileObject;
            }
        }

        if (source != null) {
            try {
                InputStream inputStream = source.openInputStream();
                byte[] array = ByteStreams.toByteArray(inputStream);
                inputStream.close();

                return defineClass(name, array, 0, array.length);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return super.findClass(name);
    }

    private ImmutableList<JavaFileObject> filterOutNonClasses(ImmutableList<JavaFileObject> javaFileObjects) {
        ArrayList<JavaFileObject> returnValue = new ArrayList<>();
        for (JavaFileObject javaFileObject : javaFileObjects) {
            if (javaFileObject.getKind() == JavaFileObject.Kind.CLASS) {
                returnValue.add(javaFileObject);
            }
        }
        return ImmutableList.copyOf(returnValue);
    }

    private String getClassNameForJavaFileObject(JavaFileObject javaFileObject) {
        return javaFileObject.getName()
                .replace("/CLASS_OUTPUT/", "")
                .replace("/", ".")
                .replace(".class", "");
    }

    public String getAllLoadedClassesAsDiagnosticString() {
        StringBuilder returnValue = new StringBuilder();
        for (int i = 0; i < compiledClassFiles.size(); i++) {
            if (i != 0) returnValue.append(", ");
            returnValue.append(getClassNameForJavaFileObject(compiledClassFiles.get(i)));
        }
        return returnValue.toString();
    }
}
