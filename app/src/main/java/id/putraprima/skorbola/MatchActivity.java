package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class MatchActivity extends AppCompatActivity implements Serializable {

    TextView homeName;
    TextView awayName;
    TextView awayScore;
    TextView homeScore;
    ImageView homeLogo;
    ImageView awayLogo;
    Uri uri1;
    Uri uri2;
    Bitmap bitmap1;
    Bitmap bitmap2;
    String homeTeam,message,result;
    String awayTeam;
    int scoreHome = 0;
    int scoreAway = 0;
    String scorerName = "";
    String homeGoals = "";
    String awayGoals = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        homeName = findViewById(R.id.txt_home);
        awayName = findViewById(R.id.txt_away);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);
        awayScore = findViewById(R.id.score_away);
        homeScore = findViewById(R.id.score_home);


        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
        Bundle extras = getIntent().getExtras();
        homeTeam = extras.getString("inputHome");
        awayTeam = extras.getString("inputAway");


        if (extras != null) {
            // TODO: display value here

            uri1 = Uri.parse(extras.getString("logoHome"));
            uri2 = Uri.parse(extras.getString("logoAway"));
            bitmap1 = null;
            bitmap2 = null;

            try {
                bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri2);
            } catch (IOException e) {
                e.printStackTrace();
            }

            homeName.setText(homeTeam);
            awayName.setText(awayTeam);
            homeLogo.setImageBitmap(bitmap1);
            awayLogo.setImageBitmap(bitmap2);

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        System.out.println("okewwwwwww");
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0){
            return;
        }
        if (requestCode == 1){
            homeGoals = data.getStringExtra("homeGoalers") + ", " + homeGoals ;
        }else if (requestCode == 2){
            awayGoals = data.getStringExtra("awayGoalers") + ", " + awayGoals;
        }
    }

    public void scoreHomeHandler(View view){
        Intent intent = new Intent(this,ScorerActivity.class);
        intent.putExtra("key",1);
        startActivityForResult(intent,1);
        scoreHome += 1;
        homeScore.setText(String.valueOf(scoreHome));
    }
    public void scoreAwayHandler(View view){
        scoreAway += 1;
        awayScore.setText(String.valueOf(scoreAway));
        Intent intent = new Intent(this,ScorerActivity.class);
        intent.putExtra("key",2);
        startActivityForResult(intent,2);
    }

    public void resultHandler(View view) {
        if (scoreHome > scoreAway){
            result = String.valueOf(scoreHome)  + " - " + String.valueOf(scoreAway);
            message = homeTeam + " WIN";
            scorerName = homeGoals;
        }else if(scoreHome < scoreAway){
            result = String.valueOf(scoreHome)  + " - " + String.valueOf(scoreAway);
            message = awayTeam + " WIN";
            scorerName = awayGoals;
        }else if(scoreHome == scoreAway){
            result = String.valueOf(scoreHome)  + " - " + String.valueOf(scoreAway);
            message = "DRAW";
            scorerName = "";
        }
        System.out.println("wawa "+homeGoals);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("result",result);
        intent.putExtra("messages", message);
        intent.putExtra("scorer",scorerName);
        startActivity(intent);
    }
}
