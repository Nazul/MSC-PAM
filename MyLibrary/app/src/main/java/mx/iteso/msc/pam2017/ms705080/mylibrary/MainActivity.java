package mx.iteso.msc.pam2017.ms705080.mylibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }

    public void btnAuthors(View view) {
        Intent intent = new Intent(MainActivity.this, AuthorsActivity.class);
        startActivity(intent);
    }

    public void btnBooks(View view) {
        Intent intent = new Intent(MainActivity.this, BooksActivity.class);
        startActivity(intent);
    }

    public void btnQuery(View view) {
        Toast.makeText(MainActivity.this, R.string.toastToBeImplemented, Toast.LENGTH_SHORT).show();
    }
}

// EOF
