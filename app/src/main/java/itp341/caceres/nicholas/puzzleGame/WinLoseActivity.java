package itp341.caceres.nicholas.puzzleGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WinLoseActivity extends AppCompatActivity {

    private TextView winLoseTextView;

    public static final String EXTRA_PUZZLES_DONE = "itp341.caceres.nicholas.puzzleGame.puzzles_done";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_lose_puzzle);

        winLoseTextView = (TextView) findViewById(R.id.win_lose_TV);

        Intent i = getIntent();
        int numberPuzzlesDone = i.getIntExtra(EXTRA_PUZZLES_DONE, 4);
        if (numberPuzzlesDone == 4) {
            winLoseTextView.setText(getResources().getString(R.string.winning_label));
        }
        else {
            winLoseTextView.setText(getResources().getString(R.string.losing_label));
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}
