package com.example.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button mBoutonVrai;
    private Button mBoutonFaux;
    private Button mBoutonSuivant;
    private Button mBoutonAide;
    private TextView mQuestionTextView;
    private static final String TAG = "quizz";
    private static final String KEY_INDEX = "index";
    private static final String TRICHE_APRES_ROTATION = "triche_apres_rotation";
    private static final String A_TRICHE = "a_triche";
    private static final String LISTE_TRICHE = "triche";
    private ArrayList<Integer> mTriche= new ArrayList<Integer>();

    private void tricheDepuisListe() {
        mQuestionTrichee[mIndexActuel] = mTriche.contains(mIndexActuel) ? true : false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null) {
            mQuestionTrichee[mIndexActuel] = data.getBooleanExtra(AideActivity.EXTRA_REPONSE_AFFICHEE, false);
            Log.d(TAG, "ce qu'on recoit :" + mQuestionTrichee[mIndexActuel]);
            if (!mTriche.contains(mIndexActuel) && mQuestionTrichee[mIndexActuel] == true) {mTriche.add(mIndexActuel);}
            else if(mQuestionTrichee[mIndexActuel] == false && mTriche.contains(mIndexActuel)) {mQuestionTrichee[mIndexActuel] = true;}
        } else return;
    }

    private VraiFaux[] mTabQuestions = new VraiFaux[]{
            new VraiFaux(R.string.question_oceans, true),
            new VraiFaux(R.string.question_africa, false),
            new VraiFaux(R.string.question_americas, true),
            new VraiFaux(R.string.question_mideast, true),
            new VraiFaux(R.string.question_asia, false)
    };

    private boolean[] mQuestionTrichee = new boolean[mTabQuestions.length];

    private int mIndexActuel = 0;

    private void majQuestion() {
        int question = mTabQuestions[mIndexActuel].getmQuestion();
        mQuestionTextView.setText(question);
    }

    private void verifieReponse(boolean userVrai) {
        boolean reponseVraie = mTabQuestions[mIndexActuel].ismQuestionVraie();

        int messReponseId = 0;

        if(mQuestionTrichee[mIndexActuel]) {
            messReponseId = R.string.toast_aide;
        } else {
            messReponseId = (userVrai == reponseVraie) ? R.string.toast_correct : R.string.toast_faux;
        }

        Toast.makeText(MainActivity.this, messReponseId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mIndexActuel = savedInstanceState.getInt(KEY_INDEX, 0);
            mQuestionTrichee[mIndexActuel] = savedInstanceState.getBoolean(A_TRICHE, false);
            mTriche = savedInstanceState.getIntegerArrayList(LISTE_TRICHE);
        }
        Log.d(TAG, "onCreate appelée");
        setContentView(R.layout.content_main);
        setTitle(R.string.app_name);
        mQuestionTextView = (TextView) findViewById(R.id.questionTextView);
        int question = mTabQuestions[mIndexActuel].getmQuestion();
        mQuestionTextView.setText(question);

        mBoutonVrai = (Button) findViewById(R.id.bouton_vrai);
        mBoutonVrai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifieReponse(true);
            }
        });

        mBoutonFaux = (Button) findViewById(R.id.bouton_faux);
        mBoutonFaux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifieReponse(false);
            }
        });

        mBoutonSuivant = (Button) findViewById(R.id.bouton_suivant);
        mBoutonSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIndexActuel = (mIndexActuel + 1) % mTabQuestions.length;
                int question = mTabQuestions[mIndexActuel].getmQuestion();
                mQuestionTextView.setText(question);
                tricheDepuisListe();
            }
        });

        majQuestion();

        mBoutonAide = (Button) findViewById(R.id.boutonAfficheAide);
        mBoutonAide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intention = new Intent(MainActivity.this, AideActivity.class);
                boolean reponse_vraie = mTabQuestions[mIndexActuel].ismQuestionVraie();
                intention.putExtra(AideActivity.EXTRA_REPONSE_VRAIE, reponse_vraie);
                //startActivity(intention);
                startActivityForResult(intention,2016);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy appelée");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause appelée");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume appelée");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop appelée");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart appelée");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, mIndexActuel);
        outState.putIntegerArrayList(LISTE_TRICHE, mTriche);
        outState.putBoolean(A_TRICHE, mQuestionTrichee[mIndexActuel]);
    }

}
