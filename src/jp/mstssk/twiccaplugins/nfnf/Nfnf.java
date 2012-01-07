package jp.mstssk.twiccaplugins.nfnf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Nfnf extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Intent intent = getIntent();
		String prefix = intent.getStringExtra("prefix");
		String userInput = intent.getStringExtra("user_input");
		String suffix = intent.getStringExtra("suffix");
		String newText = buildText(prefix, userInput, suffix);
		intent.putExtra(Intent.EXTRA_TEXT, newText);
		intent.putExtra("cursor", getLength(newText) - getLength(suffix));
		setResult(RESULT_OK, intent);
		finish();
	}

	private String buildText(String prefix, String userInput, String suffix) {

		StringBuilder sb = new StringBuilder();

		if (!isEmpty(prefix)) {
			sb.append(prefix);
		}

		int inputLength = getLength(userInput);
		if (inputLength != 0 && inputLength <= 2) {
			sb.append(getString(R.string.nf));
		} else {
			sb.append(getString(R.string.nfnf));
		}

		if (!isEmpty(userInput)) {
			String lastChar = getLastChar(userInput);
			if (lastChar != null) {
				sb.append(lastChar);
			}
			sb.append(wrapWithParentheses(userInput));
		}

		if (!isEmpty(suffix)) {
			sb.append(suffix);
		}

		return sb.toString();

	}

	private String getLastChar(String text) {

		String str = text.trim().replaceAll("　*$", "");

		if (str.endsWith("?") || str.endsWith("？")) {
			return "？";
		} else if (str.endsWith("!") || str.endsWith("！")) {
			return "！";
		} else if (str.endsWith("…") || str.endsWith("...")) {
			return "…";
		}

		return null;
	}

	private String wrapWithParentheses(String str) {

		if (str.length() == 0) {
			return "";
		}
		return "（" + str + "）";
	}

	private boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	private int getLength(String str) {
		return str != null ? str.length() : 0;
	}

}