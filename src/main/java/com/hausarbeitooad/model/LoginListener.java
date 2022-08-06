package com.hausarbeitooad.model;

/**
 *  Wird benötigt um den Active User an die Controller zu verteilen.
 *
 * @author 1st: Abdurrahman Azattemür, 2nd:Maximilian Jaesch
 * @source: Selbst erstellt
 * */
public interface LoginListener {
    void setActiveUser(String uname);
}
