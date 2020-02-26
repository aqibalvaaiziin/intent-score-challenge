package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView messageText, scorerText, resultText;
    private String result, message, scorer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultText =findViewById(R.id.textView1);
        messageText = findViewById(R.id.textView2);
        scorerText = findViewById(R.id.textView3);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            result = bundle.getString("result");
            message = bundle.getString("messages");
            scorer = bundle.getString("scorer");
            scorerText.setText(scorer);
            messageText.setText(message);
            resultText.setText(result);
            System.out.println("okew " +scorer);
        }
    }
}
