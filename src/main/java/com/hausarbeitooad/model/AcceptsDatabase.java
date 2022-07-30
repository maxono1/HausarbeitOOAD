package com.hausarbeitooad.model;

import com.hausarbeitooad.db.DatabaseConnection;

public interface AcceptsDatabase {
    void setDatabaseConnection(DatabaseConnection conn);
}
