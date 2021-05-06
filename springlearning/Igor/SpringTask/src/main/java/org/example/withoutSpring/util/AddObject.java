package org.example.withoutSpring.util;

import java.util.List;

public interface AddObject {
    public default  void addToList() {
        List<Object> addList = null;
    }
}
