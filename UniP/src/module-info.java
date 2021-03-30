module unip {
	exports unip;
	exports unip.view;
	exports unip.controller;
	opens unip.view to javafx.fxml;
	opens unip.controller to javafx.fxml;
	opens unip.model to com.google.gson;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires com.google.gson;
}