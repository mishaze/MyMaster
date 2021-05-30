package com.example.mymaster.Models;

import java.util.Objects;

public class Friends {
    private long friend;

    public Friends() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friends friends = (Friends) o;
        return Objects.equals(friend, friends.friend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(friend);
    }

    public Friends(Long friend) {
        this.friend = friend;
    }

    public Long getFriend() {
        return friend;
    }

    public void setFriend(Long friend) {
        this.friend = friend;
    }
}
