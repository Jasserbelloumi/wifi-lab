package com.example.wifilab;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView consoleText;
    private ScrollView scrollView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.BLACK);
        root.setPadding(30, 30, 30, 30);

        TextView title = new TextView(this);
        title.setText("WIFI SECURITY LAB\nSimulator");
        title.setTextColor(Color.GREEN);
        title.setTextSize(22);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 20, 0, 40);
        root.addView(title);

        scrollView = new ScrollView(this);
        LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(-1, 0, 1f);
        scrollView.setLayoutParams(scrollParams);
        
        consoleText = new TextView(this);
        consoleText.setTextColor(Color.GREEN);
        consoleText.setTextSize(14);
        consoleText.setTypeface(android.graphics.Typeface.MONOSPACE);
        consoleText.setText("System: Booting...\nMode: Educational\n\n");
        scrollView.addView(consoleText);
        root.addView(scrollView);

        LinearLayout buttons = new LinearLayout(this);
        buttons.setOrientation(LinearLayout.HORIZONTAL);
        buttons.setGravity(Gravity.CENTER);

        Button btnWpa = new Button(this);
        btnWpa.setText("WPA Handshake");
        btnWpa.setOnClickListener(v -> runSimulation("WPA Capture"));
        
        Button btnWep = new Button(this);
        btnWep.setText("WEP Crack");
        btnWep.setOnClickListener(v -> runSimulation("WEP Analysis"));

        buttons.addView(btnWpa);
        buttons.addView(btnWep);
        root.addView(buttons);

        setContentView(root);
    }

    private void runSimulation(String type) {
        consoleText.setText("");
        printLine("Initializing Simulation...");
        printLine("Target: [WIFI_TEST_LAB]");
        
        handler.postDelayed(() -> printLine("Scanning for packets..."), 1500);
        
        if(type.contains("WPA")) {
            handler.postDelayed(() -> printLine("Forcing Deauth..."), 3000);
            handler.postDelayed(() -> printLine("Handshake Captured!"), 5000);
            handler.postDelayed(() -> printLine("Saved: handshake.cap"), 6500);
        } else {
            handler.postDelayed(() -> printLine("Collecting IVs..."), 3000);
            handler.postDelayed(() -> printLine("Analyzing Weak IVs..."), 5000);
            handler.postDelayed(() -> printLine("Key Found: [ABCDE12345]"), 6500);
        }
    }

    private void printLine(String msg) {
        consoleText.append("> " + msg + "\n");
        scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
    }
}
