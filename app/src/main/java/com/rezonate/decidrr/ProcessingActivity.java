package com.rezonate.decidrr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;


public class ProcessingActivity extends Activity
        implements MoPubInterstitial.InterstitialAdListener {

    // TODO: Replace this test id with your personal ad unit id
    private static final String MOPUB_INTERSTITIAL_AD_UNIT_ID = "80df84562fd34b20ae82827fa3904522";
    private MoPubInterstitial interstitial;

    public final static String QUESTION = "com.rezonate.decidrr.QUESTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);
        interstitial = new MoPubInterstitial(this, MOPUB_INTERSTITIAL_AD_UNIT_ID);
        interstitial.setInterstitialAdListener(this);
        interstitial.load();


    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        final String question = intent.getExtras().getString("questionp");
        Intent i = new Intent(ProcessingActivity.this, AnswerActivity.class);
        i.putExtra("question", question);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        interstitial.destroy();
        super.onDestroy();
    }

    // InterstitialAdListener methods
    @Override
    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
        if (interstitial.isReady()) {
            interstitial.show();
        } else {
            // Other code
            Intent intent = getIntent();
            final String question = intent.getExtras().getString("questionp");
            Intent i = new Intent(ProcessingActivity.this, AnswerActivity.class);
            i.putExtra("question", question);
            startActivity(i);
        }
    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {}

    @Override
    public void onInterstitialShown(MoPubInterstitial interstitial) {}

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {}

    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {}
}

