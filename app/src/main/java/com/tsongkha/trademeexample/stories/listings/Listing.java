package com.tsongkha.trademeexample.stories.listings;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import javax.annotation.Nullable;

/**
 * Created by rawsond on 9/12/17.
 */
@Value.Immutable
@Gson.TypeAdapters
public abstract class Listing {

    @Gson.Named("ListingId")
    public abstract long id();

    @Gson.Named("Title")
    public abstract String title();

    @Nullable
    @Gson.Named("PictureHref")
    public abstract String pictureHref();
}
