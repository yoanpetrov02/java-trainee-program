package com.sirma.javacourse.networking.downloadagent;

import com.sirma.javacourse.networking.downloadagent.model.DownloadAgent;
import javafx.concurrent.Task;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Tracks the progress of a file download and updates the UI with the progress.
 */
public class DownloadProgressMonitor extends Task<Integer> {

    private final AtomicBoolean running;
    private final DownloadAgent agent;

    public DownloadProgressMonitor(AtomicBoolean running, DownloadAgent agent) {
        this.running = running;
        this.agent = agent;
    }

    /**
     * Updates the UI with the total amount of transferred bytes while a file is downloading.
     *
     * @return the total amount of transferred bytes after the file is downloaded.
     */
    @Override
    protected Integer call() {
        do {
            updateMessage("Transferred: " + agent.getTotalTransferred() + " bytes.");
        } while (running.get());
        return agent.getTotalTransferred();
    }
}
