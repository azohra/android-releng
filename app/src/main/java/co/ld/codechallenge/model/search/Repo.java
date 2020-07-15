/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.model.search;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

/**
 * Repository info
 */
public class Repo implements Parcelable {

    public static final Parcelable.Creator<Repo> CREATOR = new Parcelable.Creator<Repo>() {
        @Override
        public Repo createFromParcel(Parcel source) {
            return new Repo(source);
        }

        @Override
        public Repo[] newArray(int size) {
            return new Repo[size];
        }
    };
    @SerializedName("id")
    private int id;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("owner")
    private Owner owner;
    @SerializedName("html_url")
    private String url;
    @SerializedName("stargazers_count")
    private int stargazersCount;

    public Repo() {
    }

    protected Repo(Parcel in) {
        id = in.readInt();
        fullName = in.readString();
        name = in.readString();
        description = in.readString();
        owner = in.readParcelable(Owner.class.getClassLoader());
        url = in.readString();
        stargazersCount = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(fullName);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeParcelable(owner, flags);
        dest.writeString(url);
        dest.writeInt(stargazersCount);
    }

    @NonNull
    @Override
    public String toString() {
        return "Repo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
