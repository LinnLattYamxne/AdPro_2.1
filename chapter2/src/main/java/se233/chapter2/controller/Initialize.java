package se233.chapter2.controller;

import se233.chapter2.model.Currency;
import se233.chapter2.model.CurrencyEntity;

import java.util.ArrayList;
import java.util.List;

public class Initialize {
    public static List<Currency> initializeApp() {
        List<Currency> currencyList = new ArrayList<>();
        String code = "USD"; // เพิ่มสกุลเงินที่ต้องการตั้งแต่แรก
        List<CurrencyEntity> hist = FetchData.fetchRange(code, 30);  // เปลี่ยนจาก 8 เป็น 30 วัน
            if (hist != null && !hist.isEmpty()) {
                Currency c = new Currency(code);
                c.setHistorical(hist);
                c.setCurrent(hist.get(hist.size() - 1)); // ใช้ค่าล่าสุดเป็น current
                currencyList.add(c);
            }
        return currencyList;
    }
}
