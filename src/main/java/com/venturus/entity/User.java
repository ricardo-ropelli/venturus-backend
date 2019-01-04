package com.venturus.entity;

import com.venturus.enums.RideGroupEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "T_USER")
public class User implements Comparable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String userName;
    private String name;
    private String email;
    private String city;
    private RideGroupEnum rideGroup;
    // TODO Missing days of week;
    private Long posts;
    private Long albums;
    private Long photos;
    private String token;

    public User() {
    }

    public User(Long id, String userName, String name, String email, String city, RideGroupEnum rideGroup, Long posts, Long albums, Long photos, String token) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.city = city;
        this.rideGroup = rideGroup;
        this.posts = posts;
        this.albums = albums;
        this.photos = photos;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public RideGroupEnum getRideGroup() {
        return rideGroup;
    }

    public void setRideGroup(RideGroupEnum rideGroup) {
        this.rideGroup = rideGroup;
    }

    public Long getPosts() {
        return posts;
    }

    public void setPosts(Long posts) {
        this.posts = posts;
    }

    public Long getAlbums() {
        return albums;
    }

    public void setAlbums(Long albums) {
        this.albums = albums;
    }

    public Long getPhotos() {
        return photos;
    }

    public void setPhotos(Long photos) {
        this.photos = photos;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int compareTo(Object object) {
        User user = (User) object;
        return this.userName.compareTo(user.getUserName());
    }
}
