package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GitRepository implements Repository {
    private final Map<String, List<Commit>> branches = new HashMap<>();
    private final List<WebHook> webHooks = new ArrayList<>();

    public GitRepository() {
        branches.put("master", new ArrayList<>());
    }

    @Override
    public void addWebHook(WebHook webHook) {
        webHooks.add(webHook);
    }

    @Override
    public Commit commit(String branch, String author, String[] changes) {
        branches.putIfAbsent(branch, new ArrayList<>());
        Commit commit = new Commit(author, changes);

        branches.get(branch).add(commit);

        for (WebHook webHook : webHooks) {
            if (webHook.type() == Event.Type.COMMIT && webHook.branch().equals(branch)) {
                webHook.onEvent(new Event(Event.Type.COMMIT, branch, List.of(commit)));
            }
        }

        return commit;
    }

    @Override
    public void merge(String sourceBranch, String targetBranch) {
        if (!branches.containsKey(sourceBranch) || !branches.containsKey(targetBranch)) {
            throw new IllegalArgumentException();
        }

        List<Commit> sourceCommits = branches.get(sourceBranch);
        List<Commit> targetCommits = branches.get(targetBranch);

        List<Commit> newCommits = new ArrayList<>();
        for (Commit commit : sourceCommits) {
            if (!targetCommits.contains(commit)) {
                newCommits.add(commit);
            }
        }

        targetCommits.addAll(newCommits);

        if (!newCommits.isEmpty()) {
            for (WebHook webHook : webHooks) {
                if (webHook.type() == Event.Type.MERGE && webHook.branch().equals(targetBranch)) {
                    webHook.onEvent(new Event(Event.Type.MERGE, targetBranch, new ArrayList<>(newCommits)));
                }
            }
        }
    }
}