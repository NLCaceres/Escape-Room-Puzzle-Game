package itp341.caceres.nicholas.puzzleGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NamePuzzle extends AppCompatActivity {

    private EditText nameEditText;
    private String name;

    private Button nameChangesButton;

    public static final String EXTRA_NAME_ANS = "itp341.caceres.nicholas.puzzleGame.name_ans";
    public static final String EXTRA_USER_NAME_ANS = "itp341.caceres.nicholas.puzzleGame.user_name_ans";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_puzzle);

        nameEditText = (EditText) findViewById(R.id.name_editText);
        nameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                name = nameEditText.getText().toString();
                return true;
            }
        });

        nameChangesButton = (Button) findViewById(R.id.name_changes_button);
        nameChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                String nameAns = i.getStringExtra(EXTRA_NAME_ANS);
                int numberPuzzlesDone = i.getIntExtra(WinLoseActivity.EXTRA_PUZZLES_DONE, 2);
                i.putExtra(EXTRA_USER_NAME_ANS, name);
                i.putExtra(EXTRA_NAME_ANS, nameAns);
                i.putExtra(WinLoseActivity.EXTRA_PUZZLES_DONE, numberPuzzlesDone);
                setResult(RESULT_OK, i);
            }
        });
    }
}
