package com.example.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AideActivity extends AppCompatActivity {

    public static final String EXTRA_REPONSE_VRAIE = "com.lpsil.quizz.reponse_vraie";
    public static final String EXTRA_REPONSE_AFFICHEE = "com.lpsil.quizz.reponse_affichee";
    private TextView mReponseTextView;
    private Button mAfficheReponse;

    private void setReponseAffichee(boolean isReponseAffichee) {
        Intent donnees = new Intent();
        donnees.putExtra(EXTRA_REPONSE_AFFICHEE, isReponseAffichee);
        setResult(RESULT_OK, donnees);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_aide);
        final boolean mReponseVraie = getIntent().getBooleanExtra(EXTRA_REPONSE_VRAIE, false);

        mReponseTextView = (TextView) findViewById(R.id.responseTextView);
        mAfficheReponse = (Button) findViewById(R.id.boutonAfficheAide);
        mAfficheReponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mReponseVraie) {
                    mReponseTextView.setText(R.string.bouton_vrai);
                } else {
                    mReponseTextView.setText(R.string.bouton_faux);
                }
                setReponseAffichee(true);
            }
        });
    }
}
