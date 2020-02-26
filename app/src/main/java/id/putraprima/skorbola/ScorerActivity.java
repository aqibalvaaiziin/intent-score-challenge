package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ScorerActivity extends AppCompatActivity implements Serializable{

    EditText playerName;
    int key;
    String nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
        playerName = findViewById(R.id.name);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            key = extras.getInt("key");
        }
    }

    public void backToMatch(View view){
        nameField = playerName.getText().toString();
        if(nameField.equals("")){
            Toast.makeText(this, "Masukkan nama pemain!!", Toast.LENGTH_SHORT).show();
        }
        else{
            if (key == 1){
                nameField = playerName.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("homeGoalers", nameField);
                System.out.println(nameField);
                setResult(1, intent);
                finish();
            }else if (key == 2){
                nameField = playerName.getText().toString();
                Intent intent = new Intent();
                System.out.println(nameField);
                intent.putExtra("awayGoalers", nameField);
                setResult(2,intent);
                finish();
            }
        }
    }
}
