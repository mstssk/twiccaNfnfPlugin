package jp.mstssk.twiccaplugins.nfnf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Nfnf extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Intent intent = getIntent();
        final int result;
        if (intent.getAction().equals(Intent.ACTION_PROCESS_TEXT)) {
            final String text = intent.getStringExtra(Intent.EXTRA_PROCESS_TEXT);
            final String newText = NfnfUtils.buildText(this, null, text, null);
            if (intent.getBooleanExtra(Intent.EXTRA_PROCESS_TEXT_READONLY, false)) {
                Toast.makeText(this, newText, Toast.LENGTH_LONG).show();
                result = RESULT_CANCELED;
            } else {
                intent.putExtra(Intent.EXTRA_PROCESS_TEXT, newText);
                result = RESULT_OK;
            }
        } else { // twicca ACTION_EDIT_TWEET
            final String prefix = intent.getStringExtra("prefix");
            final String userInput = intent.getStringExtra("user_input");
            final String suffix = intent.getStringExtra("suffix");
            final String newText = NfnfUtils.buildText(this, prefix, userInput, suffix);
            intent.putExtra(Intent.EXTRA_TEXT, newText);
            intent.putExtra("cursor", NfnfUtils.getLength(newText) - NfnfUtils.getLength(suffix));
            result = RESULT_OK;
        }
        setResult(result, intent);
        finish();
    }

}