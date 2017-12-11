package com.tsongkha.trademeexample.stories.listings;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.util.List;

/**
 * Created by rawsond on 9/12/17.
 */
@Value.Immutable
@Gson.TypeAdapters
public abstract class SearchResult {

    @Gson.Named("List")
    public abstract List<Listing> listings();

}
