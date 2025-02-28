package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.List;

public class CommitToBranchWebHook implements WebHook {
    ArrayList<Event> events;
    String branchName;

    public CommitToBranchWebHook(String branchName) {
        events = new ArrayList<>();
        this.branchName = branchName;
    }

    @Override
    public String branch() {
        return branchName;
    }

    @Override
    public Event.Type type() {
        return Event.Type.COMMIT;
    }

    @Override
    public List<Event> caughtEvents() {
        return events;
    }

    @Override
    public void onEvent(Event event) {
        if (event.branch().equals(branchName) && event.type().equals(type()))
            events.add(event);
    }
}

