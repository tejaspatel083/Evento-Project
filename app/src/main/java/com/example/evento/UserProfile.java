package com.example.evento;

class UserProfile {


    public String useremail;
    public String username;

    public UserProfile() {
    }

    public UserProfile(String useremail, String username) {
        this.useremail = useremail;
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
