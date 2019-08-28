package itp341.caceres.nicholas.puzzleGame;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ColorPuzzle extends AppCompatActivity {

    private TextView redTextView;
    private TextView greenTextView;
    private TextView blueTextView;

    private SeekBar redSeekbar;
    private int redSeekbarValue;
    private SeekBar greenSeekbar;
    private int greenSeekbarValue;
    private SeekBar blueSeekbar;
    private int blueSeekbarValue;
    private String RGBString;
    private colorSeekbarListener colorSBListener;

    private TextView coloredView;

    private Button colorChangesButton;

    public static final String EXTRA_COLOR_ANS = "itp341.caceres.nicholas.puzzleGame.color_ans";
    public static final String EXTRA_USER_COLOR_ANS = "itp341.caceres.nicholas.puzzleGame.user_color_ans";

    private static final String TAG = ColorPuzzle.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_puzzle);
        Intent i = getIntent();
        String ansRGB = i.getStringExtra(EXTRA_COLOR_ANS);

        redTextView = (TextView) findViewById(R.id.red_textView);
        greenTextView = (TextView) findViewById(R.id.green_textView);
        blueTextView = (TextView) findViewById(R.id.blue_textView);

        colorSBListener = new colorSeekbarListener();
        redSeekbar = (SeekBar) findViewById(R.id.red_seekbar);
        redSeekbar.setOnSeekBarChangeListener(colorSBListener);
        greenSeekbar = (SeekBar) findViewById(R.id.green_seekbar);
        greenSeekbar.setOnSeekBarChangeListener(colorSBListener);
        blueSeekbar = (SeekBar) findViewById(R.id.blue_seekbar);
        blueSeekbar.setOnSeekBarChangeListener(colorSBListener);

        coloredView = (TextView) findViewById(R.id.color_view);

        colorChangesButton = (Button) findViewById(R.id.color_changes_button);
        colorChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clicked");
                RGBString = "(" + redSeekbarValue + "," + greenSeekbarValue + "," + blueSeekbarValue + ")";
                Intent i = getIntent();
                String colorAns = i.getStringExtra(EXTRA_COLOR_ANS);
                int numberPuzzlesDone = i.getIntExtra(WinLoseActivity.EXTRA_PUZZLES_DONE, 0);
                i.putExtra(EXTRA_USER_COLOR_ANS, RGBString);
                i.putExtra(EXTRA_COLOR_ANS, colorAns);
                i.putExtra(WinLoseActivity.EXTRA_PUZZLES_DONE, numberPuzzlesDone);
                setResult(RESULT_OK, i);
            }
        });
    }

    private class colorSeekbarListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
            switch (seekbar.getId()) {
                case R.id.red_seekbar: {
                    Log.d(TAG, "slided");
                    redSeekbarValue = progress;
                    redTextView.setText(getResources().getString(R.string.red_color) + "  " + redSeekbarValue);
                    coloredView.setBackgroundColor(Color.rgb(redSeekbarValue, greenSeekbarValue, blueSeekbarValue));
                    break;
                }
                case R.id.green_seekbar: {
                    greenSeekbarValue = progress;
                    greenTextView.setText(getResources().getString(R.string.green_color) + "  " + greenSeekbarValue);
                    coloredView.setBackgroundColor(Color.rgb(redSeekbarValue, greenSeekbarValue, blueSeekbarValue));
                    break;
                }
                case R.id.blue_seekbar: {
                    blueSeekbarValue = progress;
                    blueTextView.setText(getResources().getString(R.string.blue_color) + "  " + blueSeekbarValue);
                    coloredView.setBackgroundColor(Color.rgb(redSeekbarValue, greenSeekbarValue, blueSeekbarValue));
                    break;
                }
            }
        }
        public void onStopTrackingTouch(SeekBar seekbar) {

        }
        public void onStartTrackingTouch(SeekBar seekbar) {

        }
    }
}
