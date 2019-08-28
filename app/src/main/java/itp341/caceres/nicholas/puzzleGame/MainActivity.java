package itp341.caceres.nicholas.puzzleGame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private TextView firstTextView;
    private TextView secondTextView;
    private TextView thirdTextView;
    private TextView fourthTextView;

    private String[] colorArray;
    private String[] randColorArray;
    private String colorAns;
    private String[] sizeArray;
    private String[] randSizeArray;
    private String sizeAns;
    private String[] nameArray;
    private String[] randNameArray;
    private String nameAns;
    private String[] seasonsArray;
    private String[] randSeasonsArray;
    private String seasonsAns;
    private String[] randPuzzles;

    private Toast cheatToast;

    private int numCompleted;
    private Boolean firstPuzzleDone;
    private Boolean secondPuzzleDone;
    private Boolean thirdPuzzleDone;
    private Boolean fourthPuzzleDone;
    private Boolean puzzlesSolved;

    private Button roomOneButton;
    private Button roomTwoButton;
    private Button roomThreeButton;
    private Button roomFourButton;
    private Button solveButton;
    private Button cheatButton;
    private roomOpenerClickListener roomOpener;

    public static final int COLOR_PUZZLE_REQUEST = 0;
    public static final int SIZE_PUZZLE_REQUEST = 1;
    public static final int NAME_PUZZLE_REQUEST = 2;
    public static final int SEASONS_PUZZLE_REQUEST = 3;

    private static final String TAG = MainActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numCompleted = 0;
        firstPuzzleDone = false;
        secondPuzzleDone = false;
        thirdPuzzleDone = false;
        fourthPuzzleDone = false;
        puzzlesSolved = false;

        firstTextView = (TextView) findViewById(R.id.first_textView);
        secondTextView = (TextView) findViewById(R.id.second_textView);
        thirdTextView = (TextView) findViewById(R.id.third_textView);
        fourthTextView = (TextView) findViewById(R.id.fourth_textView);

        colorArray = getResources().getStringArray(R.array.color_array);
        randColorArray = colorArray.clone();
        shuffleStrings(randColorArray);

        sizeArray = getResources().getStringArray(R.array.main_activity_size_array);
        randSizeArray = sizeArray.clone();
        shuffleStrings(randSizeArray);

        nameArray = getResources().getStringArray(R.array.name_array);
        randNameArray = nameArray.clone();
        shuffleStrings(randNameArray);

        seasonsArray = getResources().getStringArray(R.array.seasons_array);
        randSeasonsArray = seasonsArray.clone();
        shuffleStrings(randSeasonsArray);

        for (int x = 0; x < colorArray.length; x++) {
            if (Objects.equals(randColorArray[0], colorArray[x])) {
                if (x == 0) {
                    colorAns = "(255,0,0)";
                    break;
                }
                else if (x == 1) {
                    colorAns = "(0,255,0)";
                    break;
                }
                else if (x == 2){
                    colorAns = "(0,0,255)";
                    break;
                }
            }
        }

        for (int x = 0; x < sizeArray.length; x++) {
            if (Objects.equals(randSizeArray[0], sizeArray[x])) {
                if (x == 0) {
                    sizeAns = "Small";
                    break;
                }
                else if (x == 1) {
                    sizeAns = "Medium";
                    break;
                }
                else if (x == 2){
                    sizeAns = "Big";
                    break;
                }
            }
        }

        for (int x = 0; x < nameArray.length; x++) {
            if (Objects.equals(randNameArray[0], nameArray[x])) {
                if (x == 0) {
                    nameAns = "Al";
                    break;
                }
                else if (x == 1) {
                    nameAns = "Jack";
                    break;
                }
                else if (x == 2) {
                    nameAns = "Steve";
                    break;
                }
            }
        }

        for (int x = 0; x < seasonsArray.length; x++) {
            if (Objects.equals(randSeasonsArray[0], seasonsArray[x])) {
                if (x == 0) {
                    seasonsAns = "Spring";
                    break;
                }
                else if (x == 1) {
                    seasonsAns = "Summer";
                    break;
                }
                else if (x == 2) {
                    seasonsAns = "Fall";
                    break;
                }
                else if (x == 3) {
                    seasonsAns = "Winter";
                    break;
                }
            }
        }

        randPuzzles = new String[] {randColorArray[0], randSizeArray[0], randNameArray[0], randSeasonsArray[0]};

        firstTextView.setText(randPuzzles[0]);
        secondTextView.setText(randPuzzles[1]);
        thirdTextView.setText(randPuzzles[2]);
        fourthTextView.setText(randPuzzles[3]);

        roomOpener = new roomOpenerClickListener();
        roomOneButton = (Button) findViewById(R.id.room_one_button);
        roomOneButton.setOnClickListener(roomOpener);
        roomTwoButton = (Button) findViewById(R.id.room_two_button);
        roomTwoButton.setOnClickListener(roomOpener);
        roomThreeButton = (Button) findViewById(R.id.room_three_button);
        roomThreeButton.setOnClickListener(roomOpener);
        roomFourButton = (Button) findViewById(R.id.room_four_button);
        roomFourButton.setOnClickListener(roomOpener);
        solveButton = (Button) findViewById(R.id.solve_button);
        solveButton.setOnClickListener(roomOpener);
        cheatButton = (Button) findViewById(R.id.cheat_button);
        cheatButton.setOnClickListener(roomOpener);
        Log.d(TAG,"all set");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case COLOR_PUZZLE_REQUEST: {
                if (resultCode == Activity.RESULT_OK) {
                    colorAns = data.getStringExtra(ColorPuzzle.EXTRA_COLOR_ANS);
                    String userColorAns = data.getStringExtra(ColorPuzzle.EXTRA_USER_COLOR_ANS);
                    numCompleted = data.getIntExtra(WinLoseActivity.EXTRA_PUZZLES_DONE, 0);
                    if (Objects.equals(colorAns, userColorAns) && numCompleted == 0 && firstPuzzleDone == false){
                        firstTextView.setTextColor(Color.parseColor("#00FF00"));
                        numCompleted = 1;
                        firstPuzzleDone = true;
                    }
                }
                break;
            }
            case SIZE_PUZZLE_REQUEST: {
                if (resultCode == Activity.RESULT_OK) {
                    sizeAns = data.getStringExtra(SizePuzzle.EXTRA_SIZE_ANS);
                    String userSizeAns = data.getStringExtra(SizePuzzle.EXTRA_USER_SIZE_ANS);
                    numCompleted = data.getIntExtra(WinLoseActivity.EXTRA_PUZZLES_DONE, 1);
                    if (Objects.equals(sizeAns, userSizeAns) && numCompleted == 1 && secondPuzzleDone == false) {
                        secondTextView.setTextColor(Color.parseColor("#00FF00"));
                        numCompleted = 2;
                        secondPuzzleDone = true;
                    }
                }
                break;
            }
            case NAME_PUZZLE_REQUEST: {
                if (resultCode == Activity.RESULT_OK) {
                    nameAns = data.getStringExtra(NamePuzzle.EXTRA_NAME_ANS);
                    String userNameAns = data.getStringExtra(NamePuzzle.EXTRA_USER_NAME_ANS);
                    numCompleted = data.getIntExtra(WinLoseActivity.EXTRA_PUZZLES_DONE, 2);
                    if (Objects.equals(nameAns, userNameAns) && numCompleted == 2 && thirdPuzzleDone == false) {
                        thirdTextView.setTextColor(Color.parseColor("#00FF00"));
                        numCompleted = 3;
                        thirdPuzzleDone = true;
                    }
                }
                break;
            }
            case SEASONS_PUZZLE_REQUEST: {
                if (resultCode == Activity.RESULT_OK) {
                    seasonsAns = data.getStringExtra(SeasonsPuzzle.EXTRA_SEASONS_ANS);
                    String userSeasonsAns = data.getStringExtra(SeasonsPuzzle.EXTRA_USER_SEASONS_ANS);
                    numCompleted = data.getIntExtra(WinLoseActivity.EXTRA_PUZZLES_DONE, 3);
                    if (Objects.equals(seasonsAns, userSeasonsAns) && numCompleted == 3 && fourthPuzzleDone == false) {
                        fourthTextView.setTextColor(Color.parseColor("#00FF00"));
                        numCompleted = 4;
                        fourthPuzzleDone = true;
                    }
                }
                break;
            }
        }
    }

    private class roomOpenerClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.room_one_button: {
                    Intent i = new Intent(getApplicationContext(), ColorPuzzle.class);
                    i.putExtra(ColorPuzzle.EXTRA_COLOR_ANS, colorAns);
                    i.putExtra(WinLoseActivity.EXTRA_PUZZLES_DONE, numCompleted);
                    startActivityForResult(i, COLOR_PUZZLE_REQUEST);
                    Log.d("it should open", "it should open");
                    break;
                }
                case R.id.room_two_button: {
                    Intent i = new Intent(getApplicationContext(), SizePuzzle.class);
                    i.putExtra(SizePuzzle.EXTRA_SIZE_ANS, sizeAns);
                    i.putExtra(WinLoseActivity.EXTRA_PUZZLES_DONE, numCompleted);
                    startActivityForResult(i, SIZE_PUZZLE_REQUEST);
                    break;
                }
                case R.id.room_three_button: {
                    Intent i = new Intent(getApplicationContext(), NamePuzzle.class);
                    i.putExtra(NamePuzzle.EXTRA_NAME_ANS, nameAns);
                    i.putExtra(WinLoseActivity.EXTRA_PUZZLES_DONE, numCompleted);
                    startActivityForResult(i, NAME_PUZZLE_REQUEST);
                    break;
                }
                case R.id.room_four_button: {
                    Intent i = new Intent(getApplicationContext(), SeasonsPuzzle.class);
                    i.putExtra(SeasonsPuzzle.EXTRA_SEASONS_ANS, seasonsAns);
                    i.putExtra(WinLoseActivity.EXTRA_PUZZLES_DONE, numCompleted);
                    startActivityForResult(i, SEASONS_PUZZLE_REQUEST);
                    break;
                }
                case R.id.solve_button: {
                    Intent i = new Intent(getApplicationContext(), WinLoseActivity.class);
                    i.putExtra(WinLoseActivity.EXTRA_PUZZLES_DONE, numCompleted);
                    startActivity(i);
                    finish();
                    break;
                }
                case R.id.cheat_button: {
                    cheatToast.makeText(MainActivity.this, colorAns + ", " + sizeAns + ", " + nameAns + ", " + seasonsAns, cheatToast.LENGTH_SHORT).show();
                }
            }
        }
    }

    static void shuffleStrings(String[] array)
    {
        Random rand = ThreadLocalRandom.current();
        for (int n = array.length - 1; n > 0; n--)
        {
            int index = rand.nextInt(n+1);

            String s = array[index];
            array[index] = array[n];
            array[n] = s;
        }
    }
}
