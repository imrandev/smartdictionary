package com.nerd.app.voisy;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.nerd.app.voisy.adapter.ResultAdapter;
import com.nerd.app.voisy.model.AdditionalData;
import com.nerd.app.voisy.model.Sense;
import com.nerd.app.voisy.model.TranslateModel;
import com.nerd.app.voisy.rest.ApiInterface;
import com.nerd.app.voisy.rest.PearsonApiClient;
import com.nerd.app.voisy.rest.YandexApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoiceActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "VoiceActivity";
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
    private ListView mList;
    private Handler mHandler;
    private EditText inputWord;
    private FloatingActionButton mFloatButton;
    private TextView textView, textView1, partText;
    private ProgressBar circular_progress;
    private RecyclerView rList;
    private ResultAdapter resultAdapter;
    private ArrayList<Sense> senseList;
    private CardView cardView;
    private CoordinatorLayout coordinatorLayout;
    private ArrayList<String> matches = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        setContentView(R.layout.activity_voice);

        // Get display items for later interaction
        mFloatButton = (FloatingActionButton) findViewById(R.id.fbtn_speak);
        mList = (ListView) findViewById(R.id.list);
        inputWord = (EditText) findViewById(R.id.input_word);
        final AppCompatButton inputButton = (AppCompatButton) findViewById(R.id.input_btn);
        cardView = (CardView) findViewById(R.id.card);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_activity);

        cardView.setVisibility(View.INVISIBLE);

        textView = (TextView) findViewById(R.id.details_text);
        textView1 = (TextView) findViewById(R.id.word);
        partText = (TextView) findViewById(R.id.parts_of_speech);
        rList = (RecyclerView) findViewById(R.id.rlist);
        circular_progress = (ProgressBar) findViewById(R.id.circle_progress);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = String.valueOf(inputWord.getText());
                if (matches != null){
                    matches.clear();
                    adapter=new ArrayAdapter<>(VoiceActivity.this, android.R.layout.simple_list_item_1, matches);
                    mList.setAdapter(adapter);
                }
                if (!data.isEmpty()){
                    textView1.setText(data);
                    retrieveData(data);
                }
                else {
                    Snackbar.make(coordinatorLayout,"Please type a word, Search field is remain empty", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        senseList = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rList.setLayoutManager(layoutManager);

        // Check to see if a recognition activity is present
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() != 0) {
            mFloatButton.setOnClickListener(this);
        } else {
            mFloatButton.setEnabled(false);
        }
        refreshVoiceSettings();
    }

    private void retrieveData(final String details) {
        circular_progress.setVisibility(View.VISIBLE);
        ApiInterface service = YandexApiClient.getClient().create(ApiInterface.class);
        ApiInterface service1 = PearsonApiClient.getClient().create(ApiInterface.class);

        Call<TranslateModel> call = service.getTranslateData("" +
                "/api" +
                "/v1.5" +
                "/tr.json" +
                "/translate?lang=en-bn" +
                "&key=trnsl.1.1.20170404T125447Z.bce949b7b58b1e67.240e1a1329af7709f29383d4078ddf72442ccbc4" +
                "&text=" + details);

        Call<AdditionalData> call1 = service1.getHeadData("" +
                "/v2" +
                "/dictionaries" +
                "/lasde" +
                "/entries?headword="+details);


        call.enqueue(new Callback<TranslateModel>() {
            @Override
            public void onResponse(Call<TranslateModel> call, Response<TranslateModel> response) {
                Log.d("onResponse", response.message());

                TranslateModel translateModel = response.body();
                String translatedData = translateModel.getText().get(0);
                if (!translatedData.isEmpty()) {
                    textView.setText(translatedData);
                } else {
                    Snackbar.make(coordinatorLayout,"No data available", Snackbar.LENGTH_INDEFINITE).show();
                }
            }

            @Override
            public void onFailure(Call<TranslateModel> call, Throwable t) {
                Snackbar.make(coordinatorLayout,"Cannot resolve host, please try again", Snackbar.LENGTH_SHORT).show();
            }
        });

        call1.enqueue(new Callback<AdditionalData>() {
            @Override
            public void onResponse(Call<AdditionalData> call, Response<AdditionalData> response) {
                AdditionalData additionalData = response.body();

                if(response.body() != null){

                    for (int i=0; i< additionalData.getCount(); i++){
                        Sense sense = additionalData.getResults().get(i).getSenses().get(0);
                        if (sense != null){
                            if (additionalData.getResults().get(i).getHeadword().equals(details)){
                                if (senseList != null){
                                    senseList.clear();
                                    senseList.add(sense);
                                } else {
                                    senseList.add(sense);
                                }
                                String part_of_speech = additionalData.getResults().get(i).getPartOfSpeech();
                                partText.setText(part_of_speech);
                            }
                        }
                    }

                    resultAdapter = new ResultAdapter(senseList, VoiceActivity.this);
                    rList.setAdapter(resultAdapter);
                    cardView.setVisibility(View.VISIBLE);

                    circular_progress.setVisibility(View.INVISIBLE);

                } else {
                    Snackbar.make(coordinatorLayout,"No data available", Snackbar.LENGTH_INDEFINITE).show();
                    circular_progress.setVisibility(View.INVISIBLE);
                }

                Log.e("onResponse: ", response.message());
            }

            @Override
            public void onFailure(Call<AdditionalData> call, Throwable t) {
                Log.e("onFailure: ", t.getMessage());
                Snackbar.make(coordinatorLayout,"Cannot resolve host, please try again", Snackbar.LENGTH_INDEFINITE).show();
            }
        });
    }

    private void refreshVoiceSettings() {
        Log.i(TAG, "Sending broadcast");
        sendOrderedBroadcast(RecognizerIntent.getVoiceDetailsIntent(this), null,
                new SupportedLanguageBroadcastReceiver(), null, Activity.RESULT_OK, null, null);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.fbtn_speak:
                startVoiceRecognitionActivity();
                break;
            default:
                break;
        }
    }

    /**
     * Fire an intent to start the speech recognition activity.
     */
    private void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        // Specify the calling package to identify your application
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());

        // Display an hint to the user about what he should say.
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say a word");

        // Given an hint to the recognizer about what the user is going to say
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        // Specify how many results you want to receive. The results will be sorted
        // where the first result is the one with higher confidence.
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);

        // Specify the recognition language. This parameter has to be specified only if the
        // recognition has to be done in a specific language and not the default one (i.e., the
        // system locale). Most of the applications do not have to set this parameter.

        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }

    /**
     * Handle the results from the recognition activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            // Fill the list view with the strings the recognizer thought it could have heard
            matches = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);

            ArrayAdapter<String>adapter=new ArrayAdapter<String>(
                    this, android.R.layout.simple_list_item_1, matches){

                @NonNull
                @Override
                public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                    View view =super.getView(position, convertView, parent);
                    TextView textView=(TextView) view.findViewById(android.R.id.text1);
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextColor(Color.WHITE);
                    return view;
                }
            };

            mList.setAdapter(adapter);
            mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    textView1.setText(matches.get(i));
                    retrieveData(matches.get(i));
                }
            });
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Handles the response of the broadcast request about the recognizer supported languages.
     *
     * The receiver is required only if the application wants to do recognition in a specific
     * language.
     */
    private class SupportedLanguageBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, final Intent intent) {
            Log.i(TAG, "Receiving broadcast " + intent);

            final Bundle extra = getResultExtras(false);

            if (getResultCode() != Activity.RESULT_OK) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showToast("Error code:" + getResultCode());
                    }
                });
            }

            if (extra == null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showToast("No extra");
                    }
                });
            }

            assert extra != null;
            if (extra.containsKey(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE)) {
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        mFloatButton.show();
                    }
                });
            }
        }

        private void showToast(String text) {
            Toast.makeText(VoiceActivity.this, text, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.history :
                return true;
            default:
                return false;
        }
    }
}
