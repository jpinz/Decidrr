package com.rezonate.decidrr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.mopub.common.MoPub;
import com.twitter.sdk.android.Twitter;

import com.digits.sdk.android.Digits;


import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;


public class InitialActivity extends Activity {

    private static final String TWITTER_KEY = "x3NWZS629e9WrUtysgVE7xYgm";
    private static final String TWITTER_SECRET = "8F0ILemevPTea8fDCUExH7n437F9TDIP82EhHjTGHOkVbEn3oq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig), new Crashlytics(), new MoPub());
        final Session activeSession = SessionRecorder.recordInitialSessionState(
                Twitter.getSessionManager().getActiveSession(),
                Digits.getSessionManager().getActiveSession()
        );

        if (activeSession != null) {
            startThemeActivity();
        } else {
            startLoginActivity();
        }
    }

    private void startThemeActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}