package com.mission1.mission1.db;

import lombok.Data;

@Data
public class BookmarkGroup {
    private int id;
    private String bookmarkName;
    private int orderId;
    private String registrationDate;
    private String editDate;
}
