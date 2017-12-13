package com.my.http.framework.session;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    private  String sessionDumpFile;
    private static ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();

    public SessionManager(String appName) {
        sessionDumpFile = appName + "Sessions.dump";
        readSessions();
    }

    public static Session getSession(String sessionID){
        return sessions.getOrDefault(sessionID, null);
    }

    public void setSession(String sessionID, Session session){
        sessions.put(sessionID, session);
        saveSessions();
    }


    private void saveSessions() {
        try(FileOutputStream fout = new FileOutputStream(sessionDumpFile);
            ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            oos.writeObject(sessions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void readSessions() {
        try(FileInputStream fout = new FileInputStream(sessionDumpFile);
            ObjectInputStream oos = new ObjectInputStream(fout)) {
            sessions = (ConcurrentHashMap) oos.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
