package com.tsongkha.trademeexample.stories.categories;

import com.google.gson.annotations.SerializedName;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.util.List;

import javax.annotation.Nullable;

/**
 * Created by rawsond on 7/12/17.
 */
@Gson.TypeAdapters
@Value.Immutable
public abstract class Category {

    @SerializedName("Name")
    public abstract String name();

    @SerializedName("Number")
    public abstract String number();

    @SerializedName("IsLeaf")
    public abstract boolean isLeaf();

    @SerializedName("Path")
    public abstract String path();

    @SerializedName("Subcategories")
    @Nullable
    public abstract List<Category> subcategories();
}

