package IGuess.IApp.GP;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import java.util.*;
import java.text.*;


public class MainActivity extends Activity {

	private LinearLayout bg;
	private LinearLayout top_name;
	private LinearLayout bg_inner;
	private LinearLayout btns_btm;
	private LinearLayout bg_ad;
	private LinearLayout linear4;
	private TextView description;
	private TextView name;
	private TextView iapp_cr;
	private TextView sug_head;
	private LinearLayout nn;
	private LinearLayout bg_inner_home;
	private LinearLayout linear10;
	private TextView view_sug;
	private TextView wt_txt;
	private TextView wt_show;
	private EditText sug1;
	private EditText sug2;
	private EditText sug3;
	private Button guess_btn;
	private Button rand_num;
	private Button about_btn;
	private Button more_apps_btn;
	private WebView ad;
	private Button bk_btn;

	private double guss = 0;
	private String s1 = "";
	private String s2 = "";
	private String s3 = "";
	private double wt_time = 0;
	private double r1 = 0;
	private double r2 = 0;


	private Timer _timer = new Timer();
	private AlertDialog.Builder dialog;
	private Intent go = new Intent();
	private TimerTask delay;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initialize();
		initializeLogic();
	}

	private void  initialize() {
		bg = (LinearLayout) findViewById(R.id.bg);
		top_name = (LinearLayout) findViewById(R.id.top_name);
		bg_inner = (LinearLayout) findViewById(R.id.bg_inner);
		btns_btm = (LinearLayout) findViewById(R.id.btns_btm);
		bg_ad = (LinearLayout) findViewById(R.id.bg_ad);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		description = (TextView) findViewById(R.id.description);
		name = (TextView) findViewById(R.id.name);
		iapp_cr = (TextView) findViewById(R.id.iapp_cr);
		sug_head = (TextView) findViewById(R.id.sug_head);
		nn = (LinearLayout) findViewById(R.id.nn);
		bg_inner_home = (LinearLayout) findViewById(R.id.bg_inner_home);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		view_sug = (TextView) findViewById(R.id.view_sug);
		wt_txt = (TextView) findViewById(R.id.wt_txt);
		wt_show = (TextView) findViewById(R.id.wt_show);
		sug1 = (EditText) findViewById(R.id.sug1);
		sug2 = (EditText) findViewById(R.id.sug2);
		sug3 = (EditText) findViewById(R.id.sug3);
		guess_btn = (Button) findViewById(R.id.guess_btn);
		rand_num = (Button) findViewById(R.id.rand_num);
		about_btn = (Button) findViewById(R.id.about_btn);
		more_apps_btn = (Button) findViewById(R.id.more_apps_btn);
		ad = (WebView) findViewById(R.id.ad);
		ad.getSettings().setJavaScriptEnabled(true);
		ad.getSettings().setSupportZoom(true);
		ad.setWebViewClient(new WebViewClient() {
				@Override
				public void onPageStarted(WebView _view, String _url, Bitmap _favicon) {

					super.onPageStarted(_view, _url, _favicon);
				}
				@Override
				public void onPageFinished(WebView _view, String _url) {

					super.onPageFinished(_view, _url);
				}
			});
		bk_btn = (Button) findViewById(R.id.bk_btn);

		dialog = new AlertDialog.Builder(this);



		guess_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					nn.setEnabled(true);
					nn.setVisibility(View.VISIBLE);
					bg_inner_home.setVisibility(View.INVISIBLE);
					bg_inner_home.setEnabled(false);
					ad.loadUrl(ad.getUrl());
					showMessage("Please Wait 5 Seconds...");
					wt_txt.setVisibility(View.VISIBLE);
					wt_show.setVisibility(View.VISIBLE);
					delay = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
									@Override
									public void run() {
										wt_time--;
										wt_show.setText(String.valueOf((long)(wt_time)));
									}
								});
						}
					};
					_timer.scheduleAtFixedRate(delay, (int)(1), (int)(1000));
					delay = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
									@Override
									public void run() {
										wt_time = 0;
										wt_show.setText("0");
										wt_show.setVisibility(View.INVISIBLE);
										view_sug.setVisibility(View.VISIBLE);
										wt_txt.setVisibility(View.INVISIBLE);
										guss = getRandom((int)(1), (int)(3));
										if (guss == 1) {
											view_sug.setText(s1);
										}
										if (guss == 2) {
											view_sug.setText(s2);
										}
										if (guss == 3) {
											view_sug.setText(s3);
										}
										delay.cancel();
										bk_btn.setEnabled(true);
										bk_btn.setVisibility(View.VISIBLE);
									}
								});
						}
					};
					_timer.schedule(delay, (int)(5000));
				}
			});
		about_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					dialog.setTitle("About");
					dialog.setMessage(" © ( IApp ) Powered by GP & PWS & Elgeneral Primo \n\n Site : PWS-Apps.BlogSpot.Com \n\n IGuess V 1.1");
					dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {

							}
						});
					dialog.create().show();
				}
			});
		more_apps_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					go.setAction(Intent.ACTION_VIEW);
					go.setData(Uri.parse("http://pws-apps.blogspot.com/search/?label=IApp"));
					startActivity(go);
				}
			});
		sug1.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence _text, int _start, int _count, int _after) {
				}
				@Override
				public void onTextChanged(CharSequence _text, int _start, int _before, int _count) {
					s1 = _text.toString();
					go.putExtra("1", s1);
				}
				@Override
				public void afterTextChanged(Editable _text) {
				}
			});
		sug2.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence _text, int _start, int _count, int _after) {
				}
				@Override
				public void onTextChanged(CharSequence _text, int _start, int _before, int _count) {
					s2 = _text.toString();
					go.putExtra("2", s2);
				}
				@Override
				public void afterTextChanged(Editable _text) {
				}
			});
		sug3.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence _text, int _start, int _count, int _after) {
				}
				@Override
				public void onTextChanged(CharSequence _text, int _start, int _before, int _count) {
					s3 = _text.toString();
					go.putExtra("3", s3);
				}
				@Override
				public void afterTextChanged(Editable _text) {
				}
			});
		rand_num.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					go.setClass(getApplicationContext(), RandActivity.class);
					startActivity(go);
				}
			});
		bk_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _v) { 
					go.setClass(getApplicationContext(), MainActivity.class);
					startActivity(go);
				}
			});

	}

	private void  initializeLogic() {
		ad.loadUrl("file:///android_asset/mob_ads.html");
		view_sug.setVisibility(View.INVISIBLE);
		wt_txt.setVisibility(View.INVISIBLE);
		nn.setEnabled(false);
		nn.setVisibility(View.INVISIBLE);
		wt_show.setVisibility(View.INVISIBLE);
		wt_time = 5;
		bk_btn.setEnabled(false);
		bk_btn.setVisibility(View.INVISIBLE);
	}




	// created automatically
	private void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}

	private int getRandom(int _minValue ,int _maxValue){
		Random random = new Random();
		return random.nextInt(_maxValue - _minValue + 1) + _minValue;
	}

	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
				_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}

}

