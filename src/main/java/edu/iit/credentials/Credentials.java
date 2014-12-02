/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.credentials;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;

/**
 *
 * @author supramo
 */
public interface Credentials {
    public final AWSCredentials credentials = new ProfileCredentialsProvider().getCredentials();
}
