package org.howard.edu.lsp.midterm.question4;

public abstract class Device {
    private final String id;
    private final String location;
    private boolean connected;
    private long lastHeartbeatEpochSeconds;

    public Device(String id, String location) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("id required");
        if (location == null) location = "";
        this.id = id;
        this.location = location;
        this.connected = false;
        this.lastHeartbeatEpochSeconds = 0L;
    }

    // Identity & metadata
    public String getId() { return id; }
    public String getLocation() { return location; }

    // Connectivity controls for subclasses
    protected void setConnected(boolean connected) { this.connected = connected; }
    public boolean isConnected() { return connected; }

    // Heartbeat support (records a timestamp and also returns a friendly line)
    public String heartbeat() {
        this.lastHeartbeatEpochSeconds = System.currentTimeMillis() / 1000L;
        return "Heartbeat ok for " + id + " at " + lastHeartbeatEpochSeconds;
    }

    public long getLastHeartbeatEpochSeconds() { return lastHeartbeatEpochSeconds; }

    // Each concrete device must report its status in a single string
    public abstract String getStatus();
}
