/*
 * Copyright 2017 Mario Contreras <marioc@nazul.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package mx.iteso.msc.pam2017.ms705080.fragmentproject;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Mario_Contreras on 2/26/2017.
 */

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If device orientation is not in landscape mode, do not continue (in landscape mode we use activity_main)
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.details_activity);

        Bundle extras = getIntent().getExtras();
        TextView tv = (TextView) findViewById(R.id.tvDetails);
        if (extras != null) {
            String s = extras.getString("osName");
            tv.setText(s);
        } else {
            tv.setText(R.string.textNoOSSelected);
        }
    }
}

// EOF
