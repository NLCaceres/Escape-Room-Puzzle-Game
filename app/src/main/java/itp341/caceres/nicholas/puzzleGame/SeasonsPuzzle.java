package itp341.caceres.nicholas.puzzleGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class SeasonsPuzzle extends AppCompatActivity {

    private RadioButton springRB;
    private RadioButton summerRB;
    private RadioButton fallRB;
    private RadioButton winterRB;

    private String userSeasonsAns;

    private Toast cheatToast;

    private Button seasonsChangesButton;
    private Button cheatButton;

    public static final String EXTRA_SEASONS_ANS = "itp341.caceres.nicholas.puzzleGame.seasons_ans";
    public static final String EXTRA_USER_SEASONS_ANS = "itp341.caceres.nicholas.puzzleGame.user_seasons_ans";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons_puzzle);

        springRB = (RadioButton) findViewById(R.id.spring_RB);
        summerRB = (RadioButton) findViewById(R.id.summer_RB);
        fallRB = (RadioButton) findViewById(R.id.fall_RB);
        winterRB = (RadioButton) findViewById(R.id.winter_RB);

        seasonsChangesButton = (Button) findViewById(R.id.seasons_change_button);
        seasonsChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                String seasonsAns = i.getStringExtra(EXTRA_SEASONS_ANS);
                int numberPuzzlesDone = i.getIntExtra(WinLoseActivity.EXTRA_PUZZLES_DONE, 3);
                if (springRB.isChecked()) {
                    userSeasonsAns = "Spring";
                    i.putExtra(EXTRA_USER_SEASONS_ANS, userSeasonsAns);
                }
                else if (summerRB.isChecked()) {
                    userSeasonsAns = "Summer";
                    i.putExtra(EXTRA_USER_SEASONS_ANS, userSeasonsAns);
                }
                else if (fallRB.isChecked()) {
                    userSeasonsAns = "Fall";
                    i.putExtra(EXTRA_USER_SEASONS_ANS, userSeasonsAns);
                }
                else {
                    userSeasonsAns = "Winter";
                    i.putExtra(EXTRA_USER_SEASONS_ANS, userSeasonsAns);
                }
                i.putExtra(EXTRA_SEASONS_ANS, seasonsAns);
                i.putExtra(WinLoseActivity.EXTRA_PUZZLES_DONE, numberPuzzlesDone);
                setResult(RESULT_OK, i);
            }
        });

        cheatButton = (Button) findViewById(R.id.cheat_button_s);
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                String seasonAns = i.getStringExtra(EXTRA_SEASONS_ANS);
                cheatToast.makeText(SeasonsPuzzle.this, seasonAns, cheatToast.LENGTH_SHORT).show();
            }
        });
    }
}
