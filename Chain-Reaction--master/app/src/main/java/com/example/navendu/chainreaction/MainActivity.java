package com.example.navendu.chainreaction;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MyButton[][] buttons;
    LinearLayout mainLayout;
    LinearLayout rowLayouts[];
    public static int n = 8;
    boolean crossturn = true;
    boolean flag1 = false;
    boolean flag2 = false;
    int c = 0;
    int r = 0, g = 0;
    int i, j;
    TextView textView1, textView2;
   /* RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF,
            .51111112f, Animation.RELATIVE_TO_SELF, 0.5f);
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        textView2 = (TextView) findViewById(R.id.green);
        textView2.setBackgroundResource(R.drawable.sq2);
        buttons = new MyButton[n][n];
        rowLayouts = new LinearLayout[n];

        for (int i = 0; i < n; i++) {
            rowLayouts[i] = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.05f);
            params.setMargins(1, 1, 1, 1);
            rowLayouts[i].setLayoutParams(params);
            rowLayouts[i].setOrientation(LinearLayout.HORIZONTAL);
            mainLayout.addView(rowLayouts[i]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                buttons[i][j] = new MyButton(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0.05f);
                params.setMargins(2, 2, 2, 2);

                buttons[i][j].setLayoutParams(params);
                buttons[i][j].setOnClickListener(this);
                buttons[i][j].setBackgroundResource(R.drawable.square);
                // buttons[i][j].setBackgroundColor(Color.DKGRAY);
                buttons[i][j].setTextSize(0);
                rowLayouts[i].addView(buttons[i][j]);

            }
        }
        // buttons[3][3].setBackgroundResource(R.drawable.chainn);


    }

    @Override
    public void onClick(View v) {
        MyButton button = (MyButton) v;

        if (crossturn) {

            green(button);
        } else {

            red(button);
        }


        flag1 = true;
        flag2 = true;
        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++) {
                if (buttons[i][j].colour == 1) {
                    flag1 = false;
                    break;
                }
            }
        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++) {
                if (buttons[i][j].colour == 2) {
                    flag2 = false;
                    break;
                }
            }

        if (flag1 == true && flag2 == false && c != 0 && c != 1) {
            Toast.makeText(this, "Player2 wins", Toast.LENGTH_LONG).show();
            for (i = 0; i < n; i++)
                for (j = 0; j < n; j++) {
                    buttons[i][j].setEnabled(false);
                }
            return;
        } else if (flag1 == false && flag2 == true && c != 0 && c != 1)

        {
            Toast.makeText(this, "Player1 wins", Toast.LENGTH_LONG).show();
            for (i = 0; i < n; i++)
                for (j = 0; j < n; j++) {
                    buttons[i][j].setEnabled(false);
                }

            return;
        }


    }


    public boolean isValidPos(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }


    public void rec(int i, int j) {
        if (!isValidPos(i, j)) {
            return;
        }

        // if (buttons[i][j].visit == 0) {
        // buttons[i][j].visit = 1;
        buttons[i][j].count = 0;
        buttons[i][j].colour = 0;
        buttons[i][j].setBackgroundResource(R.drawable.square);
        RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF,
                .51111112f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(1500);
        buttons[i][j].startAnimation(anim);

        anim.cancel();

        //buttons[i][j].count++;


        if (isValidPos(i - 1, j)) {

            buttons[i - 1][j].count++;
            if (crossturn) {
                buttons[i - 1][j].colour = 1;
                setforgreen(i - 1, j, buttons[i - 1][j].count);
            } else {

                buttons[i - 1][j].colour = 2;
                setforred(i - 1, j, buttons[i - 1][j].count);
            }

            if (buttons[i - 1][j].count == 4) {
                rec(i - 1, j);
            }
        }
        // rec(i - 1, j);
        if (isValidPos(i + 1, j)) {
            buttons[i + 1][j].count++;
            if (crossturn) {
                setforgreen(i + 1, j, buttons[i + 1][j].count);
                buttons[i + 1][j].colour = 1;
            } else {
                buttons[i + 1][j].colour = 2;
                setforred(i + 1, j, buttons[i + 1][j].count);
            }
            if (buttons[i + 1][j].count == 4) {
                rec(i + 1, j);

            }
        }
        if (isValidPos(i, j - 1)) {
            buttons[i][j - 1].count++;
            if (crossturn) {
                buttons[i][j - 1].colour = 1;
                setforgreen(i, j - 1, buttons[i][j - 1].count);

            } else {

                buttons[i][j - 1].colour = 2;
                setforred(i, j - 1, buttons[i][j - 1].count);
            }
            if (buttons[i][j - 1].count == 4) {
                rec(i, j - 1);

            }
        }
        if (isValidPos(i, j + 1)) {
            buttons[i][j + 1].count++;
            if (crossturn) {
                buttons[i][j + 1].colour = 1;
                setforgreen(i, j + 1, buttons[i][j + 1].count);
            } else {

                buttons[i][j + 1].colour = 2;
                setforred(i, j + 1, buttons[i][j + 1].count);
            }
            if (buttons[i][j + 1].count == 4) {
                rec(i, j + 1);

            }

        }

    }

/*
    public void recur(int i, int j) {
        if (buttons[i][j].count == 4) {
            buttons[i][j].count = 0;
            buttons[i][j].setBackgroundResource(R.drawable.square);
            if (i + 1 < n && j < n) {
                buttons[i + 1][j].count = buttons[i + 1][j].count + 1;
                buttons[i + 1][j].colour = buttons[i][j].colour;
                if (!crossturn) {
                    setforred(i + 1, j, buttons[i + 1][j].count);
                } else {
                    setforgreen(i + 1, j, buttons[i + 1][j].count);
                }
                if (buttons[i + 1][j].count == 4) {
                    recur(i + 1, j);
                }
            }
            if (i - 1 < n && j < n) {
                buttons[i - 1][j].count = buttons[i - 1][j].count + 1;
                buttons[i - 1][j].colour = buttons[i][j].colour;
                if (!crossturn) {
                    setforred(i - 1, j, buttons[i - 1][j].count);
                } else {
                    setforgreen(i - 1, j, buttons[i - 1][j].count);
                }
                if (buttons[i - 1][j].count == 4) {
                    recur(i - 1, j);
                }
            }
            if (i < n && j + 1 < n) {
                buttons[i][j + 1].count = buttons[i][j + 1].count + 1;
                buttons[i][j + 1].colour = buttons[i][j].colour;
                if (!crossturn) {
                    setforred(i, j + 1, buttons[i][j + 1].count);
                } else {
                    setforgreen(i, j + 1, buttons[i][j + 1].count);
                }
                if (buttons[i][j + 1].count == 4) {
                    recur(i, j + 1);
                }
            }
            if (i < n && j - 1 < n) {
                buttons[i][j - 1].count = buttons[i][j - 1].count + 1;
                buttons[i][j - 1].colour = buttons[i][j].colour;
                if (!crossturn) {
                    setforred(i, j - 1, buttons[i][j - 1].count);
                } else {
                    setforgreen(i, j - 1, buttons[i][j - 1].count);
                }
                if (buttons[i][j - 1].count == 4) {
                    recur(i, j - 1);
                }
            }
        }
    }*/

    public void setforred(int i, int j, int count) {
        if (count == 1) {
            buttons[i][j].setBackgroundResource(R.drawable.red1);
        } else if (count == 2) {
            buttons[i][j].setBackgroundResource(R.drawable.chain2red);
        } else if (count == 3) {
            buttons[i][j].setBackgroundResource(R.drawable.chain3);
            RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF,
                    .51111112f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setInterpolator(new LinearInterpolator());
            anim.setRepeatCount(Animation.INFINITE);
            anim.setDuration(1500);
            buttons[i][j].startAnimation(anim);

        } else {
            return;
        }
    }

    public void setforgreen(int i, int j, int count) {
        if (count == 1) {
            buttons[i][j].setBackgroundResource(R.drawable.chain);
        } else if (count == 2) {
            buttons[i][j].setBackgroundResource(R.drawable.chain2);
        } else if (count == 3) {
            buttons[i][j].setBackgroundResource(R.drawable.chain3green);
            RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF,
                    .51111112f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setInterpolator(new LinearInterpolator());
            anim.setRepeatCount(Animation.INFINITE);
            anim.setDuration(1500);
            buttons[i][j].startAnimation(anim);

        } else {
            return;
        }
    }

    public void green(MyButton button) {

        if (button.colour == 1 || button.colour == 0) {
            c++;
            textView1 = (TextView) findViewById(R.id.green);
            textView2 = (TextView) findViewById(R.id.red);
            textView1.setBackgroundResource(R.drawable.square);
            textView2.setBackgroundResource(R.drawable.sq);

            button.count++;
            if (button.count == 1) {
                button.setBackgroundResource(R.drawable.chain);

                crossturn = !crossturn;

                button.colour = 1;
            } else if (button.count == 2) {
                button.setBackgroundResource(R.drawable.chain2);

                crossturn = !crossturn;

                button.colour = 1;
               /* RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF,
                        .51111112f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(Animation.INFINITE);
                anim.setDuration(1500);
                button.startAnimation(anim);
            */
            } else if (button.count == 3) {
                button.setBackgroundResource(R.drawable.chain3green);
                //        button.setRotation(180);
                RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF,
                        .51111112f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(Animation.INFINITE);
                anim.setDuration(1500);
                button.startAnimation(anim);

                crossturn = !crossturn;

                button.colour = 1;

            } else if (button.count == 4) {
                for (i = 0; i < n; i++)
                    for (j = 0; j < n; j++)
                        if (button == buttons[i][j]) {
                            //                flag=true;

                            //recur(i, j);
                            rec(i, j);

                            crossturn = !crossturn;

                            //button.colour = 1;
                            break;
                        }


            }
        }
    }


    public void red(MyButton button) {

        if (button.colour == 2 || button.colour == 0) {
            c++;
            textView1 = (TextView) findViewById(R.id.green);
            textView2 = (TextView) findViewById(R.id.red);
            textView1.setBackgroundResource(R.drawable.sq2);
            textView2.setBackgroundResource(R.drawable.square);
            button.count++;

            if (button.count == 1) {
                button.setBackgroundResource(R.drawable.red1);
                crossturn = !crossturn;

                button.colour = 2;
            } else if (button.count == 2) {
                button.setBackgroundResource(R.drawable.chain2red);
              /*  RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF,
                        .51111112f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(Animation.INFINITE);
                anim.setDuration(1500);
*/
                crossturn = !crossturn;

                button.colour = 2;

                //     button.startAnimation(anim);
            } else if (button.count == 3) {
                button.setBackgroundResource(R.drawable.chain3);
                RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF,
                        .51111112f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(Animation.INFINITE);
                anim.setDuration(1500);
                button.startAnimation(anim);
                crossturn = !crossturn;

                button.colour = 2;

            } else if (button.count == 4) {
                for (i = 0; i < n; i++)
                    for (j = 0; j < n; j++)
                        if (buttons[i][j] == button) {
                            //               recur(i, j);
                            rec(i, j);
                            crossturn = !crossturn;

                            break;
                        }


            }
        }
    }
}
