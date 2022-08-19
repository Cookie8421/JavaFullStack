package Basis.annotation;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;

public class Annotation {


    @Deprecated
    public void oldMethod() {
    }

    /**
     * ignore warning
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List processList() {
        List list = new ArrayList();
        return list;
    }


    @Retention(RetentionPolicy.SOURCE)
    public @interface SourcePolicy {

    }
    @Retention(RetentionPolicy.CLASS)
    public @interface ClassPolicy {

    }
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RuntimePolicy {

    }



    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    public @interface TestInheritedAnnotation {
        String [] values();
        int number();
    }

}
