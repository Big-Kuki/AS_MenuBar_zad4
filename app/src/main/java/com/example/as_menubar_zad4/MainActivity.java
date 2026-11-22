package com.example.as_menubar_zad4;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_COUNT = "key_count";
    private static final String KEY_HAS_CHANGED = "key_has_changed";

    private int count = 0;
    private boolean hasChanged = false;

    private TextView tvCounter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvCounter = findViewById(R.id.tvCounter);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(KEY_COUNT, 0);
            hasChanged = savedInstanceState.getBoolean(KEY_HAS_CHANGED, false);
            tvCounter.setText(String.valueOf(count));
            if (hasChanged) {
                updateToolbarTitle();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, count);
        outState.putBoolean(KEY_HAS_CHANGED, hasChanged);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            count++;
            hasChanged = true;
            updateUi();
            return true;
        } else if (id == R.id.action_subtract) {
            if (count > 0) {
                count--;
                hasChanged = true;
                updateUi();
            } else {
                Toast.makeText(this, getString(R.string.toast_below_zero), Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if (id == R.id.action_reset) {
            showResetConfirmationDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateUi() {
        tvCounter.setText(String.valueOf(count));
        updateToolbarTitle();
    }

    private void updateToolbarTitle() {
        String titleText = getString(R.string.toolbar_title_format, count);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titleText);
        } else if (toolbar != null) {
            toolbar.setTitle(titleText);
        }
    }

    private void showResetConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_reset_title)
                .setMessage(R.string.dialog_reset_message)
                .setPositiveButton(R.string.dialog_yes, (dialog, which) -> {
                    count = 0;
                    hasChanged = true;
                    updateUi();
                })
                .setNegativeButton(R.string.dialog_no, null)
                .show();
    }
}
