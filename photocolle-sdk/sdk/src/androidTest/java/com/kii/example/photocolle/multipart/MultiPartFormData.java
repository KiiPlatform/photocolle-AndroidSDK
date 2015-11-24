package com.kii.example.photocolle.multipart;

import java.util.ArrayList;
import java.util.Arrays;

public class MultiPartFormData {

    public final ArrayList<Field> fields = new ArrayList<Field>();

    public MultiPartFormData(Field...fields) {
        this.fields.addAll(Arrays.asList(fields));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MultiPartFormData obj = (MultiPartFormData)o;
        return Arrays.equals(this.fields.toArray(new Field[] { }),
                obj.fields.toArray(new Field[] { }));
    }

    @Override
    public String toString() {
        return Arrays.toString(this.fields.toArray(new Field[] { }));
    }
}
