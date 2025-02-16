package com.example.demo.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SidebarModel {
    private ObservableList<String> sidebarItems;

    public SidebarModel() {
        // Initialize the sidebar items
        sidebarItems = FXCollections.observableArrayList(
                "Tableau de bord", "Calendrier", "Historiques", "Médecins", "Laboratoires", "Bilans"
        );
    }

    public ObservableList<String> getSidebarItems() {
        return sidebarItems;
    }
}