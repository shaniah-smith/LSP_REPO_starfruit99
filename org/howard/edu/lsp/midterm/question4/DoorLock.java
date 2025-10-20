package org.howard.edu.lsp.midterm.question4;

public class DoorLock extends Device implements Networked, BatteryPowered {
    private int batteryPercent;

    public DoorLock(String id, String location, int initialBattery) {
        super(id, location);                 // must call super
        setBatteryPercent(initialBattery);   // enforces 0..100
    }

    // Networked behavior
    @Override public void connect()    { setConnected(true); }
    @Override public void disconnect() { setConnected(false); }
    @Override public boolean isConnected() { return super.isConnected(); }

    // BatteryPowered behavior
    @Override public int getBatteryPercent() { return batteryPercent; }
    @Override public void setBatteryPercent(int percent) {
        if (percent < 0 || percent > 100) throw new IllegalArgumentException("battery 0..100");
        this.batteryPercent = percent;
    }

    // Required status format
    @Override public String getStatus() {
        String conn = isConnected() ? "up" : "down";
        return "DoorLock[id=" + getId() + ", loc=" + getLocation() + ", conn=" + conn + ", batt=" + batteryPercent + "%]";
    }
}
