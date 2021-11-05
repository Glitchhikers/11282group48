package com.example.CarpoolConnectBackend.api;

import com.example.CarpoolConnectBackend.model.Profile;
import com.example.CarpoolConnectBackend.services.ProfileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileAPI {

    // Creates new profile
    // Return 0: Operation completed successfully
    // Return -1: Username already exists
    @PostMapping("api/Profile/create")
    public int createProfile(@RequestBody Profile newProfile) {
        ProfileService profileService = new ProfileService();
        return profileService.writeProfileToDatabase(newProfile);
    }


    // Logs in user
    @PostMapping("api/Profile/login")
    public Profile login(@RequestBody Profile temp) {
        ProfileService profileService = new ProfileService();
        return profileService.checkLogin(temp);
    }
}
