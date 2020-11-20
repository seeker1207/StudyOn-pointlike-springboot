package com.studyon.pointlike.domain;

public enum PointValue {
    INITIAL(1000),
    MAKE_ROOM(100),
    ENTER_ROOM(50);

    final private int point;

    private PointValue(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
}
