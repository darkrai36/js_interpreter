package ru.vsu.cs.jslite.runtime.exceptions;

import ru.vsu.cs.jslite.runtime.datatypes.JSValue;

public class JSReturn extends JSControlException {
    public final JSValue value;

    public JSReturn(JSValue value) {
        this.value = value;
    }
}
