package se233.chapter2.model;

import java.util.ArrayList;
import java.util.List;

public class Currency {
    private String shortCode;
    private CurrencyEntity current;
    private List<CurrencyEntity> historical;
    private Boolean isWatch;
    private Double watchRate;
    public Currency(String shortCode) {
        this.shortCode = shortCode;
        this.isWatch = false;
        this.watchRate = 0.0;
        this.historical = new ArrayList<>();
    }

    public void addHistoricalRate(CurrencyEntity entity) {
        if (historical.size() >= 30) {
            historical.remove(0); // Remove oldest
        }
        historical.add(entity); // Add newest
    }


    public String getShortCode() {
        return shortCode;
    }
    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public void setHistorical(List<CurrencyEntity> historical) {
        this.historical = historical;
    }
    public List<CurrencyEntity> getHistorical() {
        return historical;
    }

    public void setCurrent(CurrencyEntity current) {
        this.current = current;
    }
    public CurrencyEntity getCurrent() {
        return current;
    }

    public boolean getWatch() {
        return isWatch;
        }
    public void setWatch(Boolean watch) {
        isWatch = watch;
    }

    public Double getWatchRate() {
        return watchRate;
    }
    public void setWatchRate(Double watchRate) {
        this.watchRate = watchRate;
    }


}
