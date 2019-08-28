package itp341.caceres.nicholas.puzzleGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class SizePuzzle extends AppCompatActivity {

    private Spinner sizeSpinner;
    private int spinPos;

    private Button sizeChangesButton;
    private String userSizeAns;

    public static final String EXTRA_SIZE_ANS = "itp341.caceres.nicholas.puzzleGame.size_ans";
    public static final String EXTRA_USER_SIZE_ANS = "itp341.caceres.nicholas.puzzleGame.user_size_ans";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size_puzzle);

        sizeSpinner = (Spinner) findViewById(R.id.size_spinner);
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinPos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sizeChangesButton = (Button) findViewById(R.id.size_changes_button);
        sizeChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                String spinAns = i.getStringExtra(EXTRA_SIZE_ANS);
                int numberPuzzlesDone = i.getIntExtra(WinLoseActivity.EXTRA_PUZZLES_DONE, 1);
                if (spinPos == 0) {
                    userSizeAns = "Small";
                    i.putExtra(EXTRA_USER_SIZE_ANS, userSizeAns);
                }
                else if (spinPos == 1) {
                    userSizeAns = "Medium";
                    i.putExtra(EXTRA_USER_SIZE_ANS, userSizeAns);
                }
                else {
                    userSizeAns = "Big";
                    i.putExtra(EXTRA_USER_SIZE_ANS, userSizeAns);
                }
                i.putExtra(EXTRA_SIZE_ANS, spinAns);
                i.putExtra(WinLoseActivity.EXTRA_PUZZLES_DONE, numberPuzzlesDone);
                setResult(RESULT_OK, i);
            }
        });
    }
}
