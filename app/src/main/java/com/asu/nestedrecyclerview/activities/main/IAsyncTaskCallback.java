package com.asu.nestedrecyclerview.activities.main;

import java.util.List;

public interface IAsyncTaskCallback <T> {
    void onAsyncTaskComplete(List<T> trackList);
}
