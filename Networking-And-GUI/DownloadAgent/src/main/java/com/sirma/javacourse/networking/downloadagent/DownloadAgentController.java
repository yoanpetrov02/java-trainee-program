package com.sirma.javacourse.networking.downloadagent;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import com.sirma.javacourse.networking.downloadagent.model.DownloadAgent;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Handles user input for the application.
 */
public class DownloadAgentController {

	private DownloadAgent agent;
	private AtomicBoolean running;
	private DownloadProgressMonitor monitor;
	Alert errorAlert;

	@FXML
	private TextField urlTextField;
	@FXML
	private TextField destinationTextField;
	@FXML
	private Label statusLabel;
	@FXML
	private Label progressLabel;

	public void initialize() {
		errorAlert = new Alert(Alert.AlertType.ERROR);
		errorAlert.setTitle("Error");
		errorAlert.setHeaderText("An error has occurred");
	}

	/**
	 * Handler for when the download button is clicked.
	 */
	public void downloadButtonOnClick() {
		statusLabel.setVisible(false);
		if (validateInput()) {
			setStatus("Downloading...");
			try {
				downloadFile();
			} catch (IOException e) {
				showError("An error occurred while downloading!");
				return;
			}
			setStatus("File downloaded.");
		}
	}

	/**
	 * Validates the input in {@code urlTextField} and {@code destinationTextField}.
	 *
	 * @return true if the input is valid, false otherwise.
	 */
	private boolean validateInput() {
		if (areThereEmptyFields()) {
			showError("Please, enter values in both text fields!");
			return false;
		}
		try {
			stripTextFields();
			InputValidator.validateURL(urlTextField.getText());
			InputValidator.validateFilePath(destinationTextField.getText());
		} catch (IOException e) {
			showError(e.getMessage());
			return false;
		}
		return true;
	}

	private boolean areThereEmptyFields() {
		return urlTextField.getText().isEmpty() ||
				destinationTextField.getText().isEmpty();
	}

	/**
	 * Shows the given error message in a popup window.
	 * @param message the message to be shown.
	 */
	private void showError(String message) {
		errorAlert.setContentText(message);
		errorAlert.show();
	}

	/**
	 * Removes whitespace around the text fields' values to prevent further errors.
	 */
	private void stripTextFields() {
		String strippedUrl = urlTextField.getText().strip();
		String strippedDestination = destinationTextField.getText().strip();
		urlTextField.setText(strippedUrl);
		destinationTextField.setText(strippedDestination);
	}

	/**
	 * Changes {@code statusLabel}'s text to the given status message.
	 *
	 * @param status the new status message.
	 */
	private void setStatus(String status) {
		statusLabel.setVisible(false);
		statusLabel.setText(status);
		statusLabel.setVisible(true);
	}

	/**
	 * Creates the {@code DownloadAgent} object and downloads the file.
	 *
	 * @throws IOException if an i/o error occurs while downloading.
	 */
	private void downloadFile() throws IOException {
		initializeDownloadObjects();
		bindProgressLabel();
		progressLabel.setVisible(true);
		running.set(true);
		Platform.runLater(() -> monitor.run());
		agent.downloadFile(destinationTextField.getText());
		running.set(false);
	}

	/**
	 * Initializes the objects connected to the download process.
	 *
	 * @throws IOException if an i/o error occurs.
	 */
	private void initializeDownloadObjects() throws IOException {
		running = new AtomicBoolean();
		agent = new DownloadAgent(urlTextField.getText());
		monitor = new DownloadProgressMonitor(running, agent);
	}

	/**
	 * Binds {@code progressLabel} to the {@code totalTransferred} property of the {@code DownloadAgent}.
	 */
	private void bindProgressLabel() {
		progressLabel.textProperty().bind(monitor.messageProperty());
	}
}
