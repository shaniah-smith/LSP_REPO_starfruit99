package org.howard.edu.lsp.midterm.question4;

public class Thermostat extends Device implements Networked {
    private double temperatureC;

    public Thermostat(String id, String location, double initialTempC) {
        super(id, location);
        this.temperatureC = initialTempC;
    }

    public double getTemperatureC() { return temperatureC; }
    public void setTemperatureC(double temperatureC) { this.temperatureC = temperatureC; }

    // Networked behavior
    @Override public void connect()    { setConnected(true); }
    @Override public void disconnect() { setConnected(false); }
    @Override public boolean isConnected() { return super.isConnected(); }

    @Override public String getStatus() {
        String conn = isConnected() ? "up" : "down";
        return "Thermostat[id=" + getId() + ", loc=" + getLocation() + ", conn=" + conn + ", tempC=" + temperatureC + "]";
    }
}
