package com.example.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button mBoutonVrai;
    private Button mBoutonFaux;
        private Button mBoutonSuivant;
        private TextView mQuestionTextView;
        private static final String TAG = "quizz";
        private static final String KEY_INDEX = "index";

        private VraiFaux[] mTabQuestions = new VraiFaux[] {
                new VraiFaux(R.string.question_oceans, true),
                new VraiFaux(R.string.question_africa, false),
                new VraiFaux(R.string.question_americas, true),
                new VraiFaux(R.string.question_mideast, true),
                new VraiFaux(R.string.question_asia, false)
        };

        private int mIndexActuel = 0;

        private void majQuestion() {
            int question = mTabQuestions[mIndexActuel].getmQuestion();
            mQuestionTextView.setText(question);
        }

        private void verifieReponse(boolean userVrai) {
        boolean reponseVraie = mTabQuestions[mIndexActuel].ismQuestionVraie();

        int messReponseId = 0;

        messReponseId = (userVrai==reponseVraie) ? R.string.toast_correct : R.string.toast_faux;

        Toast.makeText(MainActivity.this, messReponseId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //on récupère la valeur de mIndexActuel
        if (savedInstanceState != null) {
            mIndexActuel = savedInstanceState.getInt(KEY_INDEX,0);
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
                mIndexActuel = (mIndexActuel + 1)% mTabQuestions.length;
                int question = mTabQuestions[mIndexActuel].getmQuestion();
                mQuestionTextView.setText(question);
            }
        });

        majQuestion();
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
        Log.i(TAG,"onSaveInstanceState");
        outState.putInt(KEY_INDEX, mIndexActuel);
    }

}
