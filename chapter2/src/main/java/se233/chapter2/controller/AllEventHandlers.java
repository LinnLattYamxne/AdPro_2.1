package se233.chapter2.controller;

import javafx.scene.control.TextInputDialog;
import se233.chapter2.Launcher;
import se233.chapter2.model.Currency;
import se233.chapter2.model.CurrencyEntity;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class AllEventHandlers {
    public static void onRefresh() {
        try {
            Launcher.refreshPane();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void onAdd() {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Currency");
            dialog.setContentText("Currency code: ");
            dialog.setHeaderText(null);
            dialog.setGraphic(null);
            Optional<String> code = dialog.showAndWait();
            if (code.isPresent()) {
                List<Currency> currencyList = Launcher.getCurrencyList();
                List<CurrencyEntity> hist = FetchData.fetchRange(code.get(), 30);
                if (hist != null && !hist.isEmpty()) {
                    Currency newCurrency = new Currency(code.get());
                    newCurrency.setHistorical(hist);
                    newCurrency.setCurrent(hist.get(hist.size() - 1));
                    currencyList.add(newCurrency);
                    Launcher.setCurrencyList(currencyList);
                    Launcher.refreshPane();
                } else {
                    // แจ้งเตือน user ว่าไม่พบข้อมูล
                    javafx.application.Platform.runLater(() -> {
                        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Cannot fetch data for currency code: " + code.get());
                        alert.showAndWait();
                    });
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void onDelete(String code) {
        try {
            List<Currency> currencyList = Launcher.getCurrencyList();
            int index = -1;
            for (int i = 0; i < currencyList.size(); i++) {
                if (currencyList.get(i).getShortCode().equals(code)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                currencyList.remove(index);
                Launcher.setCurrencyList(currencyList);
                Launcher.refreshPane();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public static void onWatch(String code) {
        try {
            List<Currency> currencyList = Launcher.getCurrencyList();
            int index = -1;
            for (int i = 0; i < currencyList.size(); i++) {
                if (currencyList.get(i).getShortCode().equals(code)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Add Watch");
                dialog.setContentText("Rate");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                Optional<String> retrievedRate = dialog.showAndWait();
                if (retrievedRate.isPresent()) {
                    double rate = Double.parseDouble(retrievedRate.get());
                    currencyList.get(index).setWatch(true);
                    currencyList.get(index).setWatchRate(rate);
                    Launcher.setCurrencyList(currencyList);
                    Launcher.refreshPane();
                }
                Launcher.setCurrencyList(currencyList);
                Launcher.refreshPane();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
